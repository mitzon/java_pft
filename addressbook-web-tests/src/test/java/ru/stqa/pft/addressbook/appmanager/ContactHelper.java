package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ContactHelper extends HelperBase {

    public ContactHelper (WebDriver wd) {
        super(wd);
    }

    public void gotoAddNew() {
        click(By.linkText("add new"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("home"), contactData.getPhoneNumber());
        type(By.name("email"), contactData.getEmail());
        type(By.name("address"), contactData.getAddress());

        if (creation) {
            if (isElementPresent(By.xpath("//select[@name='new_group']/option[text()='" + contactData.getGroup()+ "']"))) {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
            } else {
                System.out.println("Run testGroupCreation or create manually a group " + contactData.getGroup() + " to choose group for new contact!");
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitAddNewContact() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
    }

    public void editContactWithId(int id) {
        wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
    }

    public void submitModificationContact() {
        click(By.name("update"));
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void create(ContactData contactData) {
        gotoAddNew();
        fillContactForm(contactData, true);
        submitAddNewContact();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        wd.switchTo().alert().accept();
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));

        for (WebElement element : elements) {
            String firstName = element.findElement(By.xpath(".//td[3]")).getText();
            String lastName = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.xpath(".//td[1]/input")).getAttribute("id"));
            ContactData contact = new ContactData()
                    .withId(id)
                    .withFirstName(firstName)
                    .withLastName(lastName);
            contacts.add(contact);
        }
        return contacts;
    }
}

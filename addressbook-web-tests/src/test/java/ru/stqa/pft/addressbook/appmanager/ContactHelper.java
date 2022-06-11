package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

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

    public void create(ContactData contact) {
        gotoAddNew();
        fillContactForm(contact, true);
        submitAddNewContact();
        contactCache = null;
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        wd.switchTo().alert().accept();
        click(By.linkText("home"));
        contactCache = null;
    }

    public void modify(ContactData contact) {
        editContactWithId(contact.getId());
        fillContactForm(contact, false);
        submitModificationContact();
        contactCache = null;
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));

        for (WebElement element : elements) {
            String firstName = element.findElement(By.xpath(".//td[3]")).getText();
            String lastName = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.xpath(".//td[1]/input")).getAttribute("id"));
            ContactData contact = new ContactData()
                    .withId(id)
                    .withFirstName(firstName)
                    .withLastName(lastName);
            contactCache.add(contact);
        }
        return contactCache;
    }
}

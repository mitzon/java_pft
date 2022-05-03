package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper (WebDriver wd) {
        super(wd);
    }

    public void gotoAddNew() {
        click(By.linkText("add new"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("home"), contactData.getPhoneNumber());
        type(By.name("email"), contactData.getEmail());
        type(By.name("address"), contactData.getAddress());
    }

    public void submitAddNewContact() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void editContact() {
        click(By.xpath("//img[@title='Edit']"));
    }

    public void submitModificationContact() {
        click(By.name("update"));
    }
}

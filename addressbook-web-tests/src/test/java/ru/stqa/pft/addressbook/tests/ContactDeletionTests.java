package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends BaseTest {

    @Test
    public void testContactDeletion() {

        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(ContactData.contactData);
            app.getNavigationHelper().returnToHomePage();
        }

        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContacts();
        app.wd.switchTo().alert().accept();
    }
}

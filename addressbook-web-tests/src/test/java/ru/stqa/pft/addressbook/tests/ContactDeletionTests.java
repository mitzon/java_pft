package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends BaseTest {

    @Test
    public void testContactDeletion() {

        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(ContactData.contactData);
            app.getNavigationHelper().returnToHomePage();
        }
        int index = 0;

        List<ContactData> before = app.getContactHelper().getContactList();

        app.getContactHelper().selectContact(index);
        app.getContactHelper().deleteSelectedContacts();
        app.wd.switchTo().alert().accept();

        app.getNavigationHelper().goToHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }
}

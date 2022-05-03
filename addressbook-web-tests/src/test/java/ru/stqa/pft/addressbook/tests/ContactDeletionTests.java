package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends BaseTest {

    @Test
    public void testContactDeletion() {

        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContacts();
        app.wd.switchTo().alert().accept();
    }
}

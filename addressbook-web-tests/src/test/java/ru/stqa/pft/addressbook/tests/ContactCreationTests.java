package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTests extends BaseTest {

    @Test
    public void testContactCreation() throws Exception {

        app.getContactHelper().gotoAddNew();
        app.getContactHelper().createContact(ContactData.contactData);
        app.getSessionHelper().logout();
    }
}

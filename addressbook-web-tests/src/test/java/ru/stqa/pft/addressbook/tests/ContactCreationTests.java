package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTests extends BaseTest {

    @Test
    public void testContactCreation() throws Exception {

        ContactData contactData = new ContactData(
                "John",
                "Doe",
                "+79619854569",
                "smth@yandex.ru",
                "Matrosova street, 16/2");

        app.getContactHelper().gotoAddNew();
        app.getContactHelper().fillContactForm(contactData);
        app.getContactHelper().submitAddNewContact();
        app.getNavigationHelper().returnToHomePage();
        app.getSessionHelper().logout();
    }
}

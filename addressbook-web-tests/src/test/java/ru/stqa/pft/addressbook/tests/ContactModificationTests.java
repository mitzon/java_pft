package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends BaseTest {

    @Test
    public void testContactModification() throws Exception {

        ContactData newContactData = new ContactData(
                "Johnathan",
                "Doe",
                "79619854570",
                "something2@yandex.ru",
                "Lenina, 18/2",
                null);

        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(ContactData.contactData);
            app.getNavigationHelper().returnToHomePage();
        }

        app.getContactHelper().editContact();
        app.getContactHelper().fillContactForm(newContactData, false);
        app.getContactHelper().submitModificationContact();
        app.getNavigationHelper().returnToHomePage();


    }
}

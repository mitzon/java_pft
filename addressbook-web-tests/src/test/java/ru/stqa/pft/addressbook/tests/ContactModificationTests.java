package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends BaseTest {

    @Test
    public void testContactModification() {

        ContactData newContactData = new ContactData(
                "Johnathan",
                "Doe",
                "79619854570",
                "something2@yandex.ru",
                "Lenina, 18/2");

        app.getContactHelper().editContact();
        app.getContactHelper().fillContactForm(newContactData);
        app.getContactHelper().submitModificationContact();
        app.getNavigationHelper().returnToHomePage();


    }
}

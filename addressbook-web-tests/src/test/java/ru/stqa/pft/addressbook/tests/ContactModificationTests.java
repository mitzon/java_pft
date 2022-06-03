package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTests extends BaseTest {

    @Test
    public void testContactModification() throws Exception {
        if (app.contact().all().size() == 0) {
            app.contact().create(ContactData.contactData);
            app.goTo().returnToHomePage();
        }

        Set<ContactData> before = app.contact().all();
        ContactData contactForModification = before.iterator().next();

        ContactData newContactData = new ContactData()
                .withId(contactForModification.getId())
                .withFirstName("Johnathan")
                .withLastName("Doe")
                .withPhoneNumber("79619854570")
                .withEmail("something2@yandex.ru")
                .withAddress("Lenina, 18/2");


        app.contact().editContactWithId(contactForModification.getId());
        app.contact().fillContactForm(newContactData, false);
        app.contact().submitModificationContact();
        app.goTo().returnToHomePage();

        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(contactForModification);
        before.add(newContactData);

        Assert.assertEquals(before, after);

    }
}

package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends BaseTest {

    @Test
    public void testContactModification() throws Exception {
        if (app.contact().all().size() == 0) {
            app.contact().create(ContactData.contactData);
            app.goTo().returnToHomePage();
        }

        Contacts before = app.contact().all();
        ContactData contactForModification = before.iterator().next();

        ContactData newContactData = new ContactData()
                .withId(contactForModification.getId())
                .withFirstName("Johnathan")
                .withLastName("Doe")
                .withPhoneNumber("79619854570")
                .withEmail("something2@yandex.ru")
                .withAddress("Lenina, 18/2");


        app.contact().modify(newContactData);
        app.goTo().returnToHomePage();

        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(
                before.without(contactForModification).withAdded(newContactData)));

    }
}

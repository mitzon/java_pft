package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends BaseTest {

    @Test
    public void testContactModification() throws Exception {
        int index = 0;

        ContactData newContactData = new ContactData(
                index,
                "Johnathan",
                "Doe",
                "79619854570",
                "something2@yandex.ru",
                "Lenina, 18/2",
                null);

        if (app.contact().list().size() == 0) {
            app.contact().create(ContactData.contactData);
            app.goTo().returnToHomePage();
        }

        List<ContactData> before = app.contact().list();

        app.contact().editContact(index);
        app.contact().fillContactForm(newContactData, false);
        app.contact().submitModificationContact();
        app.goTo().returnToHomePage();

        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(0);
        before.add(newContactData);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }
}

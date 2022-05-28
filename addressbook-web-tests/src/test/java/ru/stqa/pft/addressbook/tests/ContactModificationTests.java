package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

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

        List<ContactData> before = app.getContactHelper().getContactList();

        app.getContactHelper().editContact(0);
        app.getContactHelper().fillContactForm(newContactData, false);
        app.getContactHelper().submitModificationContact();
        app.getNavigationHelper().returnToHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(0);
        before.add(newContactData);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }
}

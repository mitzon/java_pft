package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactDeletionTests extends BaseTest {

    @Test
    public void testContactDeletion() {

        if (! app.contact().isThereAContact()) {
            app.contact().create(ContactData.contactData);
            app.goTo().returnToHomePage();
        }

        Set<ContactData> before = app.contact().all();
        ContactData contactForDeletion = before.iterator().next();

        app.contact().delete(contactForDeletion);

        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(contactForDeletion);
        Assert.assertEquals(before, after);
    }
}

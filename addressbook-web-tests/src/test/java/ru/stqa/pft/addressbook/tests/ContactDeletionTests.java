package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends BaseTest {

    @Test
    public void testContactDeletion() {

        if (! app.contact().isThereAContact()) {
            app.contact().create(ContactData.contactData);
            app.goTo().returnToHomePage();
        }
        int index = 0;

        List<ContactData> before = app.contact().list();

        app.contact().delete(index);

        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }
}

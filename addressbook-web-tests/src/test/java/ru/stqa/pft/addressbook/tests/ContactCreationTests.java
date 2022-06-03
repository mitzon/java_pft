package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;


public class ContactCreationTests extends BaseTest {

    @Test
    public void testContactCreation() throws Exception {

        Set<ContactData> before = app.contact().all();

        app.contact().gotoAddNew();
        app.contact().create(ContactData.contactData);
        app.goTo().returnToHomePage();

        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(ContactData.contactData.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()));
        Assert.assertEquals(before, after);
    }
}

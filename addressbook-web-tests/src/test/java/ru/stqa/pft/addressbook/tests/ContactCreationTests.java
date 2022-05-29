package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends BaseTest {

    @Test
    public void testContactCreation() throws Exception {

        List<ContactData> before = app.getContactHelper().getContactList();

        app.getContactHelper().gotoAddNew();
        app.getContactHelper().createContact(ContactData.contactData);
        app.getNavigationHelper().returnToHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(ContactData.contactData);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}

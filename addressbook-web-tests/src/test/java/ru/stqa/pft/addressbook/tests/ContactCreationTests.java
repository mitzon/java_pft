package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends BaseTest {

    @Test
    public void testContactCreation() throws Exception {

        Contacts before = app.contact().all();

        app.contact().gotoAddNew();
        app.contact().create(ContactData.contactData);
        app.goTo().returnToHomePage();

        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(ContactData.contactData.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
}

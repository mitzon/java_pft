package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends BaseTest {

    @Test
    public void testContactDeletion() {

        if (app.db().contacts().size() == 0) {
            app.contact().create(ContactData.contactData);
            app.goTo().returnToHomePage();
        }

        Contacts before = app.db().contacts();
        ContactData contactForDeletion = before.iterator().next();

        app.contact().delete(contactForDeletion);

        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(before.size() - 1));
        assertThat(after, equalTo(
                before.without(contactForDeletion)));

        verifyContactListInUi();
    }
}

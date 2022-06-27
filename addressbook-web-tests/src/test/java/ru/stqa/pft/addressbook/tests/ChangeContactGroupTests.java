package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class ChangeContactGroupTests extends BaseTest {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(ContactData.contactData);
            app.goTo().returnToHomePage();
        }
        if (app.db().groups().size() == 0 || app.db().contacts().iterator().next().getGroups().equals(app.db().groups())) {
            app.goTo().groupPage();
            app.group().create(new GroupData()
                    .withName(app.group().generateRandomGroupName(7))
                    .withHeader("new2")
                    .withFooter("new3"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testAddContactToGroup() {
        GroupData newGroup;
        Contacts setOfContactsBefore = app.db().contacts();
        Groups allGroupsBefore = app.db().groups();
        ContactData contactForModificationBefore = setOfContactsBefore.iterator().next();
        Groups contactGroupsBefore = contactForModificationBefore.getGroups();
        newGroup = unusedGroups(allGroupsBefore, contactForModificationBefore
                    .getGroups()).iterator().next();

        app.contact().addToGroup(contactForModificationBefore, newGroup);
        app.goTo().homePage();

        Contacts setOfContactsAfter = app.db().contacts();
        ContactData contactForModificationAfter = setOfContactsAfter.iterator().next();
        Groups contactGroupsAfter = contactForModificationAfter.getGroups();
        assertThat(contactGroupsAfter, equalTo(contactGroupsBefore.withAdded(newGroup)));
    }

    @Test
    public void testDeleteContactFromGroup() {
        GroupData newGroup;
        Contacts setOfContactsBefore = app.db().contacts();
        Groups allGroupsBefore = app.db().groups();
        ContactData contactForModificationBefore = setOfContactsBefore.iterator().next();
        Groups contactGroupsBefore = contactForModificationBefore.getGroups();

        if (contactForModificationBefore.getGroups().size() == 0) {
            newGroup = unusedGroups(allGroupsBefore, contactForModificationBefore
                    .getGroups()).iterator().next();

            app.contact().addToGroup(contactForModificationBefore, newGroup);
            app.goTo().homePage();
        } else {
            newGroup = contactForModificationBefore.getGroups().iterator().next();
            app.contact().addToGroup(contactForModificationBefore, newGroup);
            app.goTo().homePage();
        }
        app.contact().deleteFromGroup(contactForModificationBefore, newGroup);

        Contacts setOfContactsAfter = app.db().contacts();
        ContactData contactForModificationAfter = setOfContactsAfter.iterator().next();
        Groups contactGroupsAfter = contactForModificationAfter.getGroups();
        assertThat(contactGroupsAfter, equalTo(contactGroupsBefore.without(newGroup)));
    }

    private Groups unusedGroups(Groups first, Groups second) {
        Groups unusedGroups = new Groups(first);
        unusedGroups.removeAll(second);
        return unusedGroups;
    }
}

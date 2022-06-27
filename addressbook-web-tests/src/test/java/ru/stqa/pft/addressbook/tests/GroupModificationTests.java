package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends BaseTest {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("new_group").withHeader("new2").withFooter("new3"));
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.db().groups();
        GroupData groupForModification = before.iterator().next();
        GroupData  group = new GroupData()
                .withId(groupForModification.getId())
                .withName("test1")
                .withHeader("test2")
                .withFooter("test3");
        app.goTo().groupPage();
        app.group().modify(group);

        assertThat(app.group().count(), equalTo(before.size()));

        Groups after = app.db().groups();

        assertThat(after, equalTo(before.without(groupForModification).withAdded(group)));
        verifyGroupListInUi();
    }

}

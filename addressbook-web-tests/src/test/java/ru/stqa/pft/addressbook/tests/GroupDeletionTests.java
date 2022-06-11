package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends BaseTest {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();

        if (! app.group().isThereAGroup()) {
            app.group().create(new GroupData().withName("new_group").withHeader("new2").withFooter("new3"));
        }
    }

    @Test
    public void testGroupDeletion() throws Exception {
        Groups before = app.group().all();
        GroupData groupForDeletion = before.iterator().next();
        app.group().delete(groupForDeletion);

        assertThat(app.group().count(), equalTo(before.size() - 1));

        Groups after = app.group().all();

        assertThat(after, equalTo(before.without(groupForDeletion)));

    }
}

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

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
        Set<GroupData> before = app.group().all();
        GroupData groupForDeletion = before.iterator().next();
        app.group().delete(groupForDeletion);
        app.goTo().homePage();

        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(groupForDeletion);
        Assert.assertEquals(before, after);

    }
}

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;


public class GroupCreationTests extends BaseTest {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();

    app.getGroupHelper().createGroup(new GroupData("new_group", "new2", "new3"));

    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before + 1);

    app.getSessionHelper().logout();
  }

}

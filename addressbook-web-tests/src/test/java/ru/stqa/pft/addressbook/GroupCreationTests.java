package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;


public class GroupCreationTests extends BaseTest {

  @Test
  public void testGroupCreation() throws Exception {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("new_group", "new2", "new3"));
    submitGroupCreation();
    returnToGroupPage();
    logout();
  }

}

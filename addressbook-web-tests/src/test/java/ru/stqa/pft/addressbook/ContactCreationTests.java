package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;


public class ContactCreationTests extends BaseTest {

  @Test
  public void testContactCreation() throws Exception {
    gotoAddNew();
    fillContactForm(new ContactData("John", "Doe", "+79619854569", "smth@yandex.ru", "Matrosova street, 16/2"));
    submitAddNewContact();
    returnToHomePage();
    logout();
  }
}

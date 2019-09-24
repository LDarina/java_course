package tests;

import model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHome();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("first_name", "last_name","SPb", "79998887766", "name@mail.ru", "test1"), true);
    }
    app.getContactHelper().editContact();
    app.getContactHelper().fillForm(new ContactData("first_name", "last_name","SPb", "79998887766", "name@mail.ru", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();

  }
}

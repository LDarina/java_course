package tests;

import model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHome();
    app.getContactHelper().editContact();
    app.getContactHelper().fillForm(new ContactData("first_name", "last_name","SPb", "79998887766", "name@mail.ru"));
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();

  }
}

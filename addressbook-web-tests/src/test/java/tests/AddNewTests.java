package tests;

import org.testng.annotations.*;
import model.ContactData;

public class AddNewTests extends TestBase {

  @Test
  public void testAddNewTests() throws Exception {

    app.getNavigationHelper().gotoAddNew();
    app.getContactHelper().fillForm(new ContactData("first_name", "last_name","SPb", "79998887766", "name@mail.ru"));
    app.getContactHelper().submitCreation();
  }


}

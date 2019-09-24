package tests;

import org.testng.annotations.*;
import model.ContactData;

public class AddNewTests extends TestBase {

  @Test
  public void testAddNewTests() throws Exception {

    app.getContactHelper().gotoAddNew();
    app.getContactHelper().createContact(new ContactData("first_name", "last_name","SPb", "79998887766", "name@mail.ru", "test1"), true);
  }

}

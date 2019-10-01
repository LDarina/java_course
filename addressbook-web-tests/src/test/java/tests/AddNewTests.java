package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import model.ContactData;

import java.util.List;

public class AddNewTests extends TestBase {

  @Test
  public void testAddNewTests() throws Exception {
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().gotoAddNew();
    app.getContactHelper().createContact(new ContactData("first_name", "last_name","SPb", "79998887766", "name@mail.ru", "test1"), true);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

  }

}

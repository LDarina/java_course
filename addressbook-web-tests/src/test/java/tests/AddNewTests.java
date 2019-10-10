package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import model.ContactData;

import java.util.Comparator;

import java.util.List;

public class AddNewTests extends TestBase {

  @Test
  public void testAddNewTests() throws Exception {
    List<ContactData> before = app.contact().list();
    app.contact().gotoAddNew();
    ContactData contact = new ContactData().withFirstname("first_name").withLastname("last_name").withAddress("SPb").withPhone("79998887766").withEmail("name@mail.ru").withGroup("test1");
    app.contact().create((contact), true);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().max(Comparator.comparingInt(ContactData::getId)).get().getId());
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}

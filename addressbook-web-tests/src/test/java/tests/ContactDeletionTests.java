package tests;

import model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().gotoHome();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().withFirstname("first_name").withLastname("last_name").withAddress("SPb").withPhone("79998887766").withEmail("name@mail.ru").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactDeletion() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    app.goTo().gotoHome();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), index);

    before.remove(index);
    Assert.assertEquals(before, after);
  }


}

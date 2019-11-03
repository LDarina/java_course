package tests;

import model.ContactData;
import model.Contacts;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().gotoHome();
      app.contact().create(new ContactData()
              .withFirstname("first_name")
              .withLastname("last_name")
              .withAddress("SPb")
              .withPhone("79998887766")
              .withEmail("name@mail.ru")
              .withGroup("test1"), true);
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstname("first_name")
            .withLastname("last_name_modify")
            .withAddress("SPb")
            .withPhone("79998887766")
            .withPhoneWork("911")
            .withPhoneHome("812")
            .withEmail("name@mail.ru")
            .withEmail2("name2@mail.ru")
            .withEmail3("name3@mail.ru");
    app.goTo().gotoHome();
    app.contact().modify(contact);
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }
}

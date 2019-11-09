package tests;

import model.ContactData;
import model.Contacts;
import model.GroupData;
import model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static tests.TestBase.app;

public class ContactToGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().gotoHome();
      app.contact().create(new ContactData().withFirstname("first_name").withLastname("last_name").withAddress("SPb").withPhone("02").withEmail("email@mail.ru"), true);
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testContactToGroup() {
    Groups groups = app.db().groups();
    GroupData group = groups.iterator().next();
    Contacts before = app.db().contacts();

    ContactData contactAdd = contactForAdd(before);

    app.goTo().gotoHome();
    app.contact().addToGroup(contactAdd, group);
    app.goTo().selectedGroupPage(group);

    assertThat(contactAdd.getGroups().withAdded(group), equalTo(app.db().getContactByID(contactAdd.getId()).getGroups()));
  }

  private ContactData contactForAdd(Contacts before) {
    for (ContactData contact : before) {
      if (contact.getGroups().size() == 0) {
        return contact;
      }
    }
    app.contact().create(new  ContactData().withFirstname("first_name").withLastname("last_name").withAddress("SPb").withPhone("02").withEmail("email@mail.ru"), true);
    Contacts newContact = app.db().contacts();
    return newContact.iterator().next();
  }
}


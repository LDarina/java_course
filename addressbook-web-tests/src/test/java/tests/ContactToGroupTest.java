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

  private ContactData contactForAdd;

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
    Contacts contacts = app.db().contacts();
    if (contacts.stream().noneMatch(contact -> contact.getGroups().size() < 1)) {
        app.goTo().gotoHome();
        app.contact().create(new ContactData().withFirstname("first_name").withLastname("last_name").withAddress("SPb").withPhone("02").withEmail("email@mail.ru"), true);
      }
  }


  @Test
  public void testContactToGroup() {
    Contacts contacts = app.db().contacts();
    for (ContactData contact : contacts) {
      if (contact.getGroups().size() < 1) {
        contactForAdd = contact;
      }
    }
    Groups groups = app.db().groups();
    GroupData group = groups.iterator().next();

    app.goTo().gotoHome();
    app.contact().addToGroup(contactForAdd, group);
    app.goTo().selectedGroupPage(group);

    assertThat(contactForAdd.getGroups().withAdded(group), equalTo(app.db().getContactByID(contactForAdd.getId()).getGroups()));
  }


}
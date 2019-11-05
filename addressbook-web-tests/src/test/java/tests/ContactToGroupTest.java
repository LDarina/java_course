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

  private ContactData chosenContact;
  private GroupData chosenGroup;


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
  public void TestContactToGroup() {
    Contacts contacts = app.db().contacts();
    for (ContactData contact : contacts) {
      if (contact.getGroups().size() == 0) {
        chosenContact = contact;
      }
    }
    Groups groups = app.db().groups();
    for (GroupData group : groups) {
      if (group.getContacts().size() == 0) {
        chosenGroup = group;
      }

      app.goTo().gotoHome();
      Groups before = chosenContact.getGroups();
      app.contact().addToGroup(chosenContact, chosenGroup);
      ContactData contactFromDb = app.db().getContactById(chosenContact.getId());
      assertThat(contactFromDb.getGroups(), equalTo(before.withAdded(chosenGroup)));
    }
  }
}

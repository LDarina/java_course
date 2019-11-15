package tests;

import model.ContactData;
import model.Contacts;
import model.GroupData;
import model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactOutOfGroup extends TestBase {

  private GroupData groupForDel;
  private ContactData contactForDel;


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

   Groups groups = app.db().groups();
    if (groups.stream().noneMatch(group -> group.getContacts().size() > 0)) {
      Contacts contacts = app.db().contacts();
      for (ContactData contact : contacts) {
        if (contact.getGroups().size() == 0) {
          contactForDel = contact;
        }
      }
      Groups groupsAll = app.db().groups();
      for (GroupData group : groupsAll) {
        if (group.getContacts().size() == 0) {
          groupForDel = group;
        }
      }
      app.goTo().gotoHome();
      app.contact().addToGroup(contactForDel, groupForDel);
    } else {
    Contacts contacts = app.db().contacts();
    for (ContactData contact : contacts) {
      if (contact.getGroups().size() > 0) {
        contactForDel = contact;
      }
    }
    Groups contactGroups = contactForDel.getGroups();
    for (GroupData delGroup : contactGroups) {
      groupForDel = delGroup; }
    }
  }

    @Test
    public void testContactOutOfGroup () {
      Groups contactGroups = contactForDel.getGroups();
      app.goTo().gotoHome();
      app.contact().removeContactFromGroup(contactForDel, groupForDel);
      ContactData contactFromDB = app.db().getContactByID(contactForDel.getId());
      assertThat(contactFromDB.getGroups(), equalTo(contactGroups.withOut(groupForDel)));

    }
  }







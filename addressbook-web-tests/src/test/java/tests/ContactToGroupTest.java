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
  private GroupData groupForAdd;

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
    Contacts contacts = app.db().contacts();
    Groups groups = new Groups();
    for (ContactData contact : contacts) {
      groups = app.db().groups();
      int countGroup = groups.size();
      if (contact.getGroups().size() < countGroup) {
        contactForAdd = contact;
        break;
      }
    }
      Groups contactGroups = contactForAdd.getGroups();
      for (GroupData group : groups) {
       { if (!contactGroups.contains(group)) {
         groupForAdd = group;
         break;
            }
        }
      }
    app.goTo().gotoHome();
    app.contact().addToGroup(contactForAdd, groupForAdd);
    app.goTo().selectedGroupPage(groupForAdd);

    assertThat(contactForAdd.getGroups().withAdded(groupForAdd), equalTo(app.db().getContactByID(contactForAdd.getId()).getGroups()));
 }

}
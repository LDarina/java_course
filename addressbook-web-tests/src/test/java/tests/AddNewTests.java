package tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Contacts;
import model.GroupData;
import model.Groups;
import org.checkerframework.checker.units.qual.C;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import model.ContactData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class AddNewTests extends TestBase {

 @BeforeMethod
 public void ensurePreconditions() {
   app.goTo().groupPage();
   if (app.db().groups().size() == 0) {
     app.group().create(new GroupData().withName("test1"));
   }
 }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson= new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
      return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }


  @Test(dataProvider = "validContactsFromJson")
  public void testAddNewTests(ContactData contact) throws Exception {
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();
    app.contact().gotoAddNew();
    app.contact().create((contact.inGroup(groups.iterator().next())), true);
    Contacts after = app.db().contacts();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    verifyContactListInUI();
  }
}

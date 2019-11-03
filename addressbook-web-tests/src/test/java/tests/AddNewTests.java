package tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Contacts;
import model.GroupData;
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

  @DataProvider
  public Iterator<Object[]> validContacts() {
    File photo = new File("src/test/resources/IMG_0119.JPG");
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] {new ContactData().withFirstname("first_name 1").withLastname("last_name 1")
            .withAddress("SPb").withPhone("79998887766").withEmail("name@mail.ru").withGroup("test1").withPhoto(photo)});
    list.add(new Object[] {new ContactData().withFirstname("first_name 2").withLastname("last_name 2")
            .withAddress("SPb").withPhone("79998887766").withEmail("name@mail.ru").withGroup("test1").withPhoto(photo)});
    list.add(new Object[] {new ContactData().withFirstname("first_name 3").withLastname("last_name 3")
            .withAddress("SPb").withPhone("79998887766").withEmail("name@mail.ru").withGroup("test1").withPhoto(photo)});
    return list.iterator();
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
    Contacts before = app.db().contacts();
    app.contact().gotoAddNew();
    app.contact().create((contact), true);
    Contacts after = app.db().contacts();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}

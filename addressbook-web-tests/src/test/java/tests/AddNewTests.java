package tests;

import model.Contacts;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import model.ContactData;

import java.io.File;
import java.util.Comparator;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class AddNewTests extends TestBase {

  @Test
  public void testAddNewTests() throws Exception {
    Contacts before = app.contact().all();
    app.contact().gotoAddNew();
    File photo = new File("src/test/resources/IMG_0119.JPG");
    ContactData contact = new ContactData().withFirstname("first_name").withLastname("last_name")
            .withAddress("SPb").withPhone("79998887766").withEmail("name@mail.ru").withGroup("test1").withPhoto(photo);
    app.contact().create((contact), true);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

}

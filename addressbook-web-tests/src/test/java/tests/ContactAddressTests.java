package tests;

import model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().gotoHome();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("first_name").withLastname("last_name")
              .withAddress("SPb").withPhone("+7(999)8887766").withPhoneHome("66 6").withPhoneWork("812-02")
              .withEmail("name@mail.ru").withEmail2("name2@mail.ru").withEmail3("name3@mail.ru")
              .withGroup("test1"), true);
    }
  }

  @Test
  public void testContactAddress() {
    app.goTo().gotoHome();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
  }
}

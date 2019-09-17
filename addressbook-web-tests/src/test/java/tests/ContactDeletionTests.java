package tests;

import model.ContactData;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().gotoHome();
    app.getContactHelper().selectContract();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().submitDeletionContacts();
  }

}

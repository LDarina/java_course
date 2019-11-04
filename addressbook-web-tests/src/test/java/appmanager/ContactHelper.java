package appmanager;

import model.ContactData;
import model.Contacts;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getPhoneHome());
    type(By.name("mobile"), contactData.getPhone());
    type(By.name("work"), contactData.getPhoneWork());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    // attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      } else {
        Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
    }
  }
  public void gotoAddNew() {
    click(By.linkText("add new"));
  }

  public void editContact(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void editContactById(int id) {
    wd.findElement(By.xpath("//input[@id= '" + id + "']/parent::td/following-sibling::td[7]/a/img")).click();
  }

  public void submitContactModification() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  public void selectContract(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectContractById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "'")).click();
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void submitDeletionContacts() {
    wd.switchTo().alert().accept();
    wd.findElement(By.cssSelector("div.msgbox"));
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void create(ContactData contact, boolean creation) {
    gotoAddNew();
    fillForm(contact, creation);
    submitCreation();
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    editContactById(contact.getId());
    fillForm((contact), false);
    submitContactModification();
    returnToHomePage();
  }

  public void delete(int index) {
    selectContract(index);
    deleteSelectedContacts();
    submitDeletionContacts();
  }
  public void delete(ContactData contact) {
    selectContractById(contact.getId());
    deleteSelectedContacts();
    submitDeletionContacts();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name= 'entry']"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String firstname = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      String allPhones = cells.get(5).getText();
      String allEmails = cells.get(4).getText();
      String address = cells.get(3).getText();
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
    }
    return contacts;
  }


  public ContactData infoFromEditForm(ContactData contact) {
    editContactById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String homephone = wd.findElement(By.name("home")).getAttribute("value");
    String mobilephone = wd.findElement(By.name("mobile")).getAttribute("value");
    String workphone = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");

    wd.navigate().back();
    return new ContactData()
            .withId(contact.getId())
            .withFirstname(firstname)
            .withLastname(lastname)
            .withPhoneHome(homephone)
            .withPhone(mobilephone)
            .withPhoneWork(workphone)
            .withAddress(address)
            .withEmail(email)
            .withEmail2(email2)
            .withEmail3(email3);
  }

  private void addSelectedContactToGroup() {
    click(By.name("add"));
  }

  private void selectGroupForAdd(String group) {
    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group);
  }

  public void addToGroup(ContactData contact, GroupData group) {
    selectContractById(contact.getId());
    selectGroupForAdd(group.getName());
    addSelectedContactToGroup();
  }

  private void removeFromGroup() {
    wd.findElement(By.name("remove")).click();
  }

  public void removeContactFromGroup(ContactData contact, GroupData group) {
    selectGroupForRemove(group.getName());
    selectContractById(contact.getId());
    removeFromGroup();
  }
  private void selectGroupForRemove(String group) {
    new Select(wd.findElement(By.name("group"))).selectByVisibleText(group);
  }
}

package appmanager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

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
    type(By.name("mobile"), contactData.getPhone());
    type(By.name("email"), contactData.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else  {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }
  public void gotoAddNew() {
    click(By.linkText("add new"));
  }

  public void editContact(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void submitContactModification() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  public void selectContract(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
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
  public void modify(int index, ContactData contact) {
    editContact(index);
    fillForm((contact), false);
    submitContactModification();
    returnToHomePage();
  }

  public void delete(int index) {
    selectContract(index);
    deleteSelectedContacts();
    submitDeletionContacts();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name= 'entry']"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
        String firstname = cells.get(2).getText();
        String lastname = cells.get(1).getText();
        String address = cells.get(3).getText();
        String phone = cells.get(5).getText();
        int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
        contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withAddress(address).withPhone(phone));
      }
    return contacts;
  }

  }

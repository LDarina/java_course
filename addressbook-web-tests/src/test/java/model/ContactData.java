package model;

import java.util.Objects;

public class ContactData {
  private int id = Integer.MAX_VALUE;;
  private String firstname;
  private String lastname;
  private String address;
  private String homephone;
  private String workphone;
  private String phone;
  private String allPhones;
  private String email;
  private String email2;
  private String email3;
  private String allEmails;
  private String group;



  public int getId() {
    return id;
  }

  public String getAddress() { return address; }

  public String getPhone() {
    return phone;
  }

  public String getPhoneHome() { return homephone; }

  public String getPhoneWork() {
    return workphone;
  }

  public String getEmail() {
    return email;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getGroup() {
    return group;
  }

  public String getAllPhones() { return allPhones; }

  public String getAllEmails() { return allEmails; }

  public String getEmail2() { return email2; }

  public String getEmail3() { return email3; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", address='" + address + '\'' +
            ", phone='" + phone + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withPhone(String phone) {
    this.phone = phone;
    return this;
  }
  public ContactData withPhoneHome(String homephone) {
    this.homephone = homephone;
    return this;
  }
  public ContactData withPhoneWork(String workphone) {
    this.workphone = workphone;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }


  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }


  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }


  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }
}

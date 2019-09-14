package ru.stqa.ptf.addressbook;

public class ContactData {
  private final String address;
  private final String phone;
  private final String email;

  public ContactData(String address, String phone, String email) {
    this.address = address;
    this.phone = phone;
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public String getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }
}

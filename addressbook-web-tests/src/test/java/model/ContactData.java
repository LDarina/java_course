package model;

public class ContactData {
  private final String address;
  private final String phone;
  private final String email;
  private String group;
  private final String firstname;
  private final String lastname;

  public ContactData( String firstname, String lastname, String address, String phone, String email, String group) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.phone = phone;
    this.email = email;

    this.group = group;
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

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getGroup() {
    return group;
  }
}

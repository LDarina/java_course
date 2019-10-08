package model;

import java.util.Objects;

public class ContactData {
  private int id;
  private final String address;
  private final String phone;
  private final String email;
  private String group;
  private final String firstname;
  private final String lastname;

  public ContactData( String firstname, String lastname, String address, String phone, String email, String group) {
    this.id = Integer.MAX_VALUE;
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.group = group;
  }
  public ContactData( int id, String firstname, String lastname, String address, String phone, String email, String group) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.group = group;
  }

  public int getId() {
    return id;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(address, that.address) &&
            Objects.equals(phone, that.phone) &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, phone, firstname, lastname);
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

  public void setId(int id) {
    this.id = id;
  }
}

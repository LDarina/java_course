package ru.stqa.ptf.addressbook;

public class FIOData {
  private final String firstname;
  private final String lastname;

  public FIOData(String firstname, String lastname) {
    this.firstname = firstname;
    this.lastname = lastname;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }
}

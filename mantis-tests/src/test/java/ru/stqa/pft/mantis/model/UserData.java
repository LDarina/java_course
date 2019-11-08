package ru.stqa.pft.mantis.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "mantis_user_table")

public class UserData {


  @Column(name = "username")
  private String username;

  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  public String getUsername() {
    return username;
  }

  public int getId() {
    return id;
  }

  public String getEmail() { return email; }

  public String getPassword() {
    return password;
  }

  @Override
  public String toString() {
    return "UserData{" +
            "username='" + username + '\'' +
            ", id=" + id +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            '}';
  }

}

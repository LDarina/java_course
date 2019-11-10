package ru.stqa.pft.rest;

import java.util.Objects;

public class Issue {
   private int id;
   private String subject;
   private String discription;

  public int getId() {
    return id;
  }

  public Issue withId(int id) {
    this.id = id;
    return this;
  }

  public String getSubject() {
    return subject;
  }

  public Issue withSubject(String subject) {
    this.subject = subject;
    return this;
  }

  public String getDiscription() {
    return discription;
  }

  public Issue withDiscription(String discription) {
    this.discription = discription;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Issue issue = (Issue) o;
    return id == issue.id &&
            subject.equals(issue.subject) &&
            discription.equals(issue.discription);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, subject, discription);
  }

  @Override
  public String toString() {
    return "Issue{" +
            "subject='" + subject + '\'' +
            ", discription='" + discription + '\'' +
            '}';
  }
}


package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.xml.soap.SAAJResult;
import java.util.List;

public class UserHelper extends HelperBase {

  public UserHelper(ApplicationManager app) {
    super(app);
  }

  public void loginAsAdmin(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.xpath("//input[@name='username']"), username);
    click(By.xpath("//input[@value='Login']"));
    type(By.xpath("//input[@name='password']"), password);
    click(By.xpath("//input[@value='Login']"));
  }

  public void goToManageUsers() {
    click(By.xpath("//span[text()=' Manage ']"));
    click(By.xpath("//a[text()='Manage Users']"));
  }

  public void selectUserByUsername(String name) {
    click(By.xpath("//a[text() = '" + name + "']"));
  }

  public void resetPassword() {
    click(By.xpath("//input[@value='Reset Password']"));
  }

  public String findLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }


  public String changeUserPassword() {
    String newPassword = "password_new";
    return null;
  }
}
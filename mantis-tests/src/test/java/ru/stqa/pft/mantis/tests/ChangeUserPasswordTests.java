package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangeUserPasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testChangeUserPassword() throws IOException, MessagingException {
   app.user().loginAsAdmin("administrator", "root");
   app.user().goToManageUsers();
   UserData user = app.db().getUsers().iterator().next();
   String userName = user.getUsername();
   String userEmail = user.getEmail();
   app.user().selectUserByUsername(user.getUsername());
   app.user().resetPassword();
   String newPassword = "new_password";
   List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
   String confirmationLink = findConfirmationLink(mailMessages, userEmail);
   app.registration().finish(confirmationLink, newPassword);


    HttpSession session = app.newSession();
    assertTrue(session.login(userName, newPassword));

  }
  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }
  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}

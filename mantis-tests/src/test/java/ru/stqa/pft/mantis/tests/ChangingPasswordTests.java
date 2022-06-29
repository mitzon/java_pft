package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangingPasswordTests extends BaseTest {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangingPassword() throws MessagingException, IOException {
        String realName = "Real Name";
        String newPassword = "newpassword";
        String userLogin = app.db().returnUserLogin();

        app.login().asUser(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        app.goTo().manageOverviewPage();
        app.goTo().manageUsers();
        app.goTo().specificUser(userLogin);
        app.goTo().resetPassword();

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String resetPasswordLink = findResetPasswordLink(mailMessages, String.format("%s@localhost", userLogin));

        app.reset().finish(resetPasswordLink, realName, newPassword);
        assertTrue(app.newSession().login(userLogin, newPassword));
    }

    private String findResetPasswordLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}

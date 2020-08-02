package tests;

import model.Message;
import model.User;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SendMessageTest extends BaseTest {
    @BeforeClass
    @Parameters({"username", "password"})
    public void login(String username, String password) {
        loginPage.login(User.builder().username(username).password(password).build());
    }

    @Test
    @Parameters({"username", "subject", "messageBody"})
    public void sendMessage(String to, String subject, String messageBody) {
        mainPage.sendMessage(Message.builder().to(to).subject(subject).messageBody(messageBody).build());
        mainPage.getMessageGrid().getRowByText(subject).waitElement().waitUntilIsVisible();
    }

    @AfterClass
    public void logout() {
        mainPage.logout();
    }
}

package tests;

import model.User;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest extends BaseTest {
    @Test
    @Parameters({"username", "password"})
    public void login(String username, String password) {
        loginPage.login(User.builder().username(username).password(password).build());
        assertThat(mainPage.getAccountButtonWithUsername(username).isDisplayed()).isTrue();
    }
}

package tests;

import model.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LogoutTest extends BaseTest {
    @BeforeClass
    @Parameters({"username", "password"})
    public void login(String username, String password) {
        loginPage.login(User.builder().username(username).password(password).build());
    }

    @Test
    public void logout() {
        mainPage.logout();
        assertThat(loginPage.getInitialView().isDisplayed()).isTrue();
    }
}

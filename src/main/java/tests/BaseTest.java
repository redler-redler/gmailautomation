package tests;

import browser.Browser;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.MainPage;

public abstract class BaseTest {
    LoginPage loginPage = new LoginPage();
    MainPage mainPage = new MainPage();

    @Parameters({"url"})
    @BeforeClass
    public void openUrl(String url) {
        Browser.openUrl(url);
    }

    @AfterClass
    public void closeBrowser() {
        Browser.shutdown();
    }
}

package browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class Browser {
    private static final ThreadLocal<Browser> BROWSER = new ThreadLocal<>();
    private final EventFiringWebDriver webDriver;

    private Browser(EventFiringWebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private static synchronized Browser getBrowser() {
        if (!isOpened()) {
            RemoteWebDriver driver;
            switch (System.getProperty("browser")) {
                case BrowserType.CHROME:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case BrowserType.FIREFOX:
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new UnsupportedOperationException("Browser not supported");
            }
            EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
            eventFiringWebDriver.register(new RemoteWebDriverEventHandler());

            BROWSER.set(new Browser(eventFiringWebDriver));
        }
        return BROWSER.get();
    }

    public static EventFiringWebDriver getWebDriver() {
        return getBrowser().webDriver;
    }

    private static boolean isOpened() {
        return BROWSER.get() != null;
    }

    public static void shutdown() {
        if (isOpened()) {
            BROWSER.get().webDriver.quit();
            BROWSER.remove();
        }
    }

    public static void openUrl(String url) {
        Browser.getWebDriver().get(url);
    }
}

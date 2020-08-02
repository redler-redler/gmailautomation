package elements;

import browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import elements.util.CustomConditions;
import elements.util.Procedure;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class Element {
    private By locator;
    protected WebElement element;
    private Optional<Element> parentElement = Optional.empty();

    public Element(By locator) {
        this.locator = locator;
    }

    public Element(String xpath, Object... args) {
        this(By.xpath(String.format(xpath, args)));
    }

    public <T extends Element> T withParent(final Element parentElement) {
        this.parentElement = Optional.of(parentElement);
        return (T) this;
    }

    private By getLocator() {
        return parentElement.isPresent() ? new ByChained(parentElement.get().getLocator(), this.locator) : this.locator;
    }

    protected void click() {
        performActionOnElement(() -> {
            waitElement().waitUntilIsClickable();
            element.click();
        });
    }

    protected void type(String text) {
        performActionOnElement(() -> this.element.sendKeys(text));
    }

    public boolean isDisplayed() {
        return performActionOnElement(() -> this.element.isDisplayed());
    }

    private <T> T performActionOnElement(Supplier<T> action) {
        try {
            this.element = waitElement().waitForFirstDisplayed();
            return action.get();
        } catch (StaleElementReferenceException exception) {
            this.element = waitElement().waitForFirstDisplayed();
            return action.get();
        }
    }

    private void performActionOnElement(Procedure action) {
        try {
            this.element = waitElement().waitForFirstDisplayed();
            action.run();
        } catch (StaleElementReferenceException exception) {
            this.element = waitElement().waitForFirstDisplayed();
            action.run();
        }
    }

    public WaitElement waitElement() {
        return new WaitElement();
    }

    public class WaitElement {
        private static final long DEFAULT_TIMEOUT_IN_SECONDS = 15;
        private static final long DEFAULT_SLEEP_IN_MILLIS = 500;
        private TimeUnit timeUnit;
        private long timeOut;
        private long sleep;

        WaitElement(TimeUnit timeUnit, long timeOutInSeconds, long sleepInMillis) {
            this.timeUnit = timeUnit;
            this.timeOut = timeOutInSeconds;
            this.sleep = sleepInMillis;
        }

        WaitElement() {
            this(TimeUnit.SECONDS, DEFAULT_TIMEOUT_IN_SECONDS, DEFAULT_SLEEP_IN_MILLIS);
        }

        public WaitElement withTimeOut(long timeOutInSeconds) {
            this.timeOut = timeOutInSeconds;
            return this;
        }

        public WaitElement withSleep(long sleepInMillis) {
            this.sleep = sleepInMillis;
            return this;
        }

        private <T> T wait(Function<WebDriver, T> function) {
            Wait<WebDriver> wait = new WebDriverWait(Browser.getWebDriver(), timeUnit.toSeconds(timeOut), sleep);
            return wait.until(function);
        }

        public WebElement waitForFirstDisplayed() {
            return wait(CustomConditions.visibilityOfFirstDisplayedElementLocatedBy(getLocator()));
        }

        public void waitUntilIsVisible() {
            wait(ExpectedConditions.visibilityOfElementLocated(getLocator()));
        }

        public void waitUntilIsClickable() {
            wait(ExpectedConditions.elementToBeClickable(getLocator()));
        }
    }
}

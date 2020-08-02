package elements.util;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class CustomConditions {
    public static ExpectedCondition<WebElement> visibilityOfFirstDisplayedElementLocatedBy(
            final By locator) {
        return new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElements(locator).stream()
                        .filter(WebElement::isDisplayed)
                        .findFirst()
                        .orElseThrow(() -> new NoSuchElementException("Unable to locate the first displayed element"));
            }

            @Override
            public String toString() {
                return "visibility of all elements located by " + locator;
            }
        };
    }
}

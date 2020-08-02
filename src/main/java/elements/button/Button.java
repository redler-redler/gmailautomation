package elements.button;

import elements.Element;
import org.openqa.selenium.By;

public class Button extends Element {
    static final String BUTTON_BY_TEXT_PATTERN = ".//div[contains(@role,'button') and contains(.,'%s')]";

    public Button(By locator) {
        super(locator);
    }

    Button(String xpath, Object... args) {
        super(xpath, args);
    }

    @Override
    public void click() {
        super.click();
    }

    public static class ButtonByText extends Button {
        public ButtonByText(String text) {
            super(BUTTON_BY_TEXT_PATTERN, text);
        }
    }
}

package elements.util;

import org.openqa.selenium.By;

import java.io.Serializable;

public abstract class CustomBy extends By {

    public static By text(String text) {
        return new CustomBy.ByText(text);
    }

    public static By ariaLabel(String ariaLabel) {
        return new CustomBy.ByAriaLabel(ariaLabel);
    }

    public static class ByText extends ByXPath implements Serializable {
        static final String ELEMENT_BY_TEXT_PATTERN = ".//*[contains(.,'%s')]";

        ByText(String text) {
            super(String.format(ELEMENT_BY_TEXT_PATTERN, text));
        }
    }

    public static class ByAriaLabel extends ByXPath implements Serializable {
        static final String ELEMENT_BY_ARIA_LABEL_PATTERN = ".//*[contains(@aria-label,'%s')]";

        ByAriaLabel(String ariaLabel) {
            super(String.format(ELEMENT_BY_ARIA_LABEL_PATTERN, ariaLabel));
        }
    }
}

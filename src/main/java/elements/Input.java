package elements;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

public class Input extends Element {
    public Input(By locator) {
        super(locator);
    }

    @Override
    public void type(String value) {
        if (StringUtils.isNotEmpty(value)) {
            super.type(value);
        }
    }
}

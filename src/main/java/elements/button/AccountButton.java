package elements.button;

public class AccountButton extends Button {
    private static final String ACCOUNT_BUTTON_XPATH_PATTERN = ".//*[contains(@aria-label, 'Google Account')]";
    private static final String ACCOUNT_BUTTON_WITH_USERNAME_XPATH_PATTERN = ACCOUNT_BUTTON_XPATH_PATTERN +
            "[contains(@aria-label, '%s')]";

    public AccountButton() {
        super(ACCOUNT_BUTTON_XPATH_PATTERN);
    }

    public AccountButton(String username) {
        super(ACCOUNT_BUTTON_WITH_USERNAME_XPATH_PATTERN, username);
    }
}

package elements.dialog;

import elements.Element;
import elements.button.Button;
import elements.util.CustomBy;

public class UserInfoDialog extends Element {
    private Button logoutButton = new Button(CustomBy.text("Sign out")).withParent(this);

    public UserInfoDialog() {
        super(CustomBy.ariaLabel("Account Information"));
    }

    public void clickOnLogoutButton() {
        logoutButton.click();
    }
}

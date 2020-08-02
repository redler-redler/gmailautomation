package pages;

import elements.InitialView;
import model.User;

public class LoginPage extends BasePage {
    private InitialView initialView = new InitialView();

    public MainPage login(User user) {
        initialView.typeUsername(user.getUsername())
                .clickOnIdentifierNextButton()
                .typePassword(user.getPassword())
                .clickOnPasswordNextButton();
        return new MainPage().waitForAccountButton(user.getUsername());
    }

    public InitialView getInitialView() {
        return initialView;
    }

    @Override
    public LoginPage waitForPage() {
        getInitialView().waitElement().waitUntilIsVisible();
        return this;
    }
}

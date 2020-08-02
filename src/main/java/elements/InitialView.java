package elements;

import elements.button.Button;
import elements.util.CustomBy;

public class InitialView extends Element {
    private Input emailInput = new Input(CustomBy.name("identifier")).withParent(this);
    private Input passwordInput = new Input(CustomBy.name("password")).withParent(this);
    private Button identifierNextButton = new Button(CustomBy.id("identifierNext")).withParent(this);
    private Button passwordNextButton = new Button(CustomBy.id("passwordNext")).withParent(this);

    public InitialView() {
        super(CustomBy.id("initialView"));
    }

    public InitialView typeUsername(String username) {
        emailInput.type(username);
        return this;
    }

    public InitialView typePassword(String password) {
        passwordInput.type(password);
        return this;
    }

    public InitialView clickOnIdentifierNextButton() {
        identifierNextButton.click();
        return this;
    }

    public InitialView clickOnPasswordNextButton() {
        passwordNextButton.click();
        return this;
    }
}

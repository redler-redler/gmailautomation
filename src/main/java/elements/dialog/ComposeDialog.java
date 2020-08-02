package elements.dialog;

import elements.Element;
import elements.Input;
import elements.button.Button;
import elements.util.CustomBy;

public class ComposeDialog extends Element {
    private static final String COMPOSE_DIALOG_XPATH = ".//div[contains(@role, 'dialog') and contains(., 'Compose')]";
    private Input toinput = new Input(CustomBy.name("to")).withParent(this);
    private Input subjectInput = new Input(CustomBy.name("subjectbox")).withParent(this);
    private Input messageBodyInput = new Input(CustomBy.ariaLabel("Message Body")).withParent(this);
    private Button sendButton = new Button(CustomBy.ariaLabel("Send")).withParent(this);

    public ComposeDialog() {
        super(CustomBy.xpath(COMPOSE_DIALOG_XPATH));
    }

    public ComposeDialog typeTo(String to) {
        toinput.type(to);
        return this;
    }

    public ComposeDialog typeSubject(String subject) {
        subjectInput.type(subject);
        return this;
    }

    public ComposeDialog typeMessageBody(String messageBody) {
        messageBodyInput.type(messageBody);
        return this;
    }

    public ComposeDialog clickOnSendButton() {
        sendButton.click();
        return this;
    }
}

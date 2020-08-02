package pages;

import elements.button.AccountButton;
import elements.button.Button;
import elements.dialog.ComposeDialog;
import elements.dialog.UserInfoDialog;
import elements.grid.MessageGrid;
import model.Message;

public class MainPage extends BasePage {
    private AccountButton accountButton = new AccountButton();
    private UserInfoDialog userInfoDialog = new UserInfoDialog();
    private ComposeDialog newMessageDialog = new ComposeDialog();
    private MessageGrid messageGrid = new MessageGrid();
    private Button composeButton = new Button.ButtonByText("Compose");

    public AccountButton getAccountButtonWithUsername(String username) {
        return new AccountButton(username);
    }

    public MessageGrid getMessageGrid() {
        return messageGrid;
    }

    public MainPage sendMessage(Message message) {
        composeButton.click();
        newMessageDialog.waitElement().waitUntilIsVisible();
        newMessageDialog.typeTo(message.getTo())
                .typeSubject(message.getSubject())
                .typeMessageBody(message.getMessageBody())
                .clickOnSendButton();
        return this;
    }

    public LoginPage logout() {
        accountButton.click();
        userInfoDialog.waitElement().waitUntilIsVisible();
        userInfoDialog.clickOnLogoutButton();
        return new LoginPage().waitForPage();
    }

    public MainPage waitForAccountButton(String username) {
        getAccountButtonWithUsername(username).waitElement().waitUntilIsVisible();
        return this;
    }

    @Override
    public MainPage waitForPage() {
        accountButton.waitElement().waitUntilIsVisible();
        return this;
    }
}

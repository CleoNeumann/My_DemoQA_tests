package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.ButtonComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxPage extends PageBase<TextBoxPage> {
    private final SelenideElement
            fullNameInput = $("#userName"),
            emailInput = $("#userEmail"),
            currentAddressInput = $("#currentAddress"),
            permanentAddressInput = $("#permanentAddress"),
            resultBox = $("#output");
    public final SelenideElement
            nameResult = resultBox.$("#name"),
            emailResult = resultBox.$("#email"),
            currentAddressResult = resultBox.$("#currentAddress"),
            permanentAddressResult = resultBox.$("#permanentAddress");

    ButtonComponent submitButton = new ButtonComponent();

    public TextBoxPage openPage() {
        open("/text-box");
        return this;
    }

    public TextBoxPage setFullName(String name) {
        fullNameInput.setValue(name);
        return this;
    }

    public TextBoxPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public TextBoxPage setCurrentAddress(String address) {
        currentAddressInput.setValue(address);
        return this;
    }

    public TextBoxPage setPermanentAddress(String address) {
        permanentAddressInput.setValue(address);
        return this;
    }

    public TextBoxPage clickSubmitBtn() {
        submitButton.click("#submit");
        return this;
    }

    public TextBoxPage checkResultBox(SelenideElement locatorId, String text) {
        locatorId.shouldHave(text(text));
        return this;
    }
}

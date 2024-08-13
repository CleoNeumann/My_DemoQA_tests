package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pages.components.ButtonComponent;
import pages.components.CalendarComponent;
import pages.components.ResultTableComponent;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage extends PageBase<RegistrationPage> {
    private final SelenideElement
            calenderInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            hobbiesInput = $("#hobbiesWrapper"),
            uploadPicture = $("#uploadPicture"),
            currentAddressInput = $("#currentAddress"),
            stateInput = $("#state"),
            cityInput = $("#city");
    public final SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderRadioButton = $("#genterWrapper"),
            phoneNumber = $("#userNumber"),
            submitBtn = $("#submit");
    private final String alertColor = "rgb(220, 53, 69)";

    CalendarComponent calendarComponent = new CalendarComponent();
    ButtonComponent submitButton = new ButtonComponent();
    ResultTableComponent resultTable = new ResultTableComponent();

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    public RegistrationPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    public RegistrationPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public RegistrationPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public RegistrationPage setGender(String gender) {
        genderRadioButton.$(byText(gender)).click();
        return this;
    }

    public RegistrationPage setPhoneNumber(String phone) {
        phoneNumber.setValue(phone);
        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        calenderInput.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public RegistrationPage setSubject(String subject) {
        subjectsInput.setValue(subject).pressEnter();
        return this;
    }

    public RegistrationPage setHobby(String hobby) {
        hobbiesInput.$(byText(hobby)).click();
        return this;
    }

    public RegistrationPage uploadPicture(String path) {
        uploadPicture.uploadFromClasspath(path);
        return this;
    }

    public RegistrationPage setCurrentAddress(String address) {
        currentAddressInput.setValue(address);
        return this;
    }

    public RegistrationPage setState(String state) {
        stateInput.click();
        stateInput.$(byText(state)).click();
        return this;
    }

    public RegistrationPage setCity(String city) {
        cityInput.click();
        cityInput.$(byText(city)).click();
        return this;
    }

    public RegistrationPage clickSubmitBtn() {
        submitButton.click("#submit");
        return this;
    }

    public RegistrationPage checkSuccessfulRegistrationTableTitle() {
        resultTable.checkModalAfterRegistration("Thanks for submitting the form");
        return this;
    }

    public RegistrationPage checkResult(String key) {
        resultTable.checkModalRow(key);
        return this;
    }

    public RegistrationPage checkResult(String key, String value) {
        resultTable.checkModalRow(key, value);
        return this;
    }

    public RegistrationPage hasError(SelenideElement locatorId) {
        locatorId.shouldHave(Condition.cssValue("borderColor", alertColor));
        return this;
    }

    public RegistrationPage checkResultInvisible() {
        resultTable.checkModalInvisible();
        return this;
    }

    public RegistrationPage moveToBtn() {
        submitButton.moveTo("#submit");
        return this;
    }
}

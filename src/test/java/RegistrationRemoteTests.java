import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static io.qameta.allure.Allure.step;

@Tag("smoke")
public class RegistrationRemoteTests extends TestBaseRemote {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void successfulFullRegistrationTest() {
        step("Open form", () -> {
            registrationPage.openPage()
                    .removeBanner();
        });
        step("Fill form", () -> {
            registrationPage.setFirstName("Cleo")
                    .setLastName("Neumann")
                    .setEmail("cleo@neumann.com")
                    .setGender("Female")
                    .setPhoneNumber("7111987987")
                    .setDateOfBirth("28", "April", "1994")
                    .setSubject("comp")
                    .setHobby("Reading")
                    .setHobby("Music")
                    .uploadPicture("testImage.jpg")
                    .setCurrentAddress("Somewhere")
                    .moveToBtn()
                    .setState("NCR")
                    .setCity("Delhi");
        });
        step("Submit form", () -> {
            registrationPage.clickSubmitBtn();
        });
        step("Check form results", () -> {
            registrationPage.checkSuccessfulRegistrationTableTitle()
                    .checkResult("Student Name", "Cleo Neumann")
                    .checkResult("Student Email", "cleo@neumann.com")
                    .checkResult("Gender", "Female")
                    .checkResult("Mobile", "7111987987")
                    .checkResult("Date of Birth", "28 April,1994")
                    .checkResult("Subjects", "Computer Science")
                    .checkResult("Hobbies", "Reading, Music")
                    .checkResult("Picture", "testImage.jpg")
                    .checkResult("Address", "Somewhere")
                    .checkResult("State and City", "NCR Delhi");
        });
    }

    @Test
    void successfulShortRegistrationTest() {
        step("Open form", () -> {
            registrationPage.openPage()
                    .removeBanner();
        });
        step("Fill required form fields", () -> {
            registrationPage.setFirstName("Cleo")
                    .setLastName("Neumann")
                    .setGender("Female")
                    .setPhoneNumber("7111987987")
                    .setDateOfBirth("28", "April", "1994");
        });
        step("Submit form", () -> {
            registrationPage.moveToBtn()
                    .clickSubmitBtn();
        });
        step("Check form results", () -> {
            registrationPage.checkSuccessfulRegistrationTableTitle()
                    .checkResult("Student Name", "Cleo Neumann")
                    .checkResult("Student Email")
                    .checkResult("Gender", "Female")
                    .checkResult("Mobile", "7111987987")
                    .checkResult("Date of Birth", "28 April,1994")
                    .checkResult("Subjects")
                    .checkResult("Hobbies")
                    .checkResult("Picture")
                    .checkResult("Address")
                    .checkResult("State and City");
        });
    }

    @Test
    void submitWithEmptyFieldsTest() {
        step("Open form", () -> {
            registrationPage.openPage()
                    .removeBanner();
        });
        step("Submit form without filling out", () -> {
            registrationPage.moveToBtn()
                    .clickSubmitBtn();
        });
        step("Check form has errors", () -> {
            registrationPage.hasError(registrationPage.firstNameInput)
                    .hasError(registrationPage.lastNameInput)
                    .hasError(registrationPage.phoneNumber)
                    .checkResultInvisible();
        });
    }

    @Test
    void checkValidationTest() {
        step("Open form", () -> {
            registrationPage.openPage()
                    .removeBanner();
        });
        step("Fill only phone and email fields", () -> {
            registrationPage.setPhoneNumber("123")
                    .setEmail("123");
        });
        step("Submit form", () -> {
            registrationPage.moveToBtn()
                    .clickSubmitBtn();
        });
        step("Check form has errors", () -> {
            registrationPage.hasError(registrationPage.emailInput)
                    .hasError(registrationPage.phoneNumber)
                    .checkResultInvisible();
        });
    }
}

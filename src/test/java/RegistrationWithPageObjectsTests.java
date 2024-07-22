import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class RegistrationWithPageObjectsTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void successfulFullRegistrationTest() {
        registrationPage.OpenPage()
                .removeBanner()
                .setFirstName("Cleo")
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
                .setState("NCR")
                .setCity("Delhi")
                .clickSubmitBtn()
                .checkSuccessfulRegistrationTableTitle()
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
    }

    @Test
    void successfulShortRegistrationTest() {
        registrationPage.OpenPage()
                .removeBanner()
                .setFirstName("Cleo")
                .setLastName("Neumann")
                .setGender("Female")
                .setPhoneNumber("7111987987")
                .setDateOfBirth("28", "April", "1994")
                .clickSubmitBtn()
                .checkSuccessfulRegistrationTableTitle()
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
    }

    @Test
    void submitWithEmptyFieldsTest() {
        registrationPage.OpenPage()
                .removeBanner()
                .clickSubmitBtn()
                .hasError(registrationPage.firstNameInput)
                .hasError(registrationPage.lastNameInput)
                .hasError(registrationPage.genderRadioButton)
                .hasError(registrationPage.phoneNumber)
                .checkResultInvisible();
    }

    @Test
    void checkValidationTest() {
        registrationPage.OpenPage()
                .removeBanner()
                .setPhoneNumber("123")
                .setEmail("123")
                .clickSubmitBtn()
                .hasError(registrationPage.emailInput)
                .hasError(registrationPage.phoneNumber)
                .checkResultInvisible();
    }
}

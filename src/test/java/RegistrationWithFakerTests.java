import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class RegistrationWithFakerTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    TestData data = new TestData();

    @Test
    void successFullRegistrationTest() {
        registrationPage.openPage()
                .removeBanner()
                .setFirstName(data.firstName)
                .setLastName(data.lastName)
                .setEmail(data.userEmail)
                .setGender(data.gender)
                .setPhoneNumber(data.phoneNumber)
                .setDateOfBirth(data.day, data.month, data.year)
                .setSubject(data.subject)
                .setHobby(data.hobby)
                .uploadPicture(data.picturePath)
                .setCurrentAddress(data.streetAddress)
                .setState(data.state)
                .setCity(data.city)
                .clickSubmitBtn()
                .checkSuccessfulRegistrationTableTitle()
                .checkResult("Student Name", data.firstName + " " + data.lastName)
                .checkResult("Student Email", data.userEmail)
                .checkResult("Gender", data.gender)
                .checkResult("Mobile", data.phoneNumber)
                .checkResult("Date of Birth", data.day + " " + data.month + "," + data.year)
                .checkResult("Subjects", data.subject)
                .checkResult("Hobbies", data.hobby)
                .checkResult("Picture", data.picturePath)
                .checkResult("Address", data.streetAddress)
                .checkResult("State and City", data.state + " " + data.city);
    }
}

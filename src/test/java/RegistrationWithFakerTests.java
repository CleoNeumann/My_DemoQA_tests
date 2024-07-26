import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class RegistrationWithFakerTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    TestData Data = new TestData();

    @Test
    void successFullRegistrationTest() {
        registrationPage.openPage()
                .removeBanner()
                .setFirstName(Data.firstName)
                .setLastName(Data.lastName)
                .setEmail(Data.userEmail)
                .setGender(Data.gender)
                .setPhoneNumber(Data.phoneNumber)
                .setDateOfBirth(Data.day, Data.month, Data.year)
                .setSubject(Data.subject)
                .setHobby(Data.hobby)
                .uploadPicture(Data.picturePath)
                .setCurrentAddress(Data.streetAddress)
                .setState(Data.state)
                .setCity(Data.city)
                .clickSubmitBtn()
                .checkSuccessfulRegistrationTableTitle()
                .checkResult("Student Name", Data.firstName + " " + Data.lastName)
                .checkResult("Student Email", Data.userEmail)
                .checkResult("Gender", Data.gender)
                .checkResult("Mobile", Data.phoneNumber)
                .checkResult("Date of Birth", Data.day + " " + Data.month + "," + Data.year)
                .checkResult("Subjects", Data.subject)
                .checkResult("Hobbies", Data.hobby)
                .checkResult("Picture", Data.picturePath)
                .checkResult("Address", Data.streetAddress)
                .checkResult("State and City", Data.state + " " + Data.city);
    }
}

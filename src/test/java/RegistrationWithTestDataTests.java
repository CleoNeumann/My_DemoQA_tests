import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationWithTestDataTests extends TestBase {

    String firstName = "Cleo",
            lastName = "Neumann",
            userEmail = "cleo@neumann.com";

//    public static final String firstName = "Cleo",
//            lastName = "Neumann",
//            userEmail = "cleo@neumann.com";

//    static String firstName,
//            lastName,
//            userEmail;

//    @BeforeEach
//    void  prepareTestData() {
//        firstName = "Cleo";
//        lastName = "Neumann";
//        userEmail = "cleo@neumann.com";
//    }

    // или создать класс, специально для тестовых данных типа TestData

    @Test
    void successfulRegistrationTest() {
//        String firstName = "Cleo",
//                lastName = "Neumann",
//                userEmail = "cleo@neumann.com";

        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $("label[for='gender-radio-2']").click();
        $("#userNumber").setValue("7111987987");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue("3");
        $(".react-datepicker__year-select").selectOptionByValue("1994");
        $(".react-datepicker__day--004").click();

        $("#subjectsInput").setValue("comp").pressEnter();
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#hobbiesWrapper").$(byText("Music")).click();

        $("#submit").scrollIntoView(false);
        $("#uploadPicture").uploadFromClasspath("testImage.jpg");

        $("#currentAddress").setValue("Somewhere");
        $("#state").click();
        $("#react-select-3-option-0").click();
        $("#city").click();
        $("#react-select-4-option-0").click();
        $("#submit").click();

        $(".table").shouldHave(text("Student Name " + firstName + " " + lastName));
        $(".table").shouldHave(text("Student Email " + userEmail));
        $(".table").shouldHave(text("Gender Female"));
        $(".table").shouldHave(text("Mobile 7111987987"));
        $(".table").shouldHave(text("Date of Birth 04 April,1994"));
        $(".table").shouldHave(text("Subjects Computer Science"));
        $(".table").shouldHave(text("Hobbies Reading, Music"));
        $(".table").shouldHave(text("Picture testImage.jpg"));
        $(".table").shouldHave(text("Address Somewhere"));
        $(".table").shouldHave(text("State and City NCR Delhi"));
    }
}

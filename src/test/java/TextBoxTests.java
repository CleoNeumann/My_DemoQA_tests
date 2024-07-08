import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void fillFormTest() {
        open("/text-box");
        $("#userName").setValue("Cleo");
        $("#userEmail").setValue("cleo@neumann.com");
        $("#currentAddress").setValue("Somewhere");
        $("#permanentAddress").setValue("Some other address");
        $("#submit").click();

        $("#name").shouldHave(text("Cleo"));    // потому что id уникальный
        $("#output").$("#name").shouldHave(text("Cleo"));

        $("#email").shouldHave(text("cleo@neumann.com"));   // потому что email уникальный
        $("#output").$("#email").shouldHave(text("cleo@neumann.com"));

        $("p[id='currentAddress']").shouldHave(text("Somewhere"));  // с помощью web inspector
        $("#output").$("#currentAddress").shouldHave(text("Somewhere"));    // не уникальный + через родительский элемент
        $("#output #currentAddress").shouldHave(text("Somewhere"));    // короткая версия

        $("p[id='permanentAddress']").shouldHave(text("Some other address"));
        $("#output").$("#permanentAddress").shouldHave(text("Some other address"));
        $("#output #permanentAddress").shouldHave(text("Some other address"));
    }
}

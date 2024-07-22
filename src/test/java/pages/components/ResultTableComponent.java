package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultTableComponent {
    private final SelenideElement
            modalDialog = $(".modal-dialog"),
            modalTitle = $("#example-modal-sizes-title-lg"),
            modalBody = $(".table-responsive");

    public void checkModalAfterRegistration(String text) {
        modalDialog.should(appear);
        modalTitle.shouldHave(text(text));
    }

    public void checkModalRow(String key) {
        modalBody.$(byText(key)).parent().shouldHave(exactText(key));
    }

    public void checkModalRow(String key, String value) {
        modalBody.$(byText(key)).parent().shouldHave(text(value));
    }

    public void checkModalInvisible() {
        modalDialog.shouldNotBe(visible);
    }
}

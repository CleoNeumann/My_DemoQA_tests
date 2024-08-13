package pages.components;

import static com.codeborne.selenide.Selenide.$;

public class ButtonComponent {
    public void click(String id) {
        $(id).click();
    }

    public void moveTo(String id) {
        $(id).scrollIntoView(true);
    }
}

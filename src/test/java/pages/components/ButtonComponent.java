package pages.components;

import static com.codeborne.selenide.Selenide.$;

public class ButtonComponent {
    public void click(String id) {
        $(id).click();
    }
}

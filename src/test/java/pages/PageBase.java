package pages;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public class PageBase<T> {
    public T removeBanner() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return (T) this;
    }
}

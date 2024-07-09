package selenide;

import com.codeborne.selenide.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class Snippets {
    void browser_command_examples() {
        // способы открыть браузер / страницу в браузере
        // absolute
        open("https://google.com");
        // relative
        open("/text-form");
        // with authentication
        open("https://the-internet.herokuapp.com/basic_auth", AuthenticationType.BASIC,
                new BasicAuthCredentials("","admin", "admin"));

        // кнопки "назад" и "обновить страницу" самого браузера
        Selenide.back();
        Selenide.refresh();

        // cookies, localStorage
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        executeJavaScript("sessionStorage.clear();"); // no selenide command yet

        // add cookie
        var cookie = new Cookie("foo", "bar");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);

        // LocalStorage:
        // Очистить всё содержимое:
        localStorage().clear();
        // Добавить значение:
        localStorage().setItem("username", "john");
        // удалить значение:
        localStorage().removeItem("username");

        // JS alerts OK/Cancel
        Selenide.confirm();
        Selenide.dismiss();

        // закрывает активный браузер
        Selenide.closeWindow();
        // закрывает все окна браузера
        Selenide.closeWebDriver();

        Selenide.switchTo().frame("new");
        Selenide.switchTo().defaultContent(); // return from frame back to the main DOM
        Selenide.switchTo().window("The internet"); // перемещение между окнами браузера в тесте
    }

    void selectors_examples() {
        $("div").click();
        element("div").click(); // for Kotlin

        $("div", 2).click(); // the 3d div

        $x("//h1/div").click(); // Xpath только для точного определения, в крайних случаях ...
        $(byXpath("//h1/div")).click(); // Xpath ... или если он действительно быстрее

        $(byText("full text")).click();
        $(withText("ull tex")).click();

        $(byTagAndText("div", "full text")).click();
        $(withTagAndText("div", "ull te")).click();

        $("").parent(); // родительский элемент
        $("").sibling(0); // сиблинг вниз по дереву на том же уровне вложенности
        $("").preceding(0); // сиблинг вверх по дереву на том же уровне вложенности
        $("").closest("div"); // ближайший родитель с определенным тэгом
        $("").ancestor("div"); // the same as closest
        $("div:last-child");

        $("div").$("h1").find(byText("abc")).click(); // chain of selectors
        // find == $
        $("[abc=x]").click(); // byAttribute()
        $("#mytext").click(); // byID()
        $(".mytext").click(); // byClassName()
    }

    void actions_examples() {
        $("").click();
        $("").doubleClick();
        $("").contextClick(); // click by RMB

        $("").hover(); // move mouse cursor but don`t click

        $("").setValue("text"); // записать новый текст в поле ввода
        $("").append("text"); // добавить текст к уже написанному
        $("").clear(); // иногда работает неправильно
        $("").setValue(""); // clear

        $("div").sendKeys("c"); // hotkey c on element
        actions().sendKeys("c").perform(); // hotkey c whole application
        actions().sendKeys(Keys.chord(Keys.CONTROL, "f")).perform(); // ctrl + f
        $("html").sendKeys(Keys.chord(Keys.CONTROL, "f")); // the same as previous

        $("").pressEnter();
        $("").pressTab();
        $("").pressEscape();

        // complex actions with keyboard and mouse
        actions().moveToElement($("div")).clickAndHold().moveByOffset(300, 200).release().perform();
        // actions() always finishes with perform()
        // clickAndHold() - зажать клавишу мыши и держать ,release() - отпустить клавишу мыши

        // old html actions don§t work with many modern frameworks
        $("").selectOption("dropdown_option");
        $("").selectRadio("radio_options");
        // for modern html actions double  click only
    }

    void assertions_examples() {
        // shouldBe == shouldHave == should, чтобы правильно выражать мысль на АЯ
        $("").shouldBe(visible);
        $("").shouldNotBe(visible);
        $("").shouldHave(text("abc"));
        $("").shouldNotHave(text("abc"));
        $("").should(appear);
        $("").shouldNot(appear);

        // add or remove timeouts
        $("").shouldBe(visible, Duration.ofSeconds(30));
    }

    void conditions_examples() {
        // see Conditions.class -> ctrl (cmd) + LMB on visible
        $("").shouldBe(visible);

        $("").should(matchText("[0-9]]abc$"));

        $("").shouldHave(cssClass("red"));
        $("").shouldHave(cssValue("font-size", "12"));

        $("").shouldBe(checked); // for checkbox

        // exist != visible. exist: существует в DOM, но не факт что виден visible

        $("").shouldBe(disabled); // только если у элемента есть атрибут disabled.
        // тоже самое для enabled
    }

    void collections_examples() {
        // see CollectionConditions.class -> ctrl (cmd) + LMB on empty
        $$("div"); // ищет все коллекции div
        $$x("div"); // by Xpath

        // selections
        $$("div").filterBy(text("123")).shouldHave(size(1));
        $$("div").excludeWith(text("123")).shouldHave(size(1)); // противоположность filterBy

        $$("div").first().click();
        // == $("div").click();
        $$("div").last().click();
        $$("div").get(1).click();
        // == $("div", 1).click();

        $$("div").findBy(text("123")).click();
        // combine filterBy + first

        // assertions
        $$("").shouldHave(size(0));
        $$("").shouldBe(CollectionCondition.empty); // the same as previous

        $$("").shouldHave(texts("Alfa", "Betta", "Gamma"));
        // true for 3 elements, for "AlfaCentavr"
        // false for 4 elements
        // exactTexts - проверит и на кол-во и на точность текста
        // textInAnyOrder - игнорирует расположение текстов
        // itemWithText - проверит что есть хотя бы 1 элемент с таким текстом
    }

    void file_operation_examples() throws FileNotFoundException {
        File file1 = $("a.fileLink").download(); // only for <a href="..."> links
        File file2 = $("div").download(DownloadOptions.using(FileDownloadMode.FOLDER));
        // see selenide 5.14 description https://www.youtube.com/watch?v=x0KWgnjxsl4&t=709s

        // for upload
        File file = new File("src/test/resources/file1.txt");
        $("#file-upload").uploadFile(file);
        $("#file-upload").uploadFromClasspath("file1.txt");
        // don`t forget to submit!
    }

    void javascript_examples() {
        // запуск
        executeJavaScript("alert('Selenide!')");
        // запуск с аргументами
        executeJavaScript("alert(arguments[0]+arguments[1])", "123", 12);
        // запуск с аргументами и возвращением результата
        long fortytwo = executeJavaScript("return arguments[0]*arguments[1];", 6, 7);
    }
}

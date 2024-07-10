package edu.github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideRepositoryTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://github.com";
    }

    @Test
    void shouldFindSelenideRepositoryAtTheTop() {
        open("/");
        $(".header-search-button").click();
        $("#query-builder-test").setValue("selenide").pressEnter();
        $$("[data-testid='results-list'] div").first().$("a").click();
        $("#repository-container-header").shouldHave(text("selenide / selenide"));
    }

    @Test
    void andreiSolntsevShouldBeTheFirstContributor() {
        open("/selenide/selenide");
        $("div.Layout-sidebar").$(byText("Contributors"))
//                .closest(".BorderGrid-cell").$("ul li").hover();
                .closest("h2").sibling(0).$$("li").first().hover();
        $(".Popover-message").shouldHave(text("Andrei Solntsev"));
    }

    @Test
    void softAssertionsSearchTest() {

        open("/selenide/selenide");
        $("#wiki-tab").click();
        $("#wiki-pages-filter").setValue("SoftAssertions");
        $("#wiki-pages-box").$(byText("SoftAssertions")).click();
        $(withTagAndText("h4", "JUnit5")).scrollIntoView(true);
        $("#wiki-body").shouldHave(text("""
                @ExtendWith({SoftAssertsExtension.class})
                class Tests {
                @Test
                void test() {
                Configuration.assertionMode = SOFT;
                open("page.html");
                $("#first").should(visible).click();
                $("#second").should(visible).click();
                """));
    }
}

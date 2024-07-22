package pages.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CalendarComponent {
    private final ElementsCollection
            calendarDay = $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)");
    private final SelenideElement
            calendarMonth = $(".react-datepicker__month-select"),
            calendarYear = $(".react-datepicker__year-select");

    public void setDate(String day, String month, String year) {
        calendarMonth.selectOption(month);
        calendarYear.selectOption(year);
        calendarDay.findBy(text(day)).click();
    }
}

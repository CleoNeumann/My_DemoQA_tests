import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

public class TextBoxWithPageObjectsTests extends TestBase {

    TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    void fillFormTest() {
        textBoxPage.openPage()
                .setFullName("Cleo")
                .setEmail("cleo@neumann.com")
                .setCurrentAddress("Somewhere")
                .setPermanentAddress("Some other address")
                .clickSubmitBtn()
                .checkResultBox(textBoxPage.nameResult, "Cleo")
                .checkResultBox(textBoxPage.emailResult, "cleo@neumann.com")
                .checkResultBox(textBoxPage.currentAddressResult, "Somewhere")
                .checkResultBox(textBoxPage.permanentAddressResult, "Some other address");
    }
}

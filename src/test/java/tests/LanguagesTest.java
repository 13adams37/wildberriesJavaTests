package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BasePage;
import test_data.Urls;

import static com.codeborne.selenide.Selenide.open;
import static steps.CommonSteps.checkUrl;

public class LanguagesTest extends BasePage {

    @DataProvider
    public Object[][] languageCodes() {
        return new Object[][]{
                {"AM"}, {"BY"}, {"KZ"}, {"KG"}, {"UZ"}, {"WWW"}, {"IL"}
                //WWW == RU
        };
    }

    @BeforeMethod
    public void openMainPage() {
        open(Urls.MAIN_PAGE.getUrl());
    }

    @Test(dataProvider = "languageCodes")
    public void checkLanguages(String languageCode) {
        wildberriesMainPage.pressButtonWithLanguage(languageCode);
        checkUrl("https://" + languageCode.toLowerCase() + ".wildberries.ru", 5);
    }

}

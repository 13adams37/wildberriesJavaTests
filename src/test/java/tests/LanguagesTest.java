package tests;

import org.testng.SkipException;
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
                {"WWW"}, {"AM"}, {"BY"}, {"IL"}, {"KZ"}, {"KG"}, {"UZ"}
                // WWW == RU
        };
    }

    @BeforeMethod
    public void openMainPage() {
        open(Urls.MAIN_PAGE.getUrl());
    }

    @Test(dataProvider = "languageCodes")
    public void checkLanguages(String languageCode) {
        if (languageCode.equals("IL") || languageCode.equals("KG"))
            throw new SkipException("Different frontend branch");
        wildberriesMainPage.pressButtonWithLanguage(languageCode);
        checkUrl("https://" + languageCode.toLowerCase() + ".wildberries.ru");
        wildberriesMainPage.checkCountyDeliveryCity(languageCode);
    }

}

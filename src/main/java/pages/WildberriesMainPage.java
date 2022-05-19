package pages;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.*;
import static logger.CustomLogger.logger;

public class WildberriesMainPage {

    @Step
    public void pressButtonWithLanguage(String countryCode) {
        $x("//span[@data-wba-header-name='Country']").click();
        $x("//input[@value='" + transformCountryCodeToLanguage(countryCode) + "']/..").click();
        logger.info("ok");
    }

    @Step
    public String transformCountryCodeToLanguage(String countryCode) {
        switch (countryCode) {
            case "WWW":
                return "ru";
            case "AM":
                return "am";
            case "BY":
                return "by";
            case "KZ":
                return "kz";
            case "KG":
                return "kg";
            case "UZ":
                return "uz";
            case "IL":
                return "il";
            default:
                Assert.fail("Country code " + countryCode + " not found!");
        }
        return null;
    }

}

package steps;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.testng.Assert;

public class CommonSteps {
    @Step("Проверка url")
    public static void checkUrl(String url) {
        Assert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(), url);
    }

    @Step("Перевод кода страны в субдомен и столицу")
    public static String[] transformCountryCodeToLanguage(String countryCode) {
        String[] country = new String[2];
        switch (countryCode) {
            case "WWW":
                country[0] = "ru";
                country[1] = "Москва";
                return country;
            case "AM":
                country[0] = "am";
                country[1] = "Ереван";
                return country;
            case "BY":
                country[0] = "by";
                country[1] = "Минск";
                return country;
            case "KZ":
                country[0] = "kz";
                country[1] = "Нур-Султан";
                return country;
            case "KG":
                country[0] = "kg";
                country[1] = "Бишкек";
                return country;
            case "UZ":
                country[0] = "uz";
                country[1] = "Ташкент";
                return country;
            case "IL":
                country[0] = "il";
                country[1] = "Иерусалим";
                return country;
            default:
                Assert.fail("Country code " + countryCode + " not found!");
        }
        return country;
    }

}

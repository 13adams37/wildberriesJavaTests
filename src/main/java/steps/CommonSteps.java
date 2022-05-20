package steps;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;
import static logger.CustomLogger.logger;

public class CommonSteps {


    @Step("Проверка url")
    public static void checkUrl(String mustContains, int timeOut) {
        boolean conditionForUrl = false;
        for (int i = 0; i < timeOut * 2; i++) {
            if (url().contains(mustContains)) {
                conditionForUrl = true;
                getAndAttachScreenshot();
                break;
            } else {
                sleep(500);
            }
        }
        if (!conditionForUrl) {
            getAndAttachScreenshot();
            Assert.fail("Actual URL: " + url() + "\n" + "Expected URL: " + mustContains);
        }
        logger.info("ok");
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

    @SuppressWarnings("UnusedReturnValue")
    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] getAndAttachScreenshot() {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

}

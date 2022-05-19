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

    @Step
    public static void clickTheElementByTagAndText(String tag, String text) {
        $(byXpath("//" + tag + "[text()='" + text + "']")).click();
        logger.info(text + " - ok");
    }

    @Step
    public static void clickTheElementByTagAndText(String tag, String text, int index) {
        $(byXpath("(//" + tag + "[text()='" + text + "'])[" + index + "]")).click();
        logger.info(text + " - ok");
    }

    @Step
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

    @SuppressWarnings("UnusedReturnValue")
    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] getAndAttachScreenshot() {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

}

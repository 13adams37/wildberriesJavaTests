package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.*;
import static logger.CustomLogger.logger;
import static steps.CommonSteps.transformCountryCodeToLanguage;


public class WildberriesMainPage {
    private final SelenideElement

            countryHover = $x("//span[@data-wba-header-name='Country']"),
            countryCity = $x("//span[@data-wba-header-name='DLV_Adress']");


    @Step("Нажать на кнопку выбора языка")
    public void pressButtonWithLanguage(String countryCode) {
        countryHover.hover();
        $x("//input[@value='" + transformCountryCodeToLanguage(countryCode)[0] + "']/..").click();
        logger.info("ok");
    }

    @Step("Проверка отображения города доставки (столица)")
    public void checkCountyDeliveryCity(String countryCode) {
        countryCity.shouldHave(Condition.exactText(transformCountryCodeToLanguage(countryCode)[1]));
        logger.info("ok");
    }

}

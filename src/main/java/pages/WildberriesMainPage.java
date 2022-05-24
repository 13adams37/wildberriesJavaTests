package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;
import static steps.CommonSteps.transformCountryCodeToLanguage;


public class WildberriesMainPage {
    private final SelenideElement

            countryHover = $x("//span[@data-wba-header-name='Country']"),
            countryCity = $x("//span[@data-wba-header-name='DLV_Adress']"),
            searchInput = $("#searchInput");


    @Step("Нажать на кнопку языка '{countryCode}'")
    public void pressButtonWithLanguage(String countryCode) {
        countryHover.hover();
        $x("//input[@value='" + transformCountryCodeToLanguage(countryCode)[0] + "']/..").click();
    }

    @Step("Проверка отображения города доставки '{countryCode}'")
    public void checkCountyDeliveryCity(String countryCode) {
        countryCity.shouldHave(Condition.exactText(transformCountryCodeToLanguage(countryCode)[1]));
    }

    @Step("Поиск товара '{productName}'")
    public void searchProduct(String productName) {
        searchInput.click();
        searchInput.sendKeys(productName);
        searchInput.pressEnter();
    }

}

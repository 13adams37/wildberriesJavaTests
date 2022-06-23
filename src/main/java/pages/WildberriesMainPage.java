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
            loginButton = $x("//a[@data-wba-header-name=\"Login\"]"),
            searchInput = $("#searchInput");


    @Step("Нажать на кнопку языка '{countryCode}'")
    public WildberriesMainPage pressButtonWithLanguage(String countryCode) {
        countryHover.hover();
        $x("//input[@value='" + transformCountryCodeToLanguage(countryCode)[0] + "']/..").click();
        return this;
    }

    @Step("Проверка отображения города доставки '{countryCode}'")
    public WildberriesMainPage checkCountyDeliveryCity(String countryCode) {
        countryCity.shouldHave(Condition.exactText(transformCountryCodeToLanguage(countryCode)[1]));
        return this;
    }

    @Step("Поиск товара '{productName}'")
    public WildberriesMainPage searchProduct(String productName) {
        searchInput.click();
        searchInput.sendKeys(productName);
        searchInput.pressEnter();
        return this;
    }

    @Step("Переход на страницу авторизации")
    public WildberriesMainPage goToLoginPage() {
        loginButton.click();
        return this;
    }

}

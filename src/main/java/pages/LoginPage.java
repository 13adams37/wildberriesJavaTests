package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    private final SelenideElement spaAuthForm = $("#spaAuthForm"),
            authValidationError = $x("//span[@for=\"phoneInput.FullPhoneMobile\"]"),
            dropdownCountryCodeButton = $x("//div[@class=\"drop-btn\"]"),
            phoneInput = $x("//input[@class=\"input-item\"]"),
            smsCodeInput = $(".j-input-confirm-code"),
            requestCodeButton = $("#requestCode"),
            shortSessionCheckbox = $x("//input[@type='checkbox' and @data-link='shortSession']"),
            spamSmsCheckbox = $x("//input[@type=\"checkbox\" and @data-link=\"agreeToReceiveSms\"]");

    // Кликеры
    public LoginPage requestCodeButtonClick() {
        requestCodeButton.click();
        return this;
    }

    public LoginPage shortSessionCheckboxClick() {
        shortSessionCheckbox.click();
        return this;
    }

    public LoginPage smsCheckboxClick() {
        spamSmsCheckbox.click();
        return this;
    }

    public LoginPage phoneDropdownClick() {
        dropdownCountryCodeButton.click();
        return this;
    }

    // Сеттеры
    public LoginPage setPhoneNumber(String phoneNumber) {
//        phoneInput.click();
        phoneInput.sendKeys(phoneNumber);
        return this;
    }

    public LoginPage setSmsCode(String smsCode) {
        smsCodeInput.sendKeys(smsCode);
        return this;
    }

    // Геттеры
    public String getRequestCodeButtonText() {
        return requestCodeButton.getText();
    }

    public ElementsCollection getPhoneCodes() {
        return $$(".drop-select");
    }

    // Assertions
    public LoginPage loginFormShouldBeVisible() {
        spaAuthForm.shouldBe(Condition.visible);
        return this;
    }

    public LoginPage requestCodeButtonShouldBeInactive() {
        requestCodeButton.shouldHave(Condition.cssClass("disabled"));
        return this;
    }

    public LoginPage requestCodeButtonShouldBeActive() {
        requestCodeButton.shouldNotHave(Condition.cssClass("disabled"));
//        requestCodeButton.shouldBe(Condition.enabled);
        return this;
    }

    public LoginPage validationErrorShouldBeDisplayed() {
        authValidationError.shouldBe(Condition.visible);
//        authValidationError.shouldBe(hi)
        return this;
    }

    public LoginPage validationErrorIsNotVisible() {
        authValidationError.shouldNotBe(Condition.visible);
        authValidationError.shouldNotHave(Condition.visible);
        return this;
    }

    public LoginPage phoneDropdownShouldBeDisplayed() {
        dropdownCountryCodeButton.shouldBe(Condition.visible);
        return this;
    }

    public LoginPage phoneDropdownShouldNotBeDisplayed() {
        dropdownCountryCodeButton.shouldBe(Condition.visible);
        return this;
    }

    public LoginPage spamSmsCheckboxShouldBeChecked() {
        spamSmsCheckbox.shouldBe(Condition.checked);
        return this;
    }

    public LoginPage countdownWait() {
        $(".login__countdown").shouldNot(Condition.hidden, Duration.ofSeconds(60));
        return this;
    }

    public LoginPage sendSmsShouldBeVisible() {
        requestCodeButton.shouldBe(Condition.visible);
        return this;
    }

    public LoginPage sendSmsShouldNotBeVisible() {
        requestCodeButton.shouldNotBe(Condition.visible);
        return this;
    }


    // Получить код (inactive)
    // Запросить код повторно (active)

    //custom
    public LoginPage clearPhoneInput() {
        phoneInput.click();
        phoneInput.sendKeys(Keys.CONTROL + "a");
        phoneInput.sendKeys(Keys.BACK_SPACE);
        return this;
    }

    public LoginPage removeOneSymbolInPhoneInput() {
        phoneInput.click();
        phoneInput.sendKeys(Keys.BACK_SPACE);
        return this;
    }

}

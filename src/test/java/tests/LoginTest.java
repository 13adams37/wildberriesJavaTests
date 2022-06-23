package tests;

import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import test_data.Urls;

import static com.codeborne.selenide.Selenide.open;

public class LoginTest extends BasePage {
    public String[] phoneNumbers = {"qwertyuiop", "12qq456789", "!@#$%&*()+", "0000000000"};

    @BeforeMethod
    public void openMainPage() {
        open(Urls.MAIN_PAGE.getUrl());
    }

    @Test(description = "Пока без авторизации :(")
    public void loginTest() {
        wildberriesMainPage.goToLoginPage();
        loginPage.loginFormShouldBeVisible()
                .requestCodeButtonShouldBeInactive()
                .phoneDropdownShouldNotBeDisplayed()
                .spamSmsCheckboxShouldBeChecked()
                .validationErrorIsNotVisible()

                .requestCodeButtonClick()
                .requestCodeButtonShouldBeInactive()
                .validationErrorShouldBeDisplayed();
        for (SelenideElement code : loginPage.getPhoneCodes()) {
            loginPage.phoneDropdownClick()
                            .phoneDropdownShouldBeDisplayed();
            code.click();
            loginPage.phoneDropdownClick()
                    .phoneDropdownShouldNotBeDisplayed();
            for (String number : phoneNumbers) {
                loginPage.setPhoneNumber(number)
                        .removeOneSymbolInPhoneInput()
                        .requestCodeButtonClick()
//                                .requestCodeButtonShouldBeInactive() // js did`t update css class "disabled"
                        .validationErrorShouldBeDisplayed()
                        .clearPhoneInput();
            }
        }

        loginPage.phoneDropdownClick()
                .getPhoneCodes().first().click();
        loginPage.setPhoneNumber("0000000000")
                .requestCodeButtonShouldBeActive()
                .validationErrorIsNotVisible()
                .requestCodeButtonClick()
                .sendSmsShouldNotBeVisible()
                .countdownWait() // ожидание
                .sendSmsShouldBeVisible()
                .requestCodeButtonClick()
                .sendSmsShouldNotBeVisible();


    }
}

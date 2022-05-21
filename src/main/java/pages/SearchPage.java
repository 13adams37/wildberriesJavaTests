package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SearchPage {
    private final SelenideElement
            savinTestBook = $("#c47475063"),
            testLoc = $x("//div[@data-nm='47475063']/../a/span[@class='good-info__good-name']");


//    @Step("Найти товар и кликнуть по нему")
//    public void searchProductByNameAndClick(String productName, String productId) {
//        //Найдём товар и кликнем по нему
//    }
}

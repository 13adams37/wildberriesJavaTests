package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.SelenideWait;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;


public class ProductPage {
    private final SelenideElement
            addToCartButton = $x("//button[@class='btn-main']"),
            goToCartButton = $x("//a[@class='btn-base j-go-to-basket']"),
            productBrandName = $x("//span[@data-link='text{:product^brandName}']"),
            productGoodsName = $x("//span[@data-link='text{:product^goodsName}']"),
            productPrice = $x("//span[@class='price-block__final-price']"),
            productSellerName = $x("//a[@class='seller-details__title seller-details__title--link']"),
            productAddedSuccessfulPopup = $x("//p[@class='action-notification show']");

    public String[] productDetails;

    @Step("Добавить товар в корзину")
    public void addProductToBasket() {
        addToCartButton.click();
        productAddedSuccessfulPopup.shouldBe(Condition.visible, Condition.exist);
        productAddedSuccessfulPopup.shouldHave(Condition.exactText("Товар успешно добавлен в корзину")); // ????
        productAddedSuccessfulPopup.shouldNotBe(Condition.visible, Duration.ofSeconds(5));
    }

    @Step("Перейти в корзину с кнопки страницы товара")
    public void goToBasket() {
        goToCartButton.shouldBe(Condition.visible).click();
    }

    @Step("Сохранить детали товара")
    public void getProductDetails() {
        String brandName = productBrandName.text();
        String goodsName = productGoodsName.text();
        String price = productPrice.text();
        String sellerName = productSellerName.text();
        productDetails = new String[]{brandName, goodsName, price, sellerName};
    }

    @Step("Проверка замены кнопки 'Добавить в корзину' на 'Перейти в корзину'")
    public void checkAddCartButtonReplaced() {
        goToCartButton.shouldBe(Condition.visible);
        addToCartButton.shouldNotBe(Condition.visible);
    }

}

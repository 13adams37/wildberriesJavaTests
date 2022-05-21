package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;


public class ProductPage {
    private final SelenideElement
            addToCartButton = $x("//button[@class='btn-main']"),
            goToCartButton = $x("//a[@class='btn-base j-go-to-basket']"),
            productBrandName = $x("//span[@data-link='text{:product^brandName}']"),
            productGoodsName = $x("//span[@data-link='text{:product^goodsName}']"),
            productPrice = $x("//span[@class='price-block__final-price']"),
            productSellerName = $x("//a[@class='seller-details__title seller-details__title--link']");

    public String[] productDetails;

    @Step("Добавить товар в корзину")
    public void addProductToBasket() {
        addToCartButton.click();
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

}

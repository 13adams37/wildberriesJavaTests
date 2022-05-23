package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import test_data.Urls;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public class ProductTest extends BasePage {
    String productId = "47475063";

    @BeforeMethod
    public void openMainPage() {
        open(Urls.MAIN_PAGE.getUrl());
    }

    @Test(description = "Проверка делатей товара в корзине")
    public void addProductToCartByIdCheck() {
        wildberriesMainPage.searchProduct(productId);
        productPage.addProductToBasket();
        productPage.getProductDetails();
        productPage.goToBasket();
        basketPage.assertProductDetailsInBasket(productId, productPage.productDetails);
        basketPage.deleteProduct(productId);
        basketPage.checkBasketEmptiness();
    }

    @Test(description = "Изменения количества товара")
    public void changeProductAmountCheck() {
        wildberriesMainPage.searchProduct(productId);
        productPage.addProductToBasket();
        productPage.goToBasket();
        basketPage.addProductAmount(productId,6);
        basketPage.reduceProductAmount(productId, 2);
        basketPage.setProductAmount(productId, "10");
        Assert.assertEquals(basketPage.getProductAmount(productId),"10");
    }
}

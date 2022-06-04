package tests;

import io.qameta.allure.TmsLink;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import test_data.Urls;

import static com.codeborne.selenide.Selenide.open;

public class ProductTest extends BasePage {
    String productId = "47475063";

    @BeforeMethod
    public void openMainPage() {
        open(Urls.MAIN_PAGE.getUrl());
    }

    @TmsLink("https://app.qase.io/case/WBP-12")
    @Test(description = "Добавление товара в корзину и изменение количества")
    public void productTest() {
        wildberriesMainPage.searchProduct(productId);

        productPage.addProductToBasket();
        productPage.checkAddCartButtonReplaced();
        productPage.getProductDetails();

        productPage.goToBasket();
        basketPage.assertProductDetailsInBasket(productId, productPage.productDetails);

        basketPage.reduceAmountWhenInactive(productId);

        basketPage.clickOnProductAmount(productId, "Add", 1);

        basketPage.clickOnProductAmount(productId, "Reduce", 1);
        basketPage.reduceAmountWhenInactive(productId);
        // price check

        basketPage.setProductAmount(productId, "10");
        Assert.assertEquals(basketPage.getProductAmount(productId),"10");
        // price check

        basketPage.setProductAmount(productId, "2");
        // price?

        basketPage.setProductAmount(productId, "2q");

        basketPage.setProductAmount(productId, "q2");

        basketPage.clickOnProductAmount(productId, "Add", 1);
        basketPage.catchInvalidDataPopup();

        basketPage.clickOnProductAmount(productId, "Reduce", 1);
        basketPage.catchInvalidDataPopup();
        Assert.assertEquals(basketPage.getProductAmount(productId), "NaN");

        basketPage.deleteProduct(productId);
        basketPage.checkBasketEmptiness();
    }
}

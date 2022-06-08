package tests;

import io.qameta.allure.TmsLink;
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
        productPage.addProductToBasket()
                .checkAddCartButtonReplaced()
                .getProductDetails()
                .goToBasket();
        basketPage.assertProductDetailsInBasket(productId, productPage.productDetails)
                .reduceAmountWhenInactive(productId)
                .clickOnProductAmount(productId, "Add", 1)
                .clickOnProductAmount(productId, "Reduce", 1)
                .reduceAmountWhenInactive(productId)
                .setProductAmount(productId, "10")
                .checkProductAmount(basketPage.getProductAmount(productId),"10")
                .setProductAmount(productId, "2")
                .setProductAmount(productId, "2q")
                .setProductAmount(productId, "q2")
                .clickOnProductAmount(productId, "Add", 1)
                .catchInvalidDataPopup()
                .clickOnProductAmount(productId, "Reduce", 1)
                .catchInvalidDataPopup()
                .checkProductAmount(basketPage.getProductAmount(productId),"NaN")
                .deleteProduct(productId)
                .checkBasketEmptiness();
    }
}

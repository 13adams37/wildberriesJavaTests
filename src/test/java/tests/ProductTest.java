package tests;

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

    @Test()
    public void addProductToCartByIdCheck() {
        wildberriesMainPage.searchProduct(productId);
        productPage.addProductToBasket();
        productPage.getProductDetails();
        productPage.goToBasket();
        basketPage.assertProductDetailsInBasket(productId, productPage.productDetails);
    }
}

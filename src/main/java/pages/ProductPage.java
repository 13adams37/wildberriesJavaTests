package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;

import java.util.Collection;

import static com.codeborne.selenide.Selenide.$;
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

    public void addProductToBasket() {
        addToCartButton.click();
    }

    public void goToBasket() {
        goToCartButton.shouldBe(Condition.visible).click();
    }

    public void getProductDetails() {
        String brandName = productBrandName.text();
        String goodsName = productGoodsName.text();
        String price = productPrice.text();
        String sellerName = productSellerName.text();
        productDetails = new String[]{brandName, goodsName, price, sellerName};
    }

//    public void assertProductDetails(String productId) {
//        String cartProductBrandName = $x("//div[@data-nm='"+ productId +"']/../a/span[@class='good-info__good-name']").text();
//        String cartProductGoodsName = $x("//div[@data-nm='"+ productId +"']/../a/span[@class='good-info__good-brand']").text();
//        String cartProductPrice = $x("//div[@data-nm='"+ productId +"']/../../../div[@class=\"list-item__price\"]/div[1]']").text();
//        String cartProductSeller = $x("//div[@data-nm='"+ productId +"']/div/div/div/span[@class=\"seller__name seller__name--short\"]").text();
//
//        Assert.assertEquals(cartProductBrandName, productDetails[0]);
//        Assert.assertEquals(cartProductGoodsName, productDetails[1]);
//        Assert.assertEquals(cartProductPrice, productDetails[2]);
//        Assert.assertEquals(cartProductSeller, productDetails[3]);
//    }
}

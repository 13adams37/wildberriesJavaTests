package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$x;

public class BasketPage {

    @Step("Проверка детелай товара в корзине")
    public void assertProductDetailsInBasket(String productId, String[] productDetails) {
        String cartProductBrandName = $x("//div[@data-nm='" + productId + "']/../a/span[@class='good-info__good-brand']").text();
        String cartProductGoodsName = $x("//div[@data-nm='" + productId + "']/../a/span[@class='good-info__good-name']").text();
        SelenideElement cartProductPrice = $x("//div[@data-nm='" + productId + "']/../../../div[@class='list-item__price']/div[1]");
        String cartProductSeller = $x("//div[@data-nm='" + productId + "']/div/div/div/span[@class='seller__name seller__name--short']").text();

        cartProductPrice.shouldBe(Condition.exactText(productDetails[2]));

        Assert.assertEquals(cartProductBrandName, productDetails[0]);
        Assert.assertEquals(cartProductGoodsName.substring(0, cartProductGoodsName.length() - 1), productDetails[1]);
        Assert.assertEquals(cartProductPrice.text(), productDetails[2]);
        Assert.assertEquals(cartProductSeller, productDetails[3]);
    }
}

package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$x;

public class BasketPage {

    private final SelenideElement
            emptyBasket = $x("//div[@class='basket-page__basket-empty basket-empty']"),
            emptyBasketText = $x("//div[@class='basket-page__basket-empty basket-empty']/h1");

    @Step("Проверка детелай товара '{productId}' в корзине")
    public void assertProductDetailsInBasket(String productId, String[] productDetails) {
        String cartProductBrandName = $x("//div[@data-nm='" + productId + "']/../a/span[@class='good-info__good-brand']").text();
        String cartProductGoodsName = $x("//div[@data-nm='" + productId + "']/../a/span[@class='good-info__good-name']").text();
        SelenideElement cartProductPrice = $x("//div[@data-nm='" + productId + "']/../../../div[@class='list-item__price']/div[1]");
        String cartProductSeller = $x("//div[@data-nm='" + productId + "']/div/div/div/span[@class='seller__name seller__name--short']").text();

        cartProductPrice.shouldBe(Condition.exactText(productDetails[2])); // Задержка анимации

        Assert.assertEquals(cartProductBrandName, productDetails[0]);
        Assert.assertEquals(cartProductGoodsName.substring(0, cartProductGoodsName.length() - 1), productDetails[1]);
        Assert.assertEquals(cartProductPrice.text(), productDetails[2]);
        Assert.assertEquals(cartProductSeller, productDetails[3]);
    }

    @Step("Удаление товара '{productId}'")
    public void deleteProduct(String productId) {
        SelenideElement deleteItem = $x("//div[@data-nm='" + productId + "']/../../../div[@class='list-item__count count']/div[@class='list-item__btn btn ']/button[2]");
        deleteItem.hover().click();
    }

    @Step("Проверка пустоты корзины")
    public void checkBasketEmptiness(){
        emptyBasket.shouldBe(Condition.visible);
        emptyBasketText.shouldBe(Condition.exactText("В корзине пока ничего нет"));
    }

    @Step("Увеличение количества товара '{productId}' на '{changeTo}' в корзине")
    public void addProductAmount(String productId, int changeTo) {
        SelenideElement addMoreButton = $x("//div[@data-nm='" + productId + "']/../../../div[@class='list-item__count count']/div/div/button[2]");
        for(int i=1;i != changeTo;i++) {
            addMoreButton.click();
        }
    }

    @Step("Уменьшение количества товара '{productId}' на '{changeTo}' в корзине")
    public void reduceProductAmount(String productId, int changeTo) {
        SelenideElement reduceButton = $x("//div[@data-nm='" + productId + "']/../../../div[@class='list-item__count count']/div/div/button[1]"),
                amountText = $x("//div[@data-nm='" + productId + "']/../../../div[@class='list-item__count count']/div/div/input");
        for(int i = Integer.parseInt(Objects.requireNonNull(amountText.getAttribute("value"))); i != changeTo; i--) {
            reduceButton.click();
        }
    }

    @Step("Установить количество товара '{productId} на значение '{changeTo}''")
    public void setProductAmount(String productId, String changeTo) {
        SelenideElement amountText = $x("//div[@data-nm='" + productId + "']/../../../div[@class='list-item__count count']/div/div/input");
        amountText.click();
        amountText.sendKeys(Keys.CONTROL + "a");
        amountText.sendKeys(Keys.BACK_SPACE);
        amountText.sendKeys(changeTo);
        amountText.pressTab();
    }

    @Step("Получить количество товара '{productId}'")
    public String getProductAmount(String productId) {
//        return Integer.parseInt(Objects.requireNonNull($x("//div[@data-nm='" + productId + "']/../../../div[@class='list-item__count count']/div/div/input").getAttribute("value")));
        return $x("//div[@data-nm='" + productId + "']/../../../div[@class='list-item__count count']/div/div/input").getAttribute("value");
    }
}

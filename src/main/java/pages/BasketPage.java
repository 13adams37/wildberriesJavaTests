package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import test_data.Product;


import static com.codeborne.selenide.Selenide.$x;

public class BasketPage {

    private final SelenideElement
            totalPriceText = $x("//p[@class='b-top__total line']/span/span[@data-link='{formatMoneyAnim totalPriceWithCouponPersDiscAndDeliveryPrice}']"),
            productsFinalPrice = $x("//div[@data-link='{formatMoneyAnim (priceSumWithCouponAndDiscount)}']"),
            popupAlertInvalidData = $x("//div[@class='popup-alert shown centered']"),
            emptyBasket = $x("//div[@class='basket-page__basket-empty basket-empty']"),
            emptyBasketText = $x("//div[@class='basket-page__basket-empty basket-empty']/h1");

    public SelenideElement getReduceButton(String productId) {
        return $x("//div[@data-nm='" + productId + "']/../../../div[@class='list-item__count count']/div/div/button[1]");
    }

    public SelenideElement getAddButton(String productId) {
        return $x("//div[@data-nm='" + productId + "']/../../../div[@class='list-item__count count']/div/div/button[2]");
    }

    public SelenideElement getAmountInputArea(String productId) {
        return $x("//div[@data-nm='" + productId + "']/../../../div[@class='list-item__count count']/div/div/input");
    }

    public SelenideElement getProductPrice(String productId) {
        return $x("//div[@data-nm='" + productId + "']/../../../div[@class='list-item__price']/div[1]");
    }

    public SelenideElement getDeleteButton(String productId) {
        return $x("//div[@data-nm='" + productId + "']/../../../div[@class='list-item__count count']/div[@class='list-item__btn btn ']/button[2]");
    }

    @Step("Проверка отображения алерта об ошибке входных данных")
    public BasketPage catchInvalidDataPopup() {
        popupAlertInvalidData.shouldBe(Condition.visible);
        popupAlertInvalidData.lastChild().click();
        popupAlertInvalidData.shouldNotBe(Condition.visible);
        return this;
    }

    @Step("Проверка детелай товара '{productId}' в корзине")
    public BasketPage assertProductDetailsInBasket(String productId, Product product) {
        String cartProductBrandName = $x("//div[@data-nm='" + productId + "']/../a/span[@class='good-info__good-brand']").text();
        String cartProductGoodsName = $x("//div[@data-nm='" + productId + "']/../a/span[@class='good-info__good-name']").text();
        String cartProductSeller = $x("//div[@data-nm='" + productId + "']/div/div/div/span[@class='seller__name seller__name--short']").text();
        String cartProductPicture = $x("//div[@data-nm='" + productId + "']/../../a/img").getAttribute("src");

        getProductPrice(productId).shouldBe(Condition.exactText(product.getPrice())); // Задержка анимации

        Assert.assertEquals(cartProductBrandName, product.getGoodsBrand());
        Assert.assertEquals(cartProductGoodsName.substring(0, cartProductGoodsName.length() - 1), product.getGoodsName());
        Assert.assertEquals(getProductPrice(productId).text(), product.getPrice());
        Assert.assertEquals(cartProductSeller, product.getSeller());
        Assert.assertEquals(cartProductPicture, product.getPicture());
        return this;
    }

    @Step("Удаление товара '{productId}'")
    public BasketPage deleteProduct(String productId) {
        getDeleteButton(productId).hover().click();
        return this;
    }

    @Step("Проверка пустоты корзины")
    public BasketPage checkBasketEmptiness() {
        emptyBasket.shouldBe(Condition.visible);
        emptyBasketText.shouldBe(Condition.exactText("В корзине пока ничего нет"));
        return this;
    }

    @Step("Нажатие кнопки '{typeOfAction}' в количестве '{numberOfClicks}' у товара '{productId}'")
    public BasketPage clickOnProductAmount(String productId, String typeOfAction, int numberOfClicks) {
        SelenideElement button;
        if (typeOfAction.equals("Add")) {
            button = getAddButton(productId);
        } else {
            button = getReduceButton(productId);
        }
        for (int i = 0; i != numberOfClicks; i++) {
            button.click();
        }
        return this;
    }

    @Step("Установить количество товара '{productId} на значение '{changeTo}''")
    public BasketPage setProductAmount(String productId, String changeTo) {
        getAmountInputArea(productId).click();
        getAmountInputArea(productId).sendKeys(Keys.CONTROL + "a");
        getAmountInputArea(productId).sendKeys(Keys.BACK_SPACE);
        getAmountInputArea(productId).sendKeys(changeTo);
        getAmountInputArea(productId).pressTab();
        return this;
    }

    @Step("Получить количество товара '{productId}'")
    public String getProductAmount(String productId) {
        return getAmountInputArea(productId).getAttribute("value");
    }

    @Step("Проверка неактивности кнопки уменьшения количества товара '{productId}'")
    public BasketPage reduceAmountWhenInactive(String productId) {
        String count = getProductAmount(productId);
//            Element: '<button class="count__minus minus disabled" data-link="class{merge: quantity <= minQuantity toggle='disabled'}" type="button"></button>'
//            Actual value: enabled
        getReduceButton(productId).shouldBe(Condition.cssClass("disabled")).click();
        Assert.assertEquals(getProductAmount(productId), count);
        return this;
    }

    public BasketPage checkProductAmount(String Actual, String Expected) {
        Assert.assertEquals(Actual, Expected);
        return this;
    }
}

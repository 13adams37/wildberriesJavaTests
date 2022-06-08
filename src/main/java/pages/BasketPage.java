package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.testng.Assert;


import static com.codeborne.selenide.Selenide.$x;

public class BasketPage {

    private final SelenideElement
            totalPriceText = $x("//p[@class='b-top__total line']/span/span[@data-link='{formatMoneyAnim totalPriceWithCouponPersDiscAndDeliveryPrice}']"),
            productsFinalPrice = $x("//div[@data-link='{formatMoneyAnim (priceSumWithCouponAndDiscount)}']"),
            popupAlertInvalidData = $x("//div[@class='popup-alert shown centered']"),
            emptyBasket = $x("//div[@class='basket-page__basket-empty basket-empty']"),
            emptyBasketText = $x("//div[@class='basket-page__basket-empty basket-empty']/h1");

    @Step("Проверка отображения алерта об ошибке входных данных")
    public BasketPage catchInvalidDataPopup() {
        popupAlertInvalidData.shouldBe(Condition.visible);
        popupAlertInvalidData.lastChild().click();
        popupAlertInvalidData.shouldNotBe(Condition.visible);
        return this;
    }

    @Step("Проверка детелай товара '{productId}' в корзине")
    public BasketPage assertProductDetailsInBasket(String productId, String[] productDetails) {
        String cartProductBrandName = $x("//div[@data-nm='" + productId + "']/../a/span[@class='good-info__good-brand']").text();
        String cartProductGoodsName = $x("//div[@data-nm='" + productId + "']/../a/span[@class='good-info__good-name']").text();
        SelenideElement cartProductPrice = $x("//div[@data-nm='" + productId + "']/../../../div[@class='list-item__price']/div[1]");
        String cartProductSeller = $x("//div[@data-nm='" + productId + "']/div/div/div/span[@class='seller__name seller__name--short']").text();

        cartProductPrice.shouldBe(Condition.exactText(productDetails[2])); // Задержка анимации

        Assert.assertEquals(cartProductBrandName, productDetails[0]);
        Assert.assertEquals(cartProductGoodsName.substring(0, cartProductGoodsName.length() - 1), productDetails[1]);
        Assert.assertEquals(cartProductPrice.text(), productDetails[2]);
        Assert.assertEquals(cartProductSeller, productDetails[3]);
        return this;
    }

    @Step("Удаление товара '{productId}'")
    public BasketPage deleteProduct(String productId) {
        SelenideElement deleteItem = $x("//div[@data-nm='" + productId + "']/../../../div[@class='list-item__count count']/div[@class='list-item__btn btn ']/button[2]");
        deleteItem.hover().click();
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
        // 2 = plus, 1 = minus
        if (typeOfAction.equals("Add")) {
            typeOfAction = "count__plus plus";
        } else typeOfAction = "count__minus minus";
        SelenideElement button = $x("//div[@data-nm='" + productId + "']/../../../div[@class='list-item__count count']/div/div/button[@class='" + typeOfAction + "']");
        for (int i = 0; i != numberOfClicks; i++) {
            button.click();
        }
        return this;
    }

    @Step("Установить количество товара '{productId} на значение '{changeTo}''")
    public BasketPage setProductAmount(String productId, String changeTo) {
        SelenideElement amountText = $x("//div[@data-nm='" + productId + "']/../../../div[@class='list-item__count count']/div/div/input");
        amountText.click();
        amountText.sendKeys(Keys.CONTROL + "a");
        amountText.sendKeys(Keys.BACK_SPACE);
        amountText.sendKeys(changeTo);
        amountText.pressTab();
        return this;
    }

    @Step("Получить количество товара '{productId}'")
    public String getProductAmount(String productId) {
        return $x("//div[@data-nm='" + productId + "']/../../../div[@class='list-item__count count']/div/div/input").getAttribute("value");
    }

    @Step("Проверка неактивности кнопки уменьшения количества товара '{productId}'")
    public BasketPage reduceAmountWhenInactive(String productId) {
        SelenideElement reduceButton = $x("//div[@data-nm='" + productId + "']/../../../div[@class='list-item__count count']/div/div/button[1]");
        String count = getProductAmount(productId);
//        reduceButton.shouldBe(Condition.disabled);
//        Element should disabled {By.xpath: //div[@data-nm='47475063']/../../../div[@class='list-item__count count']/div/div/button[1]}
//            Element: '<button class="count__minus minus disabled" data-link="class{merge: quantity <= minQuantity toggle='disabled'}" type="button"></button>'
//            Actual value: enabled
        reduceButton.shouldBe(Condition.cssClass("disabled"));
        reduceButton.click();
        Assert.assertEquals(getProductAmount(productId), count);
        return this;
    }

    public BasketPage checkProductAmount(String n1, String n2) {
        Assert.assertEquals(n1,n2);
        return this;
    }
}

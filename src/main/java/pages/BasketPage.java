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
    public void catchInvalidDataPopup() {
        popupAlertInvalidData.shouldBe(Condition.visible);
        popupAlertInvalidData.lastChild().click();
        popupAlertInvalidData.shouldNotBe(Condition.visible);
    }

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
    public void checkBasketEmptiness() {
        emptyBasket.shouldBe(Condition.visible);
        emptyBasketText.shouldBe(Condition.exactText("В корзине пока ничего нет"));
    }

    @Step("Нажатие кнопки '{typeOfAction}' в количестве '{numberOfClicks}' у товара '{productId}'")
    public void clickOnProductAmount(String productId, String typeOfAction, int numberOfClicks) {
        // 2 = plus, 1 = minus
        if (typeOfAction.equals("Add")) {
            typeOfAction = "count__plus plus";
        } else typeOfAction = "count__minus minus";
        SelenideElement button = $x("//div[@data-nm='" + productId + "']/../../../div[@class='list-item__count count']/div/div/button[@class='" + typeOfAction + "']");
        for (int i = 0; i != numberOfClicks; i++) {
            button.click();
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
        return $x("//div[@data-nm='" + productId + "']/../../../div[@class='list-item__count count']/div/div/input").getAttribute("value");
    }

    @Step("Проверка неактивности кнопки уменьшения количества товара '{productId}'")
    public void reduceAmountWhenInactive(String productId) {
        SelenideElement reduceButton = $x("//div[@data-nm='" + productId + "']/../../../div[@class='list-item__count count']/div/div/button[1]");
        String count = getProductAmount(productId);
//        reduceButton.shouldBe(Condition.disabled);
//        Element should disabled {By.xpath: //div[@data-nm='47475063']/../../../div[@class='list-item__count count']/div/div/button[1]}
//            Element: '<button class="count__minus minus disabled" data-link="class{merge: quantity <= minQuantity toggle='disabled'}" type="button"></button>'
//            Actual value: enabled
        reduceButton.shouldBe(Condition.cssClass("disabled"));
        reduceButton.click();
        Assert.assertEquals(getProductAmount(productId), count);
    }
}

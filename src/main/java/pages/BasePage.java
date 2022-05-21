package pages;

import com.codeborne.selenide.SelenideElement;
import config.RunnerConfig;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static com.codeborne.selenide.Selenide.$x;

public class BasePage {

    public static SelenideElement
            basketButton = $x("//a[@data-wba-header-name='Cart']");
    private final RunnerConfig config = new RunnerConfig();
    public WildberriesMainPage wildberriesMainPage = new WildberriesMainPage();
    public SearchPage searchPage = new SearchPage();
    public ProductPage productPage = new ProductPage();
    public BasketPage basketPage = new BasketPage();

    @BeforeClass
    @Parameters({"browser", "browserVersion"})
    public void setUp(@Optional("Chrome") String browser, @Optional String browserVersion) {
        config.setUpConfig(browser, browserVersion);
    }
}

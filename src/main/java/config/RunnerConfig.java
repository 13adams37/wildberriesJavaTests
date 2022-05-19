package config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import logger.CustomLogger;

public class RunnerConfig {

    @Step
    public void setUpConfig(String browser, String browserVersion) {
        boolean modeDebug = true;

        Configuration.pageLoadTimeout = 20000;
        Configuration.timeout = 10000;
        Configuration.downloadsFolder = "target/build/downloads";
        Configuration.reportsFolder = "target/screenshots";
        Configuration.browserSize = "1920x1080";
        Configuration.browser = browser;
        if (browserVersion != null) {
            Configuration.browserVersion = browserVersion;
        }
        if (!modeDebug) {
            Configuration.remote = "http://localhost:4444/wd/hub";
            Configuration.browserCapabilities.setCapability("enableVNC", false);
            Configuration.browserCapabilities.setCapability("enableVideo", false);
        }
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
        CustomLogger.logger.info("ok");
    }
}

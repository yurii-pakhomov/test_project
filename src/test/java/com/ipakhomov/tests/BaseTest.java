package com.ipakhomov.tests;

import com.ipakhomov.utils.ConfigFileReader;
import com.ipakhomov.utils.WebDriverMode;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

@Slf4j
public class BaseTest {
    WebDriver driver;
    WebDriverManager wdm;
    ConfigFileReader configFile;
    WebDriverMode webDriverMode;

    @BeforeTest
    protected void setup() {
        configFile = new ConfigFileReader();
        webDriverMode = configFile.getWebDriverMode();
        switch (webDriverMode) {
            case LOCAL:
                getChromeDriverInLocal();
                System.out.println("Chrome driver was created in local");
                log.info("Chrome driver was created in local");
                break;
            case DOCKER:
                getChromeDriverInDocker();
                System.out.println("Chrome driver was created in local");
                log.info("Chrome driver was created in docker");
                break;
            default:
                throw new IllegalArgumentException("unknown webdriver mode");
        }
    }

    @AfterTest
    protected void quit() {
        if (driver != null) {
            switch (webDriverMode) {
                case LOCAL:
                    driver.quit();
                    break;
                case DOCKER:
                    wdm.quit();
                    break;
            }
        }
    }

    private void getChromeDriverInLocal() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=en-US");
        driver = new ChromeDriver(options);
    }

    private void getChromeDriverInDocker() {
        wdm = WebDriverManager.chromedriver().browserInDocker().enableVnc();
        driver = wdm.create();
    }
}

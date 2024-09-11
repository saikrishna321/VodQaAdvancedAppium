package com.appium.ai;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.Setting;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class ElementLookupTest {

    private AppiumDriverLocalService service;
    private IOSDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() throws IOException {
//        service = AppiumDriverLocalService.buildService(
//                new AppiumServiceBuilder().withAppiumJS(
//                        new File("/Users/sekars/workspace/node_modules/appium/build/lib/main.js")));
//        service.start();

        if (service == null || !service.isRunning()) {
            throw new RuntimeException("An appium server node is not started!");
        }
        XCUITestOptions options = new XCUITestOptions();
        options.setPlatformName("iOS");
        options.setPlatformVersion("12.2");
        options.setDeviceName("iPhone X");
        options.setAutomationName(AutomationName.IOS_XCUI_TEST);
        options.setApp(System.getProperty("user.dir") + "/apps/vodqa.zip");
        options.setBundleId("com.gbaldera.ShoppingCartExample");
        options.setUseJSONSource(true);
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);

//         HashMap<String, String> customFindModules = new HashMap<>();
//         customFindModules.put("ai", "test-ai-classifier");

//         caps.setCapability("customFindModules", customFindModules);
// //        caps.setCapability("testaiObjectDetectionThreshold", "0.6");
//         caps.setCapability("shouldUseCompactResponses", false);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testFindElementUsingAI() {
//        driver.setSetting(Setting.CHECK_IMAGE_ELEMENT_STALENESS, false);
        driver.findElement(AppiumBy.custom("ai:cart")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.accessibilityId("Browse"))).click();
    }

    @AfterClass
    public void tearDown() {
        try {
            driver.quit();
            if (service != null) {
                service.stop();
            }
        } catch (Exception ign) {}
    }
}

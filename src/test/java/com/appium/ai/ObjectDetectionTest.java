package com.appium.ai;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.Setting;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class ObjectDetectionTest {

    private IOSDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() throws IOException {
        XCUITestOptions options = new XCUITestOptions();
        options.setPlatformName("iOS");
        options.setPlatformVersion("12.2");
        options.setDeviceName("iPhone X");
        options.setAutomationName(AutomationName.IOS_XCUI_TEST);
        options.setBundleId("com.apple.DocumentsApp");

        HashMap<String, String> customFindModules = new HashMap<>();
        customFindModules.put("ai", "test-ai-classifier");

        options.setCapability("customFindModules", customFindModules);
        options.setCapability("testaiFindMode", "object_detection");
        options.setCapability("testaiObjectDetectionThreshold", "0.9");
        options.setCapability("shouldUseCompactResponses", false);

        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void objectDetectionTest() {
        driver.setSetting(Setting.CHECK_IMAGE_ELEMENT_STALENESS, false);

        wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.accessibilityId("Browse"))).click();

        driver.findElement(AppiumBy.custom("ai:clock")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.accessibilityId("No Recents")));
    }

    @AfterMethod
    public void tearDown() {
        try {
            driver.quit();
        } catch (Exception ign) {}
    }

}

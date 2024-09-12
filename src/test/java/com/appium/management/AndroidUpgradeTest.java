package com.appium.management;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class AndroidUpgradeTest {
    private String APP_PKG = "io.cloudgrey.the_app";
    private String APP_ACT = "com.theapp.MainActivity";

    private String APP_V1_0_0 = "https://github.com/cloudgrey-io/the-app/releases/download/v1.0.0/TheApp-v1.0.0.apk";
    private String APP_V1_0_1 = "https://github.com/cloudgrey-io/the-app/releases/download/v1.0.1/TheApp-v1.0.1.apk";
    private String APP_V1_0_2 = "https://github.com/cloudgrey-io/the-app/releases/download/v1.0.2/TheApp-v1.0.2.apk";


    private String TEST_MESSAGE = "Hello World";

    private By msgInput = AppiumBy.accessibilityId("messageInput");
    private By savedMsg = AppiumBy.accessibilityId(TEST_MESSAGE);
    private By saveMsgBtn = AppiumBy.accessibilityId("messageSaveBtn");
    private By echoBox = AppiumBy.accessibilityId("Echo Box");

    @Test
    public void testSavedTextAfterUpgrade () throws IOException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
//        capabilities.setCapability("avd", "Pixel");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("app", APP_V1_0_0);

        // change this to APP_V1_0_1 to experience a failing scenario
        String appUpgradeVersion = APP_V1_0_2;

        // Open the app.
        AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(echoBox)).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(msgInput)).sendKeys(TEST_MESSAGE);
            wait.until(ExpectedConditions.presenceOfElementLocated(saveMsgBtn)).click();
            String savedText = wait.until(ExpectedConditions.presenceOfElementLocated(savedMsg)).getText();
            Assert.assertEquals(savedText, TEST_MESSAGE);

            driver.installApp(appUpgradeVersion);
            Map<String, Object> params = new HashMap<>();
            params.put("appPackage", APP_PKG);
            params.put("appActivity", APP_ACT);
            driver.executeScript("mobile: startActivity", params);
            wait.until(ExpectedConditions.presenceOfElementLocated(echoBox)).click();
            savedText = wait.until(ExpectedConditions.presenceOfElementLocated(savedMsg)).getText();
            Assert.assertEquals(savedText, TEST_MESSAGE);
        } finally {
            driver.quit();
        }
    }
}

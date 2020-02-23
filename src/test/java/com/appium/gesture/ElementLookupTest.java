package com.appium.gesture;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.Setting;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
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
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.2");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone X");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
//        caps.setCapability(IOSMobileCapabilityType.USE_PREBUILT_WDA, true);
//        caps.setCapability("bundleId", "com.apple.mobileslideshow");
        caps.setCapability("bundleId", "com.gbaldera.ShoppingCartExample");

        HashMap<String, String> customFindModules = new HashMap<>();
        customFindModules.put("ai", "test-ai-classifier");

        caps.setCapability("customFindModules", customFindModules);
//        caps.setCapability("testaiObjectDetectionThreshold", "0.6");
        caps.setCapability("shouldUseCompactResponses", false);

        driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void testFindElementUsingAI() {
//        driver.setSetting(Setting.CHECK_IMAGE_ELEMENT_STALENESS, false);
        driver.findElement(MobileBy.custom("ai:cart")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(
                MobileBy.AccessibilityId("Browse"))).click();
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

package com.appium.gesture;

import io.appium.java_client.MobileBy;
import io.appium.java_client.Setting;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class ObjectDetectionTest {

    private IOSDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() throws IOException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.2");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Xs");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        caps.setCapability(IOSMobileCapabilityType.USE_PREBUILT_WDA, true);
        caps.setCapability("bundleId", "com.apple.DocumentsApp");

        HashMap<String, String> customFindModules = new HashMap<>();
        customFindModules.put("ai", "test-ai-classifier");

        caps.setCapability("customFindModules", customFindModules);
        caps.setCapability("testaiFindMode", "object_detection");
        caps.setCapability("testaiObjectDetectionThreshold", "0.9");
        caps.setCapability("shouldUseCompactResponses", false);

        driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void objectDetectionTest() {
        driver.setSetting(Setting.CHECK_IMAGE_ELEMENT_STALENESS, false);

        wait.until(ExpectedConditions.presenceOfElementLocated(
                MobileBy.AccessibilityId("Browse"))).click();

        driver.findElement(MobileBy.custom("ai:clock")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(
                MobileBy.AccessibilityId("No Recents")));
    }

    @AfterMethod
    public void tearDown() {
        try {
            driver.quit();
        } catch (Exception ign) {}
    }

}

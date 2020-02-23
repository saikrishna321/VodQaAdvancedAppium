package com.appium.gesture;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;

import static org.junit.Assert.assertNotNull;

public class IOSMobileCommandsTest {

    private IOSDriver driver;
    private WebDriverWait wait;
    private HashMap<String, String> args;
    private String SETTINGS_BUNDLE_ID = "com.apple.Preferences";
    private String BUNDLE_ID = "com.rarcher.PicSearch";

    private By button = MobileBy.iOSNsPredicateString("name MATCHES 'Show Picture'");
    private By image = MobileBy.className("XCUIElementTypeImage");
    private File traceZip = new File(System.getProperty("user.dir") + "/trace.zip");

    @Before
    public void setUp() throws IOException {
        args = new HashMap<>();
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability("platformVersion", "12.2");
        caps.setCapability("deviceName", "iPhone Xs");
        caps.setCapability("noReset", true);
        caps.setCapability(IOSMobileCapabilityType.BUNDLE_ID, BUNDLE_ID);
        caps.setCapability(IOSMobileCapabilityType.USE_PREBUILT_WDA, true);

        driver = new IOSDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), caps);
        wait  = new WebDriverWait(driver, 20);
    }

    @Test
    public void askSiriToShowNewPictureInPicturesApp() {
        wait.until(ExpectedConditions.presenceOfElementLocated(button)).click();
        args.put("text", "Hey Siri Show Picture");
        driver.executeScript("mobile: siriCommand", args);
        wait.until(ExpectedConditions.presenceOfElementLocated(button));
        wait.until(ExpectedConditions.presenceOfElementLocated(image));
        assertNotNull(wait.until(ExpectedConditions.presenceOfElementLocated(image)).getText());
    }

    @Test
    public void switchToSettingsAppAndValidateShortcuts() {
        args.put("bundleId", SETTINGS_BUNDLE_ID);
        driver.executeScript("mobile: launchApp", args);
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Siri"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("My Shortcuts"))).click();
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("PicSearch, “Show Picture”")));
        assertNotNull(element.getText());
        args.clear();
        args.put("bundleId", BUNDLE_ID);
        driver.executeScript("mobile: launchApp", args);
        wait.until(ExpectedConditions.presenceOfElementLocated(button)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(button)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(button)).click();
    }

    @Test
    public void measurePerformanceForPicturesApp() throws IOException {
        args.put("timeout", "60000");
        args.put("pid", "current");
        args.put("profileName", "Time Profiler");
        driver.executeScript("mobile: startPerfRecord", args);
        wait.until(ExpectedConditions.presenceOfElementLocated(button)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(button)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(button)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(button)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(button)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(button)).click();
        args = new HashMap<>();
        args.put("profileName", "Time Profiler");
        String b64Zip = (String)driver.executeScript("mobile: stopPerfRecord", args);
        byte[] bytesZip = Base64.getMimeDecoder().decode(b64Zip);
        FileOutputStream stream = new FileOutputStream(traceZip);
        stream.write(bytesZip);
    }

    @After
    public void tearDown() {
        try {
            driver.quit();
        } catch (Exception ign) {}
    }
}

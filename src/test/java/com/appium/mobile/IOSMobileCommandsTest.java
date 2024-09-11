package com.appium.mobile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Base64;
import java.util.HashMap;

import static org.junit.Assert.assertNotNull;

public class IOSMobileCommandsTest {

    private IOSDriver driver;
    private WebDriverWait wait;
    private HashMap<String, String> args;
    private String SETTINGS_BUNDLE_ID = "com.apple.Preferences";
    private String BUNDLE_ID = "com.rarcher.PicSearch";

    private By button = AppiumBy.iOSNsPredicateString("name MATCHES 'Show Picture'");
    private By image = AppiumBy.className("XCUIElementTypeImage");
    private File traceZip = new File(System.getProperty("user.dir") + "/trace.zip");

    @Before
    public void setUp() throws IOException {
        args = new HashMap<>();
        XCUITestOptions options = new XCUITestOptions();
        options.setPlatformName("iOS");
        options.setPlatformVersion("12.2");
        options.setDeviceName("iPhone Xs");
        options.setNoReset(true);
        options.setBundleId(BUNDLE_ID);
        options.setUsePrebuiltWda(true);

        driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), options);
        wait  = new WebDriverWait(driver, Duration.ofSeconds(20));
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
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Siri"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("My Shortcuts"))).click();
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("PicSearch, “Show Picture”")));
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

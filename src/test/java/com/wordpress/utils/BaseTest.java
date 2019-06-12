package com.wordpress.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by saikrisv on 12/29/16.
 */
public class BaseTest {

    private AppiumDriverLocalService service;
    public AppiumDriver<MobileElement> driver;
    public WebDriverWait wait;

    @BeforeClass
    public void beforeClass() throws Exception {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();

        if (service == null || !service.isRunning()) {
            throw new RuntimeException("An appium server node is not started!");
        }
        iosCaps();
    }

    @AfterClass
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
        if (service != null) {
            service.stop();
        }
    }

    private void androidCaps() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 900000);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,AutomationName.ESPRESSO);
        //capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, ".ui.accounts.SignInActivity");
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/sekars/workspace/VodQaAdvancedAppium/VodQA.apk");
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    private  void iosCaps() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.2");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Xs");
        //sometimes environment has performance problems
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 700000);
        capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/vodqa.zip");
        driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        System.out.println("test");
    }

    public void login() {
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.
            elementToBeClickable(MobileBy.AccessibilityId("login"))).click();
    }
}

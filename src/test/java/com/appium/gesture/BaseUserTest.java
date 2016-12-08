package com.appium.gesture;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

/**
 * Created by saikrisv on 07/12/16.
 */
public class BaseUserTest {
    private static AppiumDriverLocalService service;
    protected static AppiumDriver<MobileElement> driver;

    /**
     * initialization.
     */
    @BeforeClass
    public static void beforeClass() throws Exception {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();

        if (service == null || !service.isRunning()) {
            throw new AppiumServerHasNotBeenStartedLocallyException(
                    "An appium server node is not started!");
        }

        androidCaps();
    }

    private static void androidCaps() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/saikrisv/IdeaProjects/VodQA2017Appium/app-debug-unaligned.apk");
        driver = new AndroidDriver<>(service.getUrl(), capabilities);
    }

    private static void iosCaps(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.3");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
        //sometimes environment has performance problems
        capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 700000);
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/saikrisv/IdeaProjects/VodQA2017Appium/VodQAReactNative.app");
        driver = new IOSDriver<>(service.getUrl(), capabilities);
    }

    /**
     * finishing.
     */
    @AfterClass
    public static void afterClass() {
        if (driver != null) {
            driver.quit();
        }
        if (service != null) {
            service.stop();
        }
    }
}

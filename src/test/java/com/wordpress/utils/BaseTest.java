package com.wordpress.utils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.net.ServerSocket;
import java.time.Duration;

public class BaseTest {

    private AppiumDriverLocalService service;
    public AppiumDriver driver;
    public WebDriverWait wait;

    @BeforeClass
    public void beforeClass() throws Exception {
        service = new AppiumServiceBuilder().withArgument(GeneralServerFlag.BASEPATH, "/wd/hub")
                .withArgument(GeneralServerFlag.USE_PLUGINS, "gestures")
                .usingAnyFreePort().build();
        service.start();

        if (service == null || !service.isRunning()) {
            throw new RuntimeException("An appium server node is not started!");
        }
        if(System.getenv("platform").equalsIgnoreCase("android")) {
            androidCaps();
        } else {
            iosCaps();
        }

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

    private void androidCaps() throws IOException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setDeviceName("Android Emulator");
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setApp(System.getProperty("user.dir") + "/apps/VodQA.apk");
        driver = new AndroidDriver(service.getUrl(), options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    private  void iosCaps() throws IOException {
        XCUITestOptions options = new XCUITestOptions();
        options.setPlatformName("iOS");
        options.setDeviceName("iPhone 12");
        options.setPlatformVersion("14.5");
        options.setWdaLocalPort(new ServerSocket(0).getLocalPort());
        options.setAutomationName(AutomationName.IOS_XCUI_TEST);
        options.setApp(System.getProperty("user.dir") + "/apps/vodqa.zip");
        driver = new IOSDriver(service.getUrl(), options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void login() {
        wait.until(ExpectedConditions.
            elementToBeClickable(AppiumBy.accessibilityId("login"))).click();
    }
}

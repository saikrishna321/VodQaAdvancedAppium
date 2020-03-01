package com.appium.mobile;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.serverevents.CustomEvent;
import io.appium.java_client.serverevents.ServerEvents;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class iOSInAppAuthenticationTest {

    private IOSDriver driver;
    private WebDriverWait wait;

    private By loginButton = MobileBy.AccessibilityId("Log In");
    private By logoutButton = MobileBy.AccessibilityId("Log Out");

    @BeforeClass
    public void setUp() throws IOException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.3");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 11 Pro Max");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 700000);
        capabilities.setCapability(IOSMobileCapabilityType.USE_PREBUILT_WDA, true);
        capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/apps/BiometricLogin.app");
        driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        wait  = new WebDriverWait(driver, 20);
    }

    @Test
    public void inAppAuthenticationTest() {
        CustomEvent logEvent = new CustomEvent();

        driver.executeScript("mobile:enrollBiometric", ImmutableMap.of("isEnabled", true));
        logEvent.setVendor("VodQA");
        logEvent.setEventName("onLoginScreen");
        driver.logEvent(logEvent);
        wait.until(ExpectedConditions.presenceOfElementLocated(loginButton)).click();
        logEvent.setEventName("loggedIn");
        driver.switchTo().alert().accept();
        driver.logEvent(logEvent);

        driver.executeScript("mobile:sendBiometricMatch", ImmutableMap.of("type", "faceId", "match", true));

        wait.until(ExpectedConditions.presenceOfElementLocated(logoutButton)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(loginButton)).click();

        driver.executeScript("mobile:sendBiometricMatch", ImmutableMap.of("type", "faceId", "match", false));

        wait.until(ExpectedConditions.alertIsPresent());
    }

    @SneakyThrows
    @AfterClass
    public void afterClass() {
        if (driver != null) {
            CustomEvent customEvent = new CustomEvent();
            customEvent.setVendor("VodQA");
            customEvent.setEventName("event ends here");
            driver.logEvent(customEvent);
            ServerEvents events = driver.getEvents();
            events.save(new File(System.getProperty("user.dir") + "/eventFlow.json").toPath());
            driver.quit();
        }
    }
}

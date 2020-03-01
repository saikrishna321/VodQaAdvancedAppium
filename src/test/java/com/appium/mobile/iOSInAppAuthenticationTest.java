package com.appium.mobile;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.3");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 11 Pro Max");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 700000);
//        capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.zaimramlan.BiometricLogin");
        capabilities.setCapability(IOSMobileCapabilityType.USE_PREBUILT_WDA, true);
        capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/BiometricLogin.app");
        driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        wait  = new WebDriverWait(driver, 20);
    }

    @Test
    public void inAppAuthenticationTest() {
        driver.executeScript("mobile:enrollBiometric", ImmutableMap.of("isEnabled", true));
        wait.until(ExpectedConditions.presenceOfElementLocated(loginButton)).click();

        driver.switchTo().alert().accept();

        driver.executeScript("mobile:sendBiometricMatch", ImmutableMap.of("type", "faceId", "match", true));

        wait.until(ExpectedConditions.presenceOfElementLocated(logoutButton)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(loginButton)).click();

        driver.executeScript("mobile:sendBiometricMatch", ImmutableMap.of("type", "faceId", "match", false));

        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
    }

    @AfterClass
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }
}

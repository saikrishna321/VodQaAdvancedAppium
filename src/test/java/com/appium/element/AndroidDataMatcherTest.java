package com.appium.element;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidDataMatcherTest {

    @Test
    public void dataMatcherTest() throws  MalformedURLException {
        AppiumDriver driver;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 900000);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ESPRESSO);
        capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/apps/ApiDemos.apk");
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.findElementByAccessibilityId("Views").click();

        JSONObject orderJSON = new JSONObject();
        JSONArray objects = new JSONArray();
        objects.put("title");
        objects.put("TextClock");
        orderJSON.put("name", "hasEntry");
        orderJSON.put("args", objects);
        ((AndroidDriver) driver).findElementByAndroidDataMatcher(orderJSON.toString()).click();
    }


    @Test
    public void dataMatcher1Test() throws  MalformedURLException {
        AppiumDriver driver;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 900000);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ESPRESSO);
        capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/apps/ApiDemos.apk");
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.findElementByAccessibilityId("Views").click();

        JSONObject orderJSON = new JSONObject();
        JSONArray objects = new JSONArray();
        objects.put("title");
        objects.put("TextClock");
        orderJSON.put("name", "hasEntry");
        orderJSON.put("args", objects);
        ((AndroidDriver) driver).findElementByAndroidDataMatcher(orderJSON.toString()).click();
    }
}

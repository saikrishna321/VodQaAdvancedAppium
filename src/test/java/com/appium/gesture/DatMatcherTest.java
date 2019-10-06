package com.appium.gesture;

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

public class DatMatcherTest {

    @Test
    public void dataMatcnher() throws  MalformedURLException {
        AppiumDriver driver;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 900000);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ESPRESSO);
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/saikrisv/git/java-client/src/test/java/io/appium/java_client/ApiDemos-debug.apk");
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
    public void dataMatcher1() throws  MalformedURLException {
        AppiumDriver driver;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 900000);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ESPRESSO);
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/saikrisv/git/android-testing/ui/espresso/DataAdapterSample/app/build/outputs/apk/debug/app-debug.apk");
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        JSONObject orderJSON = new JSONObject();
        JSONArray objects = new JSONArray();
        objects.put("title");
        objects.put("TextClock");
        orderJSON.put("name", "hasEntry");
        orderJSON.put("args", objects);
        ((AndroidDriver) driver).findElementByAndroidDataMatcher(orderJSON.toString()).click();
    }
}

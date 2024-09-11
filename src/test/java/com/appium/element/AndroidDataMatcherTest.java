package com.appium.element;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidDataMatcherTest {

    @Test
    public void dataMatcherTest() throws MalformedURLException {
        AppiumDriver driver;
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("Android Emulator")
                .setAutomationName("UiAutomator2")
                .setApp(System.getProperty("user.dir") + "/apps/ApiDemos.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
        driver.findElement(AppiumBy.accessibilityId("Views")).click();

        JSONObject orderJSON = new JSONObject();
        JSONArray objects = new JSONArray();
        objects.put("title");
        objects.put("TextClock");
        orderJSON.put("name", "hasEntry");
        orderJSON.put("args", objects);
        ((AndroidDriver) driver).findElement(AppiumBy.androidDataMatcher(orderJSON.toString())).click();
    }

    @Test
    public void dataMatcher1Test() throws MalformedURLException {
        AppiumDriver driver;
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("Android Emulator")
                .setAutomationName("UiAutomator2")
                .setApp(System.getProperty("user.dir") + "/apps/ApiDemos.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
        driver.findElement(AppiumBy.accessibilityId("Views")).click();

        JSONObject orderJSON = new JSONObject();
        JSONArray objects = new JSONArray();
        objects.put("title");
        objects.put("TextClock");
        orderJSON.put("name", "hasEntry");
        orderJSON.put("args", objects);
        ((AndroidDriver) driver).findElement(AppiumBy.androidDataMatcher(orderJSON.toString())).click();
    }
}

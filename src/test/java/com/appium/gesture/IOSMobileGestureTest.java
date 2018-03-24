package com.appium.gesture;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class IOSMobileGestureTest extends BaseUserTest {

    @Test
    public void SwipeTest() throws InterruptedException {
        login();
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("verticalSwipe")));
        driver.findElementByAccessibilityId("verticalSwipe").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("listview")));
        MobileElement listview = driver.findElementByAccessibilityId("listview");

        Map<String, Object> params = new HashMap<>();
        params.put("direction", "up");
        params.put("element", listview.getId());
        driver.executeScript("mobile: swipe", params);
        await().atLeast(5, TimeUnit.SECONDS);
    }

    @Test
    public void scrollTest() throws InterruptedException {
        login();
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("verticalSwipe")));
        driver.findElementByAccessibilityId("verticalSwipe").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("listview")));

        Map<String, Object> params = new HashMap<>();
        params.put("direction", "down");
        driver.executeScript("mobile: scroll", params);
        await().atLeast(5, TimeUnit.SECONDS);
    }

    @Test
    public void doubleTapTest() throws InterruptedException {
        login();
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("doubleTap")));
        driver.findElementByAccessibilityId("doubleTap").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("doubleTapMe")));
        MobileElement doubleTapMe = driver.findElementByAccessibilityId("doubleTapMe");

        Map<String, Object> params = new HashMap<>();
        params.put("element", doubleTapMe.getId());
        driver.executeScript("mobile: doubleTap", params);
        await().atLeast(5, TimeUnit.SECONDS);
    }

    @Test
    public void touchAndHoldTest() throws InterruptedException {
        login();
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("longPress")));
        driver.findElementByAccessibilityId("longPress").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("longpress")));
        MobileElement longpress = driver.findElementByAccessibilityId("longpress");

        Map<String, Object> params = new HashMap<>();
        params.put("element", longpress.getId());
        params.put("duration", 2.0);
        driver.executeScript("mobile: touchAndHold", params);
        await().atLeast(5, TimeUnit.SECONDS);
    }

    private void login() {
        wait.until(ExpectedConditions.
                elementToBeClickable(MobileBy.AccessibilityId("login"))).click();
    }
}

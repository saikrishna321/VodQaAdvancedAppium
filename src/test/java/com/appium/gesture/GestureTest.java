package com.appium.gesture;

import io.appium.java_client.*;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;

public class GestureTest extends BaseUserTest {

    @Test
    public void horizontalSwipingTest() throws Exception {
        login();
        driver.findElementByAccessibilityId("slider1").click();
        MobileElement slider = driver.findElementByAccessibilityId("slider");

        Point source = slider.getLocation();

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragNDrop = new Sequence(finger, 1);
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), source.x, source.y));
        dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
        dragNDrop.addAction(new Pause(finger, Duration.ofMillis(600)));
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(600),
                PointerInput.Origin.viewport(),
                source.x + 400, source.y));
        dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
        driver.perform(Arrays.asList(dragNDrop));
    }


    @Test
    public void verticalSwipeTest() throws InterruptedException {
        login();
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("verticalSwipe")));
        driver.findElementByAccessibilityId("verticalSwipe").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("listview")));
        verticalSwipe("listview");
    }

    @Test
    public void dragAndDrop() throws InterruptedException {
        login();
        Thread.sleep(5000);
        driver.findElementByAccessibilityId("dragAndDrop").click();
        MobileElement dragMe = (MobileElement) new WebDriverWait(driver, 30).until(ExpectedConditions
                .elementToBeClickable(MobileBy.AccessibilityId("dragMe")));
        Point source = dragMe.getCenter();
        Point target = driver.findElementByAccessibilityId("dropzone").getCenter();
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragNDrop = new Sequence(finger, 1);
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), source.x, source.y));
        dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
        dragNDrop.addAction(new Pause(finger, Duration.ofMillis(600)));
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(600),
                PointerInput.Origin.viewport(),
                target.x, target.y));
        dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
        driver.perform(Arrays.asList(dragNDrop));
    }

    @Test
    public void pinchAndZoom() throws InterruptedException {
        login();
        Thread.sleep(5000);
        driver.findElementByAccessibilityId("photoView").click();
        Thread.sleep(3000);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");

        Dimension size = driver.manage().window().getSize();
        Point source = new Point(size.getWidth(), size.getHeight());

        Sequence pinchAndZoom1 = new Sequence(finger, 0);
        pinchAndZoom1.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), source.x / 2, source.y / 2));
        pinchAndZoom1.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        pinchAndZoom1.addAction(finger.createPointerMove(Duration.ofMillis(10000),
                PointerInput.Origin.viewport(), source.x / 3, source.y / 3));
        pinchAndZoom1.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));


        Sequence pinchAndZoom2 = new Sequence(finger2, 0);
        pinchAndZoom2.addAction(finger2.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), source.x / 2, source.y / 2));
        pinchAndZoom2.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        pinchAndZoom2.addAction(finger2.createPointerMove(Duration.ofMillis(10000),
                PointerInput.Origin.viewport(), source.x * 3 / 4, source.y * 3 / 4));
        pinchAndZoom2.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        
        driver.perform(Arrays.asList(pinchAndZoom1,pinchAndZoom2));
    }

    @Test
    public void longPress() throws InterruptedException {
        login();
        Thread.sleep(5000);
        driver.findElementByAccessibilityId("longPress").click();
        MobileElement longpress = (MobileElement) new WebDriverWait(driver, 30).
                until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("longpress")));
        new Actions(driver).clickAndHold(longpress).perform();
        Thread.sleep(5000);
    }


    @Test
    public void doubleTap() throws InterruptedException {
        login();
        Thread.sleep(5000);
        driver.findElementByAccessibilityId("doubleTap").click();
        MobileElement element = (MobileElement) new WebDriverWait(driver, 30).
                until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("doubleTapMe")));
        Thread.sleep(1000);
        Point source = element.getCenter();
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger1");
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), source.x, source.y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, Duration.ofMillis(100)));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(tap));
        Thread.sleep(5000);
    }


    private void verticalSwipe(String locator) throws InterruptedException {
        Thread.sleep(3000);
        MobileElement slider = driver.findElementByAccessibilityId(locator);
        Point source = slider.getCenter();
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragNDrop = new Sequence(finger, 1);
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(),
                source.x / 2, source.y + 400));
        dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(600),
                PointerInput.Origin.viewport(), source.getX() / 2, source.y / 2));
        dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
        driver.perform(Arrays.asList(dragNDrop));
    }

    @Test
    public void multiTouchTest() throws InterruptedException {
        login();
        wait.until(ExpectedConditions.
                elementToBeClickable(MobileBy.AccessibilityId("slider1"))).click();
        Thread.sleep(3000);
        MobileElement slider = driver.findElementByAccessibilityId("slider");
        MobileElement slider1 = driver.findElementByAccessibilityId("slider1");

        Dimension sizeSlider = slider.getSize();
        Dimension sizeSlider1 = slider1.getSize();
        TouchAction touchAction1 =
                new TouchAction(driver).press(ElementOption.element(slider, 0, sizeSlider.height / 2))
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                        .moveTo(ElementOption.element(slider, sizeSlider.width / 2, sizeSlider.height / 2));
        TouchAction touchAction2 =
                new TouchAction(driver).press(ElementOption.element(slider1, 0, sizeSlider1.height / 2)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                        .moveTo(ElementOption.element(slider1, sizeSlider1.width / 2, sizeSlider1.height / 2));
        new MultiTouchAction(driver).add(touchAction1).add(touchAction2).perform();
        Thread.sleep(2000);
    }
}

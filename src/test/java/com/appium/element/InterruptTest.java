package com.appium.element;

import com.wordpress.utils.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.Arrays;

public class InterruptTest extends BaseTest {

    @Test
    public void simulateSMSAndSlideTest() {
        login();
        ((AndroidDriver)driver).sendSMS("555-555-5555",
                "Your code is 654321");
        wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.accessibilityId("slider1"))).click();
        slideUsingSlider();
    }

    public void slideUsingSlider() {
        WebElement slider = (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("slider")));

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
}

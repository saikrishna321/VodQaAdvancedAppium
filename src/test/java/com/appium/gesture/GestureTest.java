package com.appium.gesture;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

/**
 * Created by saikrisv on 07/12/16.
 */
public class GestureTest extends BaseUserTest {

    private void login() {
        new WebDriverWait(driver,30).until(ExpectedConditions.
                elementToBeClickable(MobileBy.AccessibilityId("login"))).click();
    }

    @Test
    public void dragAndDrop() throws InterruptedException {
        login();
        driver.findElementByAccessibilityId("dragAndDrop").click();
        MobileElement dragMe = (MobileElement) new WebDriverWait(driver, 30).until(ExpectedConditions
                .elementToBeClickable(MobileBy.AccessibilityId("dragMe")));
        new TouchAction(driver).press(dragMe).waitAction(3000)
                .moveTo(driver.findElementByAccessibilityId("dropzone")).release().perform();
    }


    public void longPress() throws InterruptedException {
        login();
        Thread.sleep(5000);
        driver.findElementByAccessibilityId("longPress").click();
        MobileElement longpress = (MobileElement) new WebDriverWait(driver, 30).
                until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("longpress")));
        new TouchAction(driver).longPress(longpress,3000).perform();
    }




    private void verticalSwipe(String locator) throws InterruptedException {
        Thread.sleep(3000);
        MobileElement slider = driver.findElementByAccessibilityId(locator);
        Dimension size = slider.getSize();
        Dimension dimensions = driver.manage().window().getSize();
        Double screenHeightStart = dimensions.getHeight() * 0.5;
        int scrollStart = screenHeightStart.intValue();
        Double screenHeightEnd = dimensions.getHeight() * 0.30;
        int scrollEnd = screenHeightEnd.intValue();

         driver.swipe(0,scrollStart,0,scrollEnd,2000);
        TouchAction swipe = new TouchAction(driver).press(0, scrollStart)
            .waitAction(2000).moveTo( 0, scrollEnd).release();
        swipe.perform();
    }


     public void zoom() throws InterruptedException {
        MobileElement zoom = driver.findElementByClassName("android.widget.ImageView");;
        Dimension size = zoom.getSize();
        System.out.println("****SIZE" + size);
        TouchAction touchAction1 =
            new TouchAction(driver).press(size.getWidth() / 2, size.getHeight() / 2)
                .waitAction(3000).moveTo(size.getWidth() / 2 + 5, size.getHeight() / 2 + 5)
                .release();
        TouchAction touchAction2 =
            new TouchAction(driver).press(size.getWidth() / 2 - 5, size.getHeight() / 2 - 5 )
                .waitAction(3000).moveTo(size.getWidth() , size.getHeight()  )
                .release();
        new MultiTouchAction(driver).add(touchAction1).add(touchAction2).perform();
    }
}

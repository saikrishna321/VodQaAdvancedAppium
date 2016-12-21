package com.appium.gesture;

import io.appium.java_client.*;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.Dimension;

/**
 * Created by saikrisv on 07/12/16.
 */
public class GestureTest extends BaseUserTest {

    @Test public void horizontalSwipingTest() throws Exception {
        MobileElement slider = driver.findElementByAccessibilityId("slider");
        Dimension size = slider.getSize();

        TouchAction swipe = new TouchAction(driver).press(slider, 0, size.height / 2).waitAction(2000)
            .moveTo(slider, size.width / 2, size.height / 2).release();
        swipe.perform();
    }


    @Test
    public void veriticalSwipe() {
        MobileElement slider = driver.findElementByAccessibilityId("listview");
        Dimension size = slider.getSize();

        TouchAction swipe = new TouchAction(driver).press(slider, size.width / 2, size.height - 20)
            .waitAction(2000).moveTo(slider,size.width / 2, size.height / 2 + 20).release();
        swipe.perform();


    }
    @Test public void dragAndDrop() throws InterruptedException {
        MobileElement drag = driver.findElementByAccessibilityId("dragMe");
        new TouchAction(driver).press(drag).waitAction(3000)
            .moveTo(driver.findElementByAccessibilityId("dropzone")).release().perform();
    }

    @Test public void zoom() throws InterruptedException {
        MobileElement zoom = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAElement[4]/UIAScrollView[1]/UIAImage[2]");
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

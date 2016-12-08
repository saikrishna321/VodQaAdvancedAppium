package com.appium.gesture;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.ios.IOSTouchAction;
import org.junit.Test;

/**
 * Created by saikrisv on 07/12/16.
 */
public class GestureTest extends BaseUserTest{


    @Test public void horizontalSwipingTest() throws Exception {
        Thread.sleep(5000);
        MobileElement slider = driver.findElementByAccessibilityId("slider");

        AndroidTouchAction touchAction = new AndroidTouchAction(driver);
        touchAction.swipe(slider, SwipeElementDirection.RIGHT, 0 ,
                slider.getSize().getWidth() / 2, 2000).perform();

        AndroidTouchAction touchAction2 = new AndroidTouchAction(driver);
        touchAction2.swipe(slider, SwipeElementDirection.LEFT, slider.getSize().getWidth() / 2,
                0, 2000).perform();
    }


    @Test public void iosTest() throws InterruptedException {
        Thread.sleep(5000);
        MobileElement slider = driver.findElementByAccessibilityId("slider");

        IOSTouchAction touchAction = new IOSTouchAction(driver);
        touchAction.swipe(slider, SwipeElementDirection.RIGHT, 0 ,
                slider.getSize().getWidth() / 2, 2000).perform();

        IOSTouchAction touchAction2 = new IOSTouchAction(driver);
        touchAction2.swipe(slider, SwipeElementDirection.LEFT, slider.getSize().getWidth() / 2,
                0, 2000).perform();
    }

    @Test public void zoomTest() throws InterruptedException {
        Thread.sleep(5000);
        MobileElement e = driver.findElementById("photo");
        new MultiTouchAction(driver).zoom(e).perform();
        Thread.sleep(1000);
    }

    @Test public void zoomAndroidTest() throws InterruptedException {
        Thread.sleep(5000);
        MobileElement e = driver.findElement(MobileBy.AccessibilityId("photo"));
        e.zoom();
        e.pinch();
        Thread.sleep(5000);
    }

}

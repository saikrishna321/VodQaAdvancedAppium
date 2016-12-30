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

    @Test
    public void horizontalSwipingTest() throws Exception {
        login();
        driver.findElementByAccessibilityId("slider1").click();
        MobileElement slider = driver.findElementByAccessibilityId("slider");
        Dimension size = slider.getSize();

        TouchAction swipe = new TouchAction(driver).press(slider, 0, size.height / 2).waitAction(2000)
            .moveTo(slider, size.width / 2, size.height / 2).release();
        swipe.perform();
    }

    private void login() {
        new WebDriverWait(driver,30).until(ExpectedConditions.
                elementToBeClickable(MobileBy.AccessibilityId("login"))).click();
    }

    @Test
    public void veriticalSwipeTest() throws InterruptedException {
        login();
        driver.findElementByAccessibilityId("verticalSwipe").click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        verticalSwipe("listview");
    }

    @Test
    public void dragAndDrop() throws InterruptedException {
        login();
        Thread.sleep(5000);
        driver.findElementByAccessibilityId("dragAndDrop").click();
        MobileElement dragMe = (MobileElement) new WebDriverWait(driver, 30).until(ExpectedConditions
                .elementToBeClickable(MobileBy.AccessibilityId("dragMe")));
        new TouchAction(driver).press(dragMe).waitAction(3000)
                .moveTo(driver.findElementByAccessibilityId("dropzone")).release().perform();
    }

    @Test
    public void longPress() throws InterruptedException {
        login();
        Thread.sleep(5000);
        driver.findElementByAccessibilityId("longPress").click();
        MobileElement longpress = (MobileElement) new WebDriverWait(driver, 30).
                until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("longpress")));
        new TouchAction(driver).longPress(longpress,3000).perform();
    }




    @Test //Will work only on XCUI mode as doubleTap is not supported by IOS/Android
    public void doubleTap() throws InterruptedException {
        login();
        Thread.sleep(5000);
        driver.findElementByAccessibilityId("doubleTap").click();
        MobileElement doubleTap = (MobileElement) new WebDriverWait(driver, 30).
                until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("doubleTapMe")));
        //new IOSTouchAction(driver).doubleTap(doubleTap).perform();
        Thread.sleep(5000);
    }


    private void verticalSwipe(String locator) throws InterruptedException {
        Thread.sleep(3000);
        MobileElement slider = driver.findElementByAccessibilityId(locator);
        Dimension size = slider.getSize();

        TouchAction swipe = new TouchAction(driver).press(slider, size.width / 2, size.height - 20)
            .waitAction(2000).moveTo(slider,size.width / 2, size.height / 2 + 50).release();
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

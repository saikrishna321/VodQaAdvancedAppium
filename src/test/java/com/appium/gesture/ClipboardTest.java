package com.appium.gesture;

import com.wordpress.utils.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.clipboard.HasClipboard;
import org.testng.annotations.Test;

public class ClipboardTest extends BaseTest {

    @Test
    public void sendSMSandClipBoard() throws InterruptedException {
        ((AndroidDriver) driver).sendSMS("555-555-5555", "Your message here!");
        Thread.sleep(5000);
        ((AndroidDriver<MobileElement>) driver).openNotifications();
        Thread.sleep(5000);
        String textFromSMS = driver.findElementById("android:id/big_text").getText();
        ((HasClipboard) driver).setClipboardText(textFromSMS);
        String clipboardText = ((HasClipboard) driver).getClipboardText();
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
        driver.findElementByAccessibilityId("username").clear();

        //driver.findElementByAccessibilityId("username").setValue(clipboardText);
        Thread.sleep(5000);
    }
}

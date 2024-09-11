package com.appium.element;

import com.wordpress.utils.BaseTest;
import io.appium.java_client.AppiumBy;
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
        ((AndroidDriver) driver).openNotifications();
        Thread.sleep(5000);
        String textFromSMS = driver.findElement(AppiumBy.id("android:id/message_text")).getText();
        ((HasClipboard) driver).setClipboardText(textFromSMS);
        String clipboardText = ((HasClipboard) driver).getClipboardText();
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
        driver.findElement(AppiumBy.accessibilityId("username")).clear();

        driver.findElement(AppiumBy.accessibilityId("username")).sendKeys(clipboardText);
        Thread.sleep(5000);
    }
}

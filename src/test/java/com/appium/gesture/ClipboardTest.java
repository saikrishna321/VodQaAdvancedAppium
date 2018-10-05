package com.appium.gesture;

import io.appium.java_client.clipboard.HasClipboard;
import org.testng.annotations.Test;

public class ClipboardTest extends BaseUserTest{

    @Test
    public void clipBoard() {
        String text = "Selenium";
        ((HasClipboard)driver).setClipboardText(text);
        driver.findElementByAccessibilityId("username").clear();
        driver.findElementByAccessibilityId("username").setValue(text);
    }
}

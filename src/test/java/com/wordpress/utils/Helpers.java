package com.wordpress.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by saikrisv on 12/29/16.
 */
public class Helpers {

    @Autowired
    AppiumDriver driver;

    WebDriverWait webDriverWait;

    public MobileElement waitForElement(MobileElement by) {
        webDriverWait = new WebDriverWait(driver, 15);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
        return by;
    }
}

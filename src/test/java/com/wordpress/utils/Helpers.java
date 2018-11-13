package com.wordpress.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by saikrisv on 12/29/16.
 */
@Component
public class Helpers {

    @Autowired
    AppiumDriver<MobileElement> driver;

    WebDriverWait webDriverWait;

    public Helpers(AppiumDriver driver) {
        webDriverWait = new WebDriverWait(driver,15);
    }

    public MobileElement waitForElement(MobileElement by){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
        return by;
    }
}

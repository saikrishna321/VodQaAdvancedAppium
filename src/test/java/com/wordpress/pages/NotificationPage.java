package com.wordpress.pages;

import com.wordpress.pageobjects.NotificationPageObjects;
import com.wordpress.utils.Helpers;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by saikrisv on 12/30/16.
 */
public class NotificationPage extends Helpers {
    NotificationPageObjects notificationPageObjects;
    public NotificationPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        notificationPageObjects = new NotificationPageObjects();
        PageFactory.initElements(new AppiumFieldDecorator(driver,15,
                TimeUnit.MILLISECONDS),notificationPageObjects);
    }


    public String getFirstNotification(){
        return waitForElement(notificationPageObjects.getNotification).getText();
    }

}

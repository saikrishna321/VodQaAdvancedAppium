package com.wordpress.pages;

import com.wordpress.pageobjects.WelcomePageObjects;
import com.wordpress.utils.Helpers;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by saikrisv on 12/29/16.
 */
public class WelcomePage extends Helpers {

    WelcomePageObjects welcomePageObjects;

    public WelcomePage(AppiumDriver<MobileElement> driver) {
        super(driver);
        welcomePageObjects = new WelcomePageObjects();
        PageFactory.initElements(new AppiumFieldDecorator(driver, 10,
                TimeUnit.MILLISECONDS), welcomePageObjects);
    }


    public StatsPage navigateToStats() {
        waitForElement(welcomePageObjects.stats).click();
        return new StatsPage(driver);
    }

    public NotificationPage navigateToNotifiction(){
        waitForElement(welcomePageObjects.notifications).click();
        return new NotificationPage(driver);
    }


}

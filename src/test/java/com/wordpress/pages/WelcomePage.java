package com.wordpress.pages;

import com.wordpress.pageobjects.WelcomePageObjects;
import com.wordpress.utils.Helpers;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
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


    public ChainViewPage navigateToChainedView() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //welcomePageObjects.chainedView.click();
        welcomePageObjects.chainedView.click();
        return new ChainViewPage(driver);
    }

}

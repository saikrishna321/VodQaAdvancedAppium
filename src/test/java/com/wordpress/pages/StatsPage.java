package com.wordpress.pages;

import com.wordpress.pageobjects.StatsPageObjects;
import com.wordpress.utils.Helpers;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by saikrisv on 12/29/16.
 */
public class StatsPage extends Helpers {

    private StatsPageObjects statsPageObjects;

    StatsPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        statsPageObjects = new StatsPageObjects();
        PageFactory.initElements(new AppiumFieldDecorator(driver,
                15, TimeUnit.MILLISECONDS), statsPageObjects);
    }

    public String getTotalComments() {
        return waitForElement(statsPageObjects.views).getText();
    }


}

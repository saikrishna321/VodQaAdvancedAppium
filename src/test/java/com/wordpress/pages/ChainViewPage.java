package com.wordpress.pages;

import com.wordpress.pageobjects.ChainViewPageObjects;
import com.wordpress.utils.Helpers;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Created by saikrisv on 12/29/16.
 */
@Component
public class ChainViewPage extends AbstractPageAndObjects {

    public String getSecondComment() {
        return helpers.waitForElement(chainViewPageObjects.views).getText();
    }

    public boolean isSecondContainerExists() {
        helpers.waitForElement(chainViewPageObjects.allPossibleView);
        return chainViewPageObjects.allPossibleView.isDisplayed();
    }

    public String getContainerText() {
        helpers.waitForElement(chainViewPageObjects.allPossibleView);
        return chainViewPageObjects.locatorUsage.getText();
    }


}

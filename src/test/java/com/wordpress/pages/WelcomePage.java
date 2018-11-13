package com.wordpress.pages;

import com.wordpress.pageobjects.WelcomePageObjects;
import com.wordpress.utils.Helpers;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
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
public class WelcomePage  extends  AbstractPageAndObjects {

    public ChainViewPage navigateToChainedView() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        welcomePageObjects.chainedView.click();
        return chainViewPage;
    }

}

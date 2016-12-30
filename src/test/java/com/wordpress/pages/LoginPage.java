package com.wordpress.pages;

import com.wordpress.pageobjects.LoginPageObjects;
import com.wordpress.utils.Helpers;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by saikrisv on 12/29/16.
 */
public class LoginPage extends Helpers{

    private LoginPageObjects loginPageObjects;

    public LoginPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        loginPageObjects = new LoginPageObjects();
        PageFactory.initElements(new AppiumFieldDecorator(driver, 10,
                TimeUnit.MILLISECONDS), loginPageObjects);
    }

    public LoginPage navigateToLoginScreen() {
        waitForElement(loginPageObjects.selfHostedSite).click();
        return this;
    }

    public LoginPage enterUserNamePassword(String username, String password, String siteUrl) {
        loginPageObjects.userName.sendKeys(username);
        loginPageObjects.passWord.sendKeys(password);
        loginPageObjects.siteUrl.sendKeys(siteUrl);
        return this;
    }

    public WelcomePage signIn(){
        loginPageObjects.signButton.click();
        return new WelcomePage(driver);
    }


}

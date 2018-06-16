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
public class LoginPage extends Helpers {

    private LoginPageObjects loginPageObjects;

    public LoginPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        loginPageObjects = new LoginPageObjects();
        PageFactory.initElements(new AppiumFieldDecorator(driver), loginPageObjects);
    }

    public LoginPage enterUserNamePassword(String username, String password) {
        waitForElement(loginPageObjects.signButton);
        clearField();
        loginPageObjects.userName.sendKeys(username);
        loginPageObjects.passWord.sendKeys(password);
        return this;
    }

    public LoginPage clearField() {
        loginPageObjects.userName.clear();
        loginPageObjects.passWord.clear();
        return this;
    }

    public WelcomePage signIn(){
        loginPageObjects.signButton.click();
        return new WelcomePage(driver);
    }


}

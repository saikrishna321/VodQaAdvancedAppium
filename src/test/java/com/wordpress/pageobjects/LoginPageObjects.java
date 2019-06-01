package com.wordpress.pageobjects;

import com.wordpress.annotation.PageObject;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.CacheLookup;


/**
 * Created by saikrisv on 12/29/16.
 */
@PageObject
public class LoginPageObjects {

    @CacheLookup
    @AndroidFindBy(accessibility = "username")
    @iOSXCUITFindBy(accessibility = "username")
    public MobileElement userName;

    @CacheLookup
    @AndroidFindBy(accessibility = "password")
    @iOSXCUITFindBy(accessibility = "password")
    public MobileElement passWord;

    @AndroidFindBy(accessibility = "login")
    @iOSXCUITFindBy(accessibility = "login")
    public MobileElement signButton;

}

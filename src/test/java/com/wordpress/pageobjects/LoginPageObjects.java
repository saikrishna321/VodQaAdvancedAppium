package com.wordpress.pageobjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.CacheLookup;


/**
 * Created by saikrisv on 12/29/16.
 */
public class LoginPageObjects {

    @CacheLookup
    @AndroidFindBy(accessibility = "username")
    public MobileElement userName;

    @CacheLookup
    @AndroidFindBy(accessibility = "password")
    public MobileElement passWord;

    @AndroidFindBy(accessibility = "login")
    public MobileElement signButton;

}

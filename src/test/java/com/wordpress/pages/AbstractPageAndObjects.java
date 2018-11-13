package com.wordpress.pages;

import com.wordpress.pageobjects.ChainViewPageObjects;
import com.wordpress.pageobjects.LoginPageObjects;
import com.wordpress.pageobjects.WelcomePageObjects;
import com.wordpress.utils.Helpers;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.springframework.beans.factory.annotation.Autowired;

abstract class AbstractPageAndObjects {

    @Autowired
    AppiumDriver<MobileElement> driver;

    @Autowired
    LoginPageObjects loginPageObjects;

    @Autowired
    WelcomePage welcomePage;

    @Autowired
    Helpers helpers;

    @Autowired
    WelcomePageObjects welcomePageObjects;

    @Autowired
    ChainViewPage chainViewPage;


    @Autowired
    ChainViewPageObjects chainViewPageObjects;

}

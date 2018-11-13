package com.wordpress.utils;

import com.appium.gesture.BaseUserTest;
import com.wordpress.pageobjects.LoginPageObjects;
import com.wordpress.pageobjects.WelcomePageObjects;
import com.wordpress.pages.ChainViewPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

@Configuration
@ContextConfiguration(classes = {BeanPageObjects.class, BaseUserTest.class})
public class BeanPage {

    @Autowired
    AppiumDriver driver;

    @Autowired
    private LoginPageObjects loginPageObjects;

    @Autowired
    private WelcomePageObjects welcomePageObjects;

    @Autowired
    private ChainViewPage chainViewPage;

    @Bean
    public void initPageElements() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), loginPageObjects);
        PageFactory.initElements(new AppiumFieldDecorator(driver), welcomePageObjects);
        PageFactory.initElements(new AppiumFieldDecorator(driver), chainViewPage);
    }
}

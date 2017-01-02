package com.wordpress.pageobjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * Created by saikrisv on 12/29/16.
 */
public class WelcomePageObjects {

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"chainedView\")")
    public MobileElement chainedView;

}

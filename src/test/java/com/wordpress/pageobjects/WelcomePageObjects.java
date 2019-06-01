package com.wordpress.pageobjects;

import com.wordpress.annotation.PageObject;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

/**
 * Created by saikrisv on 12/29/16.
 */
@PageObject
public class WelcomePageObjects {

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"chainedView\")")
    @iOSXCUITFindBy(accessibility = "chainedView")
    public MobileElement chainedView;

}

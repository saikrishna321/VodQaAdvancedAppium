package com.wordpress.pageobjects;

import com.wordpress.annotation.PageObject;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.HowToUseLocators;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import static io.appium.java_client.pagefactory.LocatorGroupStrategy.ALL_POSSIBLE;

/**
 * Created by saikrisv on 12/29/16.
 */
@PageObject
public class   ChainViewPageObjects {

    @AndroidFindBy(className = "android.widget.ScrollView")
    @AndroidFindBy(accessibility = "container2")
    @AndroidFindBy(accessibility = "textView")
    @iOSXCUITFindBy(accessibility = "container2")
    @iOSXCUITFindBy(accessibility = "textView")
    public MobileElement views;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @AndroidFindBy(className = "fakeID1")
    @AndroidFindBy(accessibility = "fakeID2")
    @AndroidFindBy(accessibility = "container2")
    @iOSXCUITFindBy(className = "fakeID1")
    @iOSXCUITFindBy(accessibility = "fakeID2")
    @iOSXCUITFindBy(accessibility = "container2")
    public MobileElement allPossibleView;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @AndroidFindBy(className = "fakeID1")
    @AndroidFindBy(accessibility = "fakeID2")
    @AndroidFindBy(accessibility = "container2")
    @iOSXCUITFindBy(className = "fakeID1")
    @iOSXCUITFindBy(accessibility = "fakeID2")
    @iOSXCUITFindBy(iOSNsPredicate = "label contains 'View one'")
    @iOSXCUITFindBy(className = "fakeID1")
    @iOSXCUITFindBy(accessibility = "fakeID2")
    public MobileElement locatorUsage;

    }



/**
 * ((AndroidDriver) driver).
 * findElementByAndroidUIAutomator("new UiSelector().resourceId(\"org.wordpress.android:id/stats_latest_post_tabs\")").
 * findElementByAndroidUIAutomator(("new UiSelector().resourceId(\"org.wordpress.android:id/stats_visitors_and_views_tab_inner_container\").enabled(true).instance(2)")).
 * findElement(MobileBy.id("stats_visitors_and_views_tab_value")).getText()
 *
 */

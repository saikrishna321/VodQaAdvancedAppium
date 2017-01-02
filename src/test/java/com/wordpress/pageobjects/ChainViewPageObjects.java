package com.wordpress.pageobjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.HowToUseLocators;
import io.appium.java_client.pagefactory.iOSFindBy;

import static io.appium.java_client.pagefactory.LocatorGroupStrategy.ALL_POSSIBLE;
import static io.appium.java_client.pagefactory.LocatorGroupStrategy.CHAIN;

/**
 * Created by saikrisv on 12/29/16.
 */
public class ChainViewPageObjects {

    @AndroidFindBy(className = "android.widget.ScrollView")
    @AndroidFindBy(accessibility = "container2")
    @AndroidFindBy(accessibility = "textView")

    @iOSFindBy(accessibility = "container2")
    @iOSFindBy(accessibility = "textView")
    public MobileElement views;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE)
    @AndroidFindBy(className = "fakeID1")
    @AndroidFindBy(accessibility = "fakeID2")
    @AndroidFindBy(accessibility = "container2")

    @iOSFindBy(className = "fakeID1")
    @iOSFindBy(accessibility = "fakeID2")
    @iOSFindBy(accessibility = "container2")
    public MobileElement allPossibleView;

    }



/**
 * ((AndroidDriver) driver).
 * findElementByAndroidUIAutomator("new UiSelector().resourceId(\"org.wordpress.android:id/stats_latest_post_tabs\")").
 * findElementByAndroidUIAutomator(("new UiSelector().resourceId(\"org.wordpress.android:id/stats_visitors_and_views_tab_inner_container\").enabled(true).instance(2)")).
 * findElement(MobileBy.id("stats_visitors_and_views_tab_value")).getText()
 *
 */

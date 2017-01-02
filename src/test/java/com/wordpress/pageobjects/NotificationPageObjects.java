package com.wordpress.pageobjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBySet;
import io.appium.java_client.pagefactory.HowToUseLocators;

import static io.appium.java_client.pagefactory.LocatorGroupStrategy.ALL_POSSIBLE;
import static io.appium.java_client.pagefactory.LocatorGroupStrategy.CHAIN;

/**
 * Created by saikrisv on 12/30/16.
 */
public class NotificationPageObjects {
    @HowToUseLocators(androidAutomation = ALL_POSSIBLE)
    @AndroidFindBy(id = "fakeID1")
    @AndroidFindBy(accessibility = "fakeID2")
    @AndroidFindBy(xpath = "(.//*[@resource-id='org.wordpress.android:id/note_content_container'])[position()=3]")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"org.wordpress.android:id/note_subject\")")
    public MobileElement getNotification;
}

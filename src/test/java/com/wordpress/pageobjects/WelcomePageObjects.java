package com.wordpress.pageobjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * Created by saikrisv on 12/29/16.
 */
public class WelcomePageObjects {

    @AndroidFindBy(id = "org.wordpress.android:id/my_site_subtitle_label")
    public MobileElement loggedInName;

    @AndroidFindBy(id = "org.wordpress.android:id/my_site_stats_text_view")
    public MobileElement stats;

    @AndroidFindBy(accessibility = "Notifications")
    public MobileElement notifications;
}

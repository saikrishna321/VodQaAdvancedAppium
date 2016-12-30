package com.wordpress.pageobjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * Created by saikrisv on 12/29/16.
 */
public class LoginPageObjects {

    @AndroidFindBy(id = "org.wordpress.android:id/nux_add_selfhosted_button")
    public MobileElement selfHostedSite;

    @AndroidFindBy(id = "org.wordpress.android:id/nux_username")
    public MobileElement userName;

    @AndroidFindBy(id = "org.wordpress.android:id/nux_password")
    public MobileElement passWord;

    @AndroidFindBy(id = "org.wordpress.android:id/nux_url")
    public MobileElement siteUrl;

    @AndroidFindBy(id = "org.wordpress.android:id/nux_sign_in_button")
    public MobileElement signButton;

}

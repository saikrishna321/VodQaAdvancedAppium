package com.appium.gesture;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.HowToUseLocators;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.pagefactory.LocatorGroupStrategy.ALL_POSSIBLE;
import static io.appium.java_client.pagefactory.LocatorGroupStrategy.CHAIN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by ssekar on 12/10/16.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PageFactoryTest {
    private boolean populated = false;

    private static AppiumDriverLocalService service;
    protected static AppiumDriver<MobileElement> driver;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"org.wordpress.android:id/nux_username\")")
    public MobileElement userName;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"org.wordpress.android:id/nux_password\")")
    public MobileElement password;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"org.wordpress.android:id/nux_sign_in_button\")")
    public MobileElement sigin;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE)
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"org.wordpress.android:id/tab_icon\")")
    public MobileElement wordpressIcon;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"org.wordpress.android:id/my_site_stats_text_view\")")
    public MobileElement stats;

    @HowToUseLocators(androidAutomation = CHAIN)
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"org.wordpress.android:id/stats_insights_fragments_container\")")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"org.wordpress.android:id/stats_visitors_and_views_tab_inner_container\")")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"org.wordpress.android:id/stats_visitors_and_views_tab_label\")")
    public MobileElement views;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE)
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"org.wordpress.android:id/my_site_media_text_view\")")
    @AndroidFindBy(className = "android.widget.TextView")
    public MobileElement media;

    @AndroidFindBy(className = "android.widget.ImageButton")
    public MobileElement imgButton;

    @Test
    public void pageObjectAllPossibleTest() {
        userName.sendKeys("vodqa2017");
        password.sendKeys("password@123");
        sigin.click();
        wordpressIcon.click();
        media.click();
        assertEquals(media.getText(),"Media");
    }

    @Test
    public void pageObjectChainingTest() {
        userName.sendKeys("vodqa2017");
        password.sendKeys("password@123");
        sigin.click();
        wordpressIcon.click();
        stats.click();
        assertEquals(views.getText(),"VIEWS");
        System.out.println(views.getText());
    }

    @Test public void toastMessageDisplayTest() throws InterruptedException {
        userName.sendKeys("vodqa2017");
        password.sendKeys("password@123");
        sigin.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        assertNotNull(wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//*[@text='Error reloading your Gravatar']"))));
    }

    @Before
    public void beforeClass() throws Exception {
                service = AppiumDriverLocalService.buildDefaultService();
                service.start();

                if (service == null || !service.isRunning()) {
                    throw new AppiumServerHasNotBeenStartedLocallyException(
                            "An appium server node is not started!");
                }

        androidCaps();
        if (!populated) {
            PageFactory.initElements(new AppiumFieldDecorator(driver, 5, TimeUnit.SECONDS),this);
        }

        populated = true;
    }

    private static void androidCaps() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, ".ui.accounts.SignInActivity");
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/ssekar/workspace/VodQaAdvancedAppium/org.wordpress.android.apk");
        driver = new AndroidDriver<MobileElement>(service.getUrl(), capabilities);
    }

    private static void iosCaps() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.3");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
        //sometimes environment has performance problems
        capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 700000);
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/ssekar/workspace/VodQaAdvancedAppium/WordPress.app");
        driver = new IOSDriver<MobileElement>(service.getUrl(), capabilities);
        System.out.println("test");
    }

    @After
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
        if (service != null) {
            service.stop();
        }
    }
}

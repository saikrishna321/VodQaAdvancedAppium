/*
package com.appium.gesture;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.HowToUseLocators;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.pagefactory.LocatorGroupStrategy.ALL_POSSIBLE;
import static io.appium.java_client.pagefactory.LocatorGroupStrategy.CHAIN;
import static org.junit.Assert.assertEquals;

*
 * Created by ssekar on 12/10/16.



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PageFactoryTest {
    private boolean populated = false;

    private static AppiumDriverLocalService service;
    protected static AppiumDriver<MobileElement> driver;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"org.wordpress.android:id/nux_username\")")
    @iOSFindBy(accessibility = "Username / Email")
    public MobileElement userName;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"org.wordpress.android:id/nux_password\")")
    @iOSFindBy(accessibility = "Password")
    public MobileElement password;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"org.wordpress.android:id/nux_sign_in_button\")")
    @iOSFindBy(accessibility = "Sign In")
    public MobileElement sigin;

    @iOSFindBy(accessibility = "new-editor-modal-dismiss-button")
    public MobileElement dismiss;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"org.wordpress.android:id/tab_icon\")")
    @iOSFindBy(accessibility = "Testing")
    public MobileElement wordpressIcon;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"org.wordpress.android:id/my_site_stats_text_view\")")
    @iOSFindBy(accessibility = "Stats")
    public MobileElement stats;

    @HowToUseLocators(androidAutomation = CHAIN, iOSAutomation = CHAIN)
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"org.wordpress.android:id/stats_module_result_container\")")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"org.wordpress.android:id/stats_visitors_and_views_tab_inner_container\")")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"org.wordpress.android:id/stats_visitors_and_views_tab_label\")")
    @iOSFindBy(accessibility = "2")
    @iOSFindBy(accessibility = "VIEWS")
    public MobileElement views;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSAutomation = ALL_POSSIBLE)
    @iOSFindBy(accessibility = "Comments")
    @iOSFindBy(xpath = "//UIAStaticText[@text='Comments']")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"org.wordpress.android:id/my_site_comments_text_view\")")
    @AndroidFindBy(className = "android.widget.TextView")
    public MobileElement Comments;

    @iOSFindBy(accessibility = "Comments Table")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"org.wordpress.android:id/empty_view\")")
    public MobileElement commentsDescription;

    @Test
    public void pageObjectAllPossibleTest() {
        userName.sendKeys("vodqa@gmail.com");
        password.sendKeys("Hello12345678");
        sigin.click();
        if(driver.getSessionDetail("platformName").toString().equalsIgnoreCase("iOS")) {
            waitForElement(dismiss);
            dismiss.click();
        }
        waitForElement(wordpressIcon);
        wordpressIcon.click();
        waitForElement(Comments);
        Comments.click();
        if(driver.getSessionDetail("platformName").toString().equalsIgnoreCase("iOS")) {
            waitForElement(commentsDescription);
            assertEquals(commentsDescription.getText(),"No comments yet");
        } else if(driver.getSessionDetail("platformName").toString().equalsIgnoreCase("android")) {
            waitForElement(Comments);
            assertEquals(commentsDescription.getText(),"No comments");
        }
    }

    @Test
    public void pageObjectChainingTest() {
        userName.sendKeys("vodqa@gmail.com");
        password.sendKeys("Hello12345678");
        sigin.click();
        if(driver.getSessionDetail("platformName").toString().equalsIgnoreCase("iOS")) {
            waitForElement(dismiss);
            dismiss.click();
        }
        waitForElement(wordpressIcon);
        wordpressIcon.click();
        waitForElement(stats);
        stats.click();
        assertEquals(views.getText(),"VIEWS");
    }

//    @Test public void toastMessageDisplayTest() throws InterruptedException {
//        userName.sendKeys("vodqa2017");
//        password.sendKeys("password@123");
//        sigin.click();
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        assertNotNull(wait.until(ExpectedConditions.presenceOfElementLocated(
//            By.xpath("/
[@text='Error reloading your Gravatar']"))));
//    }

    @Before
    public void beforeClass() throws Exception {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();

        if (service == null || !service.isRunning()) {
            throw new RuntimeException("An appium server node is not started!");
        }

        iosCaps();
        if (!populated) {
            PageFactory.initElements(new AppiumFieldDecorator(driver, 5, TimeUnit.SECONDS),this);
        }

        populated = true;
    }

    public void waitForElement(MobileElement mobileElement) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(mobileElement));
    }

    private static void androidCaps() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, ".ui.accounts.SignInActivity");
        capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/wordpress_old.apk");
        driver = new AndroidDriver<>(service.getUrl(), capabilities);
    }

    private static void iosCaps() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.2");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 7");
        //sometimes environment has performance problems
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 700000);
        capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/WordPress.app");
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
*/

package com.appium.gesture;

import com.wordpress.utils.BeanPageObjects;
import com.wordpress.utils.Helpers;
import com.wordpress.utils.PageObjectBeanPostProcessor;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by saikrisv on 07/12/16.
 */
@Configuration
@ContextConfiguration(classes = {PageObjectBeanPostProcessor.class,
        BaseUserTest.class, BeanPageObjects.class,
        AutowiredAnnotationBeanPostProcessor.class})
public class BaseUserTest extends AbstractTestNGSpringContextTests {
    private AppiumDriverLocalService service;

    WebDriverWait wait;

    /**
     * initialization.
     */
    @Autowired
    public AppiumDriver driver;

    public void beforeClass() {
        service = AppiumDriverLocalService.
                buildService(new AppiumServiceBuilder()
                        .withArgument(GeneralServerFlag.RELAXED_SECURITY)
                        .withArgument(GeneralServerFlag.LOG_TIMESTAMP)
                        .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                        .usingAnyFreePort());
        service.start();

        if (service == null || !service.isRunning()) {
            throw new AppiumServerHasNotBeenStartedLocallyException(
                    "An appium server node is not started!");
        }
    }

    @Bean
    public AppiumDriver<MobileElement> androidCaps() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions chrome_options = new ChromeOptions();
        chrome_options.setExperimentalOption("androidProcess", "com.tencent.mm:tools");
        capabilities.setCapability(ChromeOptions.CAPABILITY, chrome_options);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 700000);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/VodQA.apk");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");

        //capabilities.setCapability(MobileCapabilityType.APP, "/Users/saikrisv/git/java_client_pr/java-client/src/test/java/io/appium/java_client/ApiDemos-debug.apk");
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        return driver;
    }


    @Bean
    public Helpers helpers() {
        return new Helpers(driver);
    }


    @AfterMethod
    public void quitApp() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterMethod
    public void testApp1() throws IOException {

    }

    public void login() {
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.
                elementToBeClickable(MobileBy.AccessibilityId("login"))).click();
    }
}

package com.appium.gesture;

import com.wordpress.utils.BeanPageObjects;
import com.wordpress.utils.PageObjectBeanPostProcessor;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;

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
    AppiumDriver driver;

    @Bean
    public AppiumDriver androidCaps() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 700000);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/VodQA.apk");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        return driver;
    }

    @AfterMethod
    public void quitApp() {
        if (driver != null) {
            driver.quit();
        }
    }
}

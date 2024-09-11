package com.appium.element;

import com.wordpress.utils.BaseTest;
import io.appium.java_client.AppiumBy;
import org.testng.Assert;
import org.testng.annotations.Test;

public class iOSLocatorTest extends BaseTest {

    @Test
    public void classChainTest() {
        login();
        driver.findElement(AppiumBy.iOSNsPredicateString("name IN {'chained VieW','Demos wheel picker color'} AND visible == 1")).click();
        //driver.findElement(MobileBy.iOSNsPredicateString("name IN {'chained VieW','Chained View'}")).click();
//        driver.findElement(MobileBy.iOSNsPredicateString("name BEGINSWITH 'Chained'")).click();
        String text = driver.findElement(AppiumBy.iOSClassChain("XCUIElementTypeWindow/**/XCUIElementTypeStaticText[2]")).getText();
        Assert.assertEquals(text,"Hello World, I'm View one ");
    }
}

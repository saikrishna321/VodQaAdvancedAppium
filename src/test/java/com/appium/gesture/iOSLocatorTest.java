package com.appium.gesture;

import io.appium.java_client.MobileBy;
import org.testng.Assert;
import org.testng.annotations.Test;

public class iOSLocatorTest extends BaseUserTest {

    @Test
    public void classChainTest() {
        login();
        //driver.findElement(MobileBy.iOSNsPredicateString("name IN {'chained VieW','Demos wheel picker color'} AND visible == 1")).click();
        //driver.findElement(MobileBy.iOSNsPredicateString("name IN {'chained VieW','Chained View'}")).click();
        driver.findElement(MobileBy.iOSNsPredicateString("name BEGINSWITH 'Chained'")).click();
        String text = driver.findElement(MobileBy.iOSClassChain("XCUIElementTypeWindow/**/XCUIElementTypeStaticText[2]")).getText();
        Assert.assertEquals(text,"Hello World, I'm View one ");
    }
}

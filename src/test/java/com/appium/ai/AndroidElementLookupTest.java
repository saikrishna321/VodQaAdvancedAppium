package com.appium.ai;

import com.wordpress.utils.BaseTest;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AndroidElementLookupTest extends BaseTest {

    private By cartCount = MobileBy.id("count_number");
    private By cart = MobileBy.custom("ai:cart");
    @Test
    public void elementLookupTest() {
        String prevCount = driver.findElement(cartCount).getText();

        driver.findElement(cart).click();

        Assert.assertEquals(driver.findElement(cartCount).getText(),
                String.valueOf(Integer.parseInt(prevCount) + 1));
    }
}

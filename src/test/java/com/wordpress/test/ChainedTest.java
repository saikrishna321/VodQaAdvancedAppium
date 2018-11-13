package com.wordpress.test;

import com.appium.gesture.BaseUserTest;
import com.wordpress.pages.LoginPage;
import com.wordpress.utils.BaseTest;
import io.appium.java_client.AppiumDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by saikrisv on 12/29/16.
 */


public class ChainedTest extends BaseUserTest {

    @Autowired
    AppiumDriver driver;

    @Autowired
    private LoginPage loginPage;

    @Test
    @DirtiesContext
    public void chainedTest() {
        String secondComment = loginPage
                .enterUserNamePassword("admin", "admin").signIn().
                        navigateToChainedView().getSecondComment();

        Assert.assertEquals("Hello World, I'm View two", secondComment.trim());
    }

    @Test
    @DirtiesContext
    public void allPossibleTest() {
        boolean secondComment = loginPage
                .enterUserNamePassword("admin", "admin").signIn().
                        navigateToChainedView().isSecondContainerExists();
        Assert.assertTrue(secondComment);
    }

    @Test
    public void locatorUsageTest() {
        String text = loginPage
                .enterUserNamePassword("admin", "admin").signIn().
                        navigateToChainedView().getContainerText();
        Assert.assertNotNull(text);
    }
}

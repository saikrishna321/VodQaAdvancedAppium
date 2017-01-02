package com.wordpress.test;

import com.appium.gesture.BaseUserTest;
import com.wordpress.pages.LoginPage;
import com.wordpress.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by saikrisv on 12/29/16.
 */

public class ChainedTest extends BaseUserTest {

    private LoginPage loginPage;

    //Current not working when same Id's are present across child and parent
    // Track github issue https://github.com/appium/java-client/issues/549
    @Test
    public void chainedTest() {
        loginPage = new LoginPage(driver);
        String secondComment = loginPage
                .enterUserNamePassword("admin", "admin").signIn().
                        navigateToChainedView().getSecondComment();
        System.out.println(secondComment);
        Assert.assertEquals("Hello World, I'm View two",secondComment.trim());
    }

    @Test
    public void allPossibleTest(){
        loginPage = new LoginPage(driver);
        boolean secondComment = loginPage
                .enterUserNamePassword("admin", "admin").signIn().
                        navigateToChainedView().isSecondContainerExists();
        Assert.assertTrue(secondComment);
    }

    @Test
    public void locatorUsageTest() {
        loginPage = new LoginPage(driver);
        String text = loginPage
            .enterUserNamePassword("admin", "admin").signIn().
                navigateToChainedView().getContainerText();
        Assert.assertNotNull(text);
    }
}

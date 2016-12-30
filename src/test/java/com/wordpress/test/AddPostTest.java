package com.wordpress.test;

import com.wordpress.pages.LoginPage;
import com.wordpress.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by saikrisv on 12/29/16.
 */

public class AddPostTest extends BaseTest {

    private LoginPage loginPage;


    @Test
    public void addPostTest() {
        loginPage = new LoginPage(driver);
        String comments = loginPage.navigateToLoginScreen()
                .enterUserNamePassword("vodqa@gmail.com", "Hello12345678",
                        "https://vodqademo.wordpress.com").signIn().navigateToStats().getTotalComments();
        System.out.println("*******" + comments);
        Assert.assertTrue(comments.equals("2"));
    }

    @Test
    public void getNotifications(){
        loginPage = new LoginPage(driver);
        String firstNotification = loginPage.navigateToLoginScreen()
                .enterUserNamePassword("vodqa@gmail.com", "Hello12345678",
                        "https://vodqademo.wordpress.com").signIn().navigateToNotifiction().getFirstNotification();
        System.out.println("***********" + firstNotification);

    }
}

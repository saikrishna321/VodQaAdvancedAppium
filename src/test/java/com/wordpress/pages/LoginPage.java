package com.wordpress.pages;

import org.springframework.stereotype.Component;

/**
 * Created by saikrisv on 12/29/16.
 */
@Component
public class LoginPage extends AbstractPageAndObjects {

    public LoginPage enterUserNamePassword(String username, String password) {
        helpers.waitForElement(loginPageObjects.signButton);
        clearField();
        loginPageObjects.userName.sendKeys(username);
        loginPageObjects.passWord.sendKeys(password);
        return this;
    }

    private LoginPage clearField() {
        loginPageObjects.userName.clear();
        loginPageObjects.passWord.clear();
        return this;
    }

    public WelcomePage signIn(){
        loginPageObjects.signButton.click();
        return welcomePage;
    }


}

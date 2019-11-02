package com.wordpress.utils;

import com.wordpress.pageobjects.ChainViewPageObjects;
import com.wordpress.pageobjects.LoginPageObjects;
import com.wordpress.pageobjects.WelcomePageObjects;
import com.wordpress.pages.ChainViewPage;
import com.wordpress.pages.LoginPage;
import com.wordpress.pages.WelcomePage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanPageObjects {

    @Bean
    public LoginPageObjects loginPageObjects() {
        return new LoginPageObjects();
    }

    @Bean
    public WelcomePage welcomePage() {
        return new WelcomePage();
    }

    @Bean
    public WelcomePageObjects welcomePageObjects() {
        return new WelcomePageObjects();
    }

    @Bean
    public ChainViewPage chainViewPage() {
        return new ChainViewPage();
    }

    @Bean
    public ChainViewPageObjects chainViewPageObjects() {
        return new ChainViewPageObjects();
    }


    @Bean
    public LoginPage loginPage() {
        return new LoginPage();
    }

    @Bean
    public Helpers helpers() { return new Helpers(); }

}

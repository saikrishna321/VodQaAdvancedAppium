package com.appium.mobile;

import com.wordpress.utils.BaseTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class AndroidMobileTest extends BaseTest {

    @Test
    public void mobileShellWifiTest() {
        Map<String, Object> args = new HashMap<>();
        args.put("command", "dumpsys");
        args.put("args", "wifi");
        String response = (String)driver.executeScript("mobile: shell", args);
        System.out.println(response);
    }

    @Test
    public void mobileShellBatteryTest() {
        Map<String, Object> args = new HashMap<>();
        args.put("command", "dumpsys");
        args.put("args", "battery");
        String response = (String)driver.executeScript("mobile: shell", args);
        System.out.println(response);
    }
}

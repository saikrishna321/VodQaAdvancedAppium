package com.screen;

import com.appium.gesture.BaseUserTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class recording extends BaseUserTest{

    @Test
    public void recordiOSScreen() throws IOException {
        ((IOSDriver)driver).startRecordingScreen();
        String s = ((IOSDriver) driver).stopRecordingScreen();
        byte[] decode = Base64.getDecoder().decode(s);
        FileUtils.writeByteArrayToFile(new File(System.getProperty("user.dir")+"/sample.mp4"),
                decode);
    }

    @Test
    public void recordAndroidScreen() throws IOException {
        ((AndroidDriver)driver).startRecordingScreen();
        String s = ((AndroidDriver) driver).stopRecordingScreen();
        byte[] decode = Base64.getDecoder().decode(s);
        FileUtils.writeByteArrayToFile(new File(System.getProperty("user.dir")+"/sample.mp4"),
                decode);
    }
}

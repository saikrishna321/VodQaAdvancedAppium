package com.screen;

import com.appium.gesture.BaseUserTest;
import com.wordpress.utils.BaseTest;
import io.appium.java_client.HasSettings;
import io.appium.java_client.Setting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.imagecomparison.OccurrenceMatchingOptions;
import io.appium.java_client.imagecomparison.OccurrenceMatchingResult;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;


public class RecordingTest extends BaseTest {

    @Test
    public void iOSScreenRecordTest() throws IOException {
        ((IOSDriver)driver).startRecordingScreen();
        String s = ((IOSDriver) driver).stopRecordingScreen();
        byte[] decode = Base64.getDecoder().decode(s);
        FileUtils.writeByteArrayToFile(new File(
            System.getProperty("user.dir")
                +"/sample.mp4"),
                decode);
    }

    @Test
    public void androidScreenRecordTest() throws IOException, InterruptedException {
        ((AndroidDriver)driver).startRecordingScreen();
        Thread.sleep(10000);
        String s = ((AndroidDriver) driver).stopRecordingScreen();
        byte[] decode = Base64.getDecoder().decode(s);
        FileUtils.writeByteArrayToFile(new File(System.getProperty("user.dir")+"/sample.mp4"),
                decode);
    }

    @Test
    public void testApp() throws IOException {
        //((HasSettings)driver).setSetting(Setting.IMAGE_MATCH_THRESHOLD, "4.0");
        ((AndroidDriver) driver).sendSMS("555-555-5555", "Your message here!");
        ((HasSettings)driver).setSetting(Setting.FIX_IMAGE_TEMPLATE_SIZE, "true");
        ((HasSettings)driver).setSetting(Setting.IMAGE_ELEMENT_TAP_STRATEGY, "touchActions");
        File file = new File("/Users/saikrisv/Desktop/login.png");
        File refImgFile = Paths.get(file.toURI()).toFile();

        File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
        OccurrenceMatchingResult result = driver
                .findImageOccurrence(screenshotAs, new File("/Users/saikrisv/Desktop/login.png") ,
                        new OccurrenceMatchingOptions()
                        .withEnabledVisualization());
        System.out.println(result.getRect());
        //driver.findElementByImage(s).click();
    }
}

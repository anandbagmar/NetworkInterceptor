package test;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkInterceptor {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(true);
        RemoteWebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), chromeOptions);
//        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("http://rediff.com");
        waitfor(2);
        takeScreenShot(driver, "landing");
        waitfor(5);
        driver.quit();
    }

    private static void waitfor(int numberOfSeconds) throws InterruptedException {
        Thread.sleep(numberOfSeconds * 1000L);
    }

    private static void takeScreenShot(WebDriver driver, String fileName) {
        if(null != driver) {
            String directoryPath = System.getProperty("user.dir");
            File destinationFile = createScreenshotFile(directoryPath, fileName);
            System.out.println("The screenshot will be placed here : " + destinationFile.getAbsolutePath());
            try {
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                System.out.println("Original screenshot : " + screenshot.getAbsolutePath());
                FileUtils.copyFile(screenshot, destinationFile);
                System.out.println("The screenshot is available here : " + destinationFile.getAbsolutePath());
            } catch(IOException | RuntimeException e) {
                System.out.println("ERROR: Unable to save or upload screenshot: '" + destinationFile.getAbsolutePath() + "' or upload screenshot to ReportPortal\n");
                System.out.println(ExceptionUtils.getStackTrace(e));
            }
        } else {
            System.out.println("Driver is not instantiated for this test");
        }
    }

    private static File createScreenshotFile(String dirName, String fileName) {
        fileName = fileName.endsWith(".png") ? fileName : fileName + ".png";
        return new File(dirName + File.separator + fileName);
    }
}

package test;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v114.network.Network;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;

public class NetworkInterceptorCheck {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        // Set the system property to indicate that Java 11+ Http client needs to be used.
        // By default, it uses the AsyncHttpClient.
        // https://www.selenium.dev/blog/2022/using-java11-httpclient/
        System.setProperty("webdriver.http.factory", "jdk-http-client");

        // where? // browser // devtools enabled? // status
        // local / chrome / no devtools - yes
        // local / chrome / devtools - yes
        // docker / chrome / no devtools - yes
        // docker / chrome / devtools - no

        // local / firefox / no devtools - yes
        // local / firefox / devtools - no
        // docker / firefox / no devtools - yes
        // docker / firefox / devtools - no

        String driverType = getBrowserType();

        WebDriver driver = createDriver(driverType);

        DevTools devTools = null;
        ArrayList capturedTraffic;
        if (isDevToolsEnabled()) {
            driver = new Augmenter().augment(driver);
            devTools = ((HasDevTools) driver).getDevTools();
            devTools.createSessionIfThereIsNotOne();

            devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

            capturedTraffic = new ArrayList();
            devTools.addListener(Network.requestWillBeSent(), requestSent -> {
                System.out.println("Request URL => " + requestSent.getRequest().getUrl());
                System.out.println("Request Method => " + requestSent.getRequest().getMethod());
                System.out.println("Request Headers => " + requestSent.getRequest().getHeaders().toString());
                capturedTraffic.add(requestSent.getRequest());
            });
        } else {
            capturedTraffic = null;
        }

        driver.get("http://google.com");
        waitFor(2);
        takeScreenShot(driver, driverType, "landing");

        if (isDevToolsEnabled()) {
            System.out.println(capturedTraffic);
            devTools.clearListeners();
            devTools.close();
        }
        driver.quit();
    }

    private static WebDriver createDriver(String driverType) throws MalformedURLException {
        switch (driverType.toLowerCase()) {
            case "remote-chrome":
                return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), new ChromeOptions());
            case "remote-firefox":
                return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), new FirefoxOptions());
            case "firefox":
                return new FirefoxDriver(new FirefoxOptions());
            default:
                return new ChromeDriver(new ChromeOptions());
        }
    }

    private static String getBrowserType() {
        String driverType = System.getenv("browser");
        if ((null == driverType) || (driverType.isEmpty())) {
            driverType = "chrome";
        }
        return driverType;
    }

    private static boolean isDevToolsEnabled() {
        boolean isEnabled = null != System.getenv("devtools") && Boolean.parseBoolean(System.getenv("devtools"));
        System.out.printf("Should DevTools be enabled? : %s%n", isEnabled);
        return isEnabled;
    }

    private static void waitFor(int numberOfSeconds) throws InterruptedException {
        Thread.sleep(numberOfSeconds * 1000L);
    }

    private static void takeScreenShot(WebDriver driver, String driverType, String fileName) {
        if (null != driver) {
            String directoryPath = System.getProperty("user.dir");
            File destinationFile = createScreenshotFile(directoryPath, driverType + "-" + fileName);
            System.out.println("The screenshot will be placed here : " + destinationFile.getAbsolutePath());
            try {
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                System.out.println("Original screenshot : " + screenshot.getAbsolutePath());
                FileUtils.copyFile(screenshot, destinationFile);
                System.out.println("The screenshot is available here : " + destinationFile.getAbsolutePath());
            } catch (IOException | RuntimeException e) {
                System.out.println("ERROR: Unable to save or upload screenshot: '" + destinationFile.getAbsolutePath() + "' or upload screenshot to ReportPortal\n");
                System.out.println(ExceptionUtils.getStackTrace(e));
            }
        } else {
            System.out.println("Driver is not instantiated for this test");
        }
    }

    private static File createScreenshotFile(String dirName, String fileName) {
        fileName = fileName.endsWith(".png") ? fileName : fileName + ".png";
        return new File(dirName + File.separator + "target" + File.separator + fileName);
    }
}

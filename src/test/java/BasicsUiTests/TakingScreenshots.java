package BasicsUiTests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class TakingScreenshots {
    public static void main(String[] args) throws IOException {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://www.google.co.in/");
        TakesScreenshot screenshot = (TakesScreenshot) driver; // cast driver to TakesScreenshot.
        File src = screenshot.getScreenshotAs(OutputType.FILE); // get the screenshot as a file
        File dest = new File("src/test/screenshots/screenshot1.png"); // create a file object for the destination
        FileUtils.copyFile(src,dest, true); // copy the screenshot to the destination
        driver.quit();
    }
}

package com.danil.practice.JavaSeleniumWeb.GithubRunnerpack;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class IntentionalFailureTest {

    @Test
    public void intentionalFailureTest() throws Exception {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("https://the-internet.herokuapp.com/login");

            String actualTitle = driver.getTitle();

            System.out.println("Actual page title: " + actualTitle);
            System.out.println("This test is intentionally expected to FAIL in CI.");

            Assert.assertEquals(
                    "Intentional CI failure: expected title is deliberately wrong",
                    "THIS TITLE IS INTENTIONALLY WRONG",
                    actualTitle
            );

        } finally {
            Path screenshotPath = Paths.get("target", "screenshots", "intentional-failure.png");
            Files.createDirectories(screenshotPath.getParent());

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(screenshot.toPath(), screenshotPath, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Screenshot saved to: " + screenshotPath);

            driver.quit();
        }
    }
}

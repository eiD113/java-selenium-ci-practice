package com.danil.practice.JavaSeleniumWeb;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * READY-TO-ADAPT SELENIUM TEST TEMPLATES
 *
 * Suggested location:
 * src/test/java/com/danil/practice/JavaSeleniumWeb/SeleniumTestCaseTemplates.java
 *
 * These examples use:
 * https://the-internet.herokuapp.com/
 *
 * The purpose is to keep common Selenium test flows in one file.
 * Copy a test, replace URL/locators/data, and adapt it to the application.
 */
public class SeleniumTestCaseTemplates {

    private WebDriver driver;
    private WebDriverWait wait;

    // =========================================================
    // SETUP BEFORE EACH TEST
    // =========================================================
    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();
    }

    // =========================================================
    // CLEANUP AFTER EACH TEST
    // =========================================================
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // =========================================================
    // 01. POSITIVE LOGIN
    // Flow:
    // open page -> enter username -> enter password -> click login
    // -> validate successful navigation and Logout button
    // =========================================================
    @Test
    public void successfulLoginTemplate() {
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

        username.sendKeys("tomsmith");
        password.sendKeys("SuperSecretPassword!");
        loginButton.click();

        wait.until(ExpectedConditions.urlContains("/secure"));

        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href='/logout']")));

        Assert.assertTrue(driver.getCurrentUrl().contains("/secure"));
        Assert.assertTrue(logoutButton.isDisplayed());
    }

    // =========================================================
    // 02. NEGATIVE LOGIN
    // Flow:
    // invalid credentials -> click login -> validate error
    // =========================================================
    @Test
    public void invalidLoginTemplate() {
        driver.get("https://the-internet.herokuapp.com/login");

        driver.findElement(By.id("username")).sendKeys("wrongUser");
        driver.findElement(By.id("password")).sendKeys("wrongPassword");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebElement flash = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));

        Assert.assertTrue(flash.getText().toLowerCase().contains("invalid"));
    }

    // =========================================================
    // 03. INPUT FIELD
    // Flow:
    // find input -> clear -> type -> verify current value
    // =========================================================
    @Test
    public void inputFieldTemplate() {
        driver.get("https://the-internet.herokuapp.com/inputs");

        WebElement input = driver.findElement(By.tagName("input"));

        input.clear();
        input.sendKeys("12345");

        Assert.assertEquals("12345", input.getDomProperty("value"));
    }

    // =========================================================
    // 04. HTML SELECT DROPDOWN
    // =========================================================
    @Test
    public void dropdownTemplate() {
        driver.get("https://the-internet.herokuapp.com/dropdown");

        Select dropdown = new Select(driver.findElement(By.id("dropdown")));

        dropdown.selectByVisibleText("Option 2");

        Assert.assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());
    }

    // =========================================================
    // 05. CHECKBOX
    // =========================================================
    @Test
    public void checkboxTemplate() {
        driver.get("https://the-internet.herokuapp.com/checkboxes");

        List<WebElement> checkboxes = driver.findElements(By.cssSelector("#checkboxes input[type='checkbox']"));
        WebElement firstCheckbox = checkboxes.get(0);

        if (!firstCheckbox.isSelected()) {
            firstCheckbox.click();
        }

        Assert.assertTrue(firstCheckbox.isSelected());
    }

    // =========================================================
    // 06. FIND MULTIPLE ELEMENTS
    // =========================================================
    @Test
    public void findElementsTemplate() {
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");

        WebElement addButton = driver.findElement(By.xpath("//button[text()='Add Element']"));

        addButton.click();
        addButton.click();
        addButton.click();

        List<WebElement> deleteButtons = driver.findElements(By.cssSelector("button.added-manually"));

        Assert.assertEquals(3, deleteButtons.size());
    }

    // =========================================================
    // 07. EXPLICIT WAIT FOR DYNAMIC ELEMENT
    // Flow:
    // click action -> wait for asynchronous result -> validate
    // =========================================================
    @Test
    public void explicitWaitTemplate() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        driver.findElement(By.cssSelector("#start button")).click();

        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#finish h4")));

        Assert.assertEquals("Hello World!", result.getText());
    }

    // =========================================================
    // 08. WAIT FOR ELEMENT TO APPEAR IN DOM
    // =========================================================
    @Test
    public void waitForNewElementTemplate() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");

        driver.findElement(By.cssSelector("#start button")).click();

        WebElement result = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#finish h4")));

        Assert.assertEquals("Hello World!", result.getText());
    }

    // =========================================================
    // 09. JAVASCRIPT ALERT - ACCEPT
    // =========================================================
    @Test
    public void alertAcceptTemplate() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals("I am a JS Alert", alert.getText());

        alert.accept();

        WebElement result = driver.findElement(By.id("result"));

        Assert.assertTrue(result.getText().contains("successfully clicked"));
    }

    // =========================================================
    // 10. JAVASCRIPT CONFIRM - DISMISS
    // =========================================================
    @Test
    public void alertDismissTemplate() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        alert.dismiss();

        WebElement result = driver.findElement(By.id("result"));

        Assert.assertTrue(result.getText().contains("Cancel"));
    }

    // =========================================================
    // 11. JAVASCRIPT PROMPT
    // =========================================================
    @Test
    public void alertPromptTemplate() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        alert.sendKeys("Danil");
        alert.accept();

        WebElement result = driver.findElement(By.id("result"));

        Assert.assertTrue(result.getText().contains("Danil"));
    }

    // =========================================================
    // 12. IFRAME
    // Flow:
    // locate frame -> switch -> work inside -> return to main page
    // =========================================================
    @Test
    public void iframeTemplate() {
        driver.get("https://the-internet.herokuapp.com/iframe");

        WebElement frame = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mce_0_ifr")));

        driver.switchTo().frame(frame);

        WebElement editor = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tinymce")));

        editor.sendKeys(Keys.CONTROL, "a");
        editor.sendKeys("Selenium iframe test");

        Assert.assertTrue(editor.getText().contains("Selenium iframe test"));

        driver.switchTo().defaultContent();
    }

    // =========================================================
    // 13. MULTIPLE WINDOWS / TABS
    // =========================================================
    @Test
    public void multipleWindowsTemplate() {
        driver.get("https://the-internet.herokuapp.com/windows");

        String originalWindow = driver.getWindowHandle();

        driver.findElement(By.linkText("Click Here")).click();

        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        Set<String> allWindows = driver.getWindowHandles();

        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h3")));

        Assert.assertEquals("New Window", heading.getText());

        driver.close();
        driver.switchTo().window(originalWindow);

        Assert.assertTrue(driver.getCurrentUrl().contains("/windows"));
    }

    // =========================================================
    // 14. RIGHT CLICK WITH ACTIONS
    // =========================================================
    @Test
    public void contextClickTemplate() {
        driver.get("https://the-internet.herokuapp.com/context_menu");

        WebElement area = driver.findElement(By.id("hot-spot"));
        Actions actions = new Actions(driver);

        actions.contextClick(area).perform();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals("You selected a context menu", alert.getText());

        alert.accept();
    }

    // =========================================================
    // 15. KEYBOARD ACTION
    // =========================================================
    @Test
    public void keyboardTemplate() {
        driver.get("https://the-internet.herokuapp.com/key_presses");

        WebElement input = driver.findElement(By.id("target"));

        input.sendKeys(Keys.ENTER);

        WebElement result = driver.findElement(By.id("result"));

        Assert.assertTrue(result.getText().contains("ENTER"));
    }

    // =========================================================
    // 16. FILE UPLOAD
    // Change FILE_TO_UPLOAD before running.
    // =========================================================
    @Ignore("Set FILE_TO_UPLOAD to an existing local file before running")
    @Test
    public void fileUploadTemplate() {
        driver.get("https://the-internet.herokuapp.com/upload");

        String fileToUpload = "C:\\path\\to\\sample.txt";

        driver.findElement(By.id("file-upload")).sendKeys(fileToUpload);
        driver.findElement(By.id("file-submit")).click();

        WebElement uploadedFiles = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uploaded-files")));

        Assert.assertTrue(uploadedFiles.getText().contains("sample.txt"));
    }

    // =========================================================
    // 17. NAVIGATION
    // =========================================================
    @Test
    public void navigationTemplate() {
        driver.get("https://the-internet.herokuapp.com/");

        String firstUrl = driver.getCurrentUrl();

        driver.navigate().to("https://the-internet.herokuapp.com/checkboxes");
        Assert.assertTrue(driver.getCurrentUrl().contains("/checkboxes"));

        driver.navigate().back();
        Assert.assertEquals(firstUrl, driver.getCurrentUrl());

        driver.navigate().forward();
        Assert.assertTrue(driver.getCurrentUrl().contains("/checkboxes"));

        driver.navigate().refresh();
    }

    // =========================================================
    // 18. PAGE TITLE / URL VALIDATION
    // =========================================================
    @Test
    public void titleAndUrlTemplate() {
        driver.get("https://the-internet.herokuapp.com/");

        Assert.assertEquals("The Internet", driver.getTitle());
        Assert.assertTrue(driver.getCurrentUrl().contains("the-internet.herokuapp.com"));
    }

    // =========================================================
    // 19. ELEMENT STATE VALIDATION
    // displayed / enabled / selected
    // =========================================================
    @Test
    public void elementStateTemplate() {
        driver.get("https://the-internet.herokuapp.com/checkboxes");

        WebElement checkbox = driver.findElements(By.cssSelector("#checkboxes input")).get(0);

        Assert.assertTrue(checkbox.isDisplayed());
        Assert.assertTrue(checkbox.isEnabled());

        if (!checkbox.isSelected()) {
            checkbox.click();
        }

        Assert.assertTrue(checkbox.isSelected());
    }

    // =========================================================
    // 20. GET ATTRIBUTE / PROPERTY
    // =========================================================
    @Test
    public void attributePropertyTemplate() {
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement username = driver.findElement(By.id("username"));

        Assert.assertEquals("text", username.getDomAttribute("type"));
        Assert.assertEquals("", username.getDomProperty("value"));

        username.sendKeys("Danil");

        Assert.assertEquals("Danil", username.getDomProperty("value"));
    }

    // =========================================================
    // 21. SCREENSHOT HELPER USAGE
    // =========================================================
    @Test
    public void screenshotTemplate() throws IOException {
        driver.get("https://the-internet.herokuapp.com/");

        Path screenshot = takeScreenshot("homepage");

        Assert.assertTrue(Files.exists(screenshot));
    }

    // =========================================================
    // 22. SIMPLE REUSABLE LOGIN FLOW
    // Shows how repeated UI operations can become helper methods.
    // =========================================================
    @Test
    public void reusableMethodTemplate() {
        driver.get("https://the-internet.herokuapp.com/login");

        login("tomsmith", "SuperSecretPassword!");

        wait.until(ExpectedConditions.urlContains("/secure"));

        Assert.assertTrue(driver.getCurrentUrl().contains("/secure"));
    }

    // =========================================================
    // 23. SIMPLE POM-LIKE TEST
    // The page object itself is defined at the bottom of this file.
    // =========================================================
    @Test
    public void pageObjectTemplate() {
        LoginPage loginPage = new LoginPage(driver, wait);

        loginPage.open();
        loginPage.login("tomsmith", "SuperSecretPassword!");

        Assert.assertTrue(loginPage.isLoggedIn());
    }

    // =========================================================
    // REUSABLE HELPER: LOGIN
    // =========================================================
    private void login(String username, String password) {
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        WebElement passwordInput = driver.findElement(By.id("password"));

        usernameInput.clear();
        usernameInput.sendKeys(username);

        passwordInput.clear();
        passwordInput.sendKeys(password);

        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    // =========================================================
    // REUSABLE HELPER: SCREENSHOT
    // =========================================================
    private Path takeScreenshot(String name) throws IOException {
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Path destination = Path.of("screenshots", name + ".png");

        Files.createDirectories(destination.getParent());
        Files.copy(source.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

        return destination;
    }

    // =========================================================
    // SIMPLE PAGE OBJECT TEMPLATE
    // =========================================================
    public static class LoginPage {
        private final WebDriver driver;
        private final WebDriverWait wait;

        private final By usernameInput = By.id("username");
        private final By passwordInput = By.id("password");
        private final By loginButton = By.cssSelector("button[type='submit']");
        private final By logoutButton = By.cssSelector("a[href='/logout']");

        public LoginPage(WebDriver driver, WebDriverWait wait) {
            this.driver = driver;
            this.wait = wait;
        }

        public void open() {
            driver.get("https://the-internet.herokuapp.com/login");
        }

        public void login(String username, String password) {
            WebElement usernameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
            WebElement passwordElement = driver.findElement(passwordInput);

            usernameElement.clear();
            usernameElement.sendKeys(username);

            passwordElement.clear();
            passwordElement.sendKeys(password);

            driver.findElement(loginButton).click();
        }

        public boolean isLoggedIn() {
            WebElement logout = wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton));

            return logout.isDisplayed() && driver.getCurrentUrl().contains("/secure");
        }
    }
}

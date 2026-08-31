package com.danil.practice.JavaSeleniumWeb.GithubRunnerpack;

import java.time.Duration;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SauceDemoTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void sauceDemoCartTest() {
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".app_logo")));
        Assert.assertTrue(logo.isDisplayed());

        List<WebElement> items = driver.findElements(By.cssSelector(".inventory_item_description"));
        System.out.println("Products found: " + items.size());
        Assert.assertTrue(items.size() > 0);

        WebElement firstItem = items.get(0);
        String firstItemName = firstItem.findElement(By.cssSelector(".inventory_item_name")).getText();
        System.out.println("Selected product: " + firstItemName);

        firstItem.findElement(By.cssSelector("button")).click();

        WebElement cartBadge = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".shopping_cart_badge")));
        String cartCount = cartBadge.getText();
        System.out.println("Cart count: " + cartCount);
        Assert.assertEquals("1", cartCount);

        driver.findElement(By.cssSelector(".shopping_cart_link")).click();

        WebElement cartItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart_item")));
        String cartItemName = cartItem.findElement(By.cssSelector(".inventory_item_name")).getText();
        System.out.println("Product in cart: " + cartItemName);

        Assert.assertEquals(firstItemName, cartItemName);
        System.out.println("SauceDemo Cart Test: PASSED");
    }
}

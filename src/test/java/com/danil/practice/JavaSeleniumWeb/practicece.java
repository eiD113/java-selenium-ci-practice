package com.danil.practice.JavaSeleniumWeb;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class practicece {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
    ChromeOptions options = new ChromeOptions();

    options.setExperimentalOption("prefs", Map.of(
        "credentials_enable_service", false,
        "profile.password_manager_enabled", false,
        "profile.password_manager_leak_detection", false
    ));

    driver = new ChromeDriver(options);
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


    @Test
    public void loginTest() throws InterruptedException  {
        driver.get("https://www.google.com/");
        //Thread.sleep(100000); 
        WebElement search = driver.findElement(By.cssSelector("textarea[aria-label='Search']"));
        search.click();
        search.sendKeys("Selenium WebDriver" + Keys.ENTER);
        Thread.sleep(5000);
        String titleT = driver.getTitle();
        System.out.print("Title:" + titleT);
        Thread.sleep(5000); 


    } 

    @Test
    public void task2() throws InterruptedException  {

        driver.get("https://the-internet.herokuapp.com/login");
        String username = "tomsmith";
        String password = "SuperSecretPassword!";

        WebElement nameField = driver.findElement(By.name("username"));
        nameField.sendKeys(username);
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys(password);

        WebElement subbmitButton = driver.findElement(By.xpath("/html/body/div[2]/div/div/form/button"));
        subbmitButton.click();
        Thread.sleep(5000);

        String urlCheck = driver.getCurrentUrl();
        System.out.println(urlCheck);

        WebElement logoutbutton = driver.findElement(By.cssSelector("a[class='button secondary radius']"));
        String checkLogbutton = logoutbutton.getText();

        

        System.out.println("Logout Button = " + checkLogbutton);
        Assert.assertTrue(logoutbutton.isEnabled());

    }

        @Test
        public void task3() throws InterruptedException  {

        driver.get("https://the-internet.herokuapp.com/login");
        String username = "tomsmith";
        String password = "wrongPassword!";

        WebElement nameField = driver.findElement(By.name("username"));
        nameField.sendKeys(username);
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys(password);
        WebElement subbmitButton = driver.findElement(By.xpath("/html/body/div[2]/div/div/form/button"));
        subbmitButton.click();


        WebElement errorrmes = driver.findElement(By.cssSelector("[class='flash error']"));
        String errormessage = errorrmes.getText();

        boolean mess = errormessage.contains("invalid");;


        System.out.println("Error message = " + errormessage + "Status: " + mess);
        Thread.sleep(50000);

    }

    
        @Test
        public void task4() throws InterruptedException  {

        driver.get("https://the-internet.herokuapp.com/checkboxes");
        
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));

        for(WebElement checkboxescheck:checkboxes){
            System.out.println("Your chehfehfhefhefh" + checkboxescheck);
        }

        for (int i = 0; i <= 1; i++)
        {
            if(checkboxes.get(i).isSelected()){
                System.out.println("Checkbox " + i + " is enabled");

            }
            else if (!checkboxes.get(i).isSelected()){

                checkboxes.get(i).click();
            }
            else{
                System.out.println("Error");
            }
            Assert.assertTrue(checkboxes.get(i).isSelected());
        }

        Thread.sleep(50000);

    }

        @Test
        public void task5() throws InterruptedException  {

        driver.get("https://www.saucedemo.com/");
        String username = "standard_user";
        String password = "secret_sauce";

        WebElement usernameEnter = driver.findElement(By.id("user-name"));
        usernameEnter.click();
        usernameEnter.sendKeys(username);
        WebElement passwordEnter = driver.findElement(By.id("password"));
        passwordEnter.click();
        passwordEnter.sendKeys(password);

        Thread.sleep(500);
        WebElement logIn = driver.findElement(By.id("login-button"));
        logIn.click();
        WebElement logoScreen = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='app_logo']")));

        Assert.assertTrue(logoScreen.isDisplayed());

        List <WebElement> items = driver.findElements(By.cssSelector("div[class='inventory_item_description']"));

        for(WebElement Items:items){

            System.out.println("Item description: " + Items.getText());
        }

        int productsize = items.size();
        System.out.println(productsize);

        WebElement firstitem = items.get(0);
        WebElement newfirstitem = firstitem.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[1]/div[2]/div[1]/a/div"));
        String newnewnew = newfirstitem.getText();
        System.out.println(newnewnew);

        WebElement firstItemClick = items.get(0).findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[1]/div[2]/div[2]/button"));
        firstItemClick.click();

        WebElement cart = driver.findElement(By.cssSelector("a[class='shopping_cart_link']"));

        System.out.println(cart.getText());



        WebElement cartoneicon = cart.findElement(By.cssSelector("[class='shopping_cart_badge']"));
        System.out.println("Cart amount = " + cartoneicon.getText());
        Assert.assertTrue(cartoneicon.getText().equals("1"));

        cart.click();
        


        Thread.sleep(50000);

    }
}


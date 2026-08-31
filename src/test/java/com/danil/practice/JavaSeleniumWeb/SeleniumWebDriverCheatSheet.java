package com.danil.practice.JavaSeleniumWeb;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumWebDriverCheatSheet {

    private static WebDriver driver;
    private static WebDriverWait wait;

    public static void main(String[] args) {
        // Шпаргалка: методы специально не вызываются автоматически.
        // Для практики можно запускать отдельные методы вручную.
        //
        // Пример:
        // startChrome();
        // navigation();
        // closeBrowser();
    }

    // =========================================================
    // 01. START CHROME
    // WebDriver = interface, ChromeDriver = implementation
    // Selenium Manager обычно сам управляет ChromeDriver.
    // =========================================================
    public static void startChrome() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // =========================================================
    // 02. START EDGE
    // =========================================================
    public static void startEdge() {
        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // =========================================================
    // 03. START FIREFOX
    // =========================================================
    public static void startFirefox() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // =========================================================
    // 04. CHROME OPTIONS
    // =========================================================
    public static void chromeOptions() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // =========================================================
    // 05. HEADLESS CHROME
    // =========================================================
    public static void headlessChrome() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless=new");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // =========================================================
    // 06. EDGE OPTIONS
    // =========================================================
    public static void edgeOptions() {
        EdgeOptions options = new EdgeOptions();

        options.addArguments("--start-maximized");
        options.addArguments("--inprivate");

        driver = new EdgeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // =========================================================
    // 07. OPEN URL / GET TITLE / CURRENT URL / PAGE SOURCE
    // =========================================================
    public static void basicBrowserCommands() {
        driver.get("https://example.com");

        String title = driver.getTitle();
        String currentUrl = driver.getCurrentUrl();
        String pageSource = driver.getPageSource();

        System.out.println(title);
        System.out.println(currentUrl);
        System.out.println(pageSource);
    }

    // =========================================================
    // 08. NAVIGATION
    // =========================================================
    public static void navigation() {
        driver.navigate().to("https://example.com");
        driver.navigate().refresh();
        driver.navigate().back();
        driver.navigate().forward();
    }

    // =========================================================
    // 09. WINDOW SIZE
    // =========================================================
    public static void browserWindow() {
        driver.manage().window().maximize();
        driver.manage().window().fullscreen();

        Dimension size = new Dimension(1920, 1080);
        driver.manage().window().setSize(size);

        Dimension currentSize = driver.manage().window().getSize();
        Point position = driver.manage().window().getPosition();

        System.out.println(currentSize);
        System.out.println(position);
    }

    // =========================================================
    // 10. ALL BASIC LOCATORS
    // =========================================================
    public static void basicLocators() {
        WebElement byId = driver.findElement(By.id("username"));
        WebElement byName = driver.findElement(By.name("email"));
        WebElement byClassName = driver.findElement(By.className("login-button"));
        WebElement byTagName = driver.findElement(By.tagName("button"));
        WebElement byLinkText = driver.findElement(By.linkText("Forgot Password?"));
        WebElement byPartialLinkText = driver.findElement(By.partialLinkText("Forgot"));
        WebElement byCss = driver.findElement(By.cssSelector("#username"));
        WebElement byXpath = driver.findElement(By.xpath("//input[@id='username']"));

        System.out.println(byId);
        System.out.println(byName);
        System.out.println(byClassName);
        System.out.println(byTagName);
        System.out.println(byLinkText);
        System.out.println(byPartialLinkText);
        System.out.println(byCss);
        System.out.println(byXpath);
    }

    // =========================================================
    // 11. CSS SELECTORS
    // =========================================================
    public static void cssSelectors() {
        By byId = By.cssSelector("#username");
        By byClass = By.cssSelector(".login-button");
        By byTag = By.cssSelector("input");
        By byAttribute = By.cssSelector("input[name='username']");
        By byTagAndClass = By.cssSelector("button.primary");
        By byMultipleClasses = By.cssSelector(".button.primary.large");
        By byStartsWith = By.cssSelector("input[id^='user']");
        By byEndsWith = By.cssSelector("input[id$='name']");
        By byContains = By.cssSelector("input[id*='serna']");
        By directChild = By.cssSelector("form > input");
        By descendant = By.cssSelector("form input");
        By nthChild = By.cssSelector("ul li:nth-child(2)");

        System.out.println(byId);
        System.out.println(byClass);
        System.out.println(byTag);
        System.out.println(byAttribute);
        System.out.println(byTagAndClass);
        System.out.println(byMultipleClasses);
        System.out.println(byStartsWith);
        System.out.println(byEndsWith);
        System.out.println(byContains);
        System.out.println(directChild);
        System.out.println(descendant);
        System.out.println(nthChild);
    }

    // =========================================================
    // 12. XPATH SELECTORS
    // =========================================================
    public static void xpathSelectors() {
        By byAttribute = By.xpath("//input[@id='username']");
        By byExactText = By.xpath("//button[text()='Login']");
        By byNormalizedText = By.xpath("//button[normalize-space()='Login']");
        By byContainsText = By.xpath("//button[contains(text(),'Log')]");
        By byContainsAttribute = By.xpath("//input[contains(@id,'user')]");
        By byStartsWithAttribute = By.xpath("//input[starts-with(@id,'user')]");
        By byMultipleConditions = By.xpath("//input[@type='text' and @name='username']");
        By byOrCondition = By.xpath("//button[@type='submit' or @id='login']");
        By byIndex = By.xpath("(//input[@type='text'])[2]");
        By child = By.xpath("//form//input");
        By directChild = By.xpath("//form/input");
        By parent = By.xpath("//input[@id='username']/parent::*");
        By ancestor = By.xpath("//input[@id='username']/ancestor::form");
        By followingSibling = By.xpath("//label[text()='Username']/following-sibling::input");
        By precedingSibling = By.xpath("//input[@id='username']/preceding-sibling::label");

        System.out.println(byAttribute);
        System.out.println(byExactText);
        System.out.println(byNormalizedText);
        System.out.println(byContainsText);
        System.out.println(byContainsAttribute);
        System.out.println(byStartsWithAttribute);
        System.out.println(byMultipleConditions);
        System.out.println(byOrCondition);
        System.out.println(byIndex);
        System.out.println(child);
        System.out.println(directChild);
        System.out.println(parent);
        System.out.println(ancestor);
        System.out.println(followingSibling);
        System.out.println(precedingSibling);
    }

    // =========================================================
    // 13. RELATIVE LOCATORS
    // Selenium 4 feature
    // =========================================================
    public static void relativeLocators() {
        WebElement password = driver.findElement(By.id("password"));

        WebElement inputAbovePassword = driver.findElement(RelativeLocator.with(By.tagName("input")).above(password));
        WebElement inputNearPassword = driver.findElement(RelativeLocator.with(By.tagName("input")).near(password));

        System.out.println(inputAbovePassword);
        System.out.println(inputNearPassword);
    }

    // =========================================================
    // 14. FINDELEMENT VS FINDELEMENTS
    // findElement -> first element or NoSuchElementException
    // findElements -> List<WebElement>, empty list if nothing found
    // =========================================================
    public static void findElementAndFindElements() {
        WebElement firstButton = driver.findElement(By.tagName("button"));
        List<WebElement> allButtons = driver.findElements(By.tagName("button"));

        System.out.println(firstButton.getText());
        System.out.println(allButtons.size());

        for (WebElement button : allButtons) {
            System.out.println(button.getText());
        }
    }

    // =========================================================
    // 15. FIND CHILD ELEMENT INSIDE PARENT
    // =========================================================
    public static void nestedElementSearch() {
        WebElement form = driver.findElement(By.id("login-form"));
        WebElement username = form.findElement(By.name("username"));
        List<WebElement> inputs = form.findElements(By.tagName("input"));

        System.out.println(username);
        System.out.println(inputs.size());
    }

    // =========================================================
    // 16. WEBELEMENT BASIC ACTIONS
    // =========================================================
    public static void webElementActions() {
        WebElement username = driver.findElement(By.id("username"));

        username.click();
        username.clear();
        username.sendKeys("Danil");
        username.sendKeys(Keys.TAB);

        WebElement form = driver.findElement(By.tagName("form"));
        form.submit();
    }

    // =========================================================
    // 17. KEYBOARD KEYS
    // =========================================================
    public static void keyboardKeys() {
        WebElement input = driver.findElement(By.id("search"));

        input.sendKeys("Selenium");
        input.sendKeys(Keys.ENTER);
        input.sendKeys(Keys.TAB);
        input.sendKeys(Keys.ESCAPE);
        input.sendKeys(Keys.CONTROL, "a");
        input.sendKeys(Keys.BACK_SPACE);
    }

    // =========================================================
    // 18. READ TEXT / ATTRIBUTES / PROPERTIES / CSS
    // =========================================================
    public static void readElementData() {
        WebElement element = driver.findElement(By.id("username"));

        String text = element.getText();
        String attribute = element.getAttribute("class");
        String domAttribute = element.getDomAttribute("class");
        String domProperty = element.getDomProperty("value");
        String cssValue = element.getCssValue("color");
        String tagName = element.getTagName();
        String accessibleName = element.getAccessibleName();
        String ariaRole = element.getAriaRole();

        System.out.println(text);
        System.out.println(attribute);
        System.out.println(domAttribute);
        System.out.println(domProperty);
        System.out.println(cssValue);
        System.out.println(tagName);
        System.out.println(accessibleName);
        System.out.println(ariaRole);
    }

    // =========================================================
    // 19. ELEMENT STATE
    // =========================================================
    public static void elementState() {
        WebElement element = driver.findElement(By.id("submit"));

        boolean displayed = element.isDisplayed();
        boolean enabled = element.isEnabled();
        boolean selected = element.isSelected();

        System.out.println(displayed);
        System.out.println(enabled);
        System.out.println(selected);
    }

    // =========================================================
    // 20. ELEMENT SIZE / LOCATION
    // =========================================================
    public static void elementGeometry() {
        WebElement element = driver.findElement(By.id("submit"));

        Dimension size = element.getSize();
        Point location = element.getLocation();

        System.out.println(size.getWidth());
        System.out.println(size.getHeight());
        System.out.println(location.getX());
        System.out.println(location.getY());
        System.out.println(element.getRect());
    }

    // =========================================================
    // 21. IMPLICIT WAIT
    // Applies globally to element lookup.
    // In real projects avoid mixing implicit and explicit waits.
    // =========================================================
    public static void implicitWait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebElement element = driver.findElement(By.id("dynamic-element"));
        System.out.println(element.getText());
    }

    // =========================================================
    // 22. EXPLICIT WAIT / WEBDRIVERWAIT
    // =========================================================
    public static void explicitWait() {
        WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement visibleElement = localWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        WebElement clickableButton = localWait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));

        System.out.println(visibleElement.getText());
        clickableButton.click();
    }

    // =========================================================
    // 23. COMMON EXPECTED CONDITIONS
    // =========================================================
    public static void expectedConditions() {
        WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        localWait.until(ExpectedConditions.presenceOfElementLocated(By.id("message")));
        localWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        localWait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
        localWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
        localWait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("status"), "Completed"));
        localWait.until(ExpectedConditions.titleContains("Dashboard"));
        localWait.until(ExpectedConditions.urlContains("/dashboard"));
        localWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(".result"), 0));
    }

    // =========================================================
    // 24. CUSTOM EXPLICIT WAIT WITH LAMBDA
    // =========================================================
    public static void customWait() {
        WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Boolean ready = localWait.until(webDriver -> webDriver.findElement(By.id("status")).getText().equals("READY"));

        System.out.println(ready);
    }

    // =========================================================
    // 25. FLUENTWAIT
    // Controls timeout, polling interval and ignored exceptions.
    // =========================================================
    public static void fluentWait() {
        Wait<WebDriver> fluentWait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);

        WebElement element = fluentWait.until(webDriver -> webDriver.findElement(By.id("dynamic-element")));

        System.out.println(element.getText());
    }

    // =========================================================
    // 26. PAGE LOAD / SCRIPT TIMEOUTS
    // =========================================================
    public static void timeouts() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    }

    // =========================================================
    // 27. HTML SELECT DROPDOWN
    // Works only with real <select> element.
    // =========================================================
    public static void selectDropdown() {
        WebElement dropdownElement = driver.findElement(By.id("country"));
        Select dropdown = new Select(dropdownElement);

        dropdown.selectByVisibleText("Canada");
        dropdown.selectByValue("CA");
        dropdown.selectByIndex(1);

        WebElement selectedOption = dropdown.getFirstSelectedOption();
        List<WebElement> allOptions = dropdown.getOptions();

        System.out.println(selectedOption.getText());
        System.out.println(allOptions.size());
        System.out.println(dropdown.isMultiple());
    }

    // =========================================================
    // 28. MULTI-SELECT / DESELECT
    // Only for <select multiple>.
    // =========================================================
    public static void multiSelectDropdown() {
        Select dropdown = new Select(driver.findElement(By.id("skills")));

        if (dropdown.isMultiple()) {
            dropdown.selectByVisibleText("Java");
            dropdown.selectByValue("selenium");
            dropdown.selectByIndex(2);

            List<WebElement> selectedOptions = dropdown.getAllSelectedOptions();

            for (WebElement option : selectedOptions) {
                System.out.println(option.getText());
            }

            dropdown.deselectByVisibleText("Java");
            dropdown.deselectByValue("selenium");
            dropdown.deselectByIndex(2);
            dropdown.deselectAll();
        }
    }

    // =========================================================
    // 29. CUSTOM DROPDOWN
    // For div/li dropdowns Select does NOT work.
    // Click dropdown -> find option -> click option.
    // =========================================================
    public static void customDropdown() {
        driver.findElement(By.id("country-dropdown")).click();

        List<WebElement> options = driver.findElements(By.cssSelector(".dropdown-option"));

        for (WebElement option : options) {
            if (option.getText().equals("Canada")) {
                option.click();
                break;
            }
        }
    }

    // =========================================================
    // 30. CHECKBOX
    // =========================================================
    public static void checkbox() {
        WebElement checkbox = driver.findElement(By.id("terms"));

        if (!checkbox.isSelected()) {
            checkbox.click();
        }

        System.out.println(checkbox.isSelected());
    }

    // =========================================================
    // 31. RADIO BUTTON
    // =========================================================
    public static void radioButton() {
        WebElement radio = driver.findElement(By.cssSelector("input[type='radio'][value='express']"));

        if (!radio.isSelected()) {
            radio.click();
        }

        System.out.println(radio.isSelected());
    }

    // =========================================================
    // 32. ALERT
    // =========================================================
    public static void alertAccept() {
        driver.findElement(By.id("show-alert")).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();

        System.out.println(text);
        alert.accept();
    }

    // =========================================================
    // 33. CONFIRM ALERT
    // =========================================================
    public static void alertDismiss() {
        driver.findElement(By.id("show-confirm")).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        alert.dismiss();
    }

    // =========================================================
    // 34. PROMPT ALERT
    // =========================================================
    public static void alertPrompt() {
        driver.findElement(By.id("show-prompt")).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        alert.sendKeys("Danil");
        alert.accept();
    }

    // =========================================================
    // 35. IFRAME BY INDEX
    // =========================================================
    public static void iframeByIndex() {
        driver.switchTo().frame(0);

        WebElement elementInsideFrame = driver.findElement(By.id("inside-frame"));
        System.out.println(elementInsideFrame.getText());

        driver.switchTo().defaultContent();
    }

    // =========================================================
    // 36. IFRAME BY NAME OR ID
    // =========================================================
    public static void iframeByNameOrId() {
        driver.switchTo().frame("payment-frame");

        driver.findElement(By.id("card-number")).sendKeys("4111111111111111");

        driver.switchTo().defaultContent();
    }

    // =========================================================
    // 37. IFRAME BY WEBELEMENT + PARENT FRAME
    // =========================================================
    public static void iframeByWebElement() {
        WebElement frame = driver.findElement(By.cssSelector("iframe.payment-frame"));

        driver.switchTo().frame(frame);
        driver.findElement(By.id("card-number")).sendKeys("4111111111111111");

        driver.switchTo().parentFrame();
        driver.switchTo().defaultContent();
    }

    // =========================================================
    // 38. WAIT FOR FRAME AND SWITCH
    // =========================================================
    public static void waitForIframe() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("payment-frame")));

        driver.findElement(By.id("card-number")).sendKeys("4111111111111111");

        driver.switchTo().defaultContent();
    }

    // =========================================================
    // 39. CURRENT WINDOW HANDLE / ALL WINDOW HANDLES
    // =========================================================
    public static void windowHandles() {
        String currentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        System.out.println(currentWindow);
        System.out.println(allWindows);
    }

    // =========================================================
    // 40. SWITCH TO NEW TAB OR WINDOW
    // =========================================================
    public static void switchToNewWindow() {
        String originalWindow = driver.getWindowHandle();

        driver.findElement(By.linkText("Open new window")).click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        System.out.println(driver.getTitle());

        driver.close();
        driver.switchTo().window(originalWindow);
    }

    // =========================================================
    // 41. CREATE NEW TAB / WINDOW WITH SELENIUM
    // =========================================================
    public static void createNewTabAndWindow() {
        String originalWindow = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://example.com");
        driver.close();

        driver.switchTo().window(originalWindow);

        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://selenium.dev");
        driver.close();

        driver.switchTo().window(originalWindow);
    }

    // =========================================================
    // 42. CLOSE VS QUIT
    // close() -> current window/tab
    // quit() -> entire WebDriver session
    // =========================================================
    public static void closeVsQuit() {
        driver.close();

        // driver.quit();
    }

    // =========================================================
    // 43. ACTIONS - HOVER
    // =========================================================
    public static void hover() {
        Actions actions = new Actions(driver);
        WebElement menu = driver.findElement(By.id("menu"));

        actions.moveToElement(menu).perform();
    }

    // =========================================================
    // 44. ACTIONS - DOUBLE CLICK
    // =========================================================
    public static void doubleClick() {
        Actions actions = new Actions(driver);
        WebElement button = driver.findElement(By.id("button"));

        actions.doubleClick(button).perform();
    }

    // =========================================================
    // 45. ACTIONS - RIGHT CLICK
    // =========================================================
    public static void rightClick() {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(By.id("context-menu"));

        actions.contextClick(element).perform();
    }

    // =========================================================
    // 46. ACTIONS - DRAG AND DROP
    // =========================================================
    public static void dragAndDrop() {
        Actions actions = new Actions(driver);
        WebElement source = driver.findElement(By.id("source"));
        WebElement target = driver.findElement(By.id("target"));

        actions.dragAndDrop(source, target).perform();
    }

    // =========================================================
    // 47. ACTIONS - CLICK AND HOLD / MOVE / RELEASE
    // =========================================================
    public static void clickHoldMoveRelease() {
        Actions actions = new Actions(driver);
        WebElement source = driver.findElement(By.id("source"));
        WebElement target = driver.findElement(By.id("target"));

        actions.clickAndHold(source).moveToElement(target).release().perform();
    }

    // =========================================================
    // 48. ACTIONS - KEYBOARD COMBINATIONS
    // =========================================================
    public static void actionsKeyboard() {
        Actions actions = new Actions(driver);
        WebElement input = driver.findElement(By.id("search"));

        actions.click(input).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("Selenium WebDriver").sendKeys(Keys.ENTER).perform();
    }

    // =========================================================
    // 49. ACTIONS - SCROLL
    // =========================================================
    public static void actionsScroll() {
        Actions actions = new Actions(driver);
        WebElement footer = driver.findElement(By.tagName("footer"));

        actions.scrollToElement(footer).perform();
        actions.scrollByAmount(0, 500).perform();
    }

    // =========================================================
    // 50. JAVASCRIPTEXECUTOR
    // Use mainly when normal WebDriver interaction is insufficient.
    // =========================================================
    public static void javascriptExecutor() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement button = driver.findElement(By.id("submit"));

        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
        js.executeScript("arguments[0].click();", button);

        String title = (String) js.executeScript("return document.title;");
        System.out.println(title);
    }

    // =========================================================
    // 51. SCREENSHOT - FULL PAGE VIEWPORT
    // =========================================================
    public static void screenshot() throws IOException {
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Path destination = Path.of("screenshots", "page.png");

        Files.createDirectories(destination.getParent());
        Files.copy(source.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

        System.out.println(destination.toAbsolutePath());
    }

    // =========================================================
    // 52. SCREENSHOT - SINGLE ELEMENT
    // =========================================================
    public static void elementScreenshot() throws IOException {
        WebElement element = driver.findElement(By.id("result"));
        File source = element.getScreenshotAs(OutputType.FILE);
        Path destination = Path.of("screenshots", "element.png");

        Files.createDirectories(destination.getParent());
        Files.copy(source.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
    }

    // =========================================================
    // 53. FILE UPLOAD
    // For <input type="file"> use sendKeys with local file path.
    // =========================================================
    public static void fileUpload() {
        Path filePath = Path.of("test-data", "sample.txt").toAbsolutePath();
        WebElement fileInput = driver.findElement(By.cssSelector("input[type='file']"));

        fileInput.sendKeys(filePath.toString());

        driver.findElement(By.id("upload-button")).click();
    }

    // =========================================================
    // 54. COOKIES
    // =========================================================
    public static void cookies() {
        Cookie cookie = new Cookie("testUser", "Danil");

        driver.manage().addCookie(cookie);

        Cookie savedCookie = driver.manage().getCookieNamed("testUser");
        Set<Cookie> allCookies = driver.manage().getCookies();

        System.out.println(savedCookie);
        System.out.println(allCookies);

        driver.manage().deleteCookieNamed("testUser");
        driver.manage().deleteAllCookies();
    }

    // =========================================================
    // 55. SHADOW DOM
    // Selenium 4
    // =========================================================
    public static void shadowDom() {
        WebElement shadowHost = driver.findElement(By.cssSelector("my-component"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        WebElement button = shadowRoot.findElement(By.cssSelector("button"));

        button.click();
    }

    // =========================================================
    // 56. ACTIVE ELEMENT
    // =========================================================
    public static void activeElement() {
        WebElement activeElement = driver.switchTo().activeElement();

        activeElement.sendKeys("Text for focused element");
    }

    // =========================================================
    // 57. CHECK IF ELEMENT EXISTS WITHOUT EXCEPTION
    // findElements is useful for existence checks.
    // =========================================================
    public static void elementExists() {
        boolean exists = !driver.findElements(By.id("optional-element")).isEmpty();

        System.out.println(exists);
    }

    // =========================================================
    // 58. BASIC EXCEPTION HANDLING
    // =========================================================
    public static void seleniumExceptions() {
        try {
            WebElement element = driver.findElement(By.id("missing-element"));
            element.click();
        } catch (NoSuchElementException e) {
            System.out.println("Element was not found");
        } catch (StaleElementReferenceException e) {
            System.out.println("Element became stale");
        } catch (TimeoutException e) {
            System.out.println("Wait timed out");
        }
    }

    // =========================================================
    // 59. HANDLE STALE ELEMENT WITH RE-LOCATING
    // =========================================================
    public static void staleElementExample() {
        By saveButton = By.id("save");

        try {
            driver.findElement(saveButton).click();
        } catch (StaleElementReferenceException e) {
            driver.findElement(saveButton).click();
        }
    }

    // =========================================================
    // 60. WAIT UNTIL OLD ELEMENT IS STALE
    // =========================================================
    public static void waitForStaleness() {
        WebElement oldElement = driver.findElement(By.id("result"));

        driver.findElement(By.id("refresh-result")).click();
        wait.until(ExpectedConditions.stalenessOf(oldElement));
    }

    // =========================================================
    // 61. BASIC PAGE OBJECT MODEL USAGE
    // =========================================================
    public static void pageObjectModelUsage() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.open();
        loginPage.login("tomsmith", "SuperSecretPassword!");

        System.out.println(loginPage.getFlashMessage());
    }

    public static class LoginPage {
        private final WebDriver driver;
        private final By usernameInput = By.id("username");
        private final By passwordInput = By.id("password");
        private final By loginButton = By.cssSelector("button[type='submit']");
        private final By flashMessage = By.id("flash");

        public LoginPage(WebDriver driver) {
            this.driver = driver;
        }

        public void open() {
            driver.get("https://the-internet.herokuapp.com/login");
        }

        public void login(String username, String password) {
            driver.findElement(usernameInput).clear();
            driver.findElement(usernameInput).sendKeys(username);
            driver.findElement(passwordInput).clear();
            driver.findElement(passwordInput).sendKeys(password);
            driver.findElement(loginButton).click();
        }

        public String getFlashMessage() {
            return driver.findElement(flashMessage).getText();
        }
    }

    // =========================================================
    // 62. REMOTE WEBDRIVER / SELENIUM GRID
    // Example only: requires a running Grid/server.
    // =========================================================
    public static void remoteWebDriver() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        WebDriver remoteDriver = new RemoteWebDriver(new URL("http://localhost:4444"), options);

        remoteDriver.get("https://example.com");
        System.out.println(remoteDriver.getTitle());

        remoteDriver.quit();
    }

    // =========================================================
    // 63. BASIC VALIDATION WITHOUT TEST FRAMEWORK
    // Selenium itself is not an assertion library.
    // In real tests use JUnit/TestNG assertions.
    // =========================================================
    public static void basicValidation() {
        String expectedTitle = "Example Domain";
        String actualTitle = driver.getTitle();

        if (!actualTitle.equals(expectedTitle)) {
            throw new AssertionError("Expected title: " + expectedTitle + ", actual title: " + actualTitle);
        }

        System.out.println("PASSED");
    }

    // =========================================================
    // 64. REUSABLE HELPER: WAIT + CLICK
    // =========================================================
    public static void clickWhenReady(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

        element.click();
    }

    // =========================================================
    // 65. REUSABLE HELPER: WAIT + TYPE
    // =========================================================
    public static void typeWhenVisible(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        element.clear();
        element.sendKeys(text);
    }

    // =========================================================
    // 66. REUSABLE HELPER: GET TEXT
    // =========================================================
    public static String getTextWhenVisible(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        return element.getText();
    }

    // =========================================================
    // 67. CLEAN BROWSER SHUTDOWN
    // =========================================================
    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
            wait = null;
        }
    }
}

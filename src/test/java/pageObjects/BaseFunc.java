package pageObjects;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class BaseFunc {
    private static final String CHROME_DRIVER_URL = "/Users/elena.zakharova/tests/chromedriver";
    private final String URL = " https://ctco.lv/en";
    private final WebDriver driver;

    /**
     * browser launch
     *
     */
    public BaseFunc(){
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_URL);
        driver = new ChromeDriver();
        navigateToUrl(URL);
    }

    /**
     * Go to the requested url
     *
     * @param url link to go
     */
    private void navigateToUrl(String url) {
        Preconditions.checkNotNull(url, "requested url = null");
        driver.get(url);
    }

    /**
     * Close the browser window
     */
    public void closeWindow() {
        driver.close();
    }

    /**
     * Checking element is present on page
     *
     * @param by element
     * @return true/false
     */
    public boolean isPresentElement(By by) {
        List<WebElement> elements = driver.findElements(by);
        return elements.size() != 0;
    }

    /**
     * find element on page
     *
     * @param by locator of the element
     * @return element. If element is not found then exeption
     */
    public WebElement findElemWithDriver(By by) {
        final Stopwatch timer = Stopwatch.createStarted();
        try {
            return driver.findElement(by);
        }
        finally {
            timer.stop();
        }
    }

    /**
     * Check parent and child elements are in the Dom
     *
     * @param parentLocator locator of the parent element
     * @param childLocator  locator of the child element
     * @return true - if parent and child elements are in the DOM. Else - false
     */
    public boolean isPresentElementAndChild(By parentLocator, By childLocator) {
        if (isPresentElement(parentLocator)) {
            WebElement parentElement = findElemWithDriver(parentLocator);
            try {
                parentElement.findElement(childLocator);
            } catch (StaleElementReferenceException se) {
                se.printStackTrace();
                throw new IllegalStateException(se);
            } catch (WebDriverException e) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Waits for a condition to be met for a specified amount of time
     *
     * @param timeOutInSeconds maximum wait time in seconds
     * @param element expected element
     */
    public void explicitWaitVisibilityOfElementLocated(int timeOutInSeconds, By element){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    /**
     * Hover over the element
     *
     * @param element needed element
     */
    public void moveToElement(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    /**
     * Returns the number of child elements with a specific locator
     *
     * @param parent parent element
     * @param child child element
     * @return count of child elements
     */
    public int getChildElementsCount(By parent, By child){
        return driver.findElement(parent).findElements(child).size();
    }
}

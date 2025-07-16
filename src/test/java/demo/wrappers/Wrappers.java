package demo.wrappers;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */

     private ChromeDriver driver;
    public  WebDriverWait wait;

    public Wrappers(ChromeDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void openUrl( String url) {
        driver.get(url);
        System.out.println("Test step: Successfully Navigated to URL: " + url);
    }

    public void searchBox(String searchText) {
        try {
            WebElement searchbox = driver.findElement(By.xpath("//input[@type='text']"));
            searchbox.sendKeys(searchText, Keys.ENTER);
            System.out.println("Test step: Search text '" + searchText + "' searched successfully");
        } catch (Exception e) {
            System.out.println("Error occured " + e.getMessage());
        }
    }

    public void assertCurrentUrl(String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();
        if(!actualUrl.equals(expectedUrl)) {
            throw new AssertionError("Test step: Expected URL: " +expectedUrl + ", but got: " + actualUrl);
        }else {
              System.out.println("Test step: Assertion passed: Current Url is as expected.");
        }
    }

    public void click(WebElement locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public void scrollToElement(WebElement element) {
        
        try {
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    } catch (Exception e) {
        System.out.println("Test step: Failed to scroll to element");
    }
    }

    public void jsClick(WebElement element) {
        try {
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();", element);
    } catch (Exception e) {
        System.out.println("Test step: Failed to JavaScript click on element");
    }
    }

    public String getElementText(WebElement element) {
        return element.getText();
    }

    public boolean isVisible(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch(Exception e) {
        return false;
        }
    }

    public void arrowNavigation(WebElement element, int times) throws InterruptedException {
        for(int i=0; i<times; i++) {
           try {
            if(isVisible(element)) {
                jsClick(element);
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            System.out.println("Test step: Arrow is not visible, cannot perform navigation");
                break;
        }
        }
        System.out.println("Test step: Arrow navigation success");
    }

    public long convertViews(String views) {

        try {

        if (views == null || views.isEmpty()) {
            return 0;
        }
        views = views.trim().toUpperCase();
        String suffix = views.replaceAll("[0-9.,]", "");
        String numberPart = views.replaceAll("[^0-9\\.]", "");

        double number = Double.parseDouble(numberPart);

        switch (suffix) {
            case "K":
                return (long) (number * 1000);
            case "M":
                return (long) (number * 1000000);
            case "B":
                return (long) (number * 1000000000);
            default:
                return (long) number;
        }
    }

    catch (NumberFormatException e) {
        System.out.println("Test step: Error converting views: " + e.getMessage());
        return 0;
    }
    }
}

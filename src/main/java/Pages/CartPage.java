package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private By carttable = By.xpath("//*[@id='tbodyid']");
    private By totalprice = By.id("totalp");
    private By placeOrderButton = By.xpath("//button[text()='Place Order']");
    private By namebox = By.id("name");
    private By countrybox = By.id("country");
    private By citybox = By.id("city");
    private By creditcardbox = By.id("card");
    private By monthbox = By.id("month");
    private By yearbox = By.id("year");
    private By textmsg = By.xpath("//h2[text()='Thank you for your purchase!']");
    private By purchaseButton = By.xpath("//button[text()='Purchase']");

    // Get item title by index
    public String getCartItemTitle(int index) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(carttable));
        List<WebElement> rows = driver.findElement(carttable).findElements(By.tagName("tr"));
        if (index < rows.size()) {
            return rows.get(index).findElements(By.tagName("td")).get(1).getText().trim().toLowerCase();
        }
        return "";
    }

    // Get item price by index
    public String getCartItemPrice(int index) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(carttable));
        List<WebElement> rows = driver.findElement(carttable).findElements(By.tagName("tr"));
        if (index < rows.size()) {
            return rows.get(index).findElements(By.tagName("td")).get(2).getText().trim();
        }
        return "";
    }

    // Validate Cart Items
    public boolean validateCartItems(String expectedTitle1, String expectedTitle2, String expectedPrice1, String expectedPrice2) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(carttable));
        List<WebElement> rows = driver.findElement(carttable).findElements(By.tagName("tr"));

        if (rows.size() != 2) {
            System.out.println("Expected 2 items in cart, but found: " + rows.size());
            return false;
        }

        String actualTitle1 = getCartItemTitle(0).trim().toLowerCase();
        String actualPrice1 = getCartItemPrice(0).trim();
        String actualTitle2 = getCartItemTitle(1).trim().toLowerCase();
        String actualPrice2 = getCartItemPrice(1).trim();

        System.out.println("Actual Item 1: " + actualTitle1);
        System.out.println("Actual Price 1: " + actualPrice1);
        System.out.println("Actual Item 2: " + actualTitle2);
        System.out.println("Actual Price 2: " + actualPrice2);

        return actualTitle1.equals(expectedTitle1.trim().toLowerCase()) &&
                actualPrice1.equals(expectedPrice1.trim()) &&
                actualTitle2.equals(expectedTitle2.trim().toLowerCase()) &&
                actualPrice2.equals(expectedPrice2.trim());
    }

    // Verify Total
    public boolean verifyTotal(String expectedTotalText) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(totalprice));
        String actualTotal = driver.findElement(totalprice).getText().trim();
        System.out.println("Expected Total: " + expectedTotalText + ", Actual Total: " + actualTotal);
        return actualTotal.equals(expectedTotalText.trim());
    }

    // Complete Checkout
    public boolean completeCheckout(String name, String country, String city, String card, String month, String year) {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(namebox)).sendKeys(name);
        driver.findElement(countrybox).sendKeys(country);
        driver.findElement(citybox).sendKeys(city);
        driver.findElement(creditcardbox).sendKeys(card);
        driver.findElement(monthbox).sendKeys(month);
        driver.findElement(yearbox).sendKeys(year);
        driver.findElement(purchaseButton).click();

        return wait.until(ExpectedConditions.visibilityOfElementLocated(textmsg)).isDisplayed();
    }

    public String getText() {
        return driver.findElement(textmsg).getText().trim();
    }


}
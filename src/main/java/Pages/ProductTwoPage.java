package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductTwoPage {
    WebDriver driver;
    WebDriverWait wait;
    public ProductTwoPage(WebDriver driver) {
        this.driver = driver;
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    //locators
    private By addToCartbox= By.cssSelector("a.btn.btn-success.btn-lg[onclick=\"addToCart(3)\"]\n");
    private By Homebuttonlink=By.partialLinkText("Home");
    //action

    public void clickhomebutton(){
        wait.until(ExpectedConditions.elementToBeClickable(Homebuttonlink));
        driver.findElement(Homebuttonlink).click();
    }
    public void clickAddToCart(){
        wait.until(ExpectedConditions.elementToBeClickable(addToCartbox));  // Use elementToBeClickable
        driver.findElement(addToCartbox).click();
    }
    public String alertText() {
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert().getText();
    }

    public void alertClick() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
}



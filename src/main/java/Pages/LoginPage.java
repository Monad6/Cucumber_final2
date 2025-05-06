package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    //locators
    private By usernamebox = By.id("loginusername");
    private By passwordbox = By.id("loginpassword");
    private By loginButton = By.cssSelector("button.btn.btn-primary[onclick=\"logIn()\"]");
    private By verificationofuser= By.id("nameofuser");

    //actions
    public void insertUserName(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernamebox));
        driver.findElement(usernamebox).sendKeys(username);

    }
    public void insertPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordbox));
        driver.findElement(passwordbox).sendKeys(password);

    }
    public void clickonloginbutton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));  // Use elementToBeClickable
        driver.findElement(loginButton).click();
    }


    public String getverificationofuser() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(verificationofuser));
        return driver.findElement(verificationofuser).getText();
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
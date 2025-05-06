package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignUpPage {
    WebDriver driver;
    WebDriverWait wait;
    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    //locators
    private By usernamebox = By.id("sign-username");
    private By passwordbox = By.id("sign-password");
    private By SignUpButton = By.cssSelector("button[onclick='register()']");


    //actions
    public void enterUsername(String username) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(usernamebox));
        driver.findElement(usernamebox).sendKeys(username);

    }
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordbox));
        driver.findElement(passwordbox).sendKeys(password);

    }
    public void clickSignUpButton() {

        wait.until(ExpectedConditions.elementToBeClickable(SignUpButton));  // Use elementToBeClickable
        driver.findElement(SignUpButton).click();
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


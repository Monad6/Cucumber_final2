package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //locators
    private By signUpButton = By.linkText("Sign up");
    private By loginButton = By.linkText("Log in");
    private By ProductOne = By.linkText("Samsung galaxy s6");
    private By ProductTwo = By.linkText("Nexus 6");
    private By CartButton = By.linkText("Cart");

    //actions
    public SignUpPage clickonSignUplink() {
        driver.findElement(signUpButton).click();
        return new SignUpPage(driver);

    }

    public LoginPage clickonLoginlink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
        return new LoginPage(driver);
    }

    public ProductOnePage clickonProductOne() {
        wait.until(ExpectedConditions.elementToBeClickable(ProductOne));
        driver.findElement(ProductOne).click();
        return new ProductOnePage(driver);
    }

    public ProductTwoPage clickonProductTwo() {
        wait.until(ExpectedConditions.elementToBeClickable(ProductTwo));
        driver.findElement(ProductTwo).click();
        return new ProductTwoPage(driver);

    }

    public CartPage clickonCart() {
        wait.until(ExpectedConditions.elementToBeClickable(CartButton));
        driver.findElement(CartButton).click();
        return new CartPage(driver);

    }


}




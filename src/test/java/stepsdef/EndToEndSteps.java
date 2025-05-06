package stepsdef;

import Pages.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.ja.且つ;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class EndToEndSteps {
    WebDriver driver = Hooks.getDriver();
    HomePage homePage = new HomePage(driver);
    LoginPage loginPage;
    ProductOnePage productOnePage;
    ProductTwoPage productTwoPage;
    CartPage cartPage;

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust wait time as needed

    @Given("user opens homepage and clicks on LogIn")
    public void userOpensHomepageAndClicksOnLogIn() {
        homePage.clickonLoginlink();
    }

    @When("user enters {string} and {string} and press on LogIn button")
    public void userEntersUsernameAndPasswordAndPressOnLogInButton(String username, String password) throws InterruptedException {
        loginPage = new LoginPage(driver);
        loginPage.insertUserName(username);
        Thread.sleep(1000);
        loginPage.insertPassword(password);
        loginPage.clickonloginbutton();
        Thread.sleep(1000);
    }

    @Then("user should be logged in successfully")
    public void userShouldBeLoggedInSuccessfully() {
        String actualResult = loginPage.getverificationofuser().toLowerCase().trim();
        String expectedResult = "welcome monad";
        Assert.assertEquals(actualResult, expectedResult, "Login verification failed!");
    }

    @When("user adds the first product {string} to the cart")
    public void userAddsTheFirstProductToTheCart(String arg0) {
        productOnePage = homePage.clickonProductOne();
        productOnePage.clickAddToCart();
        String actualResult = productOnePage.alertText();
        productOnePage.alertClick();
        String expectedResult = "Product added.";
        Assert.assertEquals(actualResult, expectedResult);
        productOnePage.clickhomebutton();


    }

    @And("user adds the second product {string} to the cart")
    public void userAddsTheSecondProductToTheCart(String arg0) {
        productTwoPage = homePage.clickonProductTwo();
        productTwoPage.clickAddToCart();
        String actualResult = productTwoPage.alertText();
        productTwoPage.alertClick();
        String expectedResult = "Product added.";
        Assert.assertEquals(actualResult, expectedResult);
        productTwoPage.clickhomebutton();
    }

    @And("user navigates to the cart")
    public void userNavigatesToTheCart() {
        cartPage = homePage.clickonCart();
    }

    @Then("cart should contains the products")
    public void cartShouldContainsTheProducts() throws InterruptedException {
        // Wait for 2 seconds to ensure cart items are loaded
        Thread.sleep(2000);

        // Step 1: Validate Cart - Ensure both products are in the cart
        String expectedTitle1 = "Samsung galaxy s6";
        String expectedPrice1 = "360";
        String expectedTitle2 = "Nexus 6";
        String expectedPrice2 = "650";

        // Log actual values for debugging
        String actualTitle1 = cartPage.getCartItemTitle(0).trim();  // Corrected index
        String actualTitle2 = cartPage.getCartItemTitle(1).trim();  // Corrected index
        String actualPrice1 = cartPage.getCartItemPrice(0).trim();  // Corrected index
        String actualPrice2 = cartPage.getCartItemPrice(1).trim();  // Corrected index

        System.out.println("Actual Item 1: " + actualTitle1);
        System.out.println("Actual Price 1: " + actualPrice1);
        System.out.println("Actual Item 2: " + actualTitle2);
        System.out.println("Actual Price 2: " + actualPrice2);


        // Validate Cart Items
        boolean itemsAreCorrect = cartPage.validateCartItems(expectedTitle1, expectedTitle2, expectedPrice1, expectedPrice2);
        Assert.assertTrue(itemsAreCorrect, "Cart items are incorrect!");
    }

    @And("total price should be {int}")
    public void totalPriceShouldBe(int expectedTotal) {
        boolean totalIsCorrect = cartPage.verifyTotal(String.valueOf(expectedTotal));
        Assert.assertTrue(totalIsCorrect, "Total amount is not correct!");
    }



    @When("users enters place order")
    public void users_enters_place_order(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Map<String, String> row = data.get(0); // Only one row expected

        String name = row.get("Name");
        String country = row.get("Country");
        String city = row.get("city");
        String card = row.get("card");
        String month = row.get("Month");
        String year = row.get("Year");

        boolean checkoutSuccess = cartPage.completeCheckout(name, country, city, card, month, year);
        Assert.assertTrue(checkoutSuccess, "Checkout failed – confirmation not displayed.");
    }

    @Then("confirmation message should appear {string}")
    public void confirmationMessageShouldAppear(String arg0) throws InterruptedException {
        String actualResult = cartPage.getText();
        String expectedResult = "Thank you for your purchase!";
        Assert.assertTrue(actualResult.contains(expectedResult));
        Thread.sleep(1000);

    }
}



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

    @When("user enters username and password and press on LogIn button")
    public void userEntersUsernameAndPasswordAndPressOnLogInButton() throws InterruptedException {
        loginPage.insertUserName("Monad");
        Thread.sleep(1000);
        loginPage.insertPassword("monad1234");

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
    public void cartShouldContainsTheProducts() {
        List<String> actualResultNames = cartPage.getAllCartItemTitles();
        List<String> actualResultPrices = cartPage.getAllCartItemPrices();
        List<String> expectedResultNames = Arrays.asList("Samsung galaxy s6 ", "Nexus 6");
        List<String> expectedResultPrices = Arrays.asList("360", "650");
        Assert.assertEquals(actualResultNames.containsAll(expectedResultNames), "Not all expected results are found");
        Assert.assertTrue(actualResultPrices.containsAll(expectedResultPrices), "Not all expected prices are found");
    }

    @And("total price should be {int}")
    public void totalPriceShouldBe(int expectedTotal) {
        boolean isCorrectTotal = cartPage.verifyTotal(String.valueOf(expectedTotal));
        Assert.assertTrue(isCorrectTotal, "Total price does not match expected value.");
    }

    @When("users enters place order")
    public void usersEntersPlaceOrder(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Map<String, String> userData = data.get(0);

        String name = userData.get("Name");
        String country = userData.get("Country");
        String city = userData.get("city");
        String card = userData.get("card");
        String month = userData.get("Month");
        String year = userData.get("Year");

        boolean isSuccess = cartPage.completeCheckout(name, country, city, card, month, year);
        Assert.assertTrue(isSuccess, "Checkout was not successful.");
    }

    @Then("confirmation message should appear {string}")
    public void confirmationMessageShouldAppear(String arg0) throws InterruptedException {
        String actualResult = cartPage.getText();
        String expectedResult = "Thank you for your purchase!";
        Assert.assertTrue(actualResult.contains(expectedResult));
        Thread.sleep(1000);

    }
}



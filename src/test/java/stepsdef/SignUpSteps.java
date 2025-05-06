package stepsdef;

import Pages.HomePage;
import Pages.SignUpPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SignUpSteps {

    WebDriver driver = Hooks.getDriver();
    HomePage homePage = new HomePage(driver);
    SignUpPage signUpPage;

    @Given("user opens homepage and clicks on SignUp button")
    public void user_opens_homepage_and_clicks_on_signUp_button() {
        signUpPage = homePage.clickonSignUplink();
    }

    @When("user enters a valid username and a valid password, then clicks on the sign-up button")
    public void user_enters_valid_username_and_password_then_clicks_on_sign_up_button() {
        signUpPage.enterUsername("MonadKhaled");
        signUpPage.enterPassword("Monadkhaled1234");
        signUpPage.clickSignUpButton();
    }

    @Then("a successful message appears and user is switched to the homepage")
    public void successful_message_appears_and_user_is_switched_to_the_homepage() throws InterruptedException {
        String alertText = signUpPage.alertText();
        Assert.assertTrue(alertText.toLowerCase().contains("sign up successful") ||
                        alertText.toLowerCase().contains("success"),
                "Expected success message not found in alert!");

        signUpPage.alertClick();
        Thread.sleep(2000);
    }
}

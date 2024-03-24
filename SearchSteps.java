package StepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchSteps {
    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\chrome\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Given("User is on Home Page")
    public void user_is_on_home_page() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("User navigate to Login Page")
    public void user_navigate_to_login_page() {
        // Already on the login page, nothing to navigate here
    }

    @Then("User enters {string} and {string}")
    public void user_enters_username_and_password(String username, String password) {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        usernameField.sendKeys(username);

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
    }

    @Then("Keeping case as Valid")
    public void keeping_case_as_valid() {
        // This step ensures that the test keeps the case as valid,
        // you may not need additional actions here since the username and password were already entered.
        // If additional actions are needed, you can add them here.
    }

    @Then("Keeping case as Invalid")
    public void keeping_case_as_invalid() {
        // This step ensures that the test keeps the case as invalid,
        // you may not need additional actions here since the username and password were already entered.
        // If additional actions are needed, you can add them here.
    }

    @Then("User should get logged in")
    public void user_should_get_logged_in() {
        String expectedURL = "https://www.saucedemo.com/inventory.html";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(expectedURL, actualURL);
    }

    @Then("Message displayed Login Successfully")
    public void message_displayed_login_successfully() {
        WebElement successMessage = driver.findElement(By.xpath("//h3[text()='Products']"));
        Assert.assertTrue(successMessage.isDisplayed());
    }

    @Then("User enters {string} and {string} and Keeping case as {string}")
    public void user_enters_username_and_password_and_case(String username, String password, String caseType) {
        user_enters_username_and_password(username, password);
        if (caseType.equalsIgnoreCase("Valid")) {
            user_should_get_logged_in();
            message_displayed_login_successfully();
        } else if (caseType.equalsIgnoreCase("Invalid")) {
            WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));
            Assert.assertTrue(errorMessage.isDisplayed());
        }
    }

    @Then("Provide correct credentials")
    public void provide_correct_credentials() {
        user_enters_username_and_password("standard_user", "secret_sauce");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

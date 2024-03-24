package StepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchSteps {
    WebDriver driver;

    @Given("User is on Home Page")
    public void user_is_on_home_page() {
        System.setProperty("webdriver.chrome.driver", "D:\\Training\\chrome\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @When("User navigate to Login Page")
    public void user_navigate_to_login_page() {
    	System.setProperty("webdriver.chrome.driver", "D:\\Training\\chrome\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
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
        
    }

    @Then("Keeping case as Invalid")
    public void keeping_case_as_invalid() {
        
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
    	 WebElement usernameField = driver.findElement(By.id("user-name"));
         usernameField.sendKeys("standard_user");

         WebElement passwordField = driver.findElement(By.id("password"));
         passwordField.sendKeys("secret_sauce");

         WebElement loginButton = driver.findElement(By.id("login-button"));
         loginButton.click();
    }

    @Then("User closes the browser")
    public void user_closes_the_browser() {
        driver.quit();
    }
}

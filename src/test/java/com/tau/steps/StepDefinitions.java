package com.tau.steps;

import com.tau.base.BaseUtil;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.List;

import static org.junit.Assert.assertTrue;


public class StepDefinitions extends BaseUtil {

    private BaseUtil baseUtil;  // BaseUtil class object

    public StepDefinitions(BaseUtil util){ // StepDefinition class constructor

        this.baseUtil = util;
    }
    private WebDriver driver;
    @Before
    public void setup(){    // Before Hook executes before each Scenario
        System.setProperty("web.chrome.driver","/Users/hqayyum/Desktop/tau-cucumber-course/chromedriver"); // If you're getting error of latest chromedriver version then setup chrome driver manually
        driver = new ChromeDriver(); // Creates instance of chromedriver
    }

    @Given("I am in the login page of the Para Bank Application")
    public void iAmInTheLoginPageOfTheParaBankApplication() {
        //WebDriverManager.chromedriver().setup(); // It picks the latest version of chrome driver
        driver.navigate().to("https://parabank.parasoft.com/parabank/index.htm"); // navigates to the given url
    }

    @When("I enter valid {string} and {string} with {string}")
    public void iEnterValidCredentials(String username, String password, String userFullName1) {
        baseUtil.userFullName = userFullName1;
        driver.findElement(By.name("username")).sendKeys(username); // Find the element by name and enter the value in the input field
        driver.findElement(By.name("password")).sendKeys(password); // same as above
        driver.findElement(By.cssSelector("[class=\"button\"]:nth-child(1)")).submit(); // Find the button and click it.
    }

//    @When("I enter valid {string} and {string}")
//    public void iEnterValidCredentials(String username, String password) {
//        driver.findElement(By.name("username")).sendKeys(username); // Find the element by name and enter the value in the input field
//        driver.findElement(By.name("password")).sendKeys(password); // same as above
//        driver.findElement(By.cssSelector("[class=\"button\"]:nth-child(1)")).submit(); // Find the button and click it.
//    }

//    @When("I enter valid credentials")
//    public void iEnterValidCredentials(DataTable table ) {
//        List<String> loginForm = table.asList();    // Iterating table data and assigning it to the list.
//        driver.findElement(By.name("username")).sendKeys(loginForm.get(0)); // Find the element by name and enter the value in the first index input field
//        driver.findElement(By.name("password")).sendKeys(loginForm.get(1)); // Find the element by name and enter the value in the second index input field
//        driver.findElement(By.cssSelector("[class=\"button\"]:nth-child(1)")).submit(); // Find the button and click it.
//    }



//    @Then("I should be taken to the Overview page")
//    public void iShouldBeTakenToTheOverviewPage()   throws Exception { // throw Exception: allows us to use Thread.sleep(Stops the execution for the given time irrespective to that it find the element)
//        Thread.sleep(5000); // Stops the executions for the 5000 milliseconds
//        driver.findElement(By.xpath("//*[@id=\"leftPanel\"]/ul/li[8]/a")).isDisplayed(); // The element should be visible
//        driver.findElement(By.linkText("Log Out")).click(); // Click the hyperlink Log Out
//    }

    @Then("I should be taken to the Overview page")
    public void iShouldBeTakenToTheOverviewPage()   throws Exception { // throw Exception: allows us to use Thread.sleep(Stops the execution for the given time irrespective to that it find the element)
        Thread.sleep(5000); // Stops the executions for the 5000 milliseconds
        String actualUserName = driver.findElement(By.className("smallText")).getText().toString();
        System.out.println(baseUtil.userFullName.toString());
        assertTrue (actualUserName ,actualUserName.contains(baseUtil.userFullName));
        driver.findElement(By.linkText("Log Out")).click(); // Click the hyperlink Log Out
    }

    @After
    public void quitBrowser(){  //  After Hook executes after each Scenario
        driver.quit();
    }


}

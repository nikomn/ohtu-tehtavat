package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {

    //WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";

    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
    }

    @Given("command new user is selected")
    public void newUserIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    @Given("user with username {string} with password {string} is successfully created")
    public void userWithUsernameWithPasswordIsSuccessfullyCreated(String username, String password) {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        createUserWith(username, password, password);
        pageHasContent("Welcome to Ohtu Application!");
        goToMainPage();
        logOut();
        //try{ Thread.sleep(120000); } catch(Exception e){}  // suoritus pysähtyy 120 sekunniksi
    }

    @Given("user with username {string} and password {string} is tried to be created")
    public void userWithUsernameAndPasswordIsTriedToBeCreated(String username, String password) {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        createUserWith(username, password, password);
        pageHasContent("username should have at least 3 characters");
        driver.get(baseUrl);
    }

    @When("valid username {string} and password {string} and matching password confirmation are entered")
    public void validUsernameAndPasswordAndMatchingPasswordConfirmationAreEntered(String username, String password) {
        createUserWith(username, password, password);
    }

    @Then("a new user is created")
    public void aNewUserIsCreated() {
        pageHasContent("Welcome to Ohtu Application!");
    }

    @When("invalid username {string} and password {string} and matching password confirmation are entered")
    public void invalidUsernameAndPasswordAndMatchingPasswordConfirmationAreEntered(String username, String password) {
        createUserWith(username, password, password);
    }

    @Then("user is not created and error \"username should have at least 3 characters\" is reported")
    public void userIsNotCreatedAndTooShortUsernameErrorIsReported() {
        pageHasContent("username should have at least 3 characters");
        pageHasContent("Create username and give password");
    }

    @When("username {string} and password {string} and matching password confirmation are entered")
    public void usernameAndPasswordAndMatchingPasswordConfirmationAreEntered(String username, String password) {
        createUserWith(username, password, password);
    }

    @Then("user is not created and error \"password should have at least 8 characters\" is reported")
    public void userIsNotCreatedTooShortPasswordErrorIsReported() {
        pageHasContent("password should have at least 8 characters");
        pageHasContent("Create username and give password");
    }

    @When("username {string} and password {string} and non matching password confirmation are entered")
    public void usernameAndPasswordAndNonMatchingPasswordConfirmationAreEntered(String username, String password) {
        createUserWith(username, password, "wrong!");
    }

    @Then("user is not created and error \"password and password confirmation do not match\" is reported")
    public void userIsNotCreatedAndPasswordConfirmationErrorIsReported() {
        pageHasContent("password and password confirmation do not match");
        pageHasContent("Create username and give password");

    }

    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }

    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @When("nonexistent username {string} and nonexistent password {string} are given")
    public void nonexistentUsernameAndNonexistentPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }

    @When("username {string} and password {string} are given")
    public void usernameAndPasswordAreGiven(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @Then("system will respond {string}")
    public void systemWillRespond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    /* helper methods */
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource(), driver.getPageSource().contains(content));
    }

    private void goToMainPage() {
        pageHasContent("Welcome to Ohtu Application!");
        WebElement element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        pageHasContent("Ohtu Application main page");

    }

    private void logOut() {
        pageHasContent("Ohtu Application main page");
        WebElement element = driver.findElement(By.linkText("logout"));
        element.click();
        pageHasContent("Ohtu App");

    }

    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
    }

    private void createUserWith(String username, String password, String confirmation) {
        pageHasContent("Create username and give password");
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(confirmation);
        element = driver.findElement(By.name("signup"));
        element.submit();

    }
}

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MyStepdefs {
    private WebDriver driver;
    private WebDriverWait wait;
    private String testEmail = "yasidi2579+" + System.currentTimeMillis() + "@emaxasp.com";


    @Given("I am on basketballengland.co.uk on {string}")
    public void iAmOnBasketballenglandCoUkOn(String browser) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    System.out.println("Chrome driver is used");
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    System.out.println("Firefox driver is used");
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    System.out.println("Edge driver is used");
                    break;
            }
            wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            driver.get("C:\\Users\\joaki\\Downloads\\Register\\Register.html");

    }

    @When("I fill in date of birth {string}")
    public void iFillInDateOfBirth(String dateOfBirth) {
        driver.findElement(By.cssSelector("input#dp")).sendKeys(dateOfBirth);

    }

    @And("I fill in the first name {string}")
    public void iFillInTheFirstName(String firstname) {
        driver.findElement(By.cssSelector("input#member_firstname")).sendKeys(firstname);
    }

    @And("I fill in an last name {string}")
    public void iFillInAnLastName(String lastname) {
        driver.findElement(By.cssSelector("input#member_lastname")).sendKeys(lastname);
    }

    @And("I fill in the email and then confirm email")
    public void iFillInTheEmailAndThenConfirmEmail() {
        driver.findElement(By.cssSelector("input#member_emailaddress")).sendKeys(testEmail);
        driver.findElement(By.cssSelector("input#member_confirmemailaddress")).sendKeys(testEmail);

    }

    @And("I choose the password and retype the password {string}")
    public void iChooseThePasswordAndRetypeThePassword(String password) {
        driver.findElement(By.cssSelector("input#signupunlicenced_password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#signupunlicenced_confirmpassword")).sendKeys(password);
    }


    @And("I check I have read Terms and Conditions {string}")
    public void iCheckIHaveReadTermsAndConditions(String terms) {
        if (terms.trim().equalsIgnoreCase("true"))
            driver.findElement(By.cssSelector("label[for='sign_up_25'] .box")).click();
    }


    @And("I confirm that I am over the age limit")
    public void iConfirmThatIAmOverTheAgeLimit() {
        WebElement confirm = (new WebDriverWait(driver, Duration.ofSeconds(3))).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("label[for='sign_up_26'] .box")));
        confirm.click();
    }

    @And("I check that I have read Code of Conduct")
    public void iCheckThatIHaveReadCodeOfConduct() {
        driver.findElement(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct'] .box")).click();
    }

    @And("Then I press Confirm and join")
    public void thenIPressConfirmAndJoin() {
        driver.findElement(By.cssSelector(".btn")).click();
    }

    @Then("I successfully become a member")
    public void iSuccessfullyBecomeAMember() {
        waitToBeDisplayed("h2.gray");
        String expected = "THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND";
        String actual = driver.findElement(By.cssSelector("h2.gray")).getText();
        assertEquals(expected, actual);
    }

    @And("I fill in password {string}")
    public void iFillInPassword(String password) {
        driver.findElement(By.cssSelector("#signupunlicenced_password")).sendKeys(password);
    }

    @And("I fill in confirmed password {string}")
    public void iFillInConfirmedPassword(String confirmPassword) {
        driver.findElement(By.cssSelector("input#signupunlicenced_confirmpassword")).sendKeys(confirmPassword);
        driver.findElement(By.cssSelector("#titleText1")).click();
    }

    @Then("It failed because {string}")
    public void itFailBecause(String expectedFailureMessage) {
        WebElement element = driver.findElement(By.cssSelector("span[generated=\"true\"]"));
        String actual = element.getText();
        assertEquals(expectedFailureMessage, actual);
        System.out.println("Misslyckades på grund av: " + actual);
    }

    private void waitToBeDisplayed(String css) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(css)));
    }

}

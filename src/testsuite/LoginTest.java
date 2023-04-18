package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {

    String baseUrl = "https://courses.ultimateqa.com/";

    @Before
    public void setUp() {openBrowser(baseUrl);
    }

    // Verifying user should be able to navigate to log in page
    @Test
    public void userShouldNavigateToLoginPageSuccessfully() {
        // This is from requirement
        String expectedText = "Welcome Back!";
        WebElement signinLink = driver.findElement(By.linkText("Sign In"));
        signinLink.click();
        WebElement actualTextElement = driver.findElement(By.xpath("//h2[contains(text(),'Welcome Back!')]"));
        String actualText = actualTextElement.getText();
        Assert.assertEquals("Welcome Back!", expectedText, actualText);
    }

    // Verifying the error message with invalid credentials
    @Test
    public void verifyTheErrorMessage() {

        WebElement signinLink = driver.findElement(By.linkText("Sign In"));
        signinLink.click();

        WebElement emailField = driver.findElement(By.name("user[email]"));
        emailField.sendKeys("asd@gmail.com");

        WebElement PasswordField = driver.findElement(By.id("user[password]"));
        PasswordField.sendKeys("asd@123");

        WebElement signinButton = driver.findElement(By.xpath("//a[@href='/users/sign_in']"));
        signinButton.click();

        WebElement actualTextElement = driver.findElement(By.xpath("//li[@class='form-error__list-item']"));
        String actualText = actualTextElement.getText();

        String expectedText = "Invalid email or password.";
        // Verifying actual text is equals to expected text
        Assert.assertEquals("Invalid email/password message not displayed", actualText, expectedText);
    }

    @After
    public void tearDown() {
        //closeBrowser();
    }
}
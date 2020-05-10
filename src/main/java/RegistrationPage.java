import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RegistrationPage {
    WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    private By loginUserNameField = By.xpath("//input[@name ='login-username']");
    private By loginPasswordField = By.xpath("//input[@name ='login-password']");
    private By buttonLogIn = By.xpath("//button[@value='Log In']");
    private By loginErrors = By.xpath("//div[@class = 'alert alert-danger']");

    private By registrationEmailField = By.xpath("//input[@name = 'registration-email']");
    private By registrationPasswordField = By.xpath("//input[@name = 'registration-password1']");
    private By registrationPasswordConfirmField = By.xpath("//input[@name = 'registration-password2']");
    private By registrationButton = By.xpath("//button[@value = 'Register']");
    private By registrationErrors = By.xpath("//span[@class ='error-block']");

    public void typeLoginUsername(String username) {
        driver.findElement(loginUserNameField).sendKeys(username);
    }

    public void typePasswordUsername(String password) {
        driver.findElement(loginPasswordField).sendKeys(password);
    }
    public void clickLoginButton(){
        driver.findElement(buttonLogIn).click();
    }

    public void typeRegistEmail(String email) {
        driver.findElement(registrationEmailField).sendKeys(email);
    }

    public void typeRegistPassword(String password) {
        driver.findElement(registrationPasswordField).sendKeys(password);
    }

    public void typeConfirmPassword(String password) {
        driver.findElement(registrationPasswordConfirmField).sendKeys(password);
    }
    public void clickRegistrationButton(){
        driver.findElement(registrationButton).click();
    }
    public List<WebElement> getLoginErrors(){
        return driver.findElements(loginErrors);
    }
    public List<WebElement> getRegistErrors(){
        return driver.findElements(registrationErrors);
    }
}

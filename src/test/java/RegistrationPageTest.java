import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class RegistrationPageTest {
    WebDriver driver;
    MainPage mainPage;
    RegistrationPage registPage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\driverForSelenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://selenium1py.pythonanywhere.com/ru/");
        mainPage = new MainPage(driver);
        registPage = mainPage.transferToRegistration();
    }

/*    @DataProvider(name = "login-data")
    public Object[][] loginData(){
        return new Object[][]{{"qwe","123"},{"qwe@rty.com","456"}};
    }*/
    @DataProvider(name = "registration-data")
    public Object[][] registrationData(){
        return new Object[][]{{"qwe@qe.com","123","456"},{"qwe@rty.com","456","456"}};
    }

    @Test(dataProvider = "login-data")
    public void checkLoginFields(String login, String password){
        registPage.typeLoginUsername(login);
        registPage.typePasswordUsername(password);
        registPage.clickLoginButton();
    }

    @Test(dataProvider = "registration-data")
    public void checkRegistFields(String login, String password, String confirmPassword){
        registPage.typeRegistEmail(login);
        registPage.typeRegistPassword(password);
        registPage.typeConfirmPassword(confirmPassword);
        registPage.clickRegistrationButton();
        Assert.assertFalse(registPage.getRegistErrors().isEmpty());
        if (!password.equals(confirmPassword)){
            Assert.assertEquals(registPage.getRegistErrors().get(0).getText(), "Два поля с паролями не совпадают.");
        }else{
            Assert.assertEquals(registPage.getRegistErrors().get(1).getText(),"Введённый пароль слишком широко распространён.");
        }
    }

}

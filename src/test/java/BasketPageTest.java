import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class BasketPageTest {

    WebDriver driver;
    MainPage mainPage;
    BasketPage basketPage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\driverForSelenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://selenium1py.pythonanywhere.com/ru/");
        mainPage = new MainPage(driver);
    }

    @Test
    public void goToBasket(){
        mainPage.transferToBasket();
        Assert.assertEquals(driver.getCurrentUrl(),"http://selenium1py.pythonanywhere.com/ru/basket/");
    }

    @Test
    public void checkBasketItems(){
        mainPage.openBooksCatalog();
        mainPage.addBookToBasketByTitle("The shellcoder's handbook");
        mainPage.addBookToBasketByTitle("Hacking Exposed Wireless");
        basketPage = mainPage.transferToBasket();
        Assert.assertEquals(basketPage.getBasketItems().size(), 2);
    }

    @Test
    public void checkAmountBooks(){
        mainPage.openBooksCatalog();
        mainPage.addBookToBasketByTitle("The shellcoder's handbook");
        mainPage.addBookToBasketByTitle("Hacking Exposed Wireless");
        basketPage = mainPage.transferToBasket();
        System.out.println(basketPage.getBooksPrice());
        Assert.assertEquals(basketPage.getBooksPrice(), basketPage.getFinalPrice());
    }
    @Test
    public void submitOrderCheck(){
        mainPage.openBooksCatalog();
        mainPage.addBookToBasketByTitle("The shellcoder's handbook");
        basketPage = mainPage.transferToBasket();
        Assert.assertEquals(driver.getCurrentUrl(),"http://selenium1py.pythonanywhere.com/ru/checkout/");
    }

}

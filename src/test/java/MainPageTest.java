import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.*;
import org.testng.asserts.Assertion;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainPageTest {
    WebDriver driver;
    MainPage mainPage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\driverForSelenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://selenium1py.pythonanywhere.com/ru/");
        mainPage = new MainPage(driver);
    }

    @Test
    public void checkThatLangChooseCorrect() {
        mainPage.openSelect();
        mainPage.chooseLanguage("ru");
        mainPage.applyChoseLanguage();
        mainPage.getBasketText();
        Assert.assertEquals(mainPage.getBasketText(), "Всего в корзине:");
    }

    @Test
    public void checkAmountLanguages() {
        mainPage.openSelect();
        mainPage.getLanguageList();
        Assert.assertEquals(mainPage.getLanguageList().size(), 21);
    }

    @Test
    public void checkAmountBooksOnPage() {
        mainPage.openBooksCatalog();
        Assert.assertEquals(mainPage.getBookHeader(), "Books");
        Assert.assertEquals(mainPage.getBooksList().size(), 20);
    }

    @Test
    public void checkTitleBook() {
        mainPage.openBooksCatalog();
        mainPage.getBooksTitlesList();
        Assert.assertTrue(mainPage.findBookInList("Hacking Exposed Wireless", mainPage.getBooksTitlesList()));
    }

    @Test
    public void checkCorrectPrice() {
        mainPage.openBooksCatalog();
        ArrayList<Double> list = mainPage.getBooksPriceAsDigit();
        Assert.assertEquals(list.get(0), 9.99);
        Assert.assertTrue(list.get(list.size() - 1) > list.get(0));
    }

    @Test
    public void addBookToBasketAndCheck() {
        String bookTitle = "Coders at Work";
        String expectedResult = String.format("%s был добавлен в вашу корзину.", bookTitle);
        mainPage.openBooksCatalog();
        mainPage.addBookToBasketByTitle(bookTitle);
        String actualResult = mainPage.checkSuccessAddedIntoBasket();
        System.out.println(actualResult);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void checkThatAllBooksHavePrice() {
        mainPage.openBooksCatalog();
        Assert.assertEquals(mainPage.getBooksPriceAsString().size(), 20);
    }

    @Test
    public void checkPaginationButton() throws InterruptedException {
        mainPage.openBooksCatalog();
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Assert.assertFalse(mainPage.checkPaginationBack());
        Assert.assertTrue(mainPage.checkPaginationForward());
        mainPage.clickPagination("forward");
        Assert.assertTrue(mainPage.checkPaginationBack());
    }
    @Test
    public void checkSearchResult(){
        mainPage.sendInSearchField("Hackers");
        Assert.assertEquals(mainPage.getBooksList().size(),12);
    }


    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

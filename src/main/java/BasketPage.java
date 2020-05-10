import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BasketPage {
    WebDriver driver;

    public BasketPage(WebDriver driver) {
        this.driver = driver;
    }

    private By basketItems = By.xpath("//div[@class = 'basket-items']");
    private By totalBooksPrices = By.xpath("(//div[@class = 'basket-items']//div[@class = \"col-sm-2\"])/p");
    private By finalPrice = By.xpath("(//h3)[last()]");
    private By buttonSubmitOrder = By.xpath("//a[text() = 'Перейти к оформлению']");

    public List<WebElement> getBasketItems() {
        return driver.findElements(basketItems);
    }

    public Double getBooksPrice() {
        List<WebElement> list = driver.findElements(totalBooksPrices);
        ArrayList<Double>prices = new ArrayList<Double>();
        ArrayList<String>temp = new ArrayList<String>();
        Double result = 0.0;
        for (int i = 0; i < list.size(); i++) {
            temp.add(list.get(i).getText().replaceAll("\\s{2,}",""));
        }
        for (int i = 0; i < temp.size(); i++) {
            String[]s1 = temp.get(i).split(" ");
            prices.add(Double.parseDouble(s1[0].replaceAll(",",".")));
        }
        for (int i = 0; i < prices.size(); i++) {
            result =result + prices.get(i);
        }
        return result;
    }

    public Double getFinalPrice(){
        String price = driver.findElement(finalPrice).getText().replaceAll("\\s{2,}","");
        String[]s1 = price.split(" ");
        Double result = Double.parseDouble(s1[0].replaceAll(",","."));
        return result;
    }

    public void clickSubmitOrder(){
        driver.findElement(buttonSubmitOrder).click();
    }

}

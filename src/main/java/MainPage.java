import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class MainPage {
    WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    //language
    private By languageSelect = By.xpath("//select");
    private By executeLanguageButton = By.xpath("(//button)[1]");
    private By languageList = By.xpath("//option");//for findElements
    private String language = ("//option[@value = '%s']");


    private By loginLink = By.xpath("//a[@id = 'login_link']");
    private By linkToMainPage = By.xpath("//a[text()='Oscar']");
    private By linkToBasket = By.xpath("(//a[text()='Посмотреть корзину'])[1]");
    private By allGoodsLink = By.xpath("(//a[text()='Все товары'])[1]");
    private By clothingLink = By.xpath("(//a[text()='Clothing'])[1]");
    private By offerLink = By.xpath("(//a[text()='Предложения'])[1]");
    private By searchField = By.xpath("//input[@id = 'id_q']");
    private By searchButton = By.xpath("//input[@value = 'Найти']");
    private By basketText = By.xpath("(//strong)[1]");
    //Books
    private By booksLink = By.xpath("(//a[text()='Books'])[1]");
    private By booksListOnPage = By.xpath("//li[@class = 'col-xs-6 col-sm-4 col-md-3 col-lg-3']");
    private By header = By.xpath("//h1");
    private By bookTitle = By.xpath("//li[@class = 'col-xs-6 col-sm-4 col-md-3 col-lg-3']/article/h3/a");
    public By priceBooksList = By.xpath("//li[@class = 'col-xs-6 col-sm-4 col-md-3 col-lg-3']/article/div[2]/p[1]");
    //Pagination
    public By paginationForward = By.xpath("//a[text() = 'вперед']");
    public By paginationBack = By.xpath("//a[text() = 'назад']");


    /////////////////////////////////////////Language/////////////////////////////////////////////////////////////////////////
    public void openSelect(){
        driver.findElement(languageList).click();
    }
    public void chooseLanguage(String lang){
        driver.findElement(By.xpath(String.format(language,lang))).click();
    }
    public void applyChoseLanguage(){
        driver.findElement(executeLanguageButton).click();
    }
    public List<WebElement> getLanguageList(){
        return driver.findElements(languageList);
    }

    /////////////////////////////////////////Books/////////////////////////////////////////////////////////////////////////
    public void openBooksCatalog(){
        driver.findElement(booksLink).click();
    }
    public List<WebElement> getBooksList(){
        return driver.findElements(booksListOnPage);
    }
    public String getBookHeader(){
        return driver.findElement(header).getText();
    }
    public ArrayList<String> getBooksTitlesList(){
        List<WebElement> list = new ArrayList<WebElement>();
        list = driver.findElements(bookTitle);
        ArrayList<String> titlesList = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
           titlesList.add(list.get(i).getText());
        }
        return titlesList;
    }
    public boolean findBookInList(String title, ArrayList<String> list){
        boolean result = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(title)){
                result = true;
            }
        }
        return result;
    }
    public ArrayList<String> getBooksPriceAsString(){
        List<WebElement> list = new ArrayList<WebElement>();
        ArrayList<String> priceList = new ArrayList<String>();
        String[] temp = new String[2];
        list = driver.findElements(priceBooksList);
        for (int i = 0; i < list.size(); i++) {
            String[] s1 = (list.get(i).getText().split("[ ]"));
            //priceList.add(String.format("Price book №%d: ", i + 1) + s1[0].replace(",","."));
            priceList.add(s1[0].replace(",","."));
        }
        return priceList;
    }
    public ArrayList<Double> getBooksPriceAsDigit(){
        List<WebElement> list = new ArrayList<WebElement>();
        ArrayList<Double> priceList = new ArrayList<Double>();
        String[] temp = new String[2];
        list = driver.findElements(priceBooksList);
        for (int i = 0; i < list.size(); i++) {
            String[] s1 = (list.get(i).getText().split("[ ]"));
            //priceList.add(String.format("Price book №%d: ", i + 1) + s1[0].replace(",","."));
            priceList.add(Double.parseDouble(s1[0].replace(",",".")));
        }
        return priceList;
    }
    /////////////////////////////////////////Pagination/////////////////////////////////////////////////////////////////////////

    public boolean checkPaginationBack(){
        if (driver.findElement(paginationBack).isDisplayed()){
            return true;
        }else{return false;}

    }
    public boolean checkPaginationForward(){
        if (driver.findElement(paginationForward).isDisplayed()){
            return true;
        }else{return false;}
    }
    public void clickPagination(String direction){
        if (direction.equals("forward")){
            driver.findElement(paginationForward).click();
        }
        if (direction.equals("back")){
            driver.findElement(paginationBack).click();
        }

    }




    /////////////////////////////////////////Basket/////////////////////////////////////////////////////////////////////////
    public void goToBasket(){
        driver.findElement(linkToBasket).click();
    }
    public String getBasketText(){
        return driver.findElement(basketText).getText();
    }

}

package starterkit.pages.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import starterkit.pages.AbstractPageObject;

import java.util.List;

/**
 * Created by MATSUS on 15.09.2015.
 */
public class BookListPage extends AbstractPageObject {

    @FindBy(xpath = "//input")
    private WebElement bookTitle;

    @FindBy(xpath = "//button [text()='Szukaj']")
    private WebElement searchButton;

    @FindBy(xpath = "//button [@ng-click='addBook()']")
    private WebElement addBookButton;

    @FindBy(xpath = "//section/table")
    private WebElement bookList;

    @FindBy(xpath = "//button [text()='Edit']")
    private List<WebElement> editButtons;

    @FindBy(xpath = "//button [text()='Delete']")
    private List<WebElement> deleteButtons;

    @FindBy(xpath = "//div[contains(@role,'alert')]")
    private WebElement flash;


    public BookListPage(WebDriver driver) {
        super(driver);
    }

    public BookListPage setTitlePrefix(String title) {
        this.bookTitle.sendKeys(title);
        return this;
    }

    public BookListPage clickSearchButton() {
        searchButton.click();
        return PageFactory.initElements(driver, BookListPage.class);
    }

    public NewBookPage clickAddBookButton() {
        addBookButton.click();
        return PageFactory.initElements(driver, NewBookPage.class);
    }

    public List<WebElement> getRows() {
        return bookList.findElements(By.tagName("tr"));
    }

    public NewBookPage clickEditButton(int index) {
        if( index < editButtons.size())
            editButtons.get(index).click();
        return PageFactory.initElements(driver,NewBookPage.class);
    }

    public BookListPage clickDeleteButton(int index) {
        if (index < deleteButtons.size())
            deleteButtons.get(index).click();
        return PageFactory.initElements(driver, BookListPage.class);
    }

    public boolean isFlashDisplayed(String message) {
        String xpath = "//span[contains(@class,'ng-scope') and contains(text(),'" + message + "')]";
        return flash.findElement(By.xpath(xpath)).isDisplayed();
    }

    public int countBooks() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(bookList));
        return driver.findElements(By.xpath("//table/tbody/tr")).size() - 1;
    }
}

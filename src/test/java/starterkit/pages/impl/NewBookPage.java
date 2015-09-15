package starterkit.pages.impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import starterkit.pages.AbstractPageObject;

import java.util.concurrent.TimeUnit;

/**
 * Created by MATSUS on 15.09.2015.
 */
public class NewBookPage extends AbstractPageObject {

    @FindBy(xpath = "//input [@ng-model='book.title']")
    private WebElement bookTitle;

    @FindBy(xpath = "//form/table")
    private WebElement authorsTable;

    @FindBy(xpath = "//input [@ng-model='firstName']")
    private WebElement firstName;

    @FindBy(xpath = "//input [@ng-model='lastName']")
    private WebElement lastName;

    @FindBy(xpath = "//button [@ng-click='addAuthor(firstName,lastName)']")
    private WebElement addAuthorButton;

    @FindBy(xpath = "/html/body/div[4]/div/div/div/form/div[3]/button[1]")
    private WebElement addBookButton;

    @FindBy(xpath = "//button [@id='modalAddBook']")
    private WebElement flash;

    public NewBookPage(WebDriver driver) {
        super(driver);
    }

    public NewBookPage setBookTitle(String title) {
        bookTitle.clear();
        bookTitle.sendKeys(title);
        return this;
    }
    public NewBookPage setFirstName(String firstName) {
        this.firstName.sendKeys(firstName);
        return this;
    }
    public NewBookPage setLastName(String lastName) {
        this.lastName.sendKeys(lastName);
        return this;
    }

    public NewBookPage clickAddAuthorButton() {
        addAuthorButton.click();
        return this;
    }

    public NewBookPage clickAddBookButton() {
        addBookButton.click();
        return this;
    }

    public boolean isFlashDisplayed() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        return flash.isDisplayed();
    }








}

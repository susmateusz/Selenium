package starterkit.pages.impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import starterkit.pages.AbstractPageObject;

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

    @FindBy(xpath = "//div[contains(@class, 'alert-warning') and contains(text(),'Book title is required.')]")
    private WebElement flashTitleRequired;

    @FindBy(xpath = "//div[contains(@class, 'alert-warning') and contains(text(),' Book required at least one author.')]")
    private WebElement flashAuthorRequired;

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

    public BookListPage clickAddBookButton() {
        addBookButton.click();
        return PageFactory.initElements(driver,BookListPage.class);
    }

    public boolean isTitleWarningDisplayed() {
        return flashTitleRequired.isDisplayed();
    }


    public boolean isAuthorsWarningDisplayed() {
        return flashAuthorRequired.isDisplayed();
    }
}

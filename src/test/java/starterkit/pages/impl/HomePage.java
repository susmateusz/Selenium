package starterkit.pages.impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import starterkit.pages.AbstractPageObject;

/**
 * Created by MATSUS on 15.09.2015.
 */
public class HomePage extends AbstractPageObject {

    @FindBy(linkText = "Book List")
    private WebElement bookListButton;
    @FindBy(linkText = "Author List")
    private WebElement authorListButton;
    @FindBy(linkText = "Dialog A")
    private WebElement dialogAButton;
    @FindBy(linkText = "Dialog B")
    private WebElement dialogBButton;


    public HomePage(WebDriver driver) {
        super(driver);
        this.driver.get("http://localhost:9721/workshop/");
    }

    public BookListPage clickBookList() {
        bookListButton.click();
        return PageFactory.initElements(driver, BookListPage.class);
    }

    public AuthorListPage clickAuthorList() {
        authorListButton.click();
        return PageFactory.initElements(driver, AuthorListPage.class);
    }

    public DialogAPage clickADialog() {
        dialogAButton.click();
        return PageFactory.initElements(driver,DialogAPage.class);
    }

    public WebElement getBookListButton() {
        return bookListButton;
    }

    public WebElement getAuthorListButton() {
        return authorListButton;
    }

    public WebElement getDialogAButton() {
        return dialogAButton;
    }

    public WebElement getDialogBButton() {
        return dialogBButton;
    }

}

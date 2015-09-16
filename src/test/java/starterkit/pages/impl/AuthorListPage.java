package starterkit.pages.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import starterkit.pages.AbstractPageObject;

import java.util.List;

/**
 * Created by matsus on 16.09.2015.
 */
public class AuthorListPage extends AbstractPageObject {

    @FindBy(xpath = "//input")
    private WebElement authorPrefix;

    @FindBy(xpath = "//button [text()='Szukaj']")
    private WebElement searchButton;

    @FindBy(xpath = "//section/table")
    private WebElement authorTable;


    public AuthorListPage(WebDriver driver) {
        super(driver);
    }

    public AuthorListPage setAuthorPrefix(String prefix) {
        authorPrefix.clear();
        authorPrefix.sendKeys(prefix);
        return this;
    }

    public AuthorListPage clickSearchButton() {
        searchButton.click();
        return this;
    }

    public List<WebElement> getRows() {
        return authorTable.findElements(By.tagName("tr"));
    }

    public int countAuthors() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(authorTable));
        return driver.findElements(By.xpath("//table/tbody/tr")).size() - 1;
    }

}

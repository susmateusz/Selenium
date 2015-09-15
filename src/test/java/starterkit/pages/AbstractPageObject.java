package starterkit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

/**
 * Created by MATSUS on 15.09.2015.
 */
public abstract class AbstractPageObject {
    protected WebDriver driver;

    public AbstractPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean hasError() {
        try {
            driver.findElement(By.className("help-inline"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }

    }
}

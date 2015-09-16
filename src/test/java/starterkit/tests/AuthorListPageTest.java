package starterkit.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import starterkit.pages.AbstractSelenium;
import starterkit.pages.impl.AuthorListPage;
import starterkit.pages.impl.BookListPage;

import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by matsus on 16.09.2015.
 */
public class AuthorListPageTest extends AbstractSelenium {

    private AuthorListPage authorListPage;

    private String firstName = "testFirstName";
    private String lastName = "testLastName";

    @Override
    @Before
    public void setUp() {
        super.setUp();
        authorListPage = openHomePage().clickAuthorList();
        addExampleBook(firstName, lastName);
    }

    @Override
    @After
    public void tearDown() {
        removeExampleBook();
        super.tearDown();
    }

    @Test
    public void testIfSearchWorksWithEmptyPrefix() {
        // given
        String emptyPrefix = " ";
        // when
        authorListPage.clickSearchButton();
        int allAuthorsCount = authorListPage.countAuthors();

        authorListPage.setAuthorPrefix(emptyPrefix);
        int filteredAuthorsCount = authorListPage.countAuthors();
        // then
        assertTrue(allAuthorsCount > 0);
        assertEquals(allAuthorsCount, filteredAuthorsCount);
    }

    @Test
    public void testIfSearchWorksWithPrefixForFirstName() {
        // given
        String xpathExpression = "//td[contains(text(),'" + firstName + "')]";
        Predicate<WebElement> ifContainsFirstName = e -> e.findElement(By.xpath(xpathExpression)).isDisplayed();
        // when
        authorListPage.clickSearchButton();
        authorListPage.setAuthorPrefix(firstName);
        List<WebElement> results = authorListPage.getRows();
        // then
        assertTrue(results.stream().allMatch(ifContainsFirstName));
    }

    @Test
    public void testIfSearchWorksWithPrefixForLastName() {
        // given
        String xpathExpression = "//td[contains(text(),'" + lastName + "')]";
        Predicate<WebElement> ifContainsLastName = e -> e.findElement(By.xpath(xpathExpression)).isDisplayed();
        // when
        authorListPage.clickSearchButton();
        authorListPage.setAuthorPrefix(lastName);
        List<WebElement> results = authorListPage.getRows();
        // then
        assertTrue(results.stream().allMatch(ifContainsLastName));
    }

    private void addExampleBook(String firstName, String lastName) {
        openHomePage().clickBookList().clickAddBookButton().setBookTitle("title")
                .setFirstName(firstName)
                .setLastName(lastName)
                .clickAddAuthorButton()
                .clickAddBookButton();
        openHomePage().clickAuthorList();
    }

    private void removeExampleBook() {
        BookListPage bookListPage = openHomePage().clickBookList();
        try {
            bookListPage.clickSearchButton().clickDeleteButton(bookListPage.countBooks() - 1);
        } catch (NullPointerException e) {
            System.err.println("Not found example book, maybe Test tests if the book can be removed?");
        }
    }
}

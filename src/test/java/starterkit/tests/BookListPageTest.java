package starterkit.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import starterkit.pages.AbstractSelenium;
import starterkit.pages.impl.BookListPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by MATSUS on 15.09.2015.
 */
public class BookListPageTest extends AbstractSelenium {

    private BookListPage bookListPage;
    private String exampleTitle = "xxxxxxxxxxxxxxxx";


    @Override
    @Before
    public void setUp() {
        super.setUp();
        bookListPage = openHomePage().clickBookList();
        addExampleBook(exampleTitle);
    }

    @Override
    @After
    public void tearDown() {
        removeExampleBook();
        super.tearDown();
    }

    @Test
    public void testCheckIfBookIsRemoved() throws Exception {
        // when
        bookListPage.clickSearchButton();
        int allBooksCount = bookListPage.countBooks();

        bookListPage.clickDeleteButton(allBooksCount - 1);
        int booksAfterDeletionCount = bookListPage.countBooks();
        // then
        assertEquals(allBooksCount - 1, booksAfterDeletionCount);
        assertTrue(bookListPage.isFlashDisplayed("Book was removed!"));
    }

    @Test
    public void testCheckIfFiltersWorksWithEmptyPrefix() throws Exception {
        // given
        String emptyPrefix = " ";
        // when
        bookListPage.clickSearchButton();
        int allBooksCount = bookListPage.countBooks();

        bookListPage.setTitlePrefix(emptyPrefix);
        int filteredBooksCount = bookListPage.countBooks();

        // then
        assertTrue(allBooksCount > 0);
        assertEquals(filteredBooksCount, allBooksCount);
    }

    @Test
    public void testCheckIfFiltersWorksWithPrefix() throws Exception {
        // when
        bookListPage.clickSearchButton();
        bookListPage.setTitlePrefix(exampleTitle);
        int filteredBooksCount = bookListPage.countBooks();
        // then
        assertEquals(1, filteredBooksCount);
    }

    @Test
    public void testIfEditBookWorks() throws Exception {
        // given
        String newTitle = "yyyy";
        // when
        bookListPage.clickSearchButton();
        bookListPage.clickEditButton(bookListPage.countBooks()-1).setBookTitle(newTitle).clickAddBookButton();
        bookListPage.clickSearchButton();
        int allBooksCount = bookListPage.getRows().size();
        // then
        assertNotNull(bookListPage.getRows().get(allBooksCount - 1).findElement(By.xpath("//td[text()='" + newTitle + "']")));
        assertTrue(bookListPage.isFlashDisplayed("Book \""+newTitle+"\" was edited!"));
    }

    @Test
    public  void testAddBook() {
        assertTrue(bookListPage.isFlashDisplayed("Book \""+exampleTitle+"\" was added!"));
    }

    private void addExampleBook(String title) {
        String firstName = "firstName";
        String lastName = "lastName";
        bookListPage.clickAddBookButton().setBookTitle(title)
                .setFirstName(firstName)
                .setLastName(lastName)
                .clickAddAuthorButton()
                .clickAddBookButton();
    }

    private void removeExampleBook() {
        try {
            bookListPage.clickDeleteButton(bookListPage.countBooks() - 1);
        } catch (NullPointerException e) {
            System.err.println("Hue hue hue");
        }
    }
}

package starterkit.tests;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import starterkit.pages.AbstractSelenium;
import starterkit.pages.impl.NewBookPage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by MATSUS on 15.09.2015.
 */
public class NewBookModalPageTest extends AbstractSelenium {

    private NewBookPage newBookPage;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        newBookPage = openHomePage().clickBookList().clickAddBookButton();
    }

    @Test
    public void testAddBookCorrectly() throws Exception {
        String title = "title";
        // when
        newBookPage.setBookTitle(title)
                .setFirstName("firstName")
                .setLastName("lastName")
                .clickAddAuthorButton()
                .clickAddBookButton();
        // then
        assertFalse(newBookPage.hasError());
    }

    @Test
    public void testShouldCheckIfTitleIsRequired() throws Exception {
        // given
        newBookPage.setFirstName("firstName").setLastName("lastName").clickAddAuthorButton();
        // when
        newBookPage.clickAddBookButton();
        // then
        assertTrue(newBookPage.isTitleWarningDisplayed());
        assertFalse(newBookPage.isAuthorsWarningDisplayed());
    }

    @Test
    public void testShouldCheckIfFirstNameIsRequired() throws Exception {
        // given
        newBookPage.setBookTitle("title").setLastName("lastName").clickAddAuthorButton();
        // when
        newBookPage.clickAddBookButton();
        // then
        assertFalse(newBookPage.isTitleWarningDisplayed());
        assertTrue(newBookPage.isAuthorsWarningDisplayed());
    }

    @Test
    public void testShouldCheckIfLastNameIsRequired() throws Exception {
        // given
        newBookPage.setBookTitle("title").setFirstName("firstName").clickAddAuthorButton();
        // when
        newBookPage.clickAddBookButton();
        // then
        assertFalse(newBookPage.isTitleWarningDisplayed());
        assertTrue(newBookPage.isAuthorsWarningDisplayed());
    }

    @Test
    public void testShouldCheckIfTitleAndAuthorAreRequired() throws Exception {
        // when
        newBookPage.clickAddBookButton();
        // then
        assertTrue(newBookPage.isTitleWarningDisplayed());
        assertTrue(newBookPage.isAuthorsWarningDisplayed());
    }
}

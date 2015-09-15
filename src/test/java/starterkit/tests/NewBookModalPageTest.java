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
    @Ignore
    public void testAddBookCorrectly() throws Exception {
        // given
        String title = "title";
        String firstName = "firstName";
        String lastName = "lastName";
        // when
        newBookPage.setBookTitle(title)
                .setFirstName(firstName)
                .setLastName(lastName)
                .clickAddAuthorButton();
        // then
        assertFalse(newBookPage.hasError());
    }

    @Test
    @Ignore
    public void testShouldCheckIfTitleAndAuthorAreRequired() throws Exception {
        // when
        newBookPage.clickAddBookButton();
        // then
        assertTrue(newBookPage.isFlashDisplayed());
    }
}

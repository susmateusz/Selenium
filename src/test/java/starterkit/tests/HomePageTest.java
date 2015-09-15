package starterkit.tests;

import org.junit.Before;
import org.junit.Test;
import starterkit.pages.AbstractSelenium;
import starterkit.pages.impl.HomePage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by MATSUS on 15.09.2015.
 */
public class HomePageTest extends AbstractSelenium{

    private HomePage homePage;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        homePage = openHomePage();
    }

    @Test
    public void testIfHomePageOpensCorrectly() {
        // then
        assertFalse(homePage.hasError());
        assertTrue(homePage.getBookListButton().isDisplayed());
        assertTrue(homePage.getAuthorListButton().isDisplayed());
        assertTrue(homePage.getDialogAButton().isDisplayed());
        assertTrue(homePage.getDialogBButton().isDisplayed());
    }
}

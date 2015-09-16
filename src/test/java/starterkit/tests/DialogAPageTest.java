package starterkit.tests;

import org.junit.Before;
import org.junit.Test;
import starterkit.pages.AbstractSelenium;
import starterkit.pages.impl.DialogAPage;

import static org.junit.Assert.assertTrue;

/**
 * Created by matsus on 16.09.2015.
 */
public class DialogAPageTest extends AbstractSelenium {

    private DialogAPage dialogAPage;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        dialogAPage = openHomePage().clickADialog();
    }

    @Test
    public void testIfElementsDisplays() {
        // when
        boolean ifHeaderDisplays = dialogAPage.isHeaderDisplayed();
        boolean ifImageDisplays = dialogAPage.isImageDisplayed();
        //then
        assertTrue(ifHeaderDisplays);
        assertTrue(ifImageDisplays);
    }
}
package uitests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BrowsePage;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.SeleniumUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BrowseTests extends TestBase {

    @BeforeMethod (alwaysRun = true)
    public void browseTestSetup(){
        new LoginPage().login(ConfigReader.getProperty("username1"), ConfigReader.getProperty("password1"));
    }

    @Test (groups = {"SPRNT_002"})
    public void verifyDefaultAlbums(){
       List<String> expectedAlbumNames = Arrays.asList("Fenix", "Cruel Summer", "Ultimatum", "Werk", "Oscillation", "Marisa",
               "Clouds", "I Am...Sasha Fierce", "Escape");


        BrowsePage browsePage = new BrowsePage();

        List<String> actualAlbumNames = SeleniumUtils.getElementsText(browsePage.albums);

        Collections.sort(expectedAlbumNames );
        Collections.sort(actualAlbumNames );

        Assert.assertEquals(actualAlbumNames, expectedAlbumNames);


    }







}

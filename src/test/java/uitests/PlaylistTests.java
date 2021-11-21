package uitests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BrowsePage;
import pages.LoginPage;
import pages.PlaylistsPage;
import utilities.ConfigReader;

public class PlaylistTests extends TestBase{


    @BeforeMethod(alwaysRun = true)
    public void browseTestSetup(){
        LoginPage loginPage = new LoginPage();
       loginPage.login(ConfigReader.getProperty("username1"), ConfigReader.getProperty("password1"));
       new BrowsePage().yourMusicLink.click();

    }


    @Test (groups = {"SPRNT_002"})
    public void verifyNewPlayListCreation(){

        String expectedName = "Song to listen when Automating";
        PlaylistsPage playlistsPage = new PlaylistsPage();

        playlistsPage.newPlayListButton.click();

        playlistsPage.enterPlayListName(expectedName);

        String actualName = playlistsPage.grabTheLastPlayListsText();

        Assert.assertEquals(actualName, expectedName);
    }





}

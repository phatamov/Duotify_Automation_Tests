package uitests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.ConfigReader;

public class LoginTests extends TestBase{


    @Test (groups = {"smoke"})
    public void appHealthCheck(){


        logger.info("Navigating to the homepage");
       assertTrue(driver.getCurrentUrl().equals(ConfigReader.getProperty("url")));


    }


    @Test (groups = {"smoke"})
    public void appHealthCheck1(){


        logger.info("Navigating to the homepage");
      assertTrue(driver.getCurrentUrl().equals(ConfigReader.getProperty("url")));


    }


    @Test (groups = {"smoke" , "SPRNT_002"} )
    public void appHealthCheck2(){


        logger.info("Navigating to the homepage");
       assertTrue(driver.getCurrentUrl().equals(ConfigReader.getProperty("url")));


    }

    @Test (groups = {"smoke" , "SPRNT_002"} )
    public void appHealthCheck3(){


        logger.info("Navigating to the homepage");
        assertTrue(driver.getCurrentUrl().equals(ConfigReader.getProperty("url")));


    }

    @Test (groups = {"smoke" , "SPRNT_002"} )
    public void appHealthCheck4(){


        logger.info("Navigating to the homepage");
      assertTrue(driver.getCurrentUrl().equals(ConfigReader.getProperty("url")));


    }

//    @Test (groups = {"smoke"})
//    public void positiveLogin1(){
//
//
//
//
//        LoginPage loginPage = new LoginPage();
//        logger.info("Entering the username");
//        loginPage.usernameField.sendKeys(ConfigReader.getProperty("username1"));
//        logger.info("Entering the password");
//        loginPage.passwordField.sendKeys(ConfigReader.getProperty("password1"));
//        logger.info("Clicking onn login button");
//        loginPage.loginButton.click();
//        SeleniumUtils.waitFor(2);
//        logger.info("Verifying the url is as expected");
//        Assert.assertTrue(driver.getCurrentUrl().equals("http://duotifyapp.us-east-2.elasticbeanstalk.com/browse.php?"));
//
//
//
//
//    }


    @Test
    public void positiveLogin2(){



        LoginPage loginPage = new LoginPage();
        logger.info("Logging in with the second test credentials");
        loginPage.login(ConfigReader.getProperty("username1"), ConfigReader.getProperty("password1"));
        assertTrue(driver.getCurrentUrl().equals("http://duotifyapp.us-east-2.elasticbeanstalk.com/browse.php?"));






    }



}

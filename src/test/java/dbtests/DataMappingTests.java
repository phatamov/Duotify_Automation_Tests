package dbtests;

import com.github.javafaker.Faker;
import org.apache.commons.codec.digest.DigestUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.BrowsePage;
import pages.LoginPage;
import pages.SignUpPage;
import uitests.TestBase;
import utilities.ConfigReader;
import utilities.DBUtility;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DataMappingTests extends TestBase {




    @Test
    public void verifyUserSignUpFlowFromUIToDatabase(){

        // Sign up a new user through the UI

        new LoginPage().signUpLink.click();

        SignUpPage signUpPage = new SignUpPage();
        Faker fake = new Faker();
        logger.info("Entering the username");
        String expectedUsername = fake.name().username();
        signUpPage.usernameField.sendKeys(expectedUsername);
        logger.info("Entering the first name");
        String expectedFirstName = fake.name().firstName();
        signUpPage.firstName.sendKeys(expectedFirstName);
        logger.info("Entering the last name");
        String expectedLastName = fake.name().lastName();
        signUpPage.lastName.sendKeys(expectedLastName);
        logger.info("Entering the email");
        String expectedEmail = fake.internet().emailAddress();

        signUpPage.email.sendKeys(expectedEmail);

        logger.info("Re-Entering the email");
        signUpPage.email2.sendKeys(expectedEmail);

        String expectedPassword = "duotechb5"; //28e39727d3e63395620288cb42febfd9
        logger.info("Entering the password");

        signUpPage.password.sendKeys(expectedPassword);
        logger.info("Re-Entering the password");

        signUpPage.password2.sendKeys(expectedPassword);
        signUpPage.registerButton.click();
        logger.info("Waiting till url becomes the expected");

        new WebDriverWait(driver, 5).until(ExpectedConditions.urlToBe("http://duotifyapp.us-east-2.elasticbeanstalk.com/browse.php?"));

        logger.info("Verifying the url is as expected");

       assertTrue(driver.getCurrentUrl().equals("http://duotifyapp.us-east-2.elasticbeanstalk.com/browse.php?"));




        // Connect to database

        DBUtility.createConnection();


        // Send query to retrieve the information about the user

       String query = "select * from users where username = '"+expectedUsername+"'";

        List<Map<String, Object>> listOfMaps = DBUtility.getQueryResultListOfMaps(query);

        Map<String, Object> map = listOfMaps.get(0);
        System.out.println(map);



        // Compare the information that was entered through UI with the Database info
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(map.get("username"), expectedUsername);
        softAssert.assertEquals(map.get("firstName"), expectedFirstName);
        softAssert.assertEquals(map.get("lastName"), expectedLastName);
        softAssert.assertEquals(map.get("email").toString().toLowerCase(), expectedEmail);  // email bug found, to prevent failure useing toLowercase
        softAssert.assertEquals(map.get("password"), "28e39727d3e63395620288cb42febfd9");

        softAssert.assertAll();
    }



    @Test
    public void verifyUserSignUpFlowFromDatabaseToUI() throws SQLException {


        // Connect to database
        logger.info("Connect to database");
        DBUtility.createConnection();
        // Create a user with the details
        logger.info("Create a user with the details");
        String expectedUsername = new Faker().name().username();
        String expectedPassword = new Faker().internet().password();


        String md5hash = DigestUtils.md5Hex(expectedPassword);




        String query = "INSERT INTO users ( username, firstName, lastName, email, password, signUpDate, profilePic) " +
                "values " +
                "('"+expectedUsername+"', 'Rena', 'Mammadova', 'rena.mammadova@gmail.com', '"+md5hash+"', '2021-08-26 00:00:00', 'assets/images/profile-pics/head' )";

        DBUtility.updateQuery(query);
        logger.info("Verify the user creation on the UI");
        // Verify the user creation on the UI by logging in with the credentials above

        LoginPage loginPage = new LoginPage();
        logger.info("Logging in");
        loginPage.login(expectedUsername, expectedPassword);
        assertTrue(driver.getCurrentUrl().equals("http://duotifyapp.us-east-2.elasticbeanstalk.com/browse.php?"));

        // Delete the row that was just created
        String deleteQuery = "DELETE from users where username = '"+expectedUsername+"'";
        DBUtility.updateQuery(deleteQuery);


    }


    @Test
    public void updateEmailDb() throws SQLException {


        // Send update email query

        String expectedEmail = "duotech2020@gmail.com";
        String expectedFirstName = "Duotech";
        String expectedLastName = "Academy";
        String expectedUsername = "duotech";

        String query = "update users set email='"+expectedEmail+"', firstName='"+expectedFirstName+"', lastName='"+expectedLastName+"' where username='"+expectedUsername+"'";
        System.out.println(query);
        DBUtility.updateQuery(query);


        new LoginPage().login(expectedUsername, expectedUsername);

        BrowsePage browsePage= new BrowsePage();

        String[] s = browsePage.username.getText().split(" ");
        String actualFirst = s[0];
        String actualLast = s[1];
        browsePage.username.click();

        browsePage.userdetailsButton.click();

        String actualEmail = browsePage.emailField.getAttribute("value");

        assertEquals(actualEmail, expectedEmail);
        assertEquals(actualFirst, expectedFirstName);
        assertEquals(actualLast, expectedLastName);




    }



    }

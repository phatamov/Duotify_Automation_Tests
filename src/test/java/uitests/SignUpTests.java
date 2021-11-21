package uitests;

import com.github.javafaker.Faker;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.SignUpPage;
import utilities.CSVReader;

import java.io.IOException;

public class SignUpTests extends TestBase {


    @Test(groups = {"SPRNT_002"})
    public void signUp() {

        new LoginPage().signUpLink.click();

        SignUpPage signUpPage = new SignUpPage();
        Faker fake = new Faker();
        logger.info("Entering the username");
        signUpPage.usernameField.sendKeys(fake.name().username());
        logger.info("Entering the first name");
        signUpPage.firstName.sendKeys(fake.name().firstName());
        logger.info("Entering the last name");
        signUpPage.lastName.sendKeys(fake.name().lastName());
        logger.info("Entering the email");
        String email = fake.internet().emailAddress();

        signUpPage.email.sendKeys(email);

        logger.info("Re-Entering the email");
        signUpPage.email2.sendKeys(email);

        String pass = fake.internet().password();
        logger.info("Entering the password");

        signUpPage.password.sendKeys(pass);
        logger.info("Re-Entering the password");

        signUpPage.password2.sendKeys(pass);
        signUpPage.registerButton.click();
        logger.info("Waiting till url becomes the expected");

        new WebDriverWait(driver, 5).until(ExpectedConditions.urlToBe("http://duotifyapp.us-east-2.elasticbeanstalk.com/browse.php?"));

        logger.info("Verifying the url is as expected");

        Assert.assertTrue(driver.getCurrentUrl().equals("http://duotifyapp.us-east-2.elasticbeanstalk.com/browse.php?"));


    }

    @Test(dataProvider = "getData", enabled = false)
    public void signUpUsingCSV(String username, String firstName, String lastName, String email, String password) {
        // not going to work second time, you need to change the data
        new LoginPage().signUpLink.click();

        SignUpPage signUpPage = new SignUpPage();


        signUpPage.usernameField.sendKeys(username);
        logger.info("Entering the first name");
        signUpPage.firstName.sendKeys(firstName);
        logger.info("Entering the last name");
        signUpPage.lastName.sendKeys(lastName);
        logger.info("Entering the email");


        signUpPage.email.sendKeys(email);

        logger.info("Re-Entering the email");
        signUpPage.email2.sendKeys(email);


        logger.info("Entering the password");

        signUpPage.password.sendKeys(password);
        logger.info("Re-Entering the password");

        signUpPage.password2.sendKeys(password);
        signUpPage.registerButton.click();
        logger.info("Waiting till url becomes the expected");

        new WebDriverWait(driver, 5).until(ExpectedConditions.urlToBe("http://duotifyapp.us-east-2.elasticbeanstalk.com/browse.php?"));

        logger.info("Verifying the url is as expected");

        Assert.assertTrue(driver.getCurrentUrl().equals("http://duotifyapp.us-east-2.elasticbeanstalk.com/browse.php?"));


    }

    @DataProvider
    public Object[][] getData() throws IOException {
        return CSVReader.readData("MOCK_DATA.csv");
    }


}

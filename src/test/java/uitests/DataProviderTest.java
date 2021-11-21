package uitests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.CSVReader;
import utilities.Driver;
import utilities.ExcelUtility;

import java.io.IOException;

public class DataProviderTest {






    @Test (dataProvider = "fromCSV")
    public void googleSearchPageVerification(String searchTerm){
        WebDriver driver = Driver.getDriver();

        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys(searchTerm + Keys.ENTER);

        Assert.assertTrue(driver.getTitle().contains(searchTerm));
        Driver.quitDriver();
    }


    @DataProvider (name = "searchTerms" , parallel = true)
    public Object[][] getData(){
        return new Object[][]{
                {"socks"},
                {"cheap socks"},
                {"rafael aziz socks"},
                {"short socks"},
                {"happy socks"}
        };
    }


    @DataProvider (name = "fromCSV" )
    public Object[][] getDataFromCSV() throws IOException {
        return CSVReader.readData("testData.csv");
    }






    @DataProvider (name = "fromExcel" )
    public Object[][] getDataFromExcel() throws IOException {
        ExcelUtility excelUtility = new ExcelUtility("testDataExcel.xlsx", "Sheet1");
        return excelUtility.getDataAs2DArray();
    }


    @Test (dataProvider = "fromExcel")
    public void testingEXcelReader(String searchTerm,
                                   String searchTerm1,
                                   String searchTerm2,
                                   String searchTerm3,
                                   String searchTerm4,
                                   String searchTerm5,
                                   String searchTerm6){
        System.out.println(searchTerm);
        System.out.println(searchTerm1);
        System.out.println(searchTerm2);
        System.out.println(searchTerm3);
        System.out.println(searchTerm4);
        System.out.println(searchTerm5);
        System.out.println(searchTerm6);

    }






}

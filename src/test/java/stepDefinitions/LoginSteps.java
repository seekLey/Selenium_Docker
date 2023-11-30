package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.MalformedURLException;

import java.util.concurrent.TimeUnit;

public class LoginSteps {
    WebDriver driver;
    String baseURL = "https://testpages.eviltester.com/styled/index.html";

    @Before
    public void launchbrowser() throws MalformedURLException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run Chrome in headless mode
        options.addArguments("--disable-gpu"); // This is often necessary in headless mode
        driver = new ChromeDriver(options);
        driver.get(baseURL);

//      Maximize the browser
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Given("launch the browser")
    public void launch_the_browser() {
        System.out.println("######################### TEST STARTED #########################");
    }

    @And("verify the page Title")
    public void verify_the_page_title() {
        String actualPageTitle = driver.getTitle();
        String expectedPageTitle = "Web Testing and Automation Practice Application Pages";
//        System.out.println(actualPageTitle);
        Assert.assertEquals(actualPageTitle, expectedPageTitle);
    }

    @Then("verify basic link")
    public void verify_basic_link() {
        WebElement e = driver.findElement(By.xpath(".//*[contains(text(),\"Basic Web Page Example\")]"));
        String actualText = e.getText();
        System.out.println(actualText);
        String expectedResult = "Basic Web Page Example";
        Assert.assertEquals(actualText, expectedResult);
        e.click();
        driver.navigate().back();
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    public static void main(String[] args) {
        System.out.println("######################### Test Ended #########################");
    }
}

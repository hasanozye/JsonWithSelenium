package org.example;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.Duration;

public class JsonTest {
    WebDriver driver;

    {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public static void main(String[] args) throws FileNotFoundException {
        // 1. Defines the file path for the JSON file containing the data.
        String jsonFile = "src/test/resources/datafiles/Elements.json";

        // 2. Reads the JSON file for the first time and parse it into a JSONObject.
        //    The contents of the JSON file are parsed and stored in the 'object' variable.
        JSONObject object = (JSONObject) JSONValue.parse(new FileReader(jsonFile));

        // 3. This statement outputs the entire parsed JSON object to the console.
        System.out.println(object);
    }


    @Test
    public void test1() throws FileNotFoundException {
        driver.get("https://demoqa.com/text-box");
        driver.findElement(getBy("DemoQA","FullName")).sendKeys("hasan");
        driver.findElement(getBy("DemoQA","Email")).sendKeys("test@test.com");
        driver.findElement(getBy("DemoQA","Current Address")).sendKeys("Qatar");
        driver.quit();


    }

    public static By getBy(String main, String sub) throws FileNotFoundException {

        String jsonFile = "src/test/resources/datafiles/Elements.json";

        JSONObject object = (JSONObject) JSONValue.parse(new FileReader(jsonFile));
        JSONObject mainNode = (JSONObject) object.get(main);
        JSONObject subNode = (JSONObject) mainNode.get(sub);

//         get
        String type = subNode.get("type").toString();
        String locator = subNode.get("locator").toString();

        switch (type) {
            case "xpath" -> {
                return By.xpath(locator);
            }
            case "css" -> {
                return By.cssSelector(locator);
            }
            case "id" -> {
                return By.id(locator);
            }
            case "tagname" -> {
                return By.tagName(locator);
            }
            case "classname" -> {
                return By.className(locator);
            }
            case "linktext" -> {
                return By.linkText(locator);
            }
            case "partiallinktext" -> {
                return By.partialLinkText(locator);
            }
            default -> {
                return null;
            }
        }

    }

}

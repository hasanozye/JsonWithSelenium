package mainapp;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.openqa.selenium.By;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonReadClass {
    public static void main(String[] args) throws FileNotFoundException {
        String JSONFile ="src/test/resources/datafiles/Elements.json";

        JSONObject object = (JSONObject) JSONValue.parse(new FileReader(JSONFile));


        JSONObject demoQA = (JSONObject) object.get("DemoQA");
        JSONObject fullName = (JSONObject) demoQA.get("FullName");

        String type = fullName.get("type").toString();
        String locator = fullName.get("locator").toString();
        System.out.println("type = " + type);
        System.out.println("locator = " + locator);


    }

    public static By getBy(String type, String locator) {

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

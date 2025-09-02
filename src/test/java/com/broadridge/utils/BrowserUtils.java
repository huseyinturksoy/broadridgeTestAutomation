package com.broadridge.utils;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class BrowserUtils {

    //------------------------------- VARIABLES -------------------------------






    //------------------------------- METHODS -------------------------------




    /**
     * accepts seconds as parameter, waits for given duration
     * @param seconds
     */
    public static void sleep(long seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void click(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor)Driver.getDriver();
        Actions action = new Actions(Driver.getDriver());

        int numAttempts = 5;
        int attempts = 0;

        while (attempts < numAttempts) {
            try {
                element.click();
                //System.out.println("Element clicked successfully");
                break; // If the click is successful, exit the loop
            } catch (WebDriverException e) {
                js.executeScript("arguments[0].click();", element);
                break;

            }catch (Exception e){
                System.out.println("Attempt #" + (attempts + 1) + ": Click failed");
            }
            attempts++;
        }

        if (attempts == numAttempts) {
            throw new RuntimeException("Element could not be clicked after " + attempts + " attempts");
        }
    }



    /**
     * accepts WebElement as parameter, waits for visibility of given WebElement
     */
    public static void waitForVisible(WebElement element){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofMillis(2000));
        //WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(25));
        wait.until(ExpectedConditions.visibilityOf(element));
        //System.out.println(elementName + "Element is successfully displayed");
    }

    public static void waitForVisible(By byElement){

        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(),Duration.ofMinutes(2000));
            //WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(25));
            wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));
            System.out.println("Element is displayed successfully on page");
        } catch (Exception e) {

        }
    }

    public static void waitForInvisibility(By byElement){
        try{
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofMinutes(2000));
            //WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(25));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(byElement));
            System.out.println("The element is removed successfully from page");
//May apply thread sleep for 1 or 2 seconds
        }catch(RuntimeException e){
            throw new RuntimeException("Element couldn't disappear from page");
        }}



    /**
     * accepts WebElement as parameter, waits for clickablity of given WebElement
     * @param element
     */
    public static void waitForClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),Duration.ofMillis(2500));
        //WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(25));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForClickable(By element){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),Duration.ofMillis(2500));
        //WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(25));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }




    /**
     * accepts title as parameter, waits title to be given title
     * @param title
     */
    public static void waitForTitle(String title){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),Duration.ofMillis(2500));
        //WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(25));
        wait.until(ExpectedConditions.titleIs(title));
        Assert.assertEquals(Driver.getDriver().getTitle(),title);
    }

    public static void waitForTitleToContain(String title){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),Duration.ofMillis(2500));
        //WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(25));
        wait.until(ExpectedConditions.titleContains(title));
        Assert.assertTrue(Driver.getDriver().getTitle().contains(title));
    }


    public static void waitForUrlToContain(String string){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),Duration.ofMillis(2500));
        //WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(25));
        wait.until(ExpectedConditions.urlContains(string));
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains(string));
    }

    public static void checkCheckbox(WebElement checkbox){
        if (checkbox.getAttribute("className").contains("checked")){
            System.out.println("It is already checked!");
        }else {
            click(checkbox);
        }
    }

    public static void uncheckCheckbox(WebElement checkbox){
        if (checkbox.getAttribute("className").contains("checked")){
            click(checkbox);
        }else {
            System.out.println("It is already unchecked!");
        }
    }

    public static void scrollToElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true)", element);
    }

    public static void waitAttributeNotEmpty(WebElement element, String attribute){

        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(),Duration.ofMillis(2500));
            //WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(25));
            wait.until(ExpectedConditions.attributeToBeNotEmpty(element,attribute));
            System.out.println("Element is displayed successfully on page");
        } catch (Exception e) {

        }
    }

    public static void waitPresenceOfElement(By byElement){

        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(),Duration.ofMillis(2500));
            wait.until(ExpectedConditions.presenceOfElementLocated(byElement));
            System.out.println("Element is displayed successfully on page");
        } catch (Exception e) {

        }
    }

    public static String getRandomOptionString2(List<String> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("The list must not be null or empty.");
        }

        Random random = new Random();
        int randomIndex = random.nextInt(list.size());

        return list.get(randomIndex);
    }

}

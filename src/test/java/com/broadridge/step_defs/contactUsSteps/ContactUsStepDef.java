package com.broadridge.step_defs.contactUsSteps;

import com.broadridge.pages.contactUsPage.ContactUsPage;
import com.broadridge.utils.BrowserUtils;
import com.broadridge.utils.ConfigurationReader;
import com.broadridge.utils.Driver;
import com.broadridge.utils.GenerateUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactUsStepDef {

    private ContactUsPage contactPage = new ContactUsPage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(),25);

    JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

    @Given("User navigates to contact us form page")
    public void userNavigatesToContactUsFormPage() throws InterruptedException {
        Driver.getDriver().get(ConfigurationReader.getProperty("contactUsFormUrl"));
        Thread.sleep(3000);



        try {

            WebElement acceptBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("onetrust-accept-btn-handler")
            ));
            acceptBtn.click();


            //js.executeScript("arguments[0].click();", contactPage.acceptCookiesButton);

            //BrowserUtils.click(contactPage.acceptCookiesButton);
            System.out.println("Cookies accepted");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No cookies to accept");
        }

        WebElement firstnameInput = Driver.getDriver().findElement(By.xpath("(//input[@placeholder='First name'])[2]"));
        //= By.xpath("(//input[@placeholder='First name'])[2]");

        Actions action =new Actions(Driver.getDriver());
        action.moveToElement(firstnameInput).perform();
        //js.executeScript("window.scrollTo(0, document.body.scrollHeight);");


        Assert.assertTrue(firstnameInput.isDisplayed());

    }




    @When("User fills the contact us form")
    public void userFillsTheContactUsForm() {
        String firstName = GenerateUtils.generateShortName();
        String lastName = GenerateUtils.generateShortName();


    }

    @Then("User validates the contact us form")
    public void userValidatesTheContactUsForm() {
    }
}

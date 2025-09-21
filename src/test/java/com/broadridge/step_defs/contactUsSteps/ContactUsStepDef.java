package com.broadridge.step_defs.contactUsSteps;

import com.broadridge.pages.contactUsPage.ContactUsPage;
import com.broadridge.utils.BrowserUtils;
import com.broadridge.utils.ConfigurationReader;
import com.broadridge.utils.Driver;
import com.broadridge.utils.GenerateUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.AfterClass;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v134.network.model.Request;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Optional;


public class ContactUsStepDef {

    private ContactUsPage contactPage = new ContactUsPage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofMillis(2000));




    @Given("User navigates to contact us form page")
    public void userNavigatesToContactUsFormPage() throws InterruptedException {
        Driver.getDriver().get(ConfigurationReader.getProperty("contactUsFormUrl"));
        Thread.sleep(3000);



        try {

            WebElement acceptBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("onetrust-accept-btn-handler")
            ));
            //acceptBtn.click();
            BrowserUtils.click(acceptBtn);


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

        System.out.println("---------------Navigated to contact us form --------------");

    }




    @When("User fills the contact us form")
    public void userFillsTheContactUsForm() {
        contactPage.firstName.sendKeys("First_TESTTEST");
        contactPage.lastName.sendKeys("Last_TESTTEST");
        contactPage.email.sendKeys("automated_TESTTEST@broadridge.com");
        contactPage.phone.sendKeys("999-999-9999");
        contactPage.jobTitle.sendKeys("Job_TESTTEST");
        contactPage.companyName.sendKeys("Company_TESTTEST");
        BrowserUtils.click(contactPage.countrySelectorButton); //contactPage.countrySelectorButton.click();
        BrowserUtils.click(contactPage.unitedStatesOption); //contactPage.unitedStatesOption.click();
        contactPage.message.sendKeys("This is an automated test submission.  Please ignore.");
        BrowserUtils.click(contactPage.submitButton);  //contactPage.submitButton.click();

        System.out.println("---------------Contact us button clicked --------------");

        try {
            System.out.println("Form Submission Message = " + contactPage.thankyouMessage.getText());
        } catch (Exception e) {
            System.out.println("Form Submission Message = " + contactPage.errorMessage.getText());
            throw new RuntimeException(e);
        }

    }

    @Then("User validates the contact us form")
    public void userValidatesTheContactUsForm() throws JsonProcessingException {

        String formpayload = Driver.formpayload;
        System.out.println("formpayload for validation = " + formpayload);

        String formSubmissionId = BrowserUtils.getJsonField(formpayload, "formSubmissionId");
        String fullURL = BrowserUtils.getJsonField(formpayload, "fullURL");

        System.out.println("formSubmissionId = " + formSubmissionId);
        System.out.println("fullURL = " + fullURL);

        Assert.assertTrue(formSubmissionId != null);
        Assert.assertEquals(fullURL,Driver.getDriver().getCurrentUrl());



    }



}

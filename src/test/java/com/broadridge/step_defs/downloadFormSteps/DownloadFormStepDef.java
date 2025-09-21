package com.broadridge.step_defs.downloadFormSteps;

import com.broadridge.pages.DownloadFormPage.DownloadFormPage;
import com.broadridge.utils.BrowserUtils;
import com.broadridge.utils.ConfigurationReader;
import com.broadridge.utils.Driver;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DownloadFormStepDef {

    private DownloadFormPage downloadFormPage = new DownloadFormPage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofMillis(2000));

    Actions action =new Actions(Driver.getDriver());

    @Given("User navigates to download form page")
    public void userNavigatesToDownloadFormPage() throws InterruptedException {
        Driver.getDriver().get(ConfigurationReader.getProperty("downloadformUrl"));
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


        action.moveToElement(firstnameInput).perform();
        //js.executeScript("window.scrollTo(0, document.body.scrollHeight);");


        Assert.assertTrue(firstnameInput.isDisplayed());

        System.out.println("---------------Navigated to download form --------------");

    }

    @When("User fills the download form")
    public void userFillsTheDownloadForm() throws InterruptedException {
        downloadFormPage.firstName.sendKeys("First_TESTTEST");
        downloadFormPage.lastName.sendKeys("Last_TESTTEST");
        downloadFormPage.email.sendKeys("automated_TESTTEST@broadridge.com");
        downloadFormPage.companyName.sendKeys("Company_TESTTEST");
        //downloadFormPage.checkBoxForExtraField.click();
        BrowserUtils.click(downloadFormPage.checkBoxForExtraField);
        System.out.println("---------------checkbox is clicked for extra field --------------");
        Thread.sleep(2000);
        System.out.println("---------------waited 2 seconds --------------");
        try {
            System.out.println("phone field = " + downloadFormPage.phone.isDisplayed());
            downloadFormPage.phone.sendKeys("999-999-9999");
            System.out.println("---------------phone field is filled --------------");
        } catch (Exception e) {
            BrowserUtils.click(downloadFormPage.phone);
            action.sendKeys("999-999-9999").perform();

            System.out.println("---------------extra field is forced to fill --------------");
        }
        //downloadFormPage.phone.sendKeys("999-999-9999");
        System.out.println("---------------telephone field is filled --------------");
        downloadFormPage.jobTitle.sendKeys("Job_TESTTEST");
        //downloadFormPage.countrySelectorButton.click();
        BrowserUtils.click(downloadFormPage.countrySelectorButton);
        //downloadFormPage.unitedStatesOption.click();
        BrowserUtils.click(downloadFormPage.unitedStatesOption);
        downloadFormPage.message.sendKeys("This is an automated test submission.  Please ignore.");
        //downloadFormPage.submitButton.click();
        BrowserUtils.click(downloadFormPage.submitButton);

        System.out.println("---------------Download form button clicked --------------");

        try {
            System.out.println("Form Submission Message = " + downloadFormPage.thankyouMessage.getText());
        } catch (Exception e) {
            System.out.println("Form Submission Message = " + downloadFormPage.errorMessage.getText());
            throw new RuntimeException(e);

        }


    }

    @Then("User validates the download form")
    public void userValidatesTheDownloadForm() throws JsonProcessingException {

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

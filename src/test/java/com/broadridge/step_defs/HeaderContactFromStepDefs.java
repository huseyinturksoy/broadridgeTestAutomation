package com.broadridge.step_defs;

import com.broadridge.pages.headerContactForm.HeaderContactFormPage;
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

public class HeaderContactFromStepDefs {

    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofMillis(2000));

    private HeaderContactFormPage headerContact = new HeaderContactFormPage();

    @Given("User navigates header contact us form")
    public void userNavigatesHeaderContactUsForm() throws InterruptedException {
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

        BrowserUtils.click(headerContact.headerContactUsButton);

        WebElement firstnameInput = Driver.getDriver().findElement(By.xpath("(//input[@placeholder='First name'])[1]"));
        //= By.xpath("(//input[@placeholder='First name'])[2]");

        Assert.assertTrue(firstnameInput.isDisplayed());

        System.out.println("---------------Header Contact Us Form is Displayed --------------");

    }

    @When("User fills the header contact us form")
    public void userFillsTheHeaderContactUsForm() {

        headerContact.headerFirstName.sendKeys("First_TESTTEST");
        headerContact.headerLastName.sendKeys("Last_TESTTEST");
        headerContact.headerEmail.sendKeys("automated_TESTTEST@broadridge.com");
        headerContact.headerPhone.sendKeys("999-999-9999");
        headerContact.headerJobTitle.sendKeys("Job_TESTTEST");
        headerContact.headerCompanyName.sendKeys("Company_TESTTEST");
        BrowserUtils.click(headerContact.headerCountrySelectorButton);  //headerContact.headerCountrySelectorButton.click();
        BrowserUtils.click(headerContact.headerUnitedStatesOption); //headerContact.headerUnitedStatesOption.click();
        headerContact.headerMessage.sendKeys("This is an automated test submission.  Please ignore.");
        BrowserUtils.click(headerContact.headerSubmitButton); //headerContact.headerSubmitButton.click();

        System.out.println("---------------Header Contact Us form submit button clicked --------------");

        try {
            System.out.println("Form Submission Message = " + headerContact.thankyouMessage.getText());
        } catch (Exception e) {
            System.out.println("Form Submission Message = " + headerContact.errorMessage.getText());
            throw new RuntimeException(e);
        }



    }

    @Then("User validates the header contact us form")
    public void userValidatesTheHeaderContactUsForm() throws JsonProcessingException {
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

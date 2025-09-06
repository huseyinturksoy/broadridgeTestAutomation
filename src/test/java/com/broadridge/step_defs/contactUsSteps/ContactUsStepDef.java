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
        System.out.println("firstName = " + firstName);
        String lastName = GenerateUtils.generateLastName();
        System.out.println("lastName = " + lastName);
        String email = GenerateUtils.generateEmail();
        System.out.println("email = " + email);
        String phone = GenerateUtils.generatePhoneNumber();
        System.out.println("phone = " + phone);
        String jobTitle = GenerateUtils.generateJobTitle();
        System.out.println("jobTitle = " + jobTitle);
        String companyName = GenerateUtils.generateCompanyName();
        System.out.println("companyName = " + companyName);
        String message = GenerateUtils.generateMessage();
        System.out.println("message = " + message);

        contactPage.firstName.sendKeys(firstName);
        contactPage.lastName.sendKeys(lastName);
        contactPage.email.sendKeys(email);
        contactPage.phone.sendKeys(phone);
        contactPage.jobTitle.sendKeys(jobTitle);
        contactPage.companyName.sendKeys(companyName);
        contactPage.countrySelectorButton.click();
        contactPage.unitedStatesOption.click();
        contactPage.message.sendKeys(message);
        BrowserUtils.click(contactPage.submitButton);

        try {
            System.out.println("Form Submission Message = " + contactPage.thankyouMessage.getText());
        } catch (Exception e) {
            System.out.println("Form Submission Message = " + contactPage.errorMessage.getText());
        }




    }

    @Then("User validates the contact us form")
    public void userValidatesTheContactUsForm() {
        String formURL = Driver.req.getUrl();
        System.out.println("formURL = " + formURL);
        String formPayload= Driver.req.getPostData().orElse("No payload").toString();
        System.out.println("formPayload = " + formPayload);



    }
}

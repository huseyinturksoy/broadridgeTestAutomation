package com.broadridge.pages.contactUsPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactUsPage {

    @FindBy(xpath = "(//input[@placeholder='First name'])[2]")
    public WebElement firstName;

    @FindBy(id = "name_last11")
    public WebElement lastName;

    @FindBy(id = "email_work11")
    public WebElement email;

    @FindBy(id = "phone_business11")
    public WebElement phone;

    @FindBy(xpath = "(//input[@id='job_title'])[2]")
    public WebElement jobTitle;

    @FindBy(id = "company11")
    public WebElement companyName;

    @FindBy(xpath = "//button[@aria-labelledby='country-combobox-value-1']")
    public WebElement countrySelectorButton;

    @FindBy(xpath = "(//li[@data-value=\"United States\"])[2]")
    public WebElement unitedStatesOption;

    @FindBy(xpath = "(//textarea[@id='comment'])[2]")
    public WebElement message;

    @FindBy(xpath = "(//button[@type='submit'])[2]")
    public WebElement submitButton;

    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    public WebElement acceptCookiesButton;











}

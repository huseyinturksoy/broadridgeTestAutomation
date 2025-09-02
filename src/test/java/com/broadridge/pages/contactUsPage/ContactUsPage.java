package com.broadridge.pages.contactUsPage;

import com.broadridge.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactUsPage {

    public ContactUsPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "(//input[@placeholder='First name'])[2]")
    public WebElement firstName;

    @FindBy(xpath = "(//input[@placeholder='Last name'])[2]")
    public WebElement lastName;

    @FindBy(xpath = "(//input[@placeholder='john@email.com'])[2]")
    public WebElement email;

    @FindBy(xpath = "(//input[@placeholder='+1 _ _ _ - _ _ _ - _ _ _ _'])[2]")
    public WebElement phone;

    @FindBy(xpath = "(//input[@id='job_title'])[2]")
    public WebElement jobTitle;

    @FindBy(xpath = "(//input[@placeholder='Your company name'])[2]")
    public WebElement companyName;

    @FindBy(xpath = "//button[@aria-labelledby='country-combobox-value-1']")
    public WebElement countrySelectorButton;

    @FindBy(xpath = "(//li[@data-value='United States'])[2]")
    public WebElement unitedStatesOption;

    @FindBy(xpath = "(//textarea[@id='comment'])[2]")
    public WebElement message;

    @FindBy(xpath = "(//button[@type='submit'])[2]")
    public WebElement submitButton;



    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    public WebElement acceptCookiesButton;

    @FindBy(xpath = "//div[@class='contact-us__success']")
    public WebElement thankyouMessage;

    @FindBy(xpath = "//div[@class='contact-us__error']")
    public WebElement errorMessage;











}

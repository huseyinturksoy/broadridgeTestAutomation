package com.broadridge.pages.headerContactForm;

import com.broadridge.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderContactFormPage {
    public HeaderContactFormPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//button[@data-modal='contact-modal']")
    public WebElement headerContactUsButton;

    @FindBy(xpath = "(//input[@placeholder='First name'])[1]")
    public WebElement headerFirstName;

    @FindBy(xpath = "(//input[@placeholder='Last name'])[1]")
    public WebElement headerLastName;

    @FindBy(xpath = "(//input[@placeholder='john@email.com'])[1]")
    public WebElement headerEmail;

    @FindBy(xpath = "(//input[@placeholder='+1 _ _ _ - _ _ _ - _ _ _ _'])[1]")
    public WebElement headerPhone;

    @FindBy(xpath = "(//input[@id='job_title'])[1]")
    public WebElement headerJobTitle;

    @FindBy(xpath = "(//input[@placeholder='Your company name'])[1]")
    public WebElement headerCompanyName;

    @FindBy(xpath = "//button[@aria-labelledby='country-combobox-value-0']")
    public WebElement headerCountrySelectorButton;

    @FindBy(xpath = "(//li[@data-value='United States'])[1]")
    public WebElement headerUnitedStatesOption;

    @FindBy(xpath = "(//textarea[@id='comment'])[1]")
    public WebElement headerMessage;

    @FindBy(xpath = "(//button[@type='submit'])[1]")
    public WebElement headerSubmitButton;

    @FindBy(xpath = "//div[@class='contact-us__success']")
    public WebElement thankyouMessage;

    @FindBy(xpath = "//div[@class='contact-us__error']")
    public WebElement errorMessage;




}

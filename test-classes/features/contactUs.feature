@smoke
  Feature: Contact Us form validation

    @contact
    Scenario: User fills the contact us form and validates the form on BE
      Given User navigates to contact us form page
      When User fills the contact us form
      Then User validates the contact us form
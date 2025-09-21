@smoke
Feature: Header Contact Us form validation

  @headerContact
  Scenario: User opens the header contact us form and fills the contact us form and validates the form on BE
    Given User navigates header contact us form
    When User fills the header contact us form
    Then User validates the header contact us form
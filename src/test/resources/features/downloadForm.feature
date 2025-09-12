@smoke
Feature: Download form validation

  @download
  Scenario: User fills the download form and validates the form on BE
    Given User navigates to download form page
    When User fills the download form
    Then User validates the download form
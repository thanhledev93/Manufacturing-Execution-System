
Feature: Login
  
  Background: Below are common steps for every scenario
    Given User Launch Chrome browser and User opens URL "https://quanlysanxuat.online/sign-in"

  Scenario: Success Login with Valid Credentials
    When User enters Username as "administrator" and Password as "123456@#"
    And Click on Login
    Then Page title Username is display
    When  User click on Log out link
    Then Form login title should be "Sign in"
    And close browser

  Scenario: UnSuccess Login with username or password is incorrect
    And User enters Username as "administrators" and Password as "123456@#1"
    And Click on Login
    Then Display error alert
    And close browser

  Scenario: UnSuccess Login when username and password is blank
    When User click on username and password without entering the value
    Then show below error message textbox username and password
    And close browser

    Scenario Outline: Login Data Driven
      And User enters Username as "<username>" and Password as "<password>"
      And Click on Login
      Then Page title Username is display
      When  User click on Log out link
      Then Form login title should be "Sign in"
      And close browser

      Examples:
        | username | password |
        | hungnq | 123 |
        | khanhhq | 123 |

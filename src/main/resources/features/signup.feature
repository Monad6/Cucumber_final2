Feature: SignUp Feature

  Scenario: SignUp with valid credentials
    Given user opens homepage and clicks on SignUp button
    When user enters a valid username and a valid password, then clicks on the sign-up button
    Then a successful message appears and user is switched to the homepage


Feature: End To End Feature

  Scenario: Complete end to end flow
    Given user opens homepage and clicks on LogIn
    When user enters "Monad" and "monad1234" and press on LogIn button
    Then user should be logged in successfully

    When user adds the first product "Samsung galaxy s6 " to the cart
     And user adds the second product "Nexus 6" to the cart
     And user navigates to the cart
     Then cart should contains the products
     And total price should be 1010
     When users enters place order
     |Name|Country|city|card|Month|Year|
     |Monad|Egypt |cairo|123456|May|2025|
     Then confirmation message should appear "Thank you for your purchase!"

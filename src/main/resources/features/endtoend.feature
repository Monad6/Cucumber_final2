Feature: End To End Feature

  Scenario: Login with valid credentials
    Given user opens homepage and clicks on LogIn
    When user enters username and password and press on LogIn button

   Scenario :  user adds two products to cart and complete purchase
     When user adds the first product "Samsung galaxy s6 " to the cart
     And user adds the second product "Nexus 6" to the cart
     And user navigates to the cart
     Then cart should contains the products

     And total price should be 1010
     When users enters place order
     |Name|Country|city|card|Month|Year|
     |Monad|Egypt |cairo|123456|May|2025|
     Then confirmation message should appear "Thank you for your purchase!"

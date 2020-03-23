Feature: The discount on fruits

  Background: :
    Given the price of a Cerises is 75
    And the price of a Bananes is 150

  Scenario: Apply a discount when enough fruits
    Given There is a discount of 20 every 2 Cerises
    And 2 Cerises in the basket
    When I ask for the receipt
    Then the total is 130
    And the receipt display the price of 130 for 2 Cerises

  Scenario: Apply a discount when enough fruits
    Given There is a discount of 20 every 3 Cerises
    And 2 Cerises in the basket
    And I ask for the receipt
    Then the total is 150
    And the receipt display the price of 150 for 2 Cerises

  Scenario: Apply several discounts
    Given There is a discount of 20 every 2 Cerises
    And There is a discount of 150 every 2 Bananes
    And 2 Cerises in the basket
    And 2 Bananes in the basket
    When I ask for the receipt
    Then the total is 280
    And the receipt display the price of 130 for 2 Cerises
    And the receipt display the price of 150 for 2 Bananes

    
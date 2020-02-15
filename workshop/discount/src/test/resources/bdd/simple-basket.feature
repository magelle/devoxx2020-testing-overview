Feature: Create te receipt of a basket

  Scenario: Nothing is in the basket
    Given the basket is empty
    When I ask for the receipt
    Then the total is 0
    And the item list is empty

  Scenario: Just one fruit in the basket
    Given the price of a Pommes is 100
    When I add 1 Pommes in the basket
    And I ask for the receipt
    Then the total is 100
    And the receipt display the price of 100 for 1 Pommes

  Scenario: Several fruits in the basket
    Given the price of a Pommes is 100
    And the price of a Bananes is 150
    When I add 2 Pommes in the basket
    When I add 1 Bananes in the basket
    And I ask for the receipt
    Then the total is 350
    And the receipt display the price of 200 for 2 Pommes
    And the receipt display the price of 150 for 1 Bananes
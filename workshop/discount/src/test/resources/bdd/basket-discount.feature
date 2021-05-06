Feature: Basket level discounts

  Background:
    Given the price of a Pommes is 100
    Given the price of a Mele is 100
    Given the price of a Bananes is 100
    Given the price of a Cerises is 100
    Given the price of a Apples is 100

  Scenario: having the "more than 5 fruits" discount
    Given there is a discount of 200 for 5 fruits
    And the basket contains 1 Pommes
    And the basket contains 1 Mele
    And the basket contains 1 Bananes
    And the basket contains 1 Cerises
    And the basket contains 1 Apples
    When the customer ask for the receipt
    Then the total is 300

  Scenario: having the "more than 5 fruits" discount, but not applied
    Given there is a discount of 200 for 5 fruits
    And the basket contains 4 Pommes
    When the customer ask for the receipt
    Then the total is 400

  Scenario: Applying the 4 localized pommes on the same type of pommes
    Given there is a discount of 100 for 4 localized Pommes
    And the basket contains 4 Pommes
    When the customer ask for the receipt
    Then the total is 300

  Scenario: not applying the 4 localized pommes on the same type of pommes
    Given there is a discount of 100 for 4 localized Pommes
    And the basket contains 3 Pommes
    When the customer ask for the receipt
    Then the total is 300

  Scenario: Applying the 4 localized pommes on the several type of pommes
    Given there is a discount of 100 for 4 localized Pommes
    And the basket contains 1 Pommes
    And the basket contains 1 Mele
    And the basket contains 2 Apples
    When the customer ask for the receipt
    Then the total is 300

  Scenario: Free fruits
    Given there is a discount of 100 for 4 localized Pommes
    And there is a discount of 200 for 5 fruits
    And there is a discount of 100 every 2 Mele
    And there is a discount of 100 every 3 Apples
    And the basket contains 2 Mele
    And the basket contains 3 Apples
    When the customer ask for the receipt
    Then the total is 0

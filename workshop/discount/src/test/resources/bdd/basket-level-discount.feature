Feature: Basket level discounts

  Background:
    Given the price of a Pommes is 100
    Given the price of a Mele is 100
    Given the price of a Bananes is 100
    Given the price of a Cerises is 100
    Given the price of a Apples is 100

  Scenario: having the "more than 5 fruits" discount
    Given I have a discount of 200 for 5 fruits
    When I add 1 Pommes in the basket
    When I add 1 Mele in the basket
    When I add 1 Bananes in the basket
    When I add 1 Cerises in the basket
    When I add 1 Apples in the basket
    And I ask for the receipt
    Then the total is 300

  Scenario: having the "more than 5 fruits" discount, but not applied
    Given I have a discount of 200 for 5 fruits
    But I add 4 Pommes in the basket
    And I ask for the receipt
    Then the total is 400

  Scenario: Applying the 4 localized pommes on the same type of pommes
    Given I have a discount of 100 for 4 localized Pommes
    But I add 4 Pommes in the basket
    And I ask for the receipt
    Then the total is 300

  Scenario: not applying the 4 localized pommes on the same type of pommes
    Given I have a discount of 100 for 4 localized Pommes
    But I add 3 Pommes in the basket
    And I ask for the receipt
    Then the total is 300

  Scenario: Applying the 4 localized pommes on the several type of pommes
    Given I have a discount of 100 for 4 localized Pommes
    But I add 1 Pommes in the basket
    But I add 1 Mele in the basket
    But I add 2 Apples in the basket
    And I ask for the receipt
    Then the total is 300

  Scenario: Free fruits
    Given I have a discount of 100 for 4 localized Pommes
    And I have a discount of 200 for 5 fruits
    And There is a discount of 100 every 2 Mele
    And There is a discount of 100 every 3 Apples
    But I add 2 Mele in the basket
    But I add 3 Apples in the basket
    And I ask for the receipt
    Then the total is 0

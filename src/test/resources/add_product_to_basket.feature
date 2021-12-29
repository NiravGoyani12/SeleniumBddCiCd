@smoke
Feature: Add product to basket
  As a customer,
  I want to add product to basket
  so that I can buy product

  Scenario: View books
    Given I am on the home page
    When I click on the "Books"
    Then I should see result page
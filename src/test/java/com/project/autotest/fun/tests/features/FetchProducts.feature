Feature: Flipkart - Fetch Products

  Scenario: Fetch Products with maximum price of 40k on Flipkart and save as CSV.

    Given I go to https://www.flipkart.com
    When I click on the login overlay close button
    Then the home page should appear
    When I enter the search bar - Iphone
    And I click on the search button
    Then the search page should appear
    When I click on the sort low high link
    Then the search page should appear
    And I should store the product name link,product price link,product rating link for max price of INR40000
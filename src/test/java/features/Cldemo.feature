#language: en
Feature: Cldemo base functionality

  Scenario: Cldemo base scenario
    Given i navigate to the "All tickets page"
    Then i am on the "All tickets page"
    Then i check that "Select Team" dropdown contains the following elements:
      | AC Milan					|
      | AFC Ajax                  |
      | AJ Auxerre                |
      | Arsenal FC                |
      | AS Roma                   |
      | Bursaspor                 |
      | CFR 1907 Cluj             |
      | Chelsea FC                |
      | FC Barcelona              |
      | FC Basel 1893             |
      | FC Bayern Munchen         |
      | FC Internazionale Milano  |
      | FC Kobenhavn              |
      | FC Rubin Kazan            |
      | FC Schalke 04             |
      | FC Shakhtar Donetsk       |
      | FC Spartak Moskva         |
      | FC Twente                 |
      | FK Partizan               |
      | Hapoel Tel-Aviv FC        |
      | Manchester United FC      |
      | MSK Zilina                |
      | Olympique de Marseille    |
      | Olympique Lyonnais        |
      | Panathinaikos             |
      | Rangers FC                |
      | Real Madrid CF            |
      | SC Braga                  |
      | SL Benfica                |
      | SV Werder Bremen          |
      | Tottenham Hotspur FC      |
      | Valencia CF               |
    And on the "All tickets page" i select "FK Partizan" option located in "Select team dropdown"
    And on the "All tickets page" i click on "Check Games button"
    And i click on "Check Tickets button" for game where opponent is "Arsenal FC"
    And i check that on the "All tickets page" the following elements have expected values:
      | Element name:          | Expected value: |
      | Wip price label        | 1300,00         |
      | Primary price label    | 600,00          |
      | Secondary price label  | 400,00          |
    And on the "All tickets page" i set "10" on the "Primary amount textbox"
    And on the "All tickets page" i click on "Add to shopping cart button"
    And i check that on the "All tickets page" the following elements have expected values:
      | Element name:                       | Expected value:                                                           |
      | Adding to shopping cart status text | The items have been added to your cart. You have 10 tickets in your cart. |
    And on the "All tickets page" i click on "Shopping Cart tab"
    And i am on the "Shopping Cart page"
    And i check that on the "Shopping Cart page" the following elements have expected values:
      | Element name:               | Expected value:                                                        |
      | Shopping cart status header | You have 10 items in your shopping cart with a total value of 6000 EUR |
    And i check the following data in Shopping cart table from row #1:
      | Column header:           | Expected value:          |
      | Home team                | Arsenal FC               |
      | Away team                | FK Partizan              |
      | Date                     | lördag, december 8, 2012 |
      | Nr. of WIP seats         | 0                        |
      | Nr. of primary seats     | 10                       |
      | Nr. of secondary seats   | 0                        |
    And i check that on the "Shopping Cart page" the following elements have expected values:
      | Element name:     | Expected value: |
      | Number of tickets | 10 tickets      |

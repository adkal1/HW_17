Feature: Testing Registration Page

  Background:
    Given Set up driver

  Scenario Outline: Testing of positive filling Registration Page
    When opening Registration Page
    And set First Name
    And set Last Name
    And set Date of Birth
    And set email "<text1>"
    And set password
    And set confirmation password
    And click Submit button

    Then check success registration
    Then quit driver
    Examples:
      | text1       |
      | ad3@mail.il |

  @need_to_run
  Scenario Outline: Testing with already registered Email
    When opening Registration Page
    And set First Name
    And set Last Name
    And set Date of Birth
    And set email "<text1>"
    And set password
    And set confirmation password
    And click Submit button

    Then check failure registration
    Then quit driver
    Examples:
      | text1       |
      | ad3@mail.il |

  @need_to_run
  Scenario Outline: Testing empty email field
    When opening Registration Page
    And set First Name
    And set Last Name
    And set Date of Birth
    And set email "<text1>"
    And set password
    And set confirmation password
    And click Submit button

    Then check error email field
    Then quit driver
    Examples:
      | text1 |
      |       |


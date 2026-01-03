Feature:Test of basketballengland registration users

  Scenario Outline: Add new user and everything should work.
    Given I am on basketballengland.co.uk on "<browser>"
    When I fill in date of birth "<date of birth>"
    And I fill in the first name "<first name>"
    And I fill in an last name "<last name>"
    And I fill in the email and then confirm email
    And I choose the password and retype the password "<password>"
    And I check I have read Terms and Conditions "<terms accepted>"
    And I confirm that I am over the age limit
    And I check that I have read Code of Conduct
    And Then I press Confirm and join
    Then I successfully become a member
    Examples:
      | browser | date of birth | first name | last name | password | terms accepted |
      | chrome  |05/05/1996 | Jocke | Holmstrom | test12345 | true |
      | firefox  |06/02/1990 | Henrik | Stormare | test55221 | true |
      | edge  |15/08/1998 | Daniel | Andersson | testsss15 | true |

  Scenario Outline: Add new user and try to fail.
    Given I am on basketballengland.co.uk on "<browser>"
    When I fill in date of birth "<date of birth>"
    And I fill in the first name "<first name>"
    And I fill in an last name "<last name>"
    And I fill in the email and then confirm email
    And I fill in password "<password>"
    And I fill in confirmed password "<confirmed password>"
    And I check I have read Terms and Conditions "<terms accepted>"
    And I confirm that I am over the age limit
    And I check that I have read Code of Conduct
    And Then I press Confirm and join
    Then It failed because "<error message>"

    Examples:
      | browser | date of birth | first name | last name | password | confirmed password | terms accepted | error message |
      | chrome  |05/05/1996 | Jocke |  | test12345 | test12345 | true | Last Name is required |
      | firefox  |01/05/1992 | Henrik | Stormare | test12345 | test1234 | true | Password did not match |
      | chrome  |15/08/1998 | Daniel | Andersson | testsss15 |testsss15 | false | You must confirm that you have read and accepted our Terms and Conditions |
Feature: User and Admin Login

  Background:
    Given User navigates to the application
    And User click on the login link

  Scenario: User login with valid credentials
    Given I am on the login page
    And I enter "user" into the username field
    And I enter "password" into the password field
    When I click the login button
    Then I should see a "error" message
    And I should be redirected to the "login" page

#   Scenario: Admin login with valid credentials
#     Given I am on the login page
#     And I click the admin login toggle button
#     And I enter "admin" into the username field
#     And I enter "password" into the password field
#     When I click the login button
#     Then I should see a success message
#     And I should be redirected to the user home page

#   Scenario: User login with invalid credentials
#     Given I am on the login page
#     And I enter "user" into the username field
#     And I enter "wrongpassword" into the password field
#     When I click the login button
#     Then I should see an error message

#   Scenario: Admin login with invalid credentials
#     Given I am on the login page
#     And I click the admin login toggle button
#     And I enter "admin" into the username field
#     And I enter "wrongpassword" into the password field
#     When I click the login button
#     Then I should see an error message

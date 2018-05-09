#language: en
Feature: A user wants to change his password

  Scenario: Change password
   Given a list of users:
      | name    | password |	kindCode	|
      | Prueba07    | 123456   |	1 |	
    When I login with name "Prueba07" and password "123456" and kind "1"
    And I click on change password
    And I introduce new password "newpass"
    Then My password is changed


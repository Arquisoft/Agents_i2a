#language: en
Feature: A user wants to log into the module

  Scenario: Login
   Given a list of users:
      | name    | password |	kindCode	|
      | Prueba07    | 123456   |	1 |	
    When I login with name "Prueba07" and password "123456" and kind "1"
    Then I reach the home page with success

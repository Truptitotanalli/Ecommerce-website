Feature: purchage functionality


Background:
Given i landed on ecommers page

  Scenario Outline: positive test of submitting order
  
    Given loged in with username <name> and password <password>
    When i add product <productname> to cart
    And checkout <productname> and submit the order
   
    
    Examples:
|name    |  password    |   productname    |
|abc@gamil | fhvkhb      |    app          |

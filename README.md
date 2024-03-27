# UI Automation Framework - Sauce Demo Webshop






This UI Automation framework repository has some basic functional tests for Sauce Demo Webshop. It covers login, home page tests and checkout.

## Tech

This framework built with the following:

- Java 11 - language
- Maven - build tool
- [Selenium](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java/4.18.1) - UI framework
- [TestNG](https://mvnrepository.com/artifact/org.testng/testng/7.9.0)- Testing Framework
- CI  - github
- IDE - intelliJ



## Framework Design
The pages package contains page classes
- LoginPage - login page object
- HomePage - home page objects
- CartPage – cart page objects
- CheckoutInformationPage - checkout information page objects
- CheckoutOverviewPage - checkout overview page objects
- OrderCompletePage - order complete page objects

## Test Design

- Each test in the tests package is independent and complete.
- The tests are designed with the use of TestNg assertions.
- HTML extent report gets generated in reports folder.

## Test Execution
The tests can be executed from maven test command or by running testing.xml file in IDE after cloning the repo.

sample maven commands:
 
```sh
mvn clean test
```

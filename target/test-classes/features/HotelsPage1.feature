@web
Feature: Hotels feature

  Background:
    Given I am on Hotels Home Page

  @hotels-1
    Scenario Outline: Verify user can only view the result by selected property class
    Given Initialize the browser
    Given I am on default locations search result screen
    When I select property class <stars> hotels
    Then I verify system displays only <stars> hotels on search result

    Examples:

      | stars |
      |3starred|
      |4starred|
      |5starred|





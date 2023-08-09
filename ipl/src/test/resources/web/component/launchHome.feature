#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@WebReport
Feature: Ipl navigation

  Scenario: Launch the HomePage
    Given I want to launch application for "<component>" in "<locale>"
    Then I verify the page title as "<pageTitle>"

		@ipl
    Examples: 
      | component       | locale | pageTitle                              |
      | component1      | in     | Indian Premier League Official Website |

@Website1 
Feature: Verify Member Demographics displayed on Member360 Page 
	#Scenario: 003 Verify member is able to delete an alert 
	#Given User logins in to member connect Application with "sumani@memberconnect.v3" and "Aug#2018" 
	#When User navigates to Created alert section and deletes an Alert 
	#Then User verifies that an Alert is deletd successfully 
	
	
	#Scenario: 001 Verify Member Demographics displayed on Member360 Page
	#Given User logins in to member connect Application with "sumani@memberconnect.v3" and "Aug#2018"
	#Then User verifies Member demographics below member demographics 
	#| Member Name   |Member Id  | Plan Name | Cutomer Happines Score | Gender | DOB | email | SSN | PhoneNumber |
	#| Erica Schiff  |26151615623 |	Medicare | 89.0 | Female | February 5, 1977 |eschiff@gmail.com |340-34-3948| 617-306-4574 | 
	
	
#	Scenario: 002 Verify member is able to create alert
#	Given User logins in to member connect Application with "sumani@memberconnect.v3" and "Aug#2018"
#	When User clicks on Manager Alert tab and creates an Alert with "TestAlert123" and "General" and "Alert for General" and "Musculoskeletal" and "Normal" 
#	Then User verifies that an Alert is created successfully "TestAlert123"
	
	#Scenario: 003 Verify member is able to delete an alert 
	#Given User logins in to member connect Application with "sumani@memberconnect.v3" and "Aug#2018" 
	#When User navigates to Created alert section and deletes an Alert
	#Then User verifies that an Alert is deletd
	
#	Scenario: 004 Verify that member is able to change the status of an alert
#	Given User logins in to member connect Application with "sumani@memberconnect.v3" and "Aug#2018" 
#	When User clicks on an alert and changes the alert status to "Active"
#	Then User verifies that the alert status has changed to "Active"
	
	Scenario: 005 Verify an alert is visible on member 360 where today's date lies between start date and end date
	Given User logins in to member connect Application with "sumani@memberconnect.v3" and "Aug#2018" 
	When User clicks on an alert and verifies alert tab details
	Then User verifies if the current date is in between start date and end date 
	

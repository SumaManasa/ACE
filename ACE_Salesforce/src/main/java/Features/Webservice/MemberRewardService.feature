@Webservice
Feature: Verify Member rewards webservice
 
 
  Scenario Outline: User calls a Webservice to verify Member Rewards Webserivce
    Given A Member exists in Member Connect Application with unique "<MemberId>"
    When User calls the Member rewards webservice to retreive with Member Id
    Then the status code is 200
    Then user Verifies reward summary as below
    
 |rewardDate|treatment|providerName|providerAddress|points|
 |05/22/2018|Evaluation by Specialist|Smith, Thomas, MD|12 S Michigan Ave, Chicago, IL 60603|90|
 |08/12/2017|General Checkup|University of Chicago Medicine|5841 S Maryland Ave, Chicago, IL 60637|135|
 |01/17/2017|Insertion of Heart Defibrillator|Smith, Thomas, MD|12 S Michigan Ave, Chicago, IL 60603|200|
    
Examples: 
    | MemberId|
    | testID  |
    | 83804824|
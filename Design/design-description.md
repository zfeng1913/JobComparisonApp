CS6300: Software Design Process  
Georgia Institute of Technology  
Spring 2024  
Team 186: Matthew Preston, Zixin (Wendy) Feng, Sabab Karim, Gannon McCollum  
Deliverable 1: Preliminary Work  

# Design Description Document

## Summary
This document provides concise descriptions for the requirements of Group Deliverable 1 (Preliminary Work) for Team 186 in CS6300 (Software Development Process) of Spring 2024. We accomplished this by providing the raw assignment requirements from Group Deliverable1 and describing how they are satisfied in the accompanying UML Class Diagram.

## Requirements & Diagram Details

Requirement #1:  
When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet1).  

- Realization: To realize this requirement, we identified the “nouns” of User, Main Menu, Job, Comparison  (assuming that the output of this comparison is a noun), and Location. Job class simplifies receiving the current job details, the job offers, job scores, and rankings; the Comparison class compares the job offers and edits the comparison settings. Each of these have classes shown in the UML diagram, along with attributes and operations to describe other characteristics described in the additional requirements. Additionally, a class is provisioned for location cost of living lookups and handling,called Location.

Requirement #2:  
When choosing to enter current job details, a user will:  
a.  Be shown a user interface to enter (if it is the first time) or edit all the details of their current job, which consist of:  
i.  Title [String]  
ii. Company [String]  
iii.  Location (entered as city and state) [Location class]  
iv. Cost of living in the location (expressed as an index) [int]  
v.  Yearly salary [double]  
vi. Yearly bonus [double]  
vii.  Number of stock option shares offered [double]  
viii. Home Buying Program fund (one-time dollar amount up to 15% of Yearly Salary) [double]  
x.  Personal Choice Holidays (A single overall number of days from 0 to 20) [int]  
xi. Monthly Internet Stipend ($0 to $75 inclusive) [double]  
b.  Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.  

- Realization: To realize this requirement, we added attributes to the Job Class to account for the details listed in the requirement, along with appropriate data types. The Job class handles the current job details and job offers with a combination of string, int, and double attributes that encompass each job. Five operations are added: valideAttributes (returning boolean), becomeCurrentJob (returning void), getJobScore (returning double), getNumJobs (returning int), and getWeight (returning int[6]). These operations allow a User to get the job details of all offers as well as the current job as well as getting the ranking for each job.  

Requirement #3:  
When choosing to enter job offers, a user will:  
a.  Be shown a user interface to enter all the details of the offer, which are the same ones listed above for the current job.  
b.  Be able to either save the job offer details or cancel.  
c.  Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).  

- Realization: To realize this requirement, we included operations in the MainMenu Class to account for the display, saving, and cancellation of job offer information. These options are inclusive of the possibilities listed in the requirement.  

Requirement #4:  
When adjusting the comparison settings, the user can assign integer weights to:  
a.  Yearly salary [double in implementation, int weight]  
b.  Yearly bonus [double in implementation, int weight]  
c.  Number of Stock Option Shares Offered [double in implementation, int weight]  
d.  Home Buying Program Fund [double in implementation, int weight]  
e.  Personal Choice Holidays [int]  
f.  Monthly Internet Stipend [double in implementation, int weight]  
If no weights are assigned, all factors are considered equal.  

- Realization: To realize this requirement, we included attributes for the integer weights described within the Comparison Class to account for the different characteristics that a user might configure, along with operations to save new settings or cancel an input.The weights list is of set length such that the weight at index n corresponds to the attribute above (e.g. weight[0] is the weight for the yearly salary, weight[1] is the weight for the yearly bonus, etc.). Additionally, the default value for this list is [1,1,1,1,1,1] which satisfies the requirement that not assigning weights makes all factors equal.  

Requirement #5:  
When choosing to enter job offers, a user will:  
a.  Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.  
a.  Select two jobs to compare and trigger the comparison.  
b.  Be shown a table comparing the two jobs, displaying, for each job:  
i.  Title [String]  
ii. Company [String]  
iii.  Location [Location class]  
iv. Yearly salary adjusted for cost of living [double]  
v.  Yearly bonus adjusted for cost of living [double]  
vi. Number of Stock Option Shares Offered [double]  
vii.  Home Buying Program fund (one-time up to 15% of Yearly Salary) [double]  
viii. Personal Choice Holidays (A single overall number of days from 0 to 20) [int]  
ix. Monthly Internet Stipend ($0 to $75 inclusive monthly) [double]  
b.  Be offered to perform another comparison or go back to the main menu.  

- Realization: To realize this requirement, we identified Class attributes to capture the different values associated with each of these parameters within the Job class.  

Requirement #6:  
When ranking jobs, a job’s score is computed as the weighted average of:  
AYS + AYB + (CSO/3) + HBP + (PCH * AYS / 260) + (MIS*12)  
where:  
AYS = yearly salary adjusted for cost of living  
AYB = yearly bonus adjusted for cost of living  
CSO = Company shares offered (assuming a 3-year vesting schedule and a price-per-share of $1)  
HBP = Home Buying Program  
PCH = Personal Choice Holidays  
MIS= Monthly Internet Stipend  

- Realization: To realize this requirement, we included a Job Class to account for user-defined scoring weights, and operations to generate comparison scores according to the equation defined in the requirements. The getJobScore operation takes care of this calculation. Implementation is left untouched for the sake of this UML Class Diagram.  

Requirement #7:  
The user interface must be intuitive and responsive.  

- Realization: This requirement is not realized because it is outside the scope of the current task (UML Class Diagram design), and will be addressed in future implementation.  

Requirement #8:  
For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).  

- Realization: This requirement is satisfied implicitly by the omission of external systems in the UML Class Diagram.
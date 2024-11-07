# Design Document

**Author**: Team 186

## 1 Design Considerations

### 1.1 Assumptions

- Assumptions:  
i. The User may or may not have a current job at any time.  
ii. The User may or may not have job offers at any time.  
iii. The cost of living index from the specified source (see section 3.1) is correct, and does not need to be verified.  
- Background: This application is meant to help those seeking jobs or in a current job find and rank potential jobs. This application has been made at the request of George P. Burdell to help look for a job after he graduates. He requests that the app be simple, single-user, and allow him to compare jobs' benefits, locations, salary, holidays, etc.
- Dependencies:  
i. Android SQLite  
ii. edu.gatech.seclass.jobcompare6300  
- Use:
i. User should use the app to compare current job among various job prospects.  
- Operational Enironment:
i. Team members are working from home remotely together.  
ii. Team members communicate via primarily GATech email, and secondarily via Discord.  
iii. The application is expected to operate in the Andriod environment (see section 1.3 below).  
iv. User should be able to use app on or offline.  
v. The app requires no connectivity via networking or internet.  
- Signifigant Project Issues:
i. The android app developer studio has been noted to crash half of the team members devices while trying to use the build in emulator. Seems to be a windows driver issue. The only workaround discovered is to switch to a different device.  

### 1.2 Constraints

Constraints impacting system design:
i. App data must persiste between runs.  
ii. App must remain "simple".  
iii. App must be single-user and not have networking or internet connectivity.  

### 1.3 System Environment

- Hardware:
i. The app should basline run & pass all tests on a Google Pixel 6.  
ii. Full/future releases of the app should run on android devices.  

- Software:
i. The app must use API 33 (Android 13) for sompileSdk, minSdk and targetsdk.  
ii. The app can use Android SQLite to save user data.  
iii. The app must have all classes in the following package: edu.gatech.seclass.jobcompare6300.  

## 2 Architectural Design

### 2.1 Component Diagram

The component diagram is simple, as there is no internet connectivity. There are four main components:
i. User (i.e. George P. Burdell). The user is the one who will have to enter all the information into the system.  
ii. Application. This part handles all of the required features and computations.  
iii. User Information Database. This is a stored local database that helps the application persist information between runs, and stores the user's information regarding current job, and job offers.  
iv. Cost of Living Index. This is a given website, https://www.expatistan.com/cost-of-living/index/north-america, which is our source for cost of living. This will be manually checked and updated into the app every 6 months.  

![Component Diagram](./images/Component_Diagram.png)

### 2.2 Deployment Diagram

This diagram is unnecesary since everything will be deployed to one hardware system: the Google Pixel. For each component above:
i. User: This component is an already built system (homo sapiens), further deployment or development of which is outside the scope of this project. Pertinent information from this component will be recorded onto the pixel itself.  
ii. Application: This component is going to be developed directly for the Google Pixel.  
iii. User Information Database: This component is going to be made by the Application and stored on the same device.  
iv. Cost of Living index: This component will be added manually into the the Application and User Information Database.  

## 3 Low-Level Design

### 3.1 Class Diagram

![Team's Design](./images/team_design.png)

### 3.2 Other Diagrams

![Sequence Diagram](./images/Sequence_Diagram.png)

## 4 User Interface Design

![GUI Design](./images/GUI_Design.png)

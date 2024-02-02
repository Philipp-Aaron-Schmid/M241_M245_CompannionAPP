# M223 Project Documentation

## Information Gathering
### Project Overview**  
M223_Sudoku_Backend  
  
The Project provides an environement to Play a high speed version of sudoku competitively with your peers.  
The Goal of this project was to explore the Interactions between backend and frontend in the context of of Multi user problems and solutions, so that certain information is only avaliable to privilaged users 
### Technology Analysis 
  - Springboot: Springinitializr backent initializierung
  - jwt: Web token technology
  - Mysql: database services
  - Jpa: Acess to Mysql Database
  - lombok: to create boilerplate
  - Vue.js to present a mock server in a local testin environment
  - Axios Requests to send data to the backend
  - Java: 21 for the backend
  - Jsx: Frontend
  - HTML
  - CSS
  - Codium for testin puropses
  - Roles and responsibilities

#### Backend Technology
Springboot is a Stable evironment that moks up a local bacckend server so that the code can be tested in a deployment like environment, Dependencies can easily be defined in the pom.xml file taking a lot of work off the developerss hand piecing to gether basic functionality.
In this case we Used Hiberate and a JPA connection to talk to the mysql backend via entity classes and jpa repository generated methods which makes the actuall calls to the backend @Transactional i used to make sure a call is performed ACID in case of conflicting calls such as Deletionsacross multiple tables

The backend uses Spring security co create a security pipeline and validation trogh a token system where an valid token indicates that the user has been validated with in the time frame alloweble to that token

Springboot Starter validation ensures that the project only starts up when major problems are adressed, provides absolutelly llegible and horrible eror messages.
SpringDataRest is used to set up the data repositories




#### Frontend Technology

The frontend is based on Java Script HTML and CSS in a react frame work runnng on a vite mok Server that responsively delivers changes in the code base to the Browser at localhost. 

The major differaerance between Vite and React in providing the applicationto the Browser is that react rebuilds the application itself after you update a file while vite sends the file to the browser seperately aand uses the brosers internal tools to build the Dom. 

We use axios to sent http requests to the backend via Http requests.
The router component was used to create a navigation trough the frontend and limit the visibility unauthorized pages.


### Requirements

**Functional**
- The Project must produce a product in working state
- The User authentification must Gate data based on sign in and Role.
- Data from the Database must make its was sucesfully to the frontend.
- The user must be able to Interact with the reasources grantet to them
- Multi user access has to be implemented functionally and robustly

**None-Functional**
- Must use the latest in React technology to realize the implementation of the frontend
- Must ake popular design considerationns 
- The backend has to be secure from attacks trough malicious actors.


**Constraints**
- this project had to be completed with in a tight time frame of two weeeks.
- Tecnical know how of the Texh lead is severely limited
- The Authorization Pipeline is largely given
- JPA requires very specific stylin in terms of syntax

## Planning
### Project Scope
The Project is a one man opperation and has to be completed with in 2 weeks as such expertise and time reasources are limited to less than productive time. The Project is an exploration of new technologies therfore it is not feiasaable to greate a massive applicatio from te get go.

![Pages and their usess for roles](./Graphics//Pages.svg)  


![Relational Database Description](./Graphics/SudokuDB.svg)

### Milestones and Timeline

| Event | Time |
|-------|-------|
|Rough Plan Documantation | t-13d |
|Backen ORM Functional | t- 11d |
|Backend authentification | t-7d |
| Backend Rough mappings and data transfer tested | t-7d|  
| Frontend Initialization | t-7d|
| Frontend connection tests with backend |t-4d |
| Frontend Roughly implemented no design navigation | t-2d |
| Frontendend Design Implementation/Trouble  | t-1d|
| Implementing  Automated tests | t-12h |
| Documentation | TBD

#### Tools
  - Visual Studio, this entire project is written in the very helpfull editor full if extension bot the backend and frontend were being tun in favorabl environments to speed up coding and error correction
  - GitHub, Git is the central repository for this project as a central location it orovides security and redundancy as well as room for code breaking experiments beyon a quick fix.

## Decision
### Solution Design
  The Basic arhitecture of this Project is a traditional 2.5 lyer design where we have a react Fronted handling user input and presenting backend data in aan appealing lighgt.

  In the packen we have a springboot java aapplication that simulates a backend server and connects via entities to the server layser which is not directly administered even though it is sstill imperative to know the DB layout exactly as JPA errors propagate trough the code and cause many unreadable error codes.

### Technology Stack
 
 | Technology|Role| 
|----------|----------|
| JPA/Hibernate   |   Connecting with the backend Database saving data perisistently  |    
|  Lombok  |  Replacing boilerplace code with simple annotations  | 
|  JwT  |  Securing the Users acess trough the use of a Token stored by the user and sent to the backend   | 
|  Spring boot  |  Simulation of a backend server with an easy Dependencies system pom.xml  | 
|  Axios  |   Handles Interactions between the frontend and backend asyncronously  | 


### Risk Management
  - Identified risks
  - Mitigation strategies
### Decision Log


  | Issue | Solution | Resolution |
|----------|----------|----------|
| Scope of Application is out of bounds   | Focusing on core components can save tons of time     | The scope was ligtl trimmed to avoid feature creep    |
| Some hard Bugs have been spotted in the app   | Testing and remedie measures      | Most heavy bugs have been eliminated others due to time constraints documented in toDo's  |

## Execution
**Development Process**  

This was the virst rtuly major coding project that I have taken care of by my self as such theapproach to coding was a storm of approaches, there is some semblance of organisationas I worked trough one problem after the next. But as time moved on methods changed informed about new ways to solve problems and as such the code base is a hodge poge of attempts and code stubs that are heald together by hopes  and dreams. 
  
This Project has the very lowest coding standarts avaliable what ever works was implemented as it was fornd across the web, no uniform approach was taken, continual addaption to the Technologies have challaanged initial assumptions on best practices
  - GitHub  

**Progress Tracking**  

Progress was measure by Partial User story completion meaning that with the guidance of the userstories we worked on one side of the application untill it could fulfill the users needs in its role. The Application was build layered from the Database upwards to at most times have a steadily progressing functional application.
### Testing

**Backend**
**Frontend**
**Manual**
| Test Case ID | Description                     | Steps to Reproduce                                | Expected Result               | Actual Result | Status   |
|--------------|---------------------------------|---------------------------------------------------|--------------------------------|---------------|----------|
| TC-001       | Verify login functionality      | 1. Navigate to the login page<br>2. Enter valid credentials<br>3. Click the login button | Successful login and redirection to the challangePage | Successful login and redirection to the Challange| Passed   |
| TC-002       | View Challanges           | 1. Navigate to the challenge<br>2. click on the play field of testSudoku| The testSudoku will start up and a timer has started| EThe testSudoku will start up and a timer has started | Passed   |
| TC-003       | Auto submit functional    | 1. Wait for the timer of testSudoku to run out. | User Redirect to Score Page with score 0 | User Redirect to Score Page with score 0 | passed  |
| TC-004       | Add Empty challange       | 1. Sign in as admin<br>2. Navigate to the Challenge management<br>3. enter title "Empty"<br>4. set timer to 60<br>5. clicksubmit | A new empty Sudoku Field added to the list | A new empty Sudoku Field added to the list | Passed   |
| TC-005       | Verify logout process           | 1. Log in to the application<br>2. Click on the logout button | Successful logout and redirection to the main page | Redirected to the main page | passed |



## Review
**Performance Analysis**
  - Key performance indicators
This project was noticably a first of it's kind and as such took inovation and trial and error. The Planning a head was roughly guided and not very accurate.
for future project sit is imperative to adhere to standart naming conventions to avoid simple naming problems, automatic function genertion can be finiky when variables are named creatively

  - Performance reports
The application is up and running but it is not pretty for such a small application to stil have a number of bugs shows that the development process was not a smooth one. Early definitions of donne nad Coding standarts have to be Implemented 

  - Stakeholder feedback
TBD

# Detail Aknowlegements

[OpenAI](https://www.openai.com) - For out standing aid in formating nd many  questionss that would have taken hours of search  

 With the tight deadline of this Project long searches in the Documentation of every asect of the frameworks provided functionallity woud have been too costly and as such ChatGPT provided invaluable feed back and explanations of certain annotations and features as well as providing specific implementatin ideas for example for the Authentification provider in the frontend how to handle it in the routes system.

[United Top Tech](https://youtu.be/GXxT8U7_OlE?si=1wjzbbiRsGYDM_j4) - Invaluable input to create the ORM and pointer to Lombok    
[Project Lombok](https://projectlombok.org/features/) - Refferance to the avliable annotations reducing boilerplate
This pointed to a very usefull reasource to streamline my code and make it standardized and looking de cluttered

[JavaTpoint](https://www.javatpoint.com/spring-boot-annotations) 
Helped with specific springboot implementations
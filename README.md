# MyOldSchool
It is a basic application as of now which is primarily focused on student database management and is based on <b>Spring</b>.
It contains some queries to insert, update, delete and add data into the mysql database and uses Restful web services to access and perform these operations.

<b>Database:</b> MySql 8.0.21
and I am using Wamp server to access the database. You can download it from here: https://www.wampserver.com/en/download-wampserver-64bits/

<b>MySql database details:</b><br>

<b>Database name:</b> db_test<br>
<b>Table name:</b> tbl_student<br>

The table you will create initially should have 4 columns: sid(Primary key integer type), sname(text), rollno(integer), marks(Double). Later you can manipulate the db as per your requirement.

<b>Note:</b> It by default runs on port 3306 but I am using 3308 for this project.

<b>Building Tool:</b> Gradle- 6.6.1

To run this project navigate to your project path(Ex: c:/project/manager/manager) and from terminal or cmd run: <b>gradlew bootRun</b>

Don't forget to start wamp server first before running the above command.

<b>Check Build:</b> For checking that everything is working fine after a successful build, type this http://localhost:5000/getRecords url in your browser and hit enter, now if you have already inserted some data manually into the database then it should return that entries from db.

<b>Hibernate</b>: Hibernate support is added to this project, please check branch "feature/hibernate". Now I am performing crud operations by using hibernate , that you can check in class <b>BussLayerHibernate</b> but still manual queries codebase exist in the project in class BussLayer.

<b>Microservices</b>: Added microservices to the project. Check module "demo" for the same. The eureka server runs on port 1111 and the client runs on 2222. After running the project check by using url: <b>"http://localhost:1111/"</b>, there you should see a "SpringEureka" dashboard. To run the client you need to run "EurekaClientManager" inside module "demo". Now refresh the dashboard on browser and there you should see a regsitered client named "ACCOUNT-SERVICE".

<b>ZuulProxy</b>: Added proxy to microservices with working Pre and Post filters.

<b>Inter Microservices communication(FeignClient)</b>: Inter microservices communication established by using FeignClient with ZuulPostFilter still enabled so check the logs for service <b>MyZuulProxyClient</b> to make sure the communication is working. You should see something like "c.m.manager.zuulfilter.ZulPostFilter     : Response before: intercommunication working between servicesservice running" when you will hit "http://localhost:5555/myTestService"

<b>TDD</b>: Test cases are added for getRecords and postUser API by using TestRestTemplate. Also a separate test class called ApiControllerTest is added to test update and delete user API. The point of adding a separate class ApiControllerTest is to avoid launching a complete server to test the API's so its a faster way to execute tests.

<b>Docker</b>: Docker configuration is added to project. Use command "<b>docker build -t name-of-your-choice.</b>" to create docker image of this project and while running docker image of this project use <b>docker run -p 5000:5000....</b> to map docker port 5000 to server port 5000 because our eureka server is running on port 5000. To see complete command refer to [README_DOCKER.md](https://github.com/suvDev/MyOldSchool/blob/master/README_DOCKER.md) file. 

<b>Docker Compose</b>: Docker compose is added to avoid the overhead to launch each container separately and linking it up with the dependent containers. Now I have added volume for persistant data storage, now database will not get deleted after using this command "docker-compose down" but it will be after using "docker-compose down -v".

<b>MySql Functions and Procedures</b> : The API's "tblStudentFunction" and "callStudentProcedure" makes a call to function(it concats sname and rollno columns of every row) and procedure(it inserts a row and returns all existing rows).

<b> Caching </b>: Added caching to getUser API on the basis of id.

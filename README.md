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

<b>Check Build:</b> For checking that everything is working fine after a successful build, type this http://localhost:8080/getRecords url in your browser and hit enter, now if you have already inserted some data manually into the database then it should return that entries from db.

<b>Hibernate</b>: Hibernate support is added to this project, please check branch "feature/hibernate". Now I am performing crud operations by using hibernate , that you can check in class <b>BussLayerHibernate</b> but still manual queries codebase exist in the project in class BussLayer.

<b>Microservices</b>: Added microservices to the project. Check module "demo" for the same. The eureka server runs on port 1111 and the client runs on 2222. After running the project check by using url: <b>"http://localhost:1111/"</b>, there you should see a "SpringEureka" dashboard. To run the client you need to run "EurekaClientManager" inside module "demo". Now refresh the dashboard on browser and there you should see a regsitered client named "ACCOUNT-SERVICE".

<b>ZuulProxy</b>: Added proxy to the microservices with working Pre and Post filters.

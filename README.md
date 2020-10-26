# MyOldSchool
It is a basic application as of now which is primarily focused on student database management.
It contains some queries to insert, update, delete and add data into the mysql database and uses Restful web services to access and perform these operations.

<b>Database:</b> MySql 8.0.21
I am using Wamp server to access the database. You can download it from here: https://www.wampserver.com/en/download-wampserver-64bits/

<b>Note:</b> It by default runs on port 3306 but I am using 3308 for this project.

<b>Building Tool:</b> Gradle- 6.6.1

To run this project navigate to your project path(Ex: c:/project/manager/manager) and from terminal or cmd run: <b>gradlew bootRun</b>

Don't forget to start wamp server first before running the above command.

<b>Check Build:</b> For checking that everything is working fine after a successful build, type this http://localhost:8080/getRecords url in your browser and hit enter, now if you have already inserted some data manually into the database then it should return that entries from db.



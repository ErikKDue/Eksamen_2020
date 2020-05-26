# Eksamen_2020
KEA DAT19D group 6 2nd semester project

Branch description
 - MASTER
 Everything here must be working properly.
 
 - DEV
 Works in progress.
 
 ##DB Setup
 - Kræver MySQL-installation
 - application.properties skal se sådan her ud.
 ---
user= (dbuser)<br>
password= (dbpassword)<br>
url= (db-url, normalt \jdbc:mysql://localhost:3306/)<br>
dbname=nordicmotorhome_g6?serverTimezone=UTC<br>
---
 - Run DBManagerTests, 
 - successful output: 
 ---
   Trying to create schema.<br>
   Schema created.<br>
   unavailability created.<br>
   motor_home created.<br>
   Customer created<br>
   rental period created<br>
   ---

Rules and best practices:
 - You Break It, You Fix It
 - Pull Before Pushing
 - Run Tests Before Pushing
 - "mvn clean install" before pushing
 - never push to master, only Dev
 - push to Dev early, push to Dev often
 - use new branches frequently
 - No large submissions unless team members have been informed
 - All functions/methods must have a test before submitting; test must pass
 
 Tech Stack
- Spring MVC
- Tomcat
- Thymeleaf
- Hibernate(Måske)
- Mysql



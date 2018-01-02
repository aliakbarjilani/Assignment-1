Assignment # 1.
---------------------------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------------------------
|								pacemaker-console-ali										    |
---------------------------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------------------------
Java Environment:
java version "1.8.0_141"
Java(TM) SE Runtime Environment (build 1.8.0_141-b15)
Java HotSpot(TM) 64-Bit Server VM (build 25.141-b15, mixed mode)

Maven Version:
Apache Maven 3.5.2 (138edd61fd100ec658bfa2d307c43b76940a5d7d; 2017-10-18T08:58:13+01:00)
Maven home: C:\Program Files\apache-maven-3.5.2\bin\..
Java version: 1.8.0_141, vendor: Oracle Corporation
Java home: C:\Program Files\Java\jdk1.8.0_141\jre
Default locale: en_US, platform encoding: Cp1252
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"

*********************************************************************************************************************************************************************
Persistence Methodology
*********************************************************************************************************************************************************************
1. For the solution to persist user data between its executions, data has to persist in some form. Serialization into different file formats such as XML, Json, 
YAML and Binary is an easy option. Given below are the libraries used for serialization into different formats.

Library name			Description
--------------------------------------------------------------
jettison-1.2			json serialization library
xstream-1.4.10			xml serialization library
yamlbeans-1.0			yaml serializatio library

*********************************************************************************************************************************************************************
Presentation Approach
*********************************************************************************************************************************************************************

The system offers a plain command line interface CLI which is uplifted by the use of tables implemented using the following library

Library Name			Description
--------------------------------------------------------------
asg.cliche-110413		Command line interactive interface
guava-23.0			Utility library for Java Collections Framework, and other commonly used features like hashing
java-ascii-table-1.0		Tabular interface for data representation


Implemented Command set 
---------------------------------------------------------------------------------------------------------------------------------------------------------------------
abbrev 	name 			params								sample
---------------------------------------------------------------------------------------------------------------------------------------------------------------------
gu 	list-users 		()								gu
cu 	create-user 		(first name, last name, email, password)			cu lina simpson lsimp@simpson.com secret
gu 	list-user 		(email)								gu homer@simpson.com
lu 	list-user 		(userid)							lu 47e56aa2-ea64-44d9-9042-ec761e1c873a
la 	list-activities 	(userid, sortBy: type, location, distance, date, duration)	la 05ee0eaf-fa04-461e-bb12-1e2b43e3481b
la 	list-activities 	(user id)							la b00f3428-bf29-4596-914d-08b852c1eb9b (Each format has separate data)
du 	delete-user 		(user id)							du 47e56aa2-ea64-44d9-9042-ec761e1c873a
aa 	add-activity 		(user-id, type, location, distance, datetime, duration)		aa d64941b7-5727-43c2-a0fe-59224552bb98 cycle WIT 10.0 2017:11:04.04:10:10 05:05:05
al 	add-location 		(activity-id, latitude, longitude)				al 05ee0eaf-fa04-461e-bb12-1e2b43e3481b 12.5 34.5
cff 	change-file-format 	(file format: xml, json)					cff xml, cff json, cff yaml (data is loaded after load command)
l 	load 			()								l, load
s 	store 			()								s, store
la 	list-activities 	(userid, sortBy: type, location, distance, date, duration)	xxxxxxxxxx Incomplete functionality xxxxxxxxxx
---------------------------------------------------------------------------------------------------------------------------------------------------------------------
Note:

In Order to list an activity by id, you will need to create the activity first to be able to have its id. There is no direct way of discovering an Activity ID (but from raw data file e.g. from xml file) after its conversion to UUID.

*********************************************************************************************************************************************************************
*********************************************************************************************************************************************************************
Sample Data
*********************************************************************************************************************************************************************
*********************************************************************************************************************************************************************

The solution comes with following different data serialization options.

1. xml 
2. json
3. yaml
4. binary

Note: The file test.script found in application root directory is no longer compatible with the application. It carries command set for future scripting framework.

*********************************************************************************************************************************************************************
Sample XML Data (Sample data bundled with this release in datasource.xml)
*********************************************************************************************************************************************************************
+--------------------------------------+------------+-----------+--------------------+----------+
|                  Id                  | First Name | Last Name |        Email       | Password |
+--------------------------------------+------------+-----------+--------------------+----------+
| 714ddd6a-0303-4e1c-b569-d09675624424 |       lisa |   simpson |   lisa@simpson.com |   secret |
| ed3099b1-0024-42f3-88a7-840cce4f69a8 |       bart |   simpson |   bart@simpson.com | secretgu |
| 6f8de436-cae8-4874-bf77-59c5283ac864 |     maggie |   simpson | maggie@simpson.com |   secret |
| 69e61e0e-483e-49b3-a28f-1fb4fc37771b |      marge |   simpson |  marge@simpson.com |   secret |
| 1dc85eb2-8dc3-42f0-ad30-8b324ae059ca |       lina |   simpson |  lsimp@simpson.com |   secret |
+--------------------------------------+------------+-----------+--------------------+----------+

Activities under User lina simpson (1dc85eb2-8dc3-42f0-ad30-8b324ae059ca)
+--------------------------------------+-------+----------+----------+---------------------+----------+----------------------------------------------------+
|                  Id                  |  Type | Location | Distance |         Date        | Duration |                        Route                       |
+--------------------------------------+-------+----------+----------+---------------------+----------+----------------------------------------------------+
| 28886e73-e628-4dce-8df0-4e66d2df3d94 | cycle |      WIT |     10.0 | 2017:11:04.04:10:10 |    18305 | [Location{0, 23.5, 32.5}, Location{1, 12.5, 34.5}] |
+--------------------------------------+-------+----------+----------+---------------------+----------+----------------------------------------------------+
+--------------------------------------+-------+----------+----------+---------------------+----------+-------+
|                  Id                  |  Type | Location | Distance |         Date        | Duration | Route |
+--------------------------------------+-------+----------+----------+---------------------+----------+-------+
| ec3da6db-24c0-4b62-bf5f-e44fc88369ac | drive |     Cork |     10.0 | 2017:11:03.04:10:10 |    18305 |    [] |
+--------------------------------------+-------+----------+----------+---------------------+----------+-------+

*********************************************************************************************************************************************************************
Sample JSON Data (Sample data bundled with this release in datasource.json)
*********************************************************************************************************************************************************************
+--------------------------------------+------------+-----------+-------------------+----------+
|                  Id                  | First Name | Last Name |       Email       | Password |
+--------------------------------------+------------+-----------+-------------------+----------+
| 47e56aa2-ea64-44d9-9042-ec761e1c873a |      marge |   simpson | marge@simpson.com |   secret |
| 4f6e857e-37a5-46ab-b42b-4d9fbb82be8a |      homer |   simpsom | homer@simpson.com |   secret |
+--------------------------------------+------------+-----------+-------------------+----------+

Activity under User homer simpsom (47e56aa2-ea64-44d9-9042-ec761e1c873a)
+--------------------------------------+------+----------+----------+---------------------+----------+---------------------------+
|                  Id                  | Type | Location | Distance |         Date        | Duration |           Route           |
+--------------------------------------+------+----------+----------+---------------------+----------+---------------------------+
| fea6bcc9-d16c-46fa-a2c8-51d8a40a28b7 | walk |   Office |     10.0 | 2017:10:03.04:10:10 |    18305 | [Location{0, 23.5, 32.5}] |
+--------------------------------------+------+----------+----------+---------------------+----------+---------------------------+
Activity under User homer simpsom (4f6e857e-37a5-46ab-b42b-4d9fbb82be8a)
+--------------------------------------+------+----------+----------+---------------------+----------+---------------------------+
|                  Id                  | Type | Location | Distance |         Date        | Duration |           Route           |
+--------------------------------------+------+----------+----------+---------------------+----------+---------------------------+
| 3afd4de3-5112-4a54-ade6-ad428fd79529 |  fly |   Dublin |     10.0 | 2017:10:02.04:10:10 |    18305 | [Location{1, 12.5, 34.5}] |
+--------------------------------------+------+----------+----------+---------------------+----------+---------------------------+

*********************************************************************************************************************************************************************
Sample YAML Data (Sample data bundled with this release in datasource.yaml)
*********************************************************************************************************************************************************************
+--------------------------------------+------------+-----------+----------------------+----------+
|                  Id                  | First Name | Last Name |         Email        | Password |
+--------------------------------------+------------+-----------+----------------------+----------+
| abf98813-0ce5-46af-b830-5eed368a002d |      homer |   simpsom |    homer@simpson.com |   secret |
| 04ce3cf4-7864-4a49-946e-bddfc18e50b4 |      marge |   simpson |    marge@simpson.com |   secret |
| 4d52fc9d-6cda-4b76-ab8d-ddb2459b14d4 |      Robin |      Hood | rhood@serializer.com |   xanadu |
+--------------------------------------+------------+-----------+----------------------+----------+

Activity under User Robin Hood (4d52fc9d-6cda-4b76-ab8d-ddb2459b14d4)
+--------------------------------------+------+----------+----------+---------------------+----------+----------------------------------------------------+
|                  Id                  | Type | Location | Distance |         Date        | Duration |                        Route                       |
+--------------------------------------+------+----------+----------+---------------------+----------+----------------------------------------------------+
| 85dea5a2-0eb1-4070-b0ed-c3b1fb64cc66 |  run |    forst |     10.0 | 2017:11:04.04:10:10 |    18305 | [Location{2, 23.5, 32.5}, Location{3, 12.5, 34.5}] |
+--------------------------------------+------+----------+----------+---------------------+----------+----------------------------------------------------+


*********************************************************************************************************************************************************************
*********************************************************************************************************************************************************************
Demonstration
*********************************************************************************************************************************************************************
*********************************************************************************************************************************************************************

Security framework of XStream not initialized, XStream is probably vulnerable.
Welcome to pacemaker-console - ?help for instructions
pm> cff json
json selected
pm> l
Security framework of XStream not initialized, XStream is probably vulnerable.
Data successfully loaded.
pm> gu
+--------------------------------------+------------+-----------+-------------------+----------+
|                  Id                  | First Name | Last Name |       Email       | Password |
+--------------------------------------+------------+-----------+-------------------+----------+
| 47e56aa2-ea64-44d9-9042-ec761e1c873a |      marge |   simpson | marge@simpson.com |   secret |
| 4f6e857e-37a5-46ab-b42b-4d9fbb82be8a |      homer |   simpsom | homer@simpson.com |   secret |
+--------------------------------------+------------+-----------+-------------------+----------+

pm> cff xml
xml selected
pm> l
Security framework of XStream not initialized, XStream is probably vulnerable.
Data successfully loaded.
pm> gu
+--------------------------------------+------------+-----------+--------------------+----------+
|                  Id                  | First Name | Last Name |        Email       | Password |
+--------------------------------------+------------+-----------+--------------------+----------+
| 714ddd6a-0303-4e1c-b569-d09675624424 |       lisa |   simpson |   lisa@simpson.com |   secret |
| ed3099b1-0024-42f3-88a7-840cce4f69a8 |       bart |   simpson |   bart@simpson.com | secretgu |
| 6f8de436-cae8-4874-bf77-59c5283ac864 |     maggie |   simpson | maggie@simpson.com |   secret |
| 69e61e0e-483e-49b3-a28f-1fb4fc37771b |      marge |   simpson |  marge@simpson.com |   secret |
| 1dc85eb2-8dc3-42f0-ad30-8b324ae059ca |       lina |   simpson |  lsimp@simpson.com |   secret |
+--------------------------------------+------------+-----------+--------------------+----------+

pm> la 28886e73-e628-4dce-8df0-4e66d2df3d94
+--------------------------------------+-------+----------+----------+---------------------+----------+----------------------------------------------------+
|                  Id                  |  Type | Location | Distance |         Date        | Duration |                        Route                       |
+--------------------------------------+-------+----------+----------+---------------------+----------+----------------------------------------------------+
| 28886e73-e628-4dce-8df0-4e66d2df3d94 | cycle |      WIT |     10.0 | 2017:11:04.04:10:10 |    18305 | [Location{0, 23.5, 32.5}, Location{1, 12.5, 34.5}] |
+--------------------------------------+-------+----------+----------+---------------------+----------+----------------------------------------------------+

pm> aa 1dc85eb2-8dc3-42f0-ad30-8b324ae059ca drive Cork 10.0 2017:11:03.04:10:10 05:05:05
OK
+--------------------------------------+-------+----------+----------+---------------------+----------+-------+
|                  Id                  |  Type | Location | Distance |         Date        | Duration | Route |
+--------------------------------------+-------+----------+----------+---------------------+----------+-------+
| ec3da6db-24c0-4b62-bf5f-e44fc88369ac | drive |     Cork |     10.0 | 2017:11:03.04:10:10 |    18305 |    [] |
+--------------------------------------+-------+----------+----------+---------------------+----------+-------+

pm> s
Data successfully stored.
pm> gu
+--------------------------------------+------------+-----------+--------------------+----------+
|                  Id                  | First Name | Last Name |        Email       | Password |
+--------------------------------------+------------+-----------+--------------------+----------+
| 714ddd6a-0303-4e1c-b569-d09675624424 |       lisa |   simpson |   lisa@simpson.com |   secret |
| ed3099b1-0024-42f3-88a7-840cce4f69a8 |       bart |   simpson |   bart@simpson.com | secretgu |
| 6f8de436-cae8-4874-bf77-59c5283ac864 |     maggie |   simpson | maggie@simpson.com |   secret |
| 69e61e0e-483e-49b3-a28f-1fb4fc37771b |      marge |   simpson |  marge@simpson.com |   secret |
| 1dc85eb2-8dc3-42f0-ad30-8b324ae059ca |       lina |   simpson |  lsimp@simpson.com |   secret |
+--------------------------------------+------------+-----------+--------------------+----------+

pm> gu lsimp@simpson.com
OK
+--------------------------------------+------------+-----------+-------------------+----------+
|                  Id                  | First Name | Last Name |       Email       | Password |
+--------------------------------------+------------+-----------+-------------------+----------+
| 1dc85eb2-8dc3-42f0-ad30-8b324ae059ca |       lina |   simpson | lsimp@simpson.com |   secret |
+--------------------------------------+------------+-----------+-------------------+----------+

pm> lu 1dc85eb2-8dc3-42f0-ad30-8b324ae059ca
OK
+--------------------------------------+------------+-----------+-------------------+----------+
|                  Id                  | First Name | Last Name |       Email       | Password |
+--------------------------------------+------------+-----------+-------------------+----------+
| 1dc85eb2-8dc3-42f0-ad30-8b324ae059ca |       lina |   simpson | lsimp@simpson.com |   secret |
+--------------------------------------+------------+-----------+-------------------+----------+

pm> gu
+--------------------------------------+------------+-----------+--------------------+----------+
|                  Id                  | First Name | Last Name |        Email       | Password |
+--------------------------------------+------------+-----------+--------------------+----------+
| 714ddd6a-0303-4e1c-b569-d09675624424 |       lisa |   simpson |   lisa@simpson.com |   secret |
| ed3099b1-0024-42f3-88a7-840cce4f69a8 |       bart |   simpson |   bart@simpson.com | secretgu |
| 6f8de436-cae8-4874-bf77-59c5283ac864 |     maggie |   simpson | maggie@simpson.com |   secret |
| 69e61e0e-483e-49b3-a28f-1fb4fc37771b |      marge |   simpson |  marge@simpson.com |   secret |
| 1dc85eb2-8dc3-42f0-ad30-8b324ae059ca |       lina |   simpson |  lsimp@simpson.com |   secret |
+--------------------------------------+------------+-----------+--------------------+----------+

pm> cff json
json selected
pm> l
Security framework of XStream not initialized, XStream is probably vulnerable.
Data successfully loaded.
pm> gu
+--------------------------------------+------------+-----------+-------------------+----------+
|                  Id                  | First Name | Last Name |       Email       | Password |
+--------------------------------------+------------+-----------+-------------------+----------+
| 47e56aa2-ea64-44d9-9042-ec761e1c873a |      marge |   simpson | marge@simpson.com |   secret |
| 4f6e857e-37a5-46ab-b42b-4d9fbb82be8a |      homer |   simpsom | homer@simpson.com |   secret |
+--------------------------------------+------------+-----------+-------------------+----------+

pm> aa 4f6e857e-37a5-46ab-b42b-4d9fbb82be8a fly Dublin 10.0 2017:10:02.04:10:10 05:05:05
OK
+--------------------------------------+------+----------+----------+---------------------+----------+-------+
|                  Id                  | Type | Location | Distance |         Date        | Duration | Route |
+--------------------------------------+------+----------+----------+---------------------+----------+-------+
| 3afd4de3-5112-4a54-ade6-ad428fd79529 |  fly |   Dublin |     10.0 | 2017:10:02.04:10:10 |    18305 |    [] |
+--------------------------------------+------+----------+----------+---------------------+----------+-------+

pm> aa 47e56aa2-ea64-44d9-9042-ec761e1c873a walk Office 10.0 2017:10:03.04:10:10 05:05:05
OK
+--------------------------------------+------+----------+----------+---------------------+----------+-------+
|                  Id                  | Type | Location | Distance |         Date        | Duration | Route |
+--------------------------------------+------+----------+----------+---------------------+----------+-------+
| fea6bcc9-d16c-46fa-a2c8-51d8a40a28b7 | walk |   Office |     10.0 | 2017:10:03.04:10:10 |    18305 |    [] |
+--------------------------------------+------+----------+----------+---------------------+----------+-------+

pm> al fea6bcc9-d16c-46fa-a2c8-51d8a40a28b7 23.5 32.5
+--------------------------------------+------+----------+----------+---------------------+----------+---------------------------+
|                  Id                  | Type | Location | Distance |         Date        | Duration |           Route           |
+--------------------------------------+------+----------+----------+---------------------+----------+---------------------------+
| fea6bcc9-d16c-46fa-a2c8-51d8a40a28b7 | walk |   Office |     10.0 | 2017:10:03.04:10:10 |    18305 | [Location{0, 23.5, 32.5}] |
+--------------------------------------+------+----------+----------+---------------------+----------+---------------------------+

pm> al 3afd4de3-5112-4a54-ade6-ad428fd79529 12.5 34.5
+--------------------------------------+------+----------+----------+---------------------+----------+---------------------------+
|                  Id                  | Type | Location | Distance |         Date        | Duration |           Route           |
+--------------------------------------+------+----------+----------+---------------------+----------+---------------------------+
| 3afd4de3-5112-4a54-ade6-ad428fd79529 |  fly |   Dublin |     10.0 | 2017:10:02.04:10:10 |    18305 | [Location{1, 12.5, 34.5}] |
+--------------------------------------+------+----------+----------+---------------------+----------+---------------------------+

pm> du 47e56aa2-ea64-44d9-9042-ec761e1c873a
+--------------------------------------+------------+-----------+-------------------+----------+
|                  Id                  | First Name | Last Name |       Email       | Password |
+--------------------------------------+------------+-----------+-------------------+----------+
| 4f6e857e-37a5-46ab-b42b-4d9fbb82be8a |      homer |   simpsom | homer@simpson.com |   secret |
+--------------------------------------+------------+-----------+-------------------+----------+

pm> s
Security framework of XStream not initialized, XStream is probably vulnerable.
Data successfully stored.















*********************************************************************************************************************************************************************
Test Approach
*********************************************************************************************************************************************************************
Test Driven Development using Junit 4 is made use of in the project. The main 'Mantra' for TDD is "All code is guilty until proven innocent".

Given below is a high level overview of the different stages covered.

1. Created a test project into current solution using Junit 4

2. Created test classes corresponding to each module inside the solution

3. Run the test cases against to simulate different scenarios 

*********************************************************************************************************************************************************************
Build Approach
*********************************************************************************************************************************************************************

1. Created a Maven folder and generated project in eclipse

2. Modified the following entries in pom.xml
  <groupId>com.ali</groupId>
  <artifactId>assignment-1</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>jar</packaging>

  <name>assignment-1</name>
  <url>http://maven.apache.org</url>

3. Specified the following properties in pom.xml
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>

4. Added the following dependencies into pom.xml

    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/com.google.guava/guava -->
    <dependency>
      <groupId>com.google.guava</groupId>
      <artifactId>guava</artifactId>
      <version>23.0</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/com.thoughtworks.xstream/xstream -->
    <dependency>
      <groupId>com.thoughtworks.xstream</groupId>
      <artifactId>xstream</artifactId>
      <version>1.4.10</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/com.googlecode.clichemaven/cliche -->
    <dependency>
      <groupId>com.googlecode.clichemaven</groupId>
      <artifactId>cliche</artifactId>
      <version>110413</version>
    </dependency>
    <dependency>
      <groupId>java-ascii-table</groupId>
      <artifactId>java-ascii-table</artifactId>
      <version>1.0</version>
    </dependency>    
    <!-- https://mvnrepository.com/artifact/org.codehaus.jettison/jettison -->
    <dependency>
      <groupId>org.codehaus.jettison</groupId>
      <artifactId>jettison</artifactId>
      <version>1.2</version>
    </dependency>
    <dependency>
      <groupId>net.sourceforge</groupId>
      <artifactId>yamlbeans</artifactId>
      <version>1.0</version>
    </dependency>

Following is an alternate dependency description for yamlbeans available in the remote repository

    <dependency> 
      <groupId>com.esotericsoftware.yamlbeans</groupId> 
      <artifactId>yamlbeans</artifactId> 
      <version>1.06</version> 
    </dependency>
    
5. validated the pom.xml file using below command 

mvn validate

6. Included the jave source files and java test source files and other supporting files (readme.txt, test.script, sample data) under respective folders

7. tested the project using below command

mvn test

8. Installed the missing components into local repo using below commands

mvn install:install-file -Dfile=yamlbeans-1.0.jar -DgroupId=net.sourceforge -DartifactId=yamlbeans -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=java-ascii-table-1.0.jar -DgroupId=java-ascii-table -DartifactId=java-ascii-table -Dversion=1.0 -Dpackaging=jarmvn test

9. Packaged the overall jar file for solution using below command

mvn package

10. Executed the project using below command

mvn exec:java -Dexec.mainClass="controllers.Main"

11. Launched the batch file using following command

launch

Build commands
----------------------------------------------------------------------------------------------------------------------------------------------------------
mvn --version
mvn validate
mvn test
mvn install:install-file -Dfile=yamlbeans-1.0.jar -DgroupId=net.sourceforge -DartifactId=yamlbeans -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=java-ascii-table-1.0.jar -DgroupId=java-ascii-table -DartifactId=java-ascii-table -Dversion=1.0 -Dpackaging=jarmvn test
mvn package
mvn exec:java -Dexec.mainClass="controllers.Main"
mvn eclipse:eclipse
launch
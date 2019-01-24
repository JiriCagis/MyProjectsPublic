# Zonky Loans Checker
Terminal application periodically check new loans on the market company Zonky. Company is a Czech peer-to-peer online service that mediates online loans among people. You can visit [web](https://zonky.cz/) for more information about peer-to-peer land. 
Application show today added loans after start and it check every 5 minutes whether was add new loans on market. If market contains new loans after time interval, application print new items into terminal. For terminate application press CTRL+C.

### Note: 
You can specify your own check time or update URL to Zonky API in file **zonkyLoansChecker.properties**.

<p align='center'>
<img src="screenShotApp.png"/>
</p>

## How to build?
```
mvn clean package
```

## How to run?
```
cd target
java -jar ZonkyLoansChecker-1.1-SNAPSHOT.jar 
```

## Technologies
* Java
* Spring Boot
* Maven
* Unirest
* Jackson
* Testing: Junit and Mockito

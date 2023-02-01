# URL Shortener mini HOWTO.

It is a system for creating a short url for given any url.

## Technologies

This application is a Gradle project based on Spring-Boot, H2 database (and Database could be replaced with some other DB you want, using docker image or something else), Liquibase, and other modules (look at the build.gradle to get more details). 

## What is this?

This is a web application. You need a browser to work with it.
The default URL for the application is http://localhost:8181. 

At the first-home page you will see a form, where you have to enter your link ( for example: https://www.microsoft.com/ru-ru/microsoft-365 ) and press Submit button. 
Then on the second page ( result page ), you will get your link and short link, which is a redirect to your link. 

### Algorithm 
We know that Protocol and domain name in the URL is Case Insensitive. This means that http://www.google.com is the same as HTTP://WWW.GOOGLE.com, but then the path is Case Sensitive. I split the domain part and path and store them separately in the Database's columns.  
Each row in the table has an auto-generated numeric ID. This ID is represented in HEX format in the `hexid` column, exactly this hex value is the part of the new Short URL.

When the user enters an incorrect URL, he gets error information.
 
## Requirements:

- Java 11 - to compile and run the app you have to have Java version 11. 
        
        How to use specific JDK version you can find here: 
        https://stackoverflow.com/questions/18487406/how-do-i-tell-gradle-to-use-specific-jdk-version

## Minimal Configuration
  
  - All parameters are in the ./src/main/resources/application.yml file and have default values - this is enough to start the App (except db path)
  - you can redefine most of the configuration properties using the command line.

## Run application

- Go to the application directory
- Type in the terminal/command line:
    
        gradlew bootRun --args='--DS_URL=jdbc:h2:file:/opt/h2-shortener'

        where value is:
            - /opt/h2-shortener ~ is an absolute path to the 
                H2 Database file (file name is `h2-shortener`). 
                It will be created automatically during the first starting. 
                The directory must be writeable for the current user. 

- Open in your favorite browser http://localhost:8181 



# URL Shortener mini HOWTO.

## What is this?

This application is a Gradle project based on Spring-Boot, H2 database (and Database could be replaced with some other DB you want, using docker image or something else), Liquibase and other modules (look at the build.gradle to get more details). 

This is a web application. You need a browser to work with it.
Default url for application is http://localhost:8181. 

At the first-home page you will see a form, where you have to enter your link ( for example: http://www.apple.com/iphone/ ) and press Submit button. Then at the second page ( result page ) you will get your link and short link, that is a redirect to your link. 

###Algorithm 
We know that Protocol and domain name in the URL is Case Insensitive. This means that http://www.google.com is the same as HTTP://WWW.GOOGLE.com, but then the path is Case Sensitive. Those I split the domain part and path and store them separately in the Database's columns.  
Each row in the table has an auto-generated numeric ID. This ID is represented in HEX format in the `hexid` column, exactly this hex value is the part of the new Short URL.

When user enter incorrect url, he gets an error information.
 
## Requirements:

- Java 11 - to compile and run the app you have to have Java version 11. 
        
        How to use specific JDK version you can find here: 
        https://stackoverflow.com/questions/18487406/how-do-i-tell-gradle-to-use-specific-jdk-version

## Minimal Configuration

- All parameters are in the ./src/main/resources/application.yml file and have default values - this is enough to start the App (except db path)
- you can redefine most of the configuration properties using command line.

## Run application

- Go to the application directory
- Type in the terminal/command line:
    
        gradlew bootRun --args='--DS_URL=jdbc:h2:file:/opt/h2-shortener'

        where value are:
            - /opt/h2-shortener ~ is an absolute path to the 
                H2 Database file (file name is `h2-shortener`). 
                It will be created automatically during first starting. 
                The directory must be writeable for current user. 

- Open in your favorite browser http://localhost:8181 



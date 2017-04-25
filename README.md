# vaadin-001-charts-jumpstart
An industrial style multi module maven project


## Todos - Bugs / Impromements
+ Vaadin Archetype creates redundant dependencies in pom (ui module)

        <dependency>
            <groupId>com.vaadin</groupId>
            <artifactId>vaadin-themes</artifactId>
        </dependency>
        <dependency>
            <groupId>com.vaadin</groupId>
            <artifactId>vaadin-themes</artifactId>
        </dependency>

+ unused import com.vaadin.ui.CustomComponent; created from the designer

## Preparations
The follwoing things are not automatically managed by initial mvn clean install.
### install Docker
To test with the Selenium Hub you need to install Docker on your machine. [Docker Install](https://docs.docker.com/engine/installation/)

If you want to try a few steps before, you could start the Selenium Hub by yourself with
You could specify the version of the DockerImage if you need it, maybe for legacy projects
Add for this the version numer ad the end of the command. Here I will show you to address the version 2.53.0
* docker run -d -p 4444:4444 --name selenium-hub selenium/hub:2.53.0 

I will use the latest
* docker run -d -p 4444:4444 --name selenium-hub selenium/hub 
* docker run -d --link selenium-hub:hub selenium/node-chrome
* docker run -d --link selenium-hub:hub selenium/node-firefox
* docker run -d --link selenium-hub:hub selenium/node-phantomjs

This commands are installing a local Selenium Hub with Agents for Chrome, FireFox and PhantomJS
und Linux. If you need Windows, you have to check the section ( comming soon ;-) ) called **Vagrant**.

The Hub will be available under [http://localhost:4444/grid/console](http://localhost:4444/grid/console)
The Hub is available, the nodes are inside the Docker Cliuster. The Agents are linked to the Hub.
Every Docker instance could reach your local machine with web requests.
So, the webapp is running on your host, requests are comming from inside the Docker cluster.

Later I will show, how to push the webapp into the Docker cluster, too.

## Todos - Infrastructure
+ Add stagemonitor to ui module
+ Gatling with Vaadin ?
+ Integrate Vagrant / Ansible for Tests on Windows Machines
+ Migrate to jUnit5 ?

+ Version based on Spring
+ Version based on JavaEE - TomEE or wildfly ?



## Features
+ Full stack for industrial projects
+ Undertow based Microservice - Speed compered to Jetty / jRebel combination
+ DDI - Bootstrap with a minimal DI Framework, to show the potential of this
+ could be used for Workshops / Project Ramp-Up
+ structure to support different persistence technologies
+ Local Selenium Hub via Docker for Linux based tests
+ Mutation Testing
+ CVE Security Report
+ Testbench Tests could be run locally / locally in Selenium Hub / remote Selenium Hub

+ local Nexus for clean/compile speedup
+ local Jenkins for Build Tree´s



## Questions to solve
+ How to write good Testbench tests to navigate to the component you want to test
+ Add Ansible/Vagrant for Integration Tests
+ Add Docker for Integration Tests
+ Add JavaFX Browser for concurrent Headless Tests
+ maven target to download needed Browser-Driver
+ jooq / flyway for SQL
+ speedment integration (Streams)
+ Show the full DI Stack on localhost ( gitbucket/nexus/jenkins )
+ build the same with gradle
+ bootstrap with CDI (weld)

## Scenario
In this scenario we are in the world of ..  

## Modulestructure

### api
The definition of the business logic interfaces and the used model.


### backend
could be seperated to an other microservice. (example is here the rest interface module)
 
#### logic
Business logic will be here. This must be independen of your UI.

#### persistence
Ho to provide a persistent storage for your data.


### gui
#### design
Use this module to create the UI - elements with the Vaadin Designer.
The generated code should be untouched. To extend this, use the ui module.
So you could always re-generate the ui elements without loosing your manual work.


#### uilogic
The UI based logic is based in this package. this should be independend of the backend and 
 only combining the API with the ui elements. Here you should implement 
 the interaction between the ui elements and no business logic.

#### ui
Now all comes together. Here the Vaadin bootstrap and the rest of the app will be 
put together to a webapp. Based on this you could start fom here with integration tests.

#### widgetset
your own theme and ui components are hold here.


### microservice
This module is for the bootstraping of the used microservice.
Here wwe could start and configure the used Undertow.

The Microservice used here is from RapidPM - [java-microservice.org]{http://www.java-micoservice.org}
or you can check out the soures at github - []{}

## Developer Help

### Howto start the servlet container for development?
Create a run config in intelliJ. ;-)
![IntelliJ Screenshot][./_data/doc/RunMain.png]

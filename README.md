# vaadin-001-charts-jumpstart
An industrial style multi module maven project


## Todos
Vaadin Archetype creates redundant dependencies in pom (ui module)

        <dependency>
            <groupId>com.vaadin</groupId>
            <artifactId>vaadin-themes</artifactId>
        </dependency>
        <dependency>
            <groupId>com.vaadin</groupId>
            <artifactId>vaadin-themes</artifactId>
        </dependency>




unused import com.vaadin.ui.CustomComponent; created from the designer



Add stagemonitor to ui module


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
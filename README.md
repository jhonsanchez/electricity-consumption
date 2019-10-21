# Electricity Consumption
 
## Task
Create a system that allows to receive and collect data about energy consumption from different villages. As a result, 
your system should, on demand, give out the consumption report per village for the last 24h. As a result of your work, 
we expect the end-to-end design of the system (a model, system architecture, technology, and frameworks choice, testing 
strategy, etc.). We would also like to see your code for the whole system or reasonable part of it. Since our main 
programming language is Java, it would be nice if you implement this solution using it. 
However, if you have a project (with a task of similar complexity), then you can share it with us as well, 
instead of coding this task. In this case please create a statement/document explaining the problem that is being 
solved with it, this project should speaks for your code skills :-).

Consider that your system has an API that is called by electricity counters: 

>POST /counter_callback 
```
{
    "counter_id": "1",
    "amount": 10000.123
} 
```
To get information additional information about the counter you have to call the following external API: 

>GET /counter?id=1 
``` 
{
    "id": "1",
    "village_name": "Villarriba"
}
```

As a result, it's expected that your system will expose the following API: 

>GET /consumption_report?duration=24h 
```
{
    "villages": [
        {
            "village_name": "Villarriba",
            "consumption": 12345.123
        },
        {
            "village_name": "Villabajo",
            "consumption": 23456.123
        }
    ]
}
```
## Proposal
This system was created using clean architecture to provide flexibility where modules can be changed easily
but our domain model remains pure against changes in technology (at certain level), if tomorrow the team decide
that a relational database is no longer the right approach to go we can create another module and add it to the
deployment module with minimal impact, or if we decide to move away from springboot in favor of another framework we 
could just do it

## Technology
This system uses the following technologies:
* Java 13 with --enable-preview for the switch feature, 
* Junit 5
* Springboot 2.2.0-RELEASE
* H2
* Maven
* Lombok

## Modules
* app-api
* app-impl
* domain
* infra-db-jpa
* infra-rest-springmvc
* springboot-deployment

### app-api
This module contains the use cases that our domain exposes to the system, dependencies:
* app-domain
### app-impl
This module contains the implementation of the uses cases, dependencies:
* app-domain
* app-api
### domain
This module should be pure and protected against infrastructure challenges, it contains the behavior of our system, 
dependencies:
* none
### infra-db-jpa
This module contains the implementation at the infrastructure level of the domain, the main technology is JPA with Hibernate
but to obtain the name of the village we have to connect to an external API therefore we depend on another system
one approach could be to let our domain to depend on that external API, but that would force us to be tightly couple with that
system.
The approach we are implementing is the creation of an interface at domain level where our infrastructure is depending, letting us 
invert the dependency and now the infrastructure is depending on domain as it should be. Dependecies:
* domain
* infrastructure libraries   
### infra-rest-springmvc
This module contains the implementation of the Rest API, the main technology is spring-mvc, but againg we could change to json-rpc, jersey 
or another technology, dependencies:
* app-api
* domain
* infrastructure libraries
### springboot-deployment
This module join all the modules and create the deployable artifact, the main technology is Spring Boot


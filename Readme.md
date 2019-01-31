# Bus Payment System

Is a demostration project to compute the journey details for passengers (and payment amounts) based on a "tap on"/tap off" data file (CSV).


## Implementation

This is implemented using the Google Cloud Platform App Engine service. 

The project consists of a number of JSP's to render a front end, and a number of Servlets to provide a mini web API to perform the core operations.



## Maven
### Running locally

    mvn appengine:run

To use vist: http://localhost:8080/

### Deploying

    mvn appengine:deploy

To use vist:  https://bus-payment-101.appspot.com



## Testing

    mvn verify
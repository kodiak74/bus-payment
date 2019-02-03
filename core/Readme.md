# Bus Payment System - Core

This is a demostration project to compute the journey details for passengers (and payment amounts) based on a "tap on"/tap off" data file (CSV).

The following busines logic applies to calcualting the costing for a trip:

 - Trips between Stop 1 and Stop 2 cost $3.25 
 - Trips between Stop 2 and Stop 3 cost $5.50 
 - Trips between Stop 1 and Stop 3 cost $7.30

Note that the above prices apply for travel in either direction (e.g. a passenger can tap on at stop 1 and tap off at stop 2, or they can tap on at stop 2 and can tap off at stop 1. In either case, they would be charged $3.25).

**Completed Trips**

If a passenger taps on at one stop and taps off at another stop, this is called a complete trip. The amount the passenger will be charged for the trip will be determined based on the table above. For example, if a passenger taps on at stop 3 and taps off at stop 1, they will be charged $7.30.

**Incomplete trips**

If a passenger taps on at one stop but forgets to tap off at another stop, this is called an incomplete trip. The passenger will be charged the maximum amount for a trip from that stop to any other stop they could have travelled to. For example, if a passenger taps on at Stop 2, but does not tap off, they could potentially have travelled to either stop 1 ($3.25) or stop 3 ($5.50), so they will be charged the higher value of $5.50.

**Cancelled trips**

If a passenger taps on and then taps off again at the same bus stop, this is called a cancelled trip. The passenger will not be charged for the trip.

## Implementation

This is implemented in Java 1.8, and uses the Maven build environment.
The tests are written using JUnit 4.
Currently not implemented with any logging framework - exceptions just writen to the standard error stream.
 
## Assumptions 

 - The current implementation does not validation on the input CSV, and assumes that file has a header line nad that it complies with the Standard Comma Separated Value format, as for RFC4180 but allowing empty lines.
 - Taps are in ascending chronological order. 

## Running

Clone the repository locally, and run the maven install:
   
    $ mvn install

Then use the following command to process your source csv "in" to the target output file "out".

    $ ./run.sh <in> <out>


## Testing

    mvn verify

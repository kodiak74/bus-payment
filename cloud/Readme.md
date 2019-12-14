# Bus Payment System - Cloud

This is an extension of the command line system, leveraging the Google Cloud Platform services.


## Implementation

The core of the system is deployed as a series of servlets and JSP's running in an AppEngine container.
The system stores uploaded data files in the Cloud Storage service (similar to AWS S3), and processed Trip results in Cloud Datastore (similar to AWS Dynamo DB).

## Details

This system uses the following GCloud resources:
* App Engine (Running servlets in java8 runtimes)
* Datastore (storing processin results)
* CloudStorage (storing uploaded data files)


The system consists of the following servlets (as API endpoints):
* AddDataServlet
* DeleteServlet
* ListDataServlet
* ProcessServlet
* ResultsViewServlet




## Usuage

To use, please vist: 

 [https://bus-payment-101.appspot.com](https://bus-payment-101.appspot.com)


## Local Development

```

# Setup Datastore:
$ gcloud datastore indexes create index.yaml 


# Setup
$ gcloud config set project bus-payment-101
$ gcloud auth activate-service-account bus-payment-101@appspot.gserviceaccount.com --key-file=/Users/chris/gcloud/bus-payment-101-6160f12bb031.json


# Running locally
$ export GOOGLE_APPLICATION_CREDENTIALS=/Users/chris/gcloud/b-payment-101-6160f12bb031.json
$ mvn appengine:run


# To Deploy
$ mvn appengine:deploy


```

Service account : `bus-payment-101@appspot.gserviceaccount.com`


[<< Main](../Readme.md)
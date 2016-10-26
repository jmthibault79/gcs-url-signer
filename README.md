# gcs-url-signer
A simple utility for creating [Signed URLs](https://cloud.google.com/storage/docs/access-control/signed-urls) for [Google Cloud Storage](https://cloud.google.com/storage/). 

Required: a GCS [Service Account](https://cloud.google.com/compute/docs/access/service-accounts) with an associated Client ID and a PEM file.
  
Running `sbt run /path/to/json` with the following JSON format will return a signed GCS URL that will be valid for GET requests for the following 8 hours.

```
{
	"bucketName": "<GCS bucket>", 
	"objectKey": "<path/inside/bucket>",
	"pemFile": "</path/to/pem>",
	"clientId": "<service account client ID>"
}
```

# spring-cloud-gateway-request-body-sample
## About
This is sample of spring cloud gateway that has the request body predicate (routing rule).

If request body is "target-request-body", request is routed to http://localhost:50080. Other request is routed to http://localhost:60080. 

[display-request-contents](https://github.com/sun-bs/display-request-contents) docker-compose is sample hosting http://localhost:50080 and http://localhost:60080.
It might be useful for test.

## How to run
`mvn spring-boot:run`

## try

`curl -X POST -d 'target-request-body' http://localhost:8080/test`

-> http://localhost:50080

`curl -X POST -d 'target-request-dummy-body' http://localhost:8080/test`

-> http://localhost:60080
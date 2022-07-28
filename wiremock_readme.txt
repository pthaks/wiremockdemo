Wiremock - running standalone server with JAR file

1. start the server by java -jar wiremock jar 
2. http://localhost:8001/
3. 

Thru code -
1. Use wiremock jre
2. Create 2 folders under src/test/resources as "__files" and "mappings"
3. __files folder essentially contains the responses.
4. mappings folder contains the requests

Scenarios covered -
1. GET request - Plural -> Lists the users (DONE)
2. GET request - Single --> user is found (DONE)
3. GET request - Single -> User not found (DONE)
4. POST request -> user is created -> 201 (DONE)
5. PUT request -> Update a resource -> resource is updated (DONE)
6. PUT request -> Update a resource which is not present -> resource not found (DONE)
7. Delete request -> resource is deleted -> 204 , No content returned, no body (DONE)
8. Search Request -> with query parameter (Matching)
9. Search Request -> with query parameter (NOT Matching)

java -jar wiremock-jre8-standalone-2.33.2.jar  -port=8001 -verbose=true

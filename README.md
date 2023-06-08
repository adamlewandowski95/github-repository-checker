github-repository-checker
===================

Spring implementation of a sample application using server-side processing
REST services. Application allows to check GitHub repositories and branches information
for given username.


### Requirements

- Java 17
- Spring Boot 3.1.0

### Installation

1. Open your terminal or command prompt.
2. Navigate to the root directory of your Spring Boot project.
3. Make sure you have Maven installed and added to your system's PATH.
   (If not you can use maven wrapper from this project (mvnw file)).
4. Run the following command to build the project and package it into an executable JAR file:
```bash
mvn clean install
```
5. Once the build is successful application can be started in two approaches
- First approach. Just run:
```bash
mvn spring-boot:run
```
- Second approach. Navigate to the target directory:
```bash
cd target
```
Run the following command to start your Spring Boot application:
```bash
java -jar <name-of-jar-file>.jar
```
Replace `<name-of-jar-file>` with the actual name of the JAR file generated in the previous step.

### Application

The web application is available through the browser at the default port (8080).
Request can be done from:
```http
GET http://localhost:8080/repositories/{username}
```


Where **{username}** should be replaced with username from GitHub portal. 

#### Example of API response

```javascript
[
    {
        "repositoryName": string,
        "ownerLogin": string,
        "branches": [
            {
                "name": string,
                "commitSha": string
            },
            {
                "name": string,
                "commitSha": string
            }
        ]
    }
]
```

### Fields in API response

- `repositoryName` -> name of the repository
- `ownerLogin` -> login of the owner
- `branches` -> list of the branches which exist on shown repository
- `branches.name` -> name of the branch
- `branches.commitSha` -> sha of the last commit from selected branch

## Status Codes

Application returns the following status codes in its API:

| Status Code | Description    |
|:------------|:---------------|
| 200         | `OK`           |
| 404         | `NOT FOUND`    |
| 406         | `NOT ACCEPTABLE` |
| 418         | `I'M A TEAPOT` |

# CV Manager Service
## Authors

- [@career-opz](https://www.github.com/career-opz)

This is the backend which manages the CVs and job profiles of the user. This will communicate with the DB and the Portal backend service
## Installation

- Clone the github repository
- Make sure you have Java 17 installed
- Make sure your Java HOME is setup and /bin dir is added to the PATH
- Recommand to have intellij idea installed
- Open the project through intellij idea
- Reload the project so that dependencies will be added to your local maven repository

To install the project if you want
```bash
  ./mvnw clean install
```

Otherwise you can use intellij to run the CV manager service
Make sure Portal backend service is already up so that UI can communicate with this service
## Documentation
[swager-docs](http://localhost:9092/swagger-ui/index.html)
### High level Architecture diagram
![Highlevel Architecture](https://github.com/career-opz/portal-backend/blob/main/docs/images/archi.png?raw=true)


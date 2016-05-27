## Link-Service - Specification
Create a RESTful JAVA service which is able to transform a http link into a minimized and trackable link. 
The service should be able to track a click on such a generated link and also redirect the user to the initial URL.

Example: https://www.happycar.de/info/versicherung/document/some/very/long/path will become https://link.happycar.de/abc123

### Technical Requirements
- JAVA8
- Spring MVC
- Maven or Gradle (Gradle preferred)
- JUnit4

### Code Requirements
- Google Java Style https://google.github.io/styleguide/javaguide.html
- Proper Documentation

### Functional Requirements
- RESTful API (CRUD)
- Link Tracking (e.g. referral) and Redirection
- Optional: before redirection trigger pre-configured hooks (e.g. to https://zapier.com)

### Prerequisites ###

```
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
```

### Used frameworks and technologies ###
* **JDK 1.8**
* **IntelliJ IDEA 2016.1 Community Edition**
* **Spring MVC** (REST API provider)
* **Spring Boot** (Used for correct packaging purposes)
* **Gradle** (Project build tool)
* **JUnit** (Unit-testing framework)
* **CheckStyle** (Code analyzer)
* **FindBugs** (Code analyzer)

### API spec ###
* POST: "/link/register" body: String link - registering simple link
* POST: "/link/register/trackable" body: String link - registering link with UTM params for trackability
* GET: "/lnk/**" - redirecting from short link to initial link

### Application workflow ###
1. Register link using one of two provided endpoints
    1. Register simple link using - POST: "/link/register"
    2. Register trackable link using - POST: "/link/register/trackable"
2. Access one of short already registered links in order to redirect to initial long link

### Assumptions made ###
* **Trackable links** - as per (https://support.google.com/analytics/answer/1033867?hl=en), trackable links are the links
which have special mandatory UTM fields in order to enable tracking of those links in Google Analytics. However, trackable
links should be created by issuer and that is why the only API endpoint was created in order to receive already trackable
links from 3rd-party issuers, check that all UTM fields are presented and minify them as is. Service https://goo.gl/ uses
similar approach.
* **Minified links storage** - the solution for link minimisation and storing was used very similar to https://goo.gl/
service. First of all, unique ID got generated out of Alphabet, which contains latin lower case and upper case chars as
well as arabic digits (62 characters in total). After that we check if this ID was not presented before and store the ID
together with big initial link if it was not. The ID is 6 chars length and is capable of having 62^6 unique values which
covers up enormous amount of links. In current implementation <ID, Link> pairs are stored in simple HashMap container
which is not persisted after application shutdown. In real application, there would probably be a key-value database
(maybe something like Redis).
* **Mocking frameworks** - additional advantage for testing this application could be a mocking framework like Mockito.
It would be useful, for example, when testing LinkController#redirect() method in order to mock HttpServletRequest and
HttpServletResponse objects. As mocking frameworks are not presented in technical requirements, they were not used.
* **Pre-configured hooks** - those were not used in this application as, in my opinion, the idea of hooks is quite broad
which I probably don't fully understand. If the hook is simply an action before another action, then the easiest
hook would look as below. Such approach is very easy and could be done in several minutes. But I didn't implement it as
maybe I'm mistaken and pre-configured hooks is something more complicated than I thought.
    * Establish API endpoint like - POST: "/link/hooks/delete_on_access"
    * Change some variable if specified endpoint accessed
    * Delete <ID, Link> entry from cache right after getting this entry and before redirecting

### How to start application ###

```
#!java
gradlew clean build
java -jar build/libs/tryout-1.0.0.jar
```

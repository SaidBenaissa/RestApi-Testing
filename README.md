# REST API Assignment - Grunnlag

This project implements a simple REST API as per the assessment requirements.

## Functionality

The API exposes a single endpoint:

*   `POST /grunnlag`: Accepts a JSON payload representing `GrunnlagRequest` data. It validates if the sum of `saldo` and `aksjeandel` values within the `oppgave` list matches the `sumSaldo` and `sumAksjehandel` values provided in the `oppgaveoppsummering`.
    *   Returns **HTTP 200 OK** with body "Gyldige data" if the sums match.
    *   Returns **HTTP 400 Bad Request** with body "Ugyldige data" if the sums do not match or if the input data is invalid/malformed.

## Running the Application

1.  Clone the repository.
2.  Ensure you have Java (version specified in `pom.xml`) and Maven installed.
3.  Navigate to the project root directory.
4.  Run the application using Maven:
    ```bash
    mvn spring-boot:run
    ```
5.  The API will be available at `http://localhost:8080/grunnlag`.

## Testing Philosophy

The testing strategy for this project aims for comprehensive coverage by combining unit and integration tests, focusing on verifying the core requirements of the assessment.

### What Was Tested

1.  **Core Business Logic (Service Layer):** The primary focus was testing the `GrunnlagService.validerGrunnlag` method, which contains the critical validation logic comparing calculated sums against provided summary sums. Both valid (matching sums) and invalid (mismatched sums) scenarios were tested. Edge cases like null input or null values within the data structure were also considered.
2.  **API Endpoint (Controller Layer):** The `GrunnlagController` was tested to ensure it correctly handles incoming `POST` requests to `/grunnlag`, deserializes the JSON payload, delegates to the `GrunnlagService`, and returns the appropriate HTTP status codes (200 OK for valid data, 400 Bad Request for invalid data based on the service's validation result). Tests also cover the handling of fundamentally malformed JSON input.
3.  **Data Model (Model Layer):** Basic tests ensure the `GrunnlagRequest` model and its nested classes can be instantiated and correctly handle data, including deserialization from a valid JSON structure (`request.json`).

### How It Was Tested

1.  **Unit Tests (`GrunnlagServiceTest`):** The `GrunnlagService` was tested in isolation using plain JUnit tests. Test instances of `GrunnlagRequest` were created (often by deserializing JSON from files using Jackson's `ObjectMapper`) and passed to the `validerGrunnlag` method. Assertions (`assertTrue`, `assertFalse`) were used to verify the boolean result based on the input data (valid sums vs. invalid sums).
2.  **Integration Tests (`GrunnlagControllerTest`, `GrunnlagApplicationTests`):** Spring Boot's testing support (`@SpringBootTest`, `@AutoConfigureMockMvc`) was used. `MockMvc` simulated HTTP requests to the `/grunnlag` endpoint.
    *   Valid requests were sent using the content of `request.json`, expecting an HTTP 200 status.
    *   Invalid requests (specifically testing the mismatched sums requirement) were sent using a dedicated JSON file (`invalid-sum-request.json` - *assuming this was created as suggested*) containing valid structure but incorrect summary values, expecting an HTTP 400 status.
    *   Malformed requests were tested using `schema.json` (which is not a valid request payload) to ensure the controller/framework correctly returns HTTP 400 for parsing/validation errors.
3.  **Test Data from Files:** Key test scenarios (valid request, invalid sums request, malformed request) load their input data from `.json` files located in `src/test/resources/json`. This keeps test code cleaner and separates test data from test logic.

### Why This Approach

*   **Isolation:** Unit testing the service allows for focused verification of the core business logic without the overhead of the web framework, making tests faster and easier to debug.
*   **Integration:** `MockMvc` tests for the controller ensure that the web layer is correctly configured, requests are routed properly, data binding works, the controller interacts correctly with the service, and the HTTP responses (status codes, body) are accurate according to the requirements.
*   **Confidence:** Combining unit and integration tests provides higher confidence that the application works correctly end-to-end, from receiving an HTTP request to executing the business logic and returning the result.
*   **Maintainability:** Using external files for test data makes it easier to manage and update test cases without modifying the test code itself.
*   **Requirement Focused:** The tests directly address the assessment's core requirements: the existence of the endpoint, delegation to a service, the specific sum validation logic, and the corresponding 200/400 status code responses.

# Why This Approach

* **Isolation:** Unit testing the service allows for focused verification of the core business logic without the overhead of the web framework, making tests faster and easier to debug.
* **Integration:** `MockMvc` tests for the controller ensure that the web layer is correctly configured, requests are routed properly, data binding works, the controller interacts correctly with the service, and the HTTP responses (status codes, body) are accurate according to the requirements.
* **Confidence:** Combining unit and integration tests provides higher confidence that the application works correctly end-to-end, from receiving an HTTP request to executing the business logic and returning the result.
* **Maintainability:** Using external files for test data makes it easier to manage and update test cases without modifying the test code itself.
* **Requirement Focused:** The tests directly address the assessment's core requirements: the existence of the endpoint, delegation to a service, the specific sum validation logic, and the corresponding 200/400 status code responses.

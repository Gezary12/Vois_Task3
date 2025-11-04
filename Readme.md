# REST Assured API Testing Project

## Table of Contents
- [Introduction](#introduction)
- [Scenario](#scenario)
- [Components](#components)
- [Features](#features)
- [How Solution Works](#how-solution-works)
- [Tools](#tools)
- [Reporting](#reporting)
- [Project Structure](#project-structure)
- [pom.xml Configuration](#pomxml-configuration)
- [How to Run Tests](#how-to-run-tests)
- [References](#references)

---

## Introduction
This project automates REST API testing using **Java**, **Rest Assured**, and **TestNG**.  
It uses the free public API [JSONPlaceholder](https://jsonplaceholder.typicode.com) to demonstrate how to perform **GET** and **POST** requests, validate API responses, and apply assertions for data integrity.

The purpose is to showcase reusable, maintainable API test automation that validates common API functionalities such as fetching, creating, and verifying data objects.

---

## Scenario
The project performs API testing on JSONPlaceholder endpoints with the following test cases:

1. **Print Comments for Random Post**
    - Fetches all posts
    - Selects one at random
    - Retrieves and prints all comments associated with it

2. **Validate Album Title Length**
    - Fetches all albums for a specific user (ex.`userId = 3`)
    - Ensures no album title exceeds 300 characters

3. **Create New Post**
    - Sends a `POST` request to `/posts` with a new post body
    - Validates the response status (`201`) and content

4. **Print Incomplete Todos for User**
    - Retrieves all todos for `userId = 5` where `completed = false`
    - Prints the titles of incomplete tasks

---

## Components
- **Base URI Setup**: Defined once before test execution (`https://jsonplaceholder.typicode.com`)
- **TestNG Annotations**: Used to manage test order and execution priority
- **Assertions**: Validate response data, and field values

---

## Features
✅ RESTful API validation using Rest Assured  
✅ TestNG integration for structured test execution  
✅ Automated response validation and assertions  
✅ Random data handling (dynamic post selection)  

---

## How Solution Works
1. **Setup Phase**
    - The base URI is initialized using `RestAssured.baseURI`.

2. **Execution Phase**
    - Each test case sends HTTP requests to specific endpoints.
    - Responses are validated for:
        - HTTP status codes
        - JSON structure and data types
        - Logical conditions (e.g., title length ≤ 300)

3. **Assertion Phase**
    - TestNG assertions confirm expected outcomes.

4. **Reporting Phase**
    - Allure reports can be generated optionally for rich visualization.

---

## Tools
| Tool / Library | Description |
|-----------|--------------|
| **Java 21** | Programming language used for automation |
| **Rest Assured** | Framework for REST API testing |
| **TestNG** | Testing framework to organize and execute tests |
| **Maven** | Build and dependency management tool |
| **Allure**| Reporting framework for visualizing results |

---


## Reporting
Project uses **Allure Reports** to generate detailed execution reports including each step performed.

### To generate and view the Allure Report:
```sh
allure serve "allure-results"

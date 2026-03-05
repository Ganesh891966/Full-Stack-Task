# Task 9: Spring MVC Application (Annotation-Based Configuration)

## Objective

Develop a basic Spring MVC application using annotation-based configuration.
Create a controller that accepts user requests and displays employee details using the MVC flow without XML configuration.

## Technologies Used

* Java
* Spring MVC
* Maven
* JSP

## Project Structure

```
Task9
├── pom.xml
└── src
    └── main
        ├── java
        │   └── com/example
        │       ├── Employee.java
        │       └── EmployeeController.java
        └── webapp
            └── WEB-INF
                └── views
                    └── employee.jsp
```

## MVC Flow

1. User sends request to `/employee`
2. `EmployeeController` handles the request.
3. Controller adds employee data to the Model.
4. The request is forwarded to `employee.jsp`.
5. JSP displays employee details.

## Features

* Uses `@Controller` and `@RequestMapping`
* Demonstrates Spring MVC architecture
* Annotation-based configuration
* Displays employee information using JSP view

## Output

Displays employee details such as:

* Employee ID
* Name
* Department


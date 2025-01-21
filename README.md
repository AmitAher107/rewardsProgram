
# Rewards Program

## Overview

This project is a Spring Boot application that calculates reward points for customers based on their transactions. Customers earn points based on the amount spent in each transaction:
- 2 points for every dollar spent over $100.
- 1 point for every dollar spent between $50 and $100.
- No points for the first $50.

The application provides a RESTful API to calculate and retrieve reward points for each customer.

## Technologies Used

- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok
- JUnit
- Mockito

## Setup

### Prerequisites

- Java 11 or higher
- Maven
- PostgreSQL

### Database Setup

1. Create a PostgreSQL database named `rewards_db`.
2. Update the `application.properties` file with your PostgreSQL credentials.


### Running the Application
+---------------------+
|      Customer       |
+---------------------+
          |
          |------------------+
          |                  |
          v                  v
+----------------+  +----------------+
|   Register     |  |  Earn Points   |
+----------------+  +----------------+
       |
       v
+----------------+
| Redeem Points  |
+----------------+
       |
       v
+----------------+
| View Balance   |
+----------------+
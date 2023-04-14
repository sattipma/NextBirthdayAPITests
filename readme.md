# Instructions for executing the tests

This document provides instructions for executing the tests. The tests are written using Java, Maven and TestNG framework.

## Prerequisites

Before running the tests, ensure that you have the following installed and setup on your machine:

- Java 17 or higher
- Apache Maven 3.8.2 or higher

## Running the tests

Follow the steps below to run the tests:

1. Clone the project to your local machine.
2. Open a command prompt or terminal window and navigate to the root directory of the project.
3. Run the following command to build the project:
   `mvn clean install`
4. After the build is successful it will automatically execute the tests. If you want to  rerun, use the following command to execute the tests:
   `mvn test`
5. After the tests are executed, a test report will be generated in the `target/surefire-reports` directory.
Open the `index.html` file in a web browser to view the test results.
** Sample Report **
![image](https://user-images.githubusercontent.com/42676603/232156265-e544f697-fbc5-4090-bf6a-e1d10d7e64f6.png)

## Modifying the tests

To modify the tests, follow the steps below:

1. Open the project in an IDE such as Eclipse or IntelliJ IDEA.
2. Navigate to the `src/test/java` directory to view the test classes.
3. Modify the existing test classes or create new ones as required.
4. Run the tests using the instructions provided above.

## Contact

If you have any questions or issues regarding this project, please contact me at <a>sattipma@gmail.com</a>

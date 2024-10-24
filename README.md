# LibraLink

## Description
LibraLink is a library management system that I built mainly for library admins. It has features like user sign-in, managing books, book copies, premium members, employees, and tracking book checkouts. It also has a dashboard that shows cool graphs and stats about everything happening in the library.

The project aims to incorporate both basic and advanced functionalities over time, enhancing the overall library management experience.

## Entity Relationship Diagram 

![LibraLink](https://github.com/umar-7w4/LibraLink-Library-Management-System/blob/main/ERD%20Libralink.png)

## Prerequisites
Before you begin, ensure you have met the following requirements:
- Java JDK 11 or higher installed
- Maven or Gradle (specify the version)
- PostgreSQL (Database named "LibraLink" recommended)
- Download the latest version of Spring Tool Suite (STS)

## Installation and Setup
To install and set up LibraLink, follow these steps:

1. **Clone the Repository:**

2. **Database Setup:**
- Set up a PostgreSQL database.
- Name the database "LibraLink" or choose another name (remember to update this in `application.properties`).

3. **Import Project into Spring Tool Suite:**
- Open STS.
- Choose `File > Import > Existing Maven/Gradle Projects` and select the cloned directory.

4. **Configure Database:**
- Navigate to `src/main/resources/`.
- Open `application.properties`.
- Update the database name, PostgreSQL username, and password.

5. **Run the Project:**
- Right-click the project in STS and select `Run As > Spring Boot App`.
- The project will run on port 8085.

6. **Access the Application:**
- Open a web browser.
- Visit `https://localhost:8085/auth/login`.

7. **Account Management:**
- To create an account, use the Signup option.
- To log in, use the Login option.

## Using LibraLink
Once logged in, users can [describe basic functionalities available in your application, like browsing books, managing loans, etc.].

## Contributing to LibraLink
To contribute to LibraLink, follow these steps:
1. Fork the repository.
2. Create a branch: `git checkout -b <branch_name>`.
3. Make your changes and commit them: `git commit -m '<commit_message>'`
4. Push to the original branch: `git push origin <project_name>/<location>`
5. Create a pull request.

## License
This project is licensed under the @2023 Umar Mohammad 

## Contact
If you have any questions or want to contribute, please email us at mohammadumar7w4@gmail.com


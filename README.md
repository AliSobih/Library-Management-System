# Library-Management-System
## end points:
 - Book management endpoints:
   - GET `/api/books`: Retrieve a list of all books.
   - GET `/api/books/{id}`: Retrieve details of a specific book by ID.
   - POST `/api/books`: Add a new book to the library.
   - PUT `/api/books/{id}`: Update an existing book's information.
   - DELETE `/api/books/{id}`: Remove a book from the library.
 - Patron management endpoints:
   - GET `/api/patrons`: Retrieve a list of all patrons.
   - GET `/api/patrons/{id}`: Retrieve details of a specific patron by ID.
   - POST `/api/patrons`: Add a new patron to the system.
   - PUT `/api/patrons/{id}`: Update an existing patron's information.
   - DELETE `/api/patrons/{id}`: Remove a patron from the system.
 - Borrowing endpoints:
   - POST `/api/borrow/{bookId}/patron/{patronId}`: Allow a patron to borrow a book.
   - PUT `/api/return/{bookId}/patron/{patronId}`: Record the return of a borrowed book by a patron.
  
 ## Technologies:
- Java 17 or above
- Spring Boot 3.0
- Maven
- MySQL
- Spring Data JPA
- Spring Security
- JSON Web Tokens (JWT)
- AOP
## how to use this project: 
  1- register and take Token to access other APIs:
  -  POST `/api/auth/register` - Add a new user details.
      - firstName and lastName: must be between 3 and 20 characters long
      - password:  must be at lest 8 character long.
     -  phoneNumber: must be moe than one digit.
     -  email: must be formatted email.
    ![Screenshot 2024-01-24 095042](https://github.com/AliSobih/spring-boot-spring-security-jwt-authentication/assets/43109825/c1132bae-d793-4034-b591-31874d17417b)
  2- if you have email then you can login and tack the token to use it.
     - POST `/api/auth/authentication` - sign in.
        - if the password or email is wrong then 403 forbidden response will return.
         ![Screenshot 2024-04-13 221935](https://github.com/AliSobih/Library-Management-System/assets/43109825/e932a133-ef73-4d17-a482-dbece9ba4c05)
       - if the email and password correct 200 ok will return and token.
         ![Screenshot 2024-04-13 221531](https://github.com/AliSobih/Library-Management-System/assets/43109825/ac11ad4d-5ffb-43d0-a26c-20ff39983f10)

3-  adding books (without jwt Token):
  -  POST `/api/books`
    ![Screenshot 2024-04-13 223412](https://github.com/AliSobih/Library-Management-System/assets/43109825/c0e409a6-612d-4728-9e51-0a084c2bc44e)
4- borrowing book (with Jwt Token):
  -  POST `/api/borrow/{bookId}/patron/{patronId}`
    ![Screenshot 2024-04-13 223916](https://github.com/AliSobih/Library-Management-System/assets/43109825/071858f4-858e-4723-8628-9873827c34b5)
5- return book (with Jwt Token):
  -  Put `/api/return/{bookId}/patron/{patronId}`
      ![Screenshot 2024-04-13 224500](https://github.com/AliSobih/Library-Management-System/assets/43109825/8db47b95-8ee8-4581-8521-79c81ded9ea6)
6- Notes:
    - you can't borrow same book two times without returning it.

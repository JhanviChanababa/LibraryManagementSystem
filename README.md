# Library Management System

## Tech stack

Language :- Java
</br>
Framework :- Spring Boot
</br>
Database :- PostgreSQL

## Requirements

For building and running the application you need:

JDK 1.8 or newer
</br>
Maven 3
</br>
PostgreSQL
</br>

## REST API

### 1. Add User
  Api to register a new user.

* #### Path

  /user

* #### Method

  `POST` 

* #### Data Parameters

  | Key | Type  | Required  |
  | :---:   | :-: | :-: |
  | name | String | No |
  | email | String | Yes |
  | password | String | Yes |
  | dob | Date | No |

  `{
    "name":"test",
    "email":"test@getnada.com",
    "password":"test@123",
    "dob":"1997/10/03"
  }`


* #### Success Response
  
  * User registered successfully.
    
    * **Code:** 200 <br />
  
    * **Content:** 
      | Key | Type  |  
      | :---:   | :-: | 
      | id | UUID | 
      | name | String | 
      | email | String |
      | dob | Date | 
      
      `{
        "message": "User added successfully",
        "user": {
          "id":"d420c0b5-3d7e-4241-89f1-0e9985207593",
          "name":"test",
          "email":"test@getnada.com",
          "dob":"1997/10/03"
        }
      }`
 
* **Error Response**

  * CONFLICT 
  
    * **Code:** 409

  * INTERNAL SERVER ERROR 
  
    * **Code:** 500 
 
### 2. User Login
  Api to login a user.

* #### Path

  /user/login

* #### Method:

  `POST` 

* #### Data Parameters

  | Key | Type  | Required  |
  | :---:   | :-: | :-: |
  | email | String | Yes |
  | password | String | Yes |

  `{
    "email":"test@getnada.com",
    "password":"test@123"
  }`


* #### Success Response:
  
  * User logged in successfully.
    
    * **Code:** 200 <br />
  
    * **Content:** 
      | Key | Type  |  
      | :---:   | :-: | 
      | id | UUID | 
      | name | String | 
      | email | String |
      | dob | Date | 
      
      `{
        "message": "User logged in successfully",
        "user": {
          "id":"d420c0b5-3d7e-4241-89f1-0e9985207593",
          "name":"test",
          "email":"test@getnada.com",
          "dob":"1997/10/03"
        }
      }`
 
* #### Error Response

  * UNAUTHORIZED 
  
    * **Code:** 401
    
### 3. Get User
  Api to get a user.

* #### Path

  /user/{id}

* #### Method

  `GET`

* #### Success Response
  
  * User retrieved successfully.
      
    * **Code:** 200 <br />
  
    * **Content:** 
      | Key | Type  |  
      | :---:   | :-: | 
      | id | UUID | 
      | name | String | 
      | email | String |
      | dob | Date | 
      
      `{
        "message": "User retrieved successfully",
        "user": {
          "id":"d420c0b5-3d7e-4241-89f1-0e9985207593",
          "name":"test",
          "email":"test@getnada.com",
          "dob":"1997/10/03"
        }
      }`
 
* #### Error Response

  * BAD REQUEST 
  
    * **Code:** 400  

  * FORBIDDEN 
  
    * **Code:** 403

  * NOT FOUND 
  
    * **Code:** 404

  * INTERNAL SERVER ERROR 
  
    * **Code:** 500 
  
### 4. Update User
  Api to update an existing user.

* #### Path

  /user/{id}

* #### Method

  `PUT` 

* #### Data Parameters

  | Key | Type  | Required  |
  | :-:   | :-: | :-: |
  | name | String | No |
  | email | String | Yes |
  | password | String | No |
  | dob | Date | Yes |

  `{
    "id":"d420c0b5-3d7e-4241-89f1-0e9985207593",
    "name":"test",
    "email":"test@getnada.com",
    "password":"test@123",
    "dob":"1997/10/03"
  }`


* #### Success Response
  
  * User updated successfully.
    
    * **Code:** 200 <br />
  
    * **Content:** 
      | Key | Type  |  
      | :---:   | :-: | 
      | id | UUID | 
      | name | String | 
      | email | String |
      | dob | Date | 
      
      `{
        "message": "User updated successfully",
        "user": {
          "id":"d420c0b5-3d7e-4241-89f1-0e9985207593",
          "name":"test",
          "email":"test@getnada.com",
          "dob":"1997/10/03"
        }
      }`
 
* #### Error Response

  * FORBIDDEN 
  
    * **Code:** 403

  * NOT FOUND 
  
    * **Code:** 404
    
  * CONFLICT 
  
    * **Code:** 409

  * INTERNAL SERVER ERROR 
  
    * **Code:** 500 
 
### 5. Add Librarian
  Api to register a new librarian.

* #### Path

  /librarian

* #### Method

  `POST` 

* #### Data Parameters

  | Key | Type  | Required  |
  | :---:   | :-: | :-: |
  | name | String | Yes |
  | email | String | Yes |
  | password | String | Yes |
  | address | String | No |
  | salary | Long | No |
  | contact_number | Long | Yes |
  | dob | Date | No |

  `{
    "name":"test",
    "email":"test@getnada.com",
    "password":"test@123",
    "dob":"1997/10/03",
    "address":"test test",
    "salary":"25000",
    "contactNumber":"9343423244"
  }`


* #### Success Response
  
  * Librarian registered successfully.
    
    * **Code:** 200 <br />
  
    * **Content:** 
      | Key | Type  |  
      | :---:   | :-: | 
      | id | UUID | 
      | name | String | 
      | email | String |
      | dob | Date | 
      | address | String |
      | salary | Long |
      | contact_number | Long |
      
      `{
        "message": "Librarian registered successfully",
        "librarian": {
          "id":"d420c0b5-3d7e-4241-89f1-0e9985207593"
          "name":"test",
          "email":"test@getnada.com",
          "dob":"1997/10/03",
          "address":"test test",
          "salary":"25000",
          "contactNumber":"9343423244"
        }
      }`
 
* **Error Response:**

  * CONFLICT 
  
    * **Code:** 409

  * INTERNAL SERVER ERROR 
  
    * **Code:** 500 
 
### 6. Librarian Login
  Api to login a librarian.

* #### Path

  /librarian/login

* #### Method

  `POST` 

* #### Data Parameters

  | Key | Type  | Required  |
  | :---:   | :-: | :-: |
  | email | String | Yes |
  | password | String | Yes |

  `{
    "email":"test@getnada.com",
    "password":"test@123"
  }`


* #### Success Response
  
  * Librarian logged in successfully.
    
    * **Code:** 200 <br />
  
    * **Content:** 
      | Key | Type  |  
      | :---:   | :-: | 
      | id | UUID | 
      | name | String | 
      | email | String |
      | dob | Date | 
      | address | String |
      | salary | Long |
      | contact_number | Long |
      
      `{
        "message": "Librarian logged in successfully",
        "librarian": {
          "id":"d420c0b5-3d7e-4241-89f1-0e9985207593",
          "name":"test",
          "email":"test@getnada.com",
          "dob":"1997/10/03",
          "address":"test test",
          "salary":"25000",
          "contactNumber":"9343423244"
        }
      }`
 
* #### Error Response

  * UNAUTHORIZED 
  
    * **Code:** 401
    
### 7. Get Librarian
  Api to get a librarian.

* #### Path

  /librarian/{id}

* #### Method:

  `GET`

* #### Success Response
  
  * Librarian retrieved successfully.
    
    * **Code:** 200 <br />
  
    * **Content:** 
      | Key | Type  |  
      | :---:   | :-: | 
      | id | UUID | 
      | name | String | 
      | email | String |
      | dob | Date | 
      
      `{
        "message": "Librarian retrieved successfully",
        "librarian": {
          "id":"d420c0b5-3d7e-4241-89f1-0e9985207593",
          "name":"test",
          "email":"test@getnada.com",
          "dob":"1997/10/03",
          "address":"test test",
          "salary":"25000",
          "contactNumber":"9343423244"
        }
      }`
 
* #### Error Response

  * BAD REQUEST 
  
    * **Code:** 400  

  * FORBIDDEN 
  
    * **Code:** 403

  * NOT FOUND 
  
    * **Code:** 404

  * INTERNAL SERVER ERROR 
  
    * **Code:** 500 
  
### 8. Update Librarian
  Api to update an existing librarian.

* #### Path

  /librarian/{id}

* #### Method

  `PUT` 

* #### Data Parameters

  | Key | Type  | Required  |
  | :---:   | :-: | :-: |
  | name | String | Yes |
  | email | String | Yes |
  | password | String | Yes |
  | dob | Date | No |
  | address | String | No |
  | salary | Long | No |
  | contact_number | Long | Yes |

  `{
    "id":"d420c0b5-3d7e-4241-89f1-0e9985207593",
    "name":"test",
    "email":"test@getnada.com",
    "password":"test@123",
    "dob":"1997/10/03",
    "address":"test test",
    "salary":"25000",
    "contactNumber":"9343423244"
  }`


* #### Success Response
  
  * Librarian updated successfully.
    
    * **Code:** 200 <br />
  
    * **Content:** 
      | Key | Type  |  
      | :---:   | :-: | 
      | id | UUID | 
      | name | String | 
      | email | String |
      | dob | Date | 
      | address | String |
      | salary | Long |
      | contact_number | Long |
      
      `{
        "message": "Librarian updated successfully",
        "librarian": {
          "id":"d420c0b5-3d7e-4241-89f1-0e9985207593",
          "name":"test",
          "email":"test@getnada.com",
          "dob":"1997/10/03",
          "address":"test test",
          "salary":"25000",
          "contactNumber":"9343423244"
        }
      }`
 
* #### Error Response

  * FORBIDDEN 
  
    * **Code:** 403

  * NOT FOUND 
  
    * **Code:** 404
    
  * CONFLICT 
  
    * **Code:** 409

  * INTERNAL SERVER ERROR 
  
    * **Code:** 500 

### 9. Add Book
  Api to add a book.

* #### Path

  /book

* #### Method

  `POST` 

* #### Data Parameters

  | Key | Type  | Required  |
  | :---:   | :-: | :-: |
  | isbn | Long | Yes |
  | name | String | Yes |
  | author_name | String | Yes |
  | is_issued | boolean | Yes |
  | is_active | boolean | Yes |
  | librarian | Object | Yes |

  `{
    "isbn":"91173823143",
    "name":"java@test",
    "author_name":"springboot",
    "is_issued":"false",
    "is_active":"true",
    "librarian":{
        "id":"3e24f04e-d2ed-4ab6-8233-97989d4e4dfb"
    }
}`


* #### Success Response
  
  * Book added successfully.
    
    * **Code:** 200 <br />
  
    * **Content:** 
      | Key | Type  |  
      | :---:   | :-: | 
      | id | UUID | 
      | isbn | Long |
      | name | String |
      | author_name | String |
      | is_issued | boolean |
      | is_active | boolean |
      | librarian | Object |
      
      `{
        "message": "Book added successfully",
        "book": {
          "id":"3e24f04e-d2ed-4ab6-8133-97989d4e4dfb",
          "isbn":"91173823143",
          "name":"java@test",
          "author_name":"springboot",
          "is_issued":"false",
          "is_active":"true",
          "librarian":{
              "id":"3e24f04e-d2ed-4ab6-8233-97989d4e4dfb"
          }
        }
      }`
 
* **Error Response**

  * CONFLICT 
  
    * **Code:** 409

  * INTERNAL SERVER ERROR 
  
    * **Code:** 500 
    
### 10. Get Books
  Api to get books.

* #### Path

  /book

* #### Method

  `GET`
  
* #### URL Parameters
 
   | Key | Type  | Required  |
   | :---:   | :-: | :-: |
   | searchString | String | Yes |

* #### Success Response
  
  * Books retrieved successfully.
      
    * **Code:** 200 <br />
  
    * **Content:** 
      | Key | Type  |  
      | :---:   | :-: | 
      | id | UUID | 
      | isbn | Long |
      | name | String |
      | author_name | String |
      | is_issued | boolean |
      | is_active | boolean |
      | librarian | Object |
      
      `[{
        "id":"3e24f04e-d2ed-4ab6-8133-97989d4e4dfb",
        "isbn":"91173823143",
        "name":"java@test",
        "author_name":"springboot",
        "is_issued":"false",
        "is_active":"true",
        "librarian":{
            "id":"d420c0b5-3d7e-4241-89f1-0e9985207593"
            "name":"test",
            "email":"test@getnada.com",
            "dob":"1997/10/03",
            "address":"test test",
            "salary":"25000",
            "contactNumber":"9343423244"
         }
        }]`
 
* #### Error Response

  * INTERNAL SERVER ERROR 
  
    * **Code:** 500 
  
### 11. Update Book
  Api to update an existing book.

* #### Path

  /book/{id}

* #### Method

  `PUT` 

* #### Data Parameters

  | Key | Type  | Required  |
  | :---:   | :-: | :-: |
  | id | UUID | 
  | isbn | Long | Yes |
  | name | String | Yes |
  | author_name | String | Yes |
  | is_issued | boolean | Yes |
  | is_active | boolean | Yes |
  | librarian | Object | Yes |

  `{
      "id":"3e24f04e-d2ed-4ab6-8133-97989d4e4dfb",
      "isbn":"91173823143",
      "name":"java@test",
      "author_name":"springboot",
      "is_issued":"false",
      "is_active":"true",
      "librarian":{
          "id":"3e24f04e-d2ed-4ab6-8233-97989d4e4dfb"
    }`


* #### Success Response
  
  * Book updated successfully.
    
    * **Code:** 200 <br />
  
    * **Content:** 
      | Key | Type  |  
      | :---:   | :-: | 
      | id | UUID | 
      | isbn | Long |
      | name | String |
      | author_name | String |
      | is_issued | boolean |
      | is_active | boolean |
      | librarian | Object |
      
      `{
        "message": "Book updated successfully",
        "book": {
          "id":"3e24f04e-d2ed-4ab6-8133-97989d4e4dfb",
          "isbn":"91173823143",
          "name":"java@test",
          "author_name":"springboot",
          "is_issued":"false",
          "is_active":"true",
          "librarian":{
              "id":"3e24f04e-d2ed-4ab6-8233-97989d4e4dfb"
         }
       }
      }`
 
* #### Error Response

  * FORBIDDEN 
  
    * **Code:** 403

  * NOT FOUND 
  
    * **Code:** 404
    
  * CONFLICT 
  
    * **Code:** 409

  * INTERNAL SERVER ERROR 
  
    * **Code:** 500 
    
### 12. Delete Book
  Api to delete a book.

* #### Path

  /book/{id}

* #### Method

  `DELETE`

* #### Success Response
  
  * Book deleted successfully.
    
    * **Code:** 200
 
* #### Error Response

  * BAD REQUEST 
  
    * **Code:** 400  

  * FORBIDDEN 
  
    * **Code:** 403

  * NOT FOUND 
  
    * **Code:** 404

  * INTERNAL SERVER ERROR 
  
    * **Code:** 500 

### 13. Issue Book
  Api to issue a book.

* #### Path

  /issueBook

* #### Method

  `POST` 

* #### Data Parameters

  | Key | Type  | Required  |
  | :---:   | :-: | :-: |
  | user | Object | Yes |
  | book | Object | Yes |
  | issued_date | Date | Yes |
  | expected_return_date | Date | No |
  | actual_return_date | Date | No |

  `{
    "book":{
        "id":"c64e1a58-c913-4627-bf37-f14f064cf2c8"
    },
    "user":{
        "id":"d420c0b5-3d7e-4241-89f1-0e9985207593"
    },
    "issued_date":"2021-05-16T22:54:01.754Z"
   }`


* #### Success Response
  
  * Book issued successfully.
    
    * **Code:** 200 <br />
  
    * **Content:** 
      | Key | Type  |  
      | :---:   | :-: | 
      | id | UUID | 
      | user | Object |
      | book | Object |
      | issued_date | Date |
      | expected_return_date | Date |
      | actual_return_date | Date |
      
      `{
        "message": "Book issued successfully",
        "issuedBook": {
          "book":{
              "id":"c64e1a58-c913-4627-bf37-f14f064cf2c8"
          },
          "user":{
              "id":"d420c0b5-3d7e-4241-89f1-0e9985207593"
          },
          "issued_date":"2021-05-16T22:54:01.754Z",
          "expected_return_date":"2021-05-23T22:54:01.754Z",
          "actual_return_date":null
         }
       }`
 
* **Error Response**

  * CONFLICT 
  
    * **Code:** 409

  * INTERNAL SERVER ERROR 
  
    * **Code:** 500 
    
### 14. Get Issued Books
  Api to get issued books.

* #### Path

  /issueBook

* #### Method

  `GET`

* #### Success Response
  
  * Issued Books retrieved successfully.
      
    * **Code:** 200 <br />
  
    * **Content:** 
      | Key | Type  |  
      | :---:   | :-: | 
      | id | UUID | 
      | isbn | Long |
      | name | String |
      | author_name | String |
      | is_issued | boolean |
      | is_active | boolean |
      | librarian | Object |
      
      `[{
        "book":{
            "id":"c64e1a58-c913-4627-bf37-f14f064cf2c8"
        },
        "user":{
            "id":"d420c0b5-3d7e-4241-89f1-0e9985207593"
        },
        "issued_date":"2021-05-16T22:54:01.754Z",
        "expected_return_date":"2021-05-23T22:54:01.754Z",
        "actual_return_date":null
       }]`
 
* #### Error Response

  * INTERNAL SERVER ERROR 
  
    * **Code:** 500 
  
### 15. Update Issued Book
  Api to update an issued book.

* #### Path

  /issueBook/{id}

* #### Method

  `PUT` 

* #### Data Parameters

  | Key | Type  | Required  |
  | :---:   | :-: | :-: |
  | id | UUID | 
  | user | Object |
  | book | Object |
  | issued_date | Date |
  | expected_return_date | Date |
  | actual_return_date | Date |

  `{
      "id":"c64e1b58-c913-4628-bf37-f14f064cf2c8",
      "book":{
            "id":"c64e1a58-c913-4627-bf37-f14f064cf2c8"
        },
        "user":{
            "id":"d420c0b5-3d7e-4241-89f1-0e9985207593"
        },
        "issued_date":"2021-05-16T22:54:01.754Z",
        "expected_return_date":"2021-05-23T22:54:01.754Z",
        "actual_return_date":"2021-05-20T22:54:01.754Z"
    }`


* #### Success Response
  
  * Issued Book updated successfully.
    
    * **Code:** 200 <br />
  
    * **Content:** 
      | Key | Type  |  
      | :---:   | :-: | 
      | id | UUID | 
      | isbn | Long |
      | name | String |
      | author_name | String |
      | is_issued | boolean |
      | is_active | boolean |
      | librarian | Object |
      
      `{
        "message": "Issued Book updated successfully",
        "issuedBook": {
          "id":"c64e1b58-c913-4628-bf37-f14f064cf2c8",
          "book":{
                "id":"c64e1a58-c913-4627-bf37-f14f064cf2c8"
            },
            "user":{
                "id":"d420c0b5-3d7e-4241-89f1-0e9985207593"
            },
            "issued_date":"2021-05-16T22:54:01.754Z",
            "expected_return_date":"2021-05-23T22:54:01.754Z",
            "actual_return_date":"2021-05-20T22:54:01.754Z"
        }
      }`
 
* #### Error Response

  * FORBIDDEN 
  
    * **Code:** 403

  * NOT FOUND 
  
    * **Code:** 404
    
  * CONFLICT 
  
    * **Code:** 409

  * INTERNAL SERVER ERROR 
  
    * **Code:** 500 

## Building and Running

1. Clone this repo.
2. Open command prompt and navigate to project folder.
3. Run 'mvn spring-boot:run'.
4. By default, the tomcat server runs on port 8080; the default url is http://localhost:8080.

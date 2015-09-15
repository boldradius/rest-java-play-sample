
Sample java play project to go with the play-rest-workshop slides.

* In this exercise you will implement a simple RESTful API for providing access to user information.

* The User model is provided in User.java.  

* A __*GET*__ endpoint for retrieving all users is provided.  Go to __*http://localhost:9000/users*__ to see the list of all users.  Currently it’s empty.

Example exercises are provided below (duplicated from the workshop slides).

---

# __Exercise 1__

* Add a __*POST*__ endpoint for creating a new user with id and username specified in the request body in json format.

* Create 3 new users using __*curl*__ with ids 1-3.
	
	```curl -vX POST http://localhost:9000/users -d '{"name":"John", "id":1}' --header "Content-Type: application/json"```  
	
	What code should be returned in the response header on success?  

---

# __Exercise 1 cont’d__

* Check __*http://localhost:9000/users*__ again to see all the users you added.

* What happens when you try to POST with a non-integer user id or with the id missing entirely?  

	What code should be returned in the response header?  

---

# __Exercise 1 JUnit Tests__

* Add tests to ApplicationTest.java to test for
	* Successful user creation 
	* Bad parameter data

---

# __Exercise 2__

* Add a __*GET*__ endpoint for retrieving a user with a specific id.  

	*	Try manually checking that your endpoint works correctly using your web browser.
	*	What code should be returned in the response header on success?
	*	What code should be returned when the user does not exist?
	*	What code should be returned when you request a non-integer user id?


---

# __Exercise 2 JUnit Tests__

* Add tests to ApplicationTest.java to test for
	* Successful user retrieval
	* User not found

---

# __Exercise 3__

* Add a __*PUT*__ endpoint for updating a specific user. This time accept the id parameter in the path and the name parameter as form-encoded.
	
	Use __*curl*__ to update the name of any user.

	```curl -vX POST http://localhost:9000/users/1 -d "name=John" --header "Content-Type: application/json"``` 
	


---

# __Exercise 3 JUnit Tests__

* Add tests to ApplicationTest.java to test for
	* Successful user update
	* User not found

---

# __Exercise 4__

* Let’s say that DELETE requests are not allowed.  Try using curl to delete a user with a specific id.  What response do you get?

* Write a handler for the DELETE endpoint so that you return a 405 Method Not Allowed error code.  



---

# __Exercise 4 JUnit Tests__

* Add a test to ApplicationTest.java to make sure 405 is returned for DELETE requests.


---

# __Exercise 5: Api docs with Swagger__

* Add the following line to the libraryDependencies in build.sbt 

```"pl.matisoft" %% "swagger-play24" % "1.4"```

* Add annotations to your handlers in Application.java

__*http://docs.swagger.io/swagger-core/apidocs/index.html*__

---
# __Exercise 5 cont'd__



* Modify routes file to provide endpoints for documentation

```GET  /api-docs.json            @pl.matisoft.swagger.ApiHelpController.getResources```

```GET  /api-docs.json/users      @pl.matisoft.swagger.ApiHelpController.getResource(path = "/users") ```

* Now you can see your api documentation as json at 
__*http://localhost:9000/api-docs.json*__ 
__*http://localhost:9000/api-docs.json/users*__ 

---

# __Exercise 5 Swagger UI__

Add Swagger UI to get a human-friendly format for the json docs.

![inline](./images/swagger.jpg)

---

```git clone https://github.com/swagger-api/swagger-ui```

* Copy contents of dist folder to your public folder
* Copy __*/public/swagger/index.html*__ into your views folder and rename it __*swagger.scala.html*__
* swagger.scala.html needs to be customized
	* update all the static paths at the top of the file to prepend __*assets/swagger*__ to the path (see __*link*__ and __*script*__ tags)
	* search for __*new SwaggerUi*__ and change the url to "http://localhost:9000/api-docs.json" instead of the petstore example url 


---

# __Exercise 5:  Swagger UI__

* Add a __*/swagger*__ endpoint to your routes file and add the corresponding action to Application.java to render swagger.scala.html
* Go to http://localhost:9000/swagger to see your formatted documentation.
* Use your new Swagger documentation to test your api.  Create new users and query users.

---


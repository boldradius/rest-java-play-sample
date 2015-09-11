
Sample java play project to go with the play-rest-workshop slides.

* In this exercise you will implement a simple RESTful API for providing access to user information.

* The User model is provided in User.java.  

* A __*GET*__ endpoint for retrieving all users is provided.  Go to __*http://localhost:9000/users*__ to see the list of all users.  Currently it’s empty.

Example exercises are provided below (duplicated from the workshop slides).

---

# __Exercise 1__

* Add a __*POST*__ endpoint for creating a new user with id and username specified in the request body.

* Create 3 new users using __*curl*__ with ids 1-3.
	
	```curl -vX POST http://localhost:9000/users -d '{"name":"John", "id":1}' --header "Content-Type: application/json"```  
	
	What code should be returned in the response header on success?  

---

# __Exercise 1 cont’d__

* Check __*http://localhost:9000/users*__ again to see all the users you added.

* What happens when you try to POST with a non-integer user id or with the id missing entirely?  

	What code should be returned in the response header?  

---

# __Exercise 2__

* Add a __*GET*__ endpoint for retrieving a user with a specific id.  

	*	Try manually checking that your endpoint works correctly using your web browser.
	*	What code should be returned in the response header on success?
	*	What code should be returned when the user does not exist?
	*	What should when you request a non-integer user id?


---

# __Exercise 3__

* Add a __*PUT*__ endpoint for updating a specific user.  
	
	Use __*curl*__ to update the name of any user.

	```curl -vX POST http://localhost:9000/users/1 -d '{"name":"John"}' --header "Content-Type: application/json"``` 
	

---

# __Exercise 4__

* Let’s say that DELETE requests are not allowed.  Try using curl to delete a user with a specific id.  What response do you get?

* Write a handler for the DELETE endpoint so that you return a 405 Method Not Allowed error code.  


---



Sample java play project to go with the play-rest-workshop slides.

* In this exercise you will implement a simple RESTful API for providing access to user information.

* The User model is provided in User.java.  

* A __*GET*__ endpoint for retrieving all users is provided.  Go to __*http://localhost:9000/users*__ to see the list of all users.  Currently it’s empty.

Example exercises are provided below (duplicated from the workshop slides).

---

# __Exercise 1__

* Add a __*POST*__ endpoint for creating a new user with id and username specified in the request body.

* Create 10 new users using __*curl*__ with ids 1-10.  

* Check __*http://localhost:9000/users*__ again to see all the users you added.

---

# __Exercise 1 cont’d__


* What happens when you try to POST with a non-integer user id?  

	Update your endpoint so that a 400 (Bad Request) response code is returned in this case.  

---

# __Exercise 2__

* Add a __*GET*__ endpoint for retrieving a user with a specific id.  

	Try manually checking that your endpoint works correctly using your web browser.

---

# __Exercise 2 cont’d__
	
* What happens when you request a user id that does not exist?  

	Update your endpoint so that a 404 (Not Found) response code is returned when a user is not found.  

* What happens when you try to GET a non-integer user id?

---

# __Exercise 3__

* Add a __*PUT*__ endpoint for updating a specific user.  
	
	Use __*curl*__ to update the name of any user.

* What happens when you request a user id that does not exist?  

* What happens when you request a non-integer user id?  
	
* Are you duplicating code that checks for these cases in each handler?  Is there a better way?
	

---

# __Exercise 4__

* Let’s say that DELETE requests are not allowed.  Try using curl to delete a user with a specific id.  What response do you get?

* Write a handler for the DELETE endpoint so that you return a 405 Method Not Allowed error code.  


---




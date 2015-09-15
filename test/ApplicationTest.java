import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.User;
import org.junit.*;



import play.libs.Json;
import play.mvc.*;
import play.mvc.Result;
import play.test.FakeApplication;
import play.test.Helpers;
import play.test.WithApplication;
import play.twirl.api.Content;

import static play.test.Helpers.*;
import static org.junit.Assert.*;



/**
 *
 * Basic tests
 * (Incomplete!)
 *
 */
public class ApplicationTest extends WithApplication {

    @Override
    protected FakeApplication provideFakeApplication() {
        return fakeApplication();
    }

    @Test
    public void getAllUsersRequestShouldReturnOK() {
        Result result = route(fakeRequest(GET, "/users"));
        assertEquals(OK, result.status());
    }

    //-------------------- Exercise 1 --------------------

    @Test
    public void postRequestShouldCreateNewUser() {
        User john = new User("john",1L);
        Result result = route(fakeRequest(POST, "/users").bodyJson(Json.toJson(john)));
        assertEquals(CREATED, result.status());
        User returned = Json.fromJson(Json.parse(contentAsString(result)), User.class);
        assertEquals(returned.getId(), john.getId());
        assertEquals(returned.getName(), john.getName());
    }

    @Test
    public void postRequestShouldHandleMissingData() {
        String jsonStr = "{\"name\": \"john\"}";
        Result result = route(fakeRequest(POST, "/users").bodyJson(Json.parse(jsonStr)));
        assertEquals(BAD_REQUEST, result.status());
    }

    //-------------------- Exercise 2 --------------------

    @Test
    public void getRequestShouldReturnUserIfFound() {
        User john = new User("john",1L);
        Result result1 = route(fakeRequest(POST, "/users").bodyJson(Json.toJson(john)));
        assertEquals(CREATED, result1.status());

        Result result2 = route(fakeRequest(GET, "/users/1"));
        assertEquals(OK, result2.status());

        User returned = Json.fromJson(Json.parse(contentAsString(result2)), User.class);
        assertEquals(returned.getId(), john.getId());
        assertEquals(returned.getName(),john.getName());
    }

    @Test
    public void getRequestShouldHandleNotFound() {
        Result result = route(fakeRequest(GET, "/users/888"));
        assertEquals(NOT_FOUND, result.status());
    }

    //-------------------- Exercise 3 --------------------

//    @Test
//    public void putRequestShouldUpdateExistingUser() {
//    }
//
//
//    @Test
//    public void putRequestShouldHandleNotFound() {
//    }

    //-------------------- Exercise 4 --------------------

//    @Test
//    public void deleteRequestShouldReturnNotAllowed() {
//    }

}

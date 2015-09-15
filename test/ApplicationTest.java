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


    @Test
    public void getRequestShouldHandleNotFound() {
        Result result = route(fakeRequest(GET, "/users/888"));
        assertEquals(NOT_FOUND, result.status());
    }

    @Test
    public void postRequestShouldCreateNewUser() {
        User john = new User("john",1L);
        Result result = route(fakeRequest(POST, "/users").bodyJson(Json.toJson(john)));
        assertEquals(CREATED, result.status());
    }

    @Test
    public void postRequestShouldHandleMissingData() {
        String jsonStr = "{\"name\": \"john\"}";
        Result result = route(fakeRequest(POST, "/users").bodyJson(Json.parse(jsonStr)));
        assertEquals(BAD_REQUEST, result.status());
    }

    @Test
    public void putRequestShouldHandleNotFound() {
        String jsonStr = "{\"name\": \"john\"}";
        Result result = route(fakeRequest(PUT,"/users/888").bodyJson(Json.parse(jsonStr)));
        assertEquals(NOT_FOUND, result.status());
    }

    @Test
    public void deleteRequestShouldReturnNotAllowed() {
        Result result = route(fakeRequest(DELETE, "/users/888"));
        assertEquals(METHOD_NOT_ALLOWED, result.status());
    }

}

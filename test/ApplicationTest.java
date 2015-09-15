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

//    @Test
//    public void postRequestShouldCreateNewUser() {
//    }
//
//    @Test
//    public void postRequestShouldHandleMissingData() {
//    }

    //-------------------- Exercise 2 --------------------

//    @Test
//    public void getRequestShouldReturnUserIfFound() {
//    }
//
//    @Test
//    public void getRequestShouldHandleNotFound() {
//    }

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

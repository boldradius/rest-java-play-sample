import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import play.libs.Json;

import controllers.routes;
import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;


/**
 *
 * Basic tests
 * (Incomplete!)
 *
 */
public class ApplicationTest {


    @Test
    public void postRequestShouldCreateNewUser() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                String jsonStr = "{\"name\": \"john\", \"id\":0}";
                Result result = callAction(routes.ref.Application.createUser(), fakeRequest().withJsonBody(Json.parse(jsonStr)));
                        assertThat(status(result)).isEqualTo(CREATED);
            }
        });
    }

    @Test
    public void postRequestShouldHandleMissingData() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                String jsonStr = "{\"name\": \"john\"}";
                Result result = callAction(routes.ref.Application.createUser(), fakeRequest().withJsonBody(Json.parse(jsonStr)));
                assertThat(status(result)).isEqualTo(BAD_REQUEST);
            }
        });
    }

    @Test
    public void getRequestShouldHandleNotFound() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Result result = callAction(routes.ref.Application.getUser(888L));
                assertThat(status(result)).isEqualTo(NOT_FOUND);
            }
        });
    }


    @Test
    public void putRequestShouldHandleNotFound() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                String jsonStr = "{\"name\": \"john\"}";
                Result result = callAction(routes.ref.Application.updateUser(888L), fakeRequest().withJsonBody(Json.parse(jsonStr)));
                assertThat(status(result)).isEqualTo(NOT_FOUND);
            }
        });
    }

    @Test
    public void deleteRequestShouldReturnNotAllowed() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Result result = callAction(routes.ref.Application.deleteUser(888L));
                assertThat(status(result)).isEqualTo(METHOD_NOT_ALLOWED);
            }
        });
    }
}

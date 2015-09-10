package controllers;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import play.libs.Json;
import play.mvc.Http;

import java.util.HashMap;

public class Application extends Controller {

    static HashMap<Long, User> map = new HashMap<Long,User>();
    
    public static Result index() {
        return ok(views.html.index.render("Hello RESTful Exercise!"));
    }

    public static Result getUsers() {
        return ok(Json.toJson(map.values()));
    }

    //TODO Everything below this comment should be removed and left as an exercise for students.

    public static Result getUser(Long id) {
        User user = map.get(id);
        return user == null ? notFound() : ok(Json.toJson(user));
    }

    public static Result createUser() {
        User user = getUserFromRequest(request());
        if(user==null)
            return badRequest();

        map.put(user.getId(), user);
        return created(Json.toJson(user));
    }


    public static Result updateUser(Long id) {
        if(map.get(id)==null)
            return notFound();

        String name = getNameFromRequest(request());

        if(name==null)
            return badRequest();

        User user = new User(id, name);
        User updated = map.replace(id, user);
        return ok(Json.toJson(updated));
    }


    public static Result deleteUser(Long id) {
        return new Results.Status(play.core.j.JavaResults.MethodNotAllowed());
    }

    //Helper
    private static String getNameFromRequest(Http.Request r) {
        try {
            String name = r.body().asJson().get("name").asText();
            return name;
        } catch (NullPointerException e) {
            return null;
        }
    }

    //Helper
    private static User getUserFromRequest(Http.Request r) {
         try {
             User user = Json.fromJson(request().body().asJson(), User.class);
             return (user.getId()==null || user.getName()==null) ? null : user;
         } catch (Exception e) {
            return null;
         }
    }

}

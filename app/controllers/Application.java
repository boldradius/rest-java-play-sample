package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.libs.Json;

import java.util.HashMap;

public class Application extends Controller {

    static HashMap<Long, User> map = new HashMap<Long,User>();
    
    public static Result index() {
        return ok(views.html.index.render("Hello RESTful Exercise!"));
    }

    public static Result getUsers()
    {
        return ok(Json.toJson(map.values()));
    }

    public static Result getUser(Long id)
    {
        User user = map.get(id);
        return user == null ? notFound() : ok(Json.toJson(user));
    }

    public static Result createUser(Long id, String name)
    {
        User newUser = new User(id, name);  //TODO Json.fromJson(request().body().asJson(), User.class);
        map.put(id, newUser);
        return created(Json.toJson(map.get(newUser.getId())));
    }


    public static Result updateUser(Long id, String name)
    {
        User user = new User(id, name);
        User updated = map.replace(id, user);
        return ok(Json.toJson(updated));
    }


    public static Result deleteUser(Long id) {
        map.remove(id);
        return noContent();
    }
}

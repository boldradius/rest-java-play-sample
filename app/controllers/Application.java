package controllers;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.wordnik.swagger.annotations.*;
import models.User;
import play.libs.Json;
import play.*;
import play.mvc.*;

import views.html.*;
import java.util.HashMap;


@Api(value = "/users", description = "Operations with Users")
public class Application extends Controller {

    static HashMap<Long, User> map = new HashMap<Long,User>();
    
    public Result index() {
        return ok(index.render("Hello RESTful Exercise!"));
    }

    public Result swagger() {
        return ok(swagger.render());
    }

    @ApiOperation(
        nickname = "getUsers",
        value = "Get All Users",
        notes = "Returns List of All Users",
        response = User.class,
        httpMethod = "GET")
    public Result getUsers() {
        return ok(Json.toJson(map.values()));
    }

    @ApiOperation(
        nickname = "getUser",
        value = "Get a User",
        notes = "Returns a Single User",
        response = User.class,
        httpMethod = "GET")
    public Result getUser(Long id) {
        User user = map.get(id);
        return user == null ? notFound() : ok(Json.toJson(user));
    }

    @ApiOperation(
        nickname = "createUser",
        value = "Create New User",
        notes = "Create New User",
        httpMethod = "POST",
        response = User.class
    )
    @ApiImplicitParams({
        @ApiImplicitParam(
            name = "body",
            dataType = "User",
            required = true,
            paramType = "body",
            value = "user id and name"
        )
    })
    @ApiResponses(
        value = {
            @com.wordnik.swagger.annotations.ApiResponse(code = 400, message = "Json Processing Exception"),
            @com.wordnik.swagger.annotations.ApiResponse(code = 201, message = "Successfully created new user")

        }
    )
    public Result createUser() {
        User user = getUserFromRequest(request());
        if(user==null)
            return badRequest();

        map.put(user.getId(), user);
        return created(Json.toJson(user));
    }


    @ApiOperation(
        nickname = "Update User",
        value = "Update User Information",
        notes = "Update User Information",
        response = User.class,
        httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(
            name = "id",
            dataType = "User",
            required = true,
            paramType = "Long",
            value = "Id"
        ), @ApiImplicitParam(
            name = "name",
            dataType = "User",
            required = true,
            paramType = "String",
            value = "Name"
        )
    })
    @ApiResponses(
        value = {
            @com.wordnik.swagger.annotations.ApiResponse(code = 400, message = "Json Processing Exception"),
            @com.wordnik.swagger.annotations.ApiResponse(code = 200, message = "Successfully updated new user")

        }
    )
    public Result updateUser(Long id) {
        if(map.get(id)==null)
            return notFound();

        String name = getNameFromRequest(request());

        if(name==null)
            return badRequest();

        User user = new User(id, name);
        User updated = map.replace(id, user);
        return ok(Json.toJson(updated));
    }


    public Result deleteUser(Long id) {
        return new Results.Status(play.core.j.JavaResults.MethodNotAllowed());
    }

    //Helper
    private String getNameFromRequest(Http.Request r) {
        try {
            String name = r.body().asJson().get("name").asText();
            return name;
        } catch (NullPointerException e) {
            return null;
        }
    }

    //Helper
    private User getUserFromRequest(Http.Request r) {
         try {
             User user = Json.fromJson(request().body().asJson(), User.class);
             return (user.getId()==null || user.getName()==null) ? null : user;
         } catch (Exception e) {
            return null;
         }
    }

}

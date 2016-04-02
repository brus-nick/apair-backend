package views;

import models.User;
import play.mvc.Result;

import static play.libs.Json.toJson;
import static play.mvc.Http.Context.Implicit.session;
import static play.mvc.Results.ok;

/**
 * Created by Борис on 13.03.2016.
 */
public class IsAdmin {
    public static Result isAdmin(){
        return ok(toJson(""));
    }

    public User getUser(){
        return User.find.where().eq("user_id", session().get("session_id")).findUnique();
    }
}

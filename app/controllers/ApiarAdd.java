package controllers;

import models.Apiarys;
import models.User;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Security;

import static play.libs.Json.toJson;
import static play.mvc.Http.Context.Implicit.session;
import static play.mvc.Results.ok;

/**
 * Created by Борис on 13.02.2016.
 */
public class ApiarAdd {

    @Security.Authenticated(Auth.ActionAuthenticator.class)
    public Result add() {
        Form<Apiarys> appiarForm = Form.form(Apiarys.class).bindFromRequest();
        Apiarys apiarys = appiarForm.get();
        User user = getUser();
        apiarys.user = user;
        user.apiary.add(apiarys);
        user.update();
        return ok(toJson(apiarys));
    }

    @Security.Authenticated(Auth.ActionAuthenticator.class)
    public Result getAllApp() {
        return ok(toJson(Apiarys.find.where().eq("user.user_id", session().get("session_id")).findList()));
    }

    public User getUser() {
        return User.find.where().eq("user_id", session().get("session_id")).findUnique();
    }
}

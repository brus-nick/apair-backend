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
 * Created by Борис on 08.06.2016.
 */
public class Apiary {

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
    public Result delApiar()
    {
        Form<Apiarys> apiarForm = Form.form(Apiarys.class).bindFromRequest();
        Apiarys apiary = apiarForm.get();
        apiary = queryApiar(apiary);
        int test = queryAp(apiary);
        return test == 0 ? ok(toJson("Пасеки с таким номером не существует")) : ok(toJson("Пасека и все пренадлежащие ей улья удалены"));

    }

    @Security.Authenticated(Auth.ActionAuthenticator.class)
    public Result changeAp()
    {
        Form<Apiarys> appiarForm = Form.form(Apiarys.class).bindFromRequest();
        Apiarys apiary = appiarForm.get();
        apiary.update();
        return ok(toJson(apiary));
    }

    public Apiarys queryApiar(Apiarys apiary)
    {
        return Apiarys.find.where().eq("ap_id", apiary.ap_id).findUnique();
    }

    public int queryAp(Apiarys apiary)
    {
        apiary.delete();
        return 1;
    }

    @Security.Authenticated(Auth.ActionAuthenticator.class)
    public Result getAllApp() {
        return ok(toJson(Apiarys.find.where().eq("user_id", session().get("session_id")).findList()));
    }

    public User getUser() {
        return User.find.where().eq("user_id", session().get("session_id")).findUnique();
    }

}

package controllers;

import models.Apiarys;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Security;

import static play.libs.Json.toJson;
import static play.mvc.Results.ok;

/**
 * Created by Борис on 25.02.2016.
 */
public class ChangeAp {
    @Security.Authenticated(Auth.ActionAuthenticator.class)
    public Result changeAp()
    {
        Form<Apiarys> appiarForm = Form.form(Apiarys.class).bindFromRequest();
        Apiarys apiary = appiarForm.get();
        apiary.update();
        return ok(toJson(apiary));
    }
}

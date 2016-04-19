package controllers;

import models.Hives;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Security;

import static play.libs.Json.toJson;
import static play.mvc.Results.ok;

/**
 * Created by Борис on 19.04.2016.
 */
public class ChangeHive {
    @Security.Authenticated(Auth.ActionAuthenticator.class)
    public Result changeHive()
    {
        Form<Hives> hivesForm = Form.form(Hives.class).bindFromRequest();
        Hives hiveNew = hivesForm.get();
        Hives hive = queryHives(hiveNew);
        hive.phone = hiveNew.phone;
        hive.update();
        return ok(toJson(hive));
    }

    public Hives queryHives(Hives hiveNew)
    {
        return Hives.find.where().eq("hive_id", hiveNew.hive_id).findUnique();
    }
}

package controllers;

import com.avaje.ebean.Ebean;
import models.Apiarys;
import models.Hives;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Security;

import static play.libs.Json.toJson;
import static play.mvc.Results.ok;

/**
 * Created by Борис on 29.02.2016.
 */
public class DelHives {
    @Security.Authenticated(Auth.ActionAuthenticator.class)
    public Result delHive()
    {
        Form<Hives> hivesForm = Form.form(Hives.class).bindFromRequest();
        Hives hive = hivesForm.get();
        hive = queryHives(hive);
        if (hive == null)
        {
            return ok(toJson("Улья с таким номером не существует"));
        }
        else
        {
            hive.delete();
            return ok(toJson("Улей удален"));
        }
    }

    public Hives queryHives(Hives hive)
    {
        return Hives.find.where().eq("hive_id", hive.hive_id).findUnique();
    }
}

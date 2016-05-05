package controllers;

import models.Apiarys;
import models.Hives;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Security;

import static play.libs.Json.toJson;
import static play.mvc.Results.ok;

/**
 * Created by Борис on 26.02.2016.
 */
public class AddHives {
    @Security.Authenticated(Auth.ActionAuthenticator.class)
    public Result addHive() {
        Form<Apiarys> appiarForm = Form.form(Apiarys.class).bindFromRequest();
        Apiarys apiary = appiarForm.get();
        Form<Hives> hiveForm = Form.form(Hives.class).bindFromRequest();
        Hives hive = hiveForm.get();
        apiary = getApiar(apiary);
        hive.apiary = apiary;
        apiary.hive.add(hive);
        apiary.update();
        return ok(toJson(hive));
    }

    public Apiarys getApiar(Apiarys apiary) {
        return Apiarys.find.where().eq("ap_id", apiary.ap_id).findUnique();
    }
}

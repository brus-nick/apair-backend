package controllers;

import models.Apiarys;
import models.Hives;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Security;

import java.util.List;

import static play.libs.Json.toJson;
import static play.mvc.Results.ok;

/**
 * Created by Борис on 29.02.2016.
 */
public class Hive {

    @Security.Authenticated(Auth.ActionAuthenticator.class)
    public Result delHive()
    {
        Form<models.Hives> hivesForm = Form.form(models.Hives.class).bindFromRequest();
        Hives hive = hivesForm.get();
        hive = queryHive(hive);
        hive.delete();
        return ok(toJson("Улей удален"));
    }

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

    @Security.Authenticated(Auth.ActionAuthenticator.class)
    public Result changeHive()
    {
        Form<Hives> hivesForm = Form.form(Hives.class).bindFromRequest();
        Hives hiveNew = hivesForm.get();
        Hives hive = queryHive(hiveNew);
        hive.phone = hiveNew.phone;
        hive.update();
        return ok(toJson(hive));
    }

    @Security.Authenticated(Auth.ActionAuthenticator.class)
    public Result getHives()
    {
        Form<Apiarys> apiarForm = Form.form(Apiarys.class).bindFromRequest();
        Apiarys apiary = apiarForm.get();
        List<Hives> hives = queryHives(apiary);
        return ok(toJson(hives));
    }

    public List<Hives> queryHives(Apiarys apiary)
    {
        return Hives.find.where().eq("ap_id", apiary.ap_id).findList();
    }
    public Apiarys getApiar(Apiarys apiary) {
        return Apiarys.find.where().eq("ap_id", apiary.ap_id).findUnique();
    }
    public Hives queryHive(Hives hive)
    {
        return Hives.find.where().eq("hive_id", hive.hive_id).findUnique();
    }


}

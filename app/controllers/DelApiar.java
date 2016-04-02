package controllers;

import models.Apiarys;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Security;

import static play.libs.Json.toJson;
import static play.mvc.Results.ok;

/**
 * Created by Борис on 28.02.2016.
 */
public class DelApiar {
    @Security.Authenticated(Auth.ActionAuthenticator.class)
    public Result delApiar()
    {
        Form<Apiarys> apiarForm = Form.form(Apiarys.class).bindFromRequest();
        Apiarys apiary = apiarForm.get();
        apiary = queryApiar(apiary);
        int test = queryAp(apiary);
        return test == 0 ? ok(toJson("Пасеки с таким номером не существует")) : ok(toJson("Пасека и все пренадлежащие ей улья удалены"));

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
}

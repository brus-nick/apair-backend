package controllers;

import models.Apiarys;
import models.Hives;
import play.data.Form;
import play.mvc.Result;

import java.util.List;

import static play.libs.Json.toJson;
import static play.mvc.Results.ok;

/**
 * Created by Борис on 29.02.2016.
 */
public class GetHives {
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
}

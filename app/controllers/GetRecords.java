package controllers;

import models.Hives;
import models.Journal;
import play.data.Form;
import play.mvc.Result;

import java.util.List;

import static play.libs.Json.toJson;
import static play.mvc.Results.ok;

/**
 * Created by Борис on 05.05.2016.
 */
public class GetRecords {
    public Result getRecords()
    {
        Form<Hives> hivesForm = Form.form(Hives.class).bindFromRequest();
        Hives hive = hivesForm.get();
        hive = queryHives(hive);
        Journal log = new Journal();

        log.temp = 25;
        log.hum = 28;

        log.hive = hive;
        hive.journals.add(log);
        hive.update();
        List<Journal> journal = queryJournal(hive);
        return ok(toJson(journal));
    }

    public Hives queryHives(Hives hive){
        return Hives.find.where().eq("hive_id", hive.hive_id).findUnique();
    }

    public List<Journal> queryJournal(Hives hive)
    {
        return Journal.find.where().eq("hive_id", hive.hive_id).findList();
    }
}

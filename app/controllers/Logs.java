package controllers;

import models.Hives;
import models.Journal;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Security;

import java.util.List;

import static play.libs.Json.toJson;
import static play.mvc.Results.ok;

/**
 * Created by Борис on 08.06.2016.
 */
public class Logs {

    @Security.Authenticated(Auth.ActionAuthenticator.class)
    public Result addRecord(String temp, String hum) {

        return ok(temp, hum);
    }

    @Security.Authenticated(Auth.ActionAuthenticator.class)
    public Result getRecords()
    {
        Form<Hives> hivesForm = Form.form(Hives.class).bindFromRequest();
        Hives hive = hivesForm.get();
        List<Journal> journal = queryListJournal(hive);
        return ok(toJson(journal));
    }

    @Security.Authenticated(Auth.ActionAuthenticator.class)
    public Result delRecord()
    {
        Form<Journal> journalForm = Form.form(Journal.class).bindFromRequest();
        Journal journal = journalForm.get();
        journal = queryJournal(journal);
        journal.delete();
        return ok(toJson("Запись удалена"));
    }

    public Journal queryJournal(Journal journal)
    {
        return Journal.find.where().eq("journal_id", journal.journal_id).findUnique();
    }

    public List<Journal> queryListJournal(Hives hive)
    {
        return Journal.find.where().eq("hive_id", hive.hive_id).findList();
    }
}

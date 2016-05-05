package controllers;

import models.Journal;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Security;

import static play.libs.Json.toJson;
import static play.mvc.Results.ok;

/**
 * Created by Борис on 05.05.2016.
 */
public class DelRecord {
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
}


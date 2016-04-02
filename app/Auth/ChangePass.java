package Auth;

import models.Registr;
import play.data.Form;
import play.mvc.Result;

import static play.libs.Json.toJson;
import static play.mvc.Http.Context.Implicit.session;
import static play.mvc.Results.ok;

/**
 * Created by Борис on 18.03.2016.
 */
public class ChangePass {
    public Result changePass()
    {
        Form<Registr> registrForm = Form.form(Registr.class).bindFromRequest();
        Registr registr = registrForm.get();
        Registr reg = regQuery();
        reg.pass = registr.pass;
        reg.update();
        return ok(toJson(reg));
    }

    public Registr regQuery()
    {
        return Registr.find.where().eq("user.user_id", session().get("session_id")).findUnique();
    }
}

package Auth;

import models.Registr;
import play.mvc.Result;

import java.util.Map;

import static play.libs.Json.toJson;
import static play.mvc.Controller.request;
import static play.mvc.Controller.session;
import static play.mvc.Results.ok;

/**
 * Created by Борис on 15.02.2016.
 */
public class Authenticate {

    public Result Authentication() {

        final Map<String, String[]> values = request().body().asFormUrlEncoded();
        String Login = values.get("login")[0];
        String Pass = values.get("pass")[0];
        Registr regq = regQuery(Login, Pass);
        if (regq != null)
        {
            session("session_id", regq.user.user_id.toString());
            session().put("name", regq.user.name);
            session().put("surname", regq.user.surname);
            return ok(toJson("Здравствуйте " + regq.user.name + " " + regq.user.surname));
        }
        else
        {
            return ok(toJson("User not found"));
        }
    }

    public Registr regQuery(String Login, String Pass){
        return Registr.find.where().eq("login", Login).eq("pass", Pass).findUnique();
    }
}

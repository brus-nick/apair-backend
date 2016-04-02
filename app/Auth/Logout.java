package Auth;

import play.mvc.Result;

import static play.libs.Json.toJson;
import static play.mvc.Http.Context.Implicit.session;
import static play.mvc.Results.ok;

/**
 * Created by Борис on 14.03.2016.
 */
public class Logout {
    public Result logout()
    {
        session().clear();
        return ok(toJson("Пока"));
    }
}

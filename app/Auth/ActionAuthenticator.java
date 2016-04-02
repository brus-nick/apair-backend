package Auth;

import models.User;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

import static play.mvc.Http.Context.Implicit.session;

/**
 * Created by Борис on 19.02.2016.
 */
public class ActionAuthenticator extends Security.Authenticator {
    @Override
    public String getUsername(Http.Context ctx) {
        String token = session().get("session_id");
        if (token != null)
        {
            User user = User.find.where().eq("user_id", token).findUnique();
            if (user != null) {
                return user.email;
            }
        }
        return null;
    }

    @Override
    public Result onUnauthorized(Http.Context context) {
        return super.onUnauthorized(context);
    }

}
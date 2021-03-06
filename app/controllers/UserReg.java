package controllers;

import com.avaje.ebean.Ebean;
import models.Registr;
import models.User;
import play.data.Form;
import play.mvc.Result;

import java.util.List;

import static play.libs.Json.toJson;
import static play.mvc.Controller.session;
import static play.mvc.Results.ok;

/**
 * Created by Борис on 09.02.2016.
 */
public class UserReg {

    public Result reg() {

        Form<User> userForm = Form.form(User.class).bindFromRequest();
        User users = userForm.get();
        Form<Registr> registrForm = Form.form(Registr.class).bindFromRequest();
        Registr regs = registrForm.get();
        User inspectMail = getMail(users);
        Registr inspectLogin = getLogin(regs);
        if ((inspectMail == null) && (inspectLogin == null))
        {
            regs.user = users;
            users.registr = regs;
            Ebean.save(users);
            session("session_id", users.user_id.toString());
            session().put("name", users.name);
            session().put("surname", users.surname);
            return ok(toJson(users));
        }
        else if (inspectMail != null) {
            return ok(toJson("Пользователь с такой почтой уже существует"));
        }
        else if (inspectLogin != null){
            return ok(toJson("Пользователь с таким логином уже существует"));
        }
        return ok();
    }

    public User getMail(User users){
        return User.find.where().eq("email", users.email).findUnique();
    }

    public Registr getLogin(Registr regs){
        return Registr.find.where().eq("login", regs.login).findUnique();
    }

}

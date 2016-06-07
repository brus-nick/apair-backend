package controllers;

import play.mvc.Result;

import static play.mvc.Results.ok;

/**
 * Created by Борис on 29.02.2016.
 */
public class Hives {
    public Result log(String temp, String hum) {

        return ok(temp, hum);
    }


}

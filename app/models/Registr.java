package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Борис on 11.02.2016.
 */

@Entity
public class Registr extends Model {

    public static Finder<Long, Registr> find = new Finder<>(Registr.class);

    @Id
    public Long reg_id;

    public String login;

    public String pass;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    public User user;
}

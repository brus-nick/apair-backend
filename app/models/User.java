package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Борис on 09.02.2016.
 */

@Entity
public class User extends Model {

    public static Finder<Long, User> find = new Finder<>(User.class);

    @Id
    public Long user_id;

    public String name;

    public String surname;

    public String patronymic;

    public String phone;

    public String email;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    public Registr registr;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public List<Apiarys> apiary;
}
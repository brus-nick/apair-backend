package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Борис on 13.02.2016.
 */

@Entity
public class Apiarys extends Model{

    public static Finder<Long, Apiarys> find = new Finder<>(Apiarys.class);

    @Id
    public Long ap_id;

    public String min_temp;

    public String max_temp;

    public String min_hum;

    public String max_hum;

    public String gps;

    public Integer an_mas;

    public Integer an_temp;

    public Integer an_hum;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    @OneToMany(mappedBy = "apiary", cascade = CascadeType.ALL)
    public List<Hives> hive;
}

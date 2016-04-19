package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Борис on 26.02.2016.
 */

@Entity
public class Hives extends Model {

    public static Finder<Long, Hives> find = new Finder<>(Hives.class);

    @Id
    public Long hive_id;

    public Integer an_mas;

    public Integer an_temp;

    public Integer an_hum;

    public String coordinates;

    public String state; //(open/close)

    public String phone; //GSM

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ap_id")
    public Apiarys apiary;
}

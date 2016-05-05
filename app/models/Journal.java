package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Борис on 20.04.2016.
 */
@Entity
public class Journal extends Model{
    public static Finder<Long, Journal> find = new Finder<>(Journal.class);

    @Id
    public Long journal_id;

    public Integer mas;

    public Integer temp;

    public Integer hum;

    public String coordinates;

    public String state; //(open/close)

    public String date_time;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "hive_id")
    public Hives hive;
}

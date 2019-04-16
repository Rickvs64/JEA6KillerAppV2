package Domains;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class PlayedMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Setting up date format
    // We convert a Java date object to a String; the database stores the date as a VARCHAR
    // Note that we're deliberately using a format that - while technically a STRING - still allows easy filtering between dates
    // E.g. between 2019/04/01 and 2019/04/10 can be done via an alphabetical "BETWEEN" query without ever converting the strings to dates
    @Transient
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Transient
    private Date date_unparsed = new Date();

    private String date = dateFormat.format(date_unparsed);


    private Integer winningTeam;

    public PlayedMatch() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWinningTeam() {
        return winningTeam;
    }

    public void setWinningTeam(Integer winningTeam) {
        this.winningTeam = winningTeam;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

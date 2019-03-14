package Domains;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date date = new Date(2019,1,1);

    @Column()
    private Integer gamemode_id;

    private Integer winningTeam;

    public Match() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGamemode_id() {
        return gamemode_id;
    }

    public void setGamemode_id(Integer gamemode_id) {
        this.gamemode_id = gamemode_id;
    }

    public Integer getWinningTeam() {
        return winningTeam;
    }

    public void setWinningTeam(Integer winningTeam) {
        this.winningTeam = winningTeam;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

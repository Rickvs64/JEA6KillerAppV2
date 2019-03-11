package Domains;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date date;

    @Column()
    private Integer gamemode_id;

    private Integer winningTeam;

    @OneToOne
    private Gamemode gamemode;
}

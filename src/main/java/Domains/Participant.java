package Domains;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * A Participant is a player (Account) that participated in a match (PlayedMatch) using a specific Hero.
 * Participants are some of the most commonly persisted objects.
 */
@Entity
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer matchId;
    private Integer accountId;
    private Integer heroId;
    private Integer kills;
    private Integer deaths;
    private Integer assists;
    private Integer teamIndex;

    public Participant() {
    }

    public Participant(Integer matchId, Integer accountId, Integer heroId, Integer kills, Integer deaths, Integer assists, Integer teamIndex) {
        this.matchId = matchId;
        this.accountId = accountId;
        this.heroId = heroId;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.teamIndex = teamIndex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getHeroId() {
        return heroId;
    }

    public void setHeroId(Integer heroId) {
        this.heroId = heroId;
    }

    public Integer getKills() {
        return kills;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getAssists() {
        return assists;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    public Integer getTeamIndex() {
        return teamIndex;
    }

    public void setTeamIndex(Integer teamIndex) {
        this.teamIndex = teamIndex;
    }
}

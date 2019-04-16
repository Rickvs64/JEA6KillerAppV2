package Domains;

import javax.persistence.*;

@Entity
public class Gamemode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private Float respawnTime;

    public Gamemode() {

    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getRespawnTime() {
        return respawnTime;
    }

    public void setRespawnTime(Float respawnTime) {
        this.respawnTime = respawnTime;
    }

}

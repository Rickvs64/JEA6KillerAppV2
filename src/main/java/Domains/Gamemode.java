package Domains;

import javax.persistence.*;

/**
 * The Gamemode class is responsible holding important gameplay-related data such as respawn time.
 */
@Entity
public class Gamemode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private Float respawnTime = 10.0f;

    public Gamemode() {

    }

    public Gamemode(String name, String description, Float respawnTime) {
        this.name = name;
        this.description = description;
        this.respawnTime = respawnTime;
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

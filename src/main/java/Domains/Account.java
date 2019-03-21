package Domains;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(unique=true)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length=15)
    private EPlatform platform = EPlatform.PC;

    public Account() {

    }

    public Account(String name) {
        this.name = name;
    }

    public Account(String name, EPlatform platform) {
        this.name = name;
        this.platform = platform;
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

    public EPlatform getPlatform() {
        return platform;
    }

    public void setPlatform(EPlatform platform) {
        this.platform = platform;
    }
}

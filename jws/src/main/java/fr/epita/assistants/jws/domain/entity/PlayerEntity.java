package fr.epita.assistants.jws.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
public class PlayerEntity {
    private long id;

    private Integer lives;
    private String name;

    private Integer posX;
    private Integer posY;


    public PlayerEntity()
    {
        this.posY = 0;
        this.posX = 0;
        this.lives = 3;
    }
}

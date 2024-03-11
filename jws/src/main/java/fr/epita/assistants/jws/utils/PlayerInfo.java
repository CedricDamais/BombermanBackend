package fr.epita.assistants.jws.utils;

import fr.epita.assistants.jws.data.model.PlayerModel;
import fr.epita.assistants.jws.domain.entity.PlayerEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerInfo {
    private long id;

    private Integer lives;
    private String name;

    private Integer posX;
    private Integer posY;

    public PlayerInfo(PlayerEntity playerEntity)
    {
        this.name = playerEntity.getName();
        this.id = playerEntity.getId();
        this.posX = playerEntity.getPosX();
        this.posY = playerEntity.getPosY();
        this.lives = playerEntity.getLives();
    }

    public PlayerInfo(PlayerModel playerEntity)
    {
        this.name = playerEntity.getName();
        this.id = playerEntity.getId();
        this.posX = playerEntity.getPosX();
        this.posY = playerEntity.getPosY();
        this.lives = playerEntity.getLives();
    }
}

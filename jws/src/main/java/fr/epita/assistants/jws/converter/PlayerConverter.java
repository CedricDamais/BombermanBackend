package fr.epita.assistants.jws.converter;

import fr.epita.assistants.jws.data.model.PlayerModel;
import fr.epita.assistants.jws.domain.entity.PlayerEntity;
import fr.epita.assistants.jws.utils.PlayerInfo;

public class PlayerConverter {
    public static PlayerModel convertToModel(PlayerInfo playerEntity)
    {
        PlayerModel player = new PlayerModel();
        player.setName(playerEntity.getName());
        player.setPosX(playerEntity.getPosX());
        player.setPosY(playerEntity.getPosY());
        player.setLives(playerEntity.getLives());
        return player;
    }

    public static PlayerEntity convertToEntity(PlayerModel player)
    {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setName(player.getName());
        playerEntity.setPosX(player.getPosX());
        playerEntity.setPosY(player.getPosY());
        player.setLives(playerEntity.getLives());
        playerEntity.setId(player.getId());
        return playerEntity;
    }
}

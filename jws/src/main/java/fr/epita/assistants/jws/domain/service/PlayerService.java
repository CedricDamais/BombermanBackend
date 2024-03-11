package fr.epita.assistants.jws.domain.service;

import fr.epita.assistants.jws.converter.GameConverter;
import fr.epita.assistants.jws.converter.PlayerConverter;
import fr.epita.assistants.jws.data.repository.PlayerRepository;
import fr.epita.assistants.jws.domain.entity.GameEntity;
import fr.epita.assistants.jws.domain.entity.PlayerEntity;
import fr.epita.assistants.jws.utils.GameInfo;
import fr.epita.assistants.jws.utils.MovePlayerResult;
import fr.epita.assistants.jws.utils.PlayerInfo;
import fr.epita.assistants.jws.utils.PutBombResult;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PlayerService {
    @Inject
    PlayerRepository playerRepository;
    public PlayerEntity CreatePlayer(String playerName)
    {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setName(playerName);
        return playerEntity;
    }

    public MovePlayerResult MovePlayer(int posX, int posY, Long id, Long playerId)
    {
        MovePlayerResult result = playerRepository.MovePlayer(posX, posY, id, playerId);
        return result;
    }

    public PutBombResult PutBomb(int posX, int posY, Long id, Long playerId)
    {
        return playerRepository.PutBomb(posX, posY, id, playerId);
    }
}

package fr.epita.assistants.jws.converter;

import fr.epita.assistants.jws.data.model.GameModel;
import fr.epita.assistants.jws.data.model.PlayerModel;
import fr.epita.assistants.jws.data.repository.GameRepository;
import fr.epita.assistants.jws.domain.entity.GameEntity;
import fr.epita.assistants.jws.utils.GameInfo;
import fr.epita.assistants.jws.utils.GameState;
import fr.epita.assistants.jws.utils.PlayerInfo;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
public class GameConverter {

    public static GameModel convertToModel(GameInfo gameEntity, GameModel gameModel) {
        gameModel.setMap(gameEntity.getMap());
        gameModel.setStartTime(LocalDateTime.now());
        GameState state = gameEntity.getState();
        String sqlState = GetState(state);
        gameModel.setState(sqlState);
        return gameModel;
    }

    public static String GetState(GameState state) {
        switch (state) {
            case RUNNING -> {
                return "RUNNING";
            }
            case STARTING -> {
                return "STARTING";
            }
            case FINISHED -> {
                return "FINISHED";
            }
            default -> {
                return "UNKNOWN";
            }

        }
    }

    public static GameState GetEnumState(String state) {
        switch (state) {
            case "RUNNING" -> {
                return GameState.RUNNING;
            }
            case "STARTING" -> {
                return GameState.STARTING;
            }
            case "FINISHED" -> {
                return GameState.FINISHED;
            }
            default -> {
                return GameState.UNKNOWN;
            }

        }
    }

    public static GameEntity ConvertToEntity(GameModel game) {
        if (game == null)
        {
            return null;
        }
        GameEntity gameEntity = new GameEntity();
        gameEntity.setId(game.getId());
        gameEntity.setState(GetEnumState(game.getState()));
        gameEntity.setStartTime(game.getStartTime());
        gameEntity.setMap(game.getMap());
        if (game.getPlayers() == null)
        {
            return gameEntity;
        }
        for (PlayerModel player : game.getPlayers()) {
            if (gameEntity.getPlayers() == null) {
                gameEntity.setPlayers(new ArrayList<>());
            }
            gameEntity.getPlayers().add(PlayerConverter.convertToEntity(player));
        }
        return gameEntity;
    }
}

package fr.epita.assistants.jws.domain.service;


import fr.epita.assistants.jws.converter.GameConverter;
import fr.epita.assistants.jws.data.model.GameModel;
import fr.epita.assistants.jws.data.repository.GameRepository;
import fr.epita.assistants.jws.domain.entity.GameEntity;
import fr.epita.assistants.jws.domain.entity.PlayerEntity;
import fr.epita.assistants.jws.utils.CreatedGame;
import fr.epita.assistants.jws.utils.GameInfo;
import fr.epita.assistants.jws.utils.Games;
import fr.epita.assistants.jws.utils.PlayerInfo;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GameService {

    public GameService(){}

    @Inject
    PlayerService playerService;

    @Inject GameRepository gameRepository;
    @Inject
    GameMapService gameMapService;

    public CreatedGame createGame(String playerName)
    {
        GameEntity gameEntity = new GameEntity();

        PlayerEntity playerEntity = playerService.CreatePlayer(playerName);
        playerEntity.setPosX(1);
        playerEntity.setPosY(1);

        gameEntity.setMap(gameMapService.getMapPaths());
        gameEntity.getPlayers().add(playerEntity);

        GameInfo gameInfo = new GameInfo(gameEntity);
        gameRepository.AddGame(gameInfo);
        gameEntity.setId(gameInfo.getId());

        return new CreatedGame(gameEntity);
    }

    public List<Games> getAllGames()
    {
        List<GameModel> gameList = gameRepository.listAllGames();
        List<GameEntity> gameEntityList = new ArrayList<>();
        for(GameModel game : gameList)
        {
            gameEntityList.add(GameConverter.ConvertToEntity(game));
        }

        List<Games> games = new ArrayList<>();
        for(GameEntity gameEntity : gameEntityList)
        {
            Games game = new Games(gameEntity);
            games.add(game);
        }
        return games;
    }

    public GameInfo GetGameInfos(Long id)
    {
        GameModel game = gameRepository.GetGame(id);
        if (game == null)
        {
            return null;
        }
        GameEntity gameEntity = GameConverter.ConvertToEntity(game);
        gameEntity.setMap(gameMapService.getMapPaths());
        return new GameInfo(gameEntity);
    }

    public GameInfo JoinGame(Long id, String playerName, boolean isFull)
    {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setName(playerName);

        GameInfo info = GetGameInfos(id);
        if (info == null)
        {
            return null;
        }
        if (info.getPlayers() == null)
        {
            return info;
        }
        if(info.getPlayers().size() == 1)
        {
            playerEntity.setPosX(15);
            playerEntity.setPosY(1);
        }
        else if (info.getPlayers().size() == 2)
        {
            playerEntity.setPosY(13);
            playerEntity.setPosX(15);
        }
        else
        {
            playerEntity.setPosY(13);
            playerEntity.setPosX(1);
        }

        boolean isFull1 = false;
        GameEntity gameEntity = GameConverter.ConvertToEntity(gameRepository.JoinGame(id, new PlayerInfo(playerEntity), isFull1));

        isFull = isFull1;
        if (gameEntity == null)
        {
            return null;
        }
        if(gameEntity.getPlayers() == null)
        {
            return new GameInfo(gameEntity);
        }
        return new GameInfo(gameEntity);
    }

    public GameInfo StartGame(Long id)
    {
        GameEntity gameEntity = GameConverter.ConvertToEntity(gameRepository.StartGame(id));
        if (gameEntity == null)
        {
            return null;
        }
        return new GameInfo(gameEntity);
    }
}

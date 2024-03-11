package fr.epita.assistants.jws.data.repository;

import fr.epita.assistants.jws.converter.GameConverter;
import fr.epita.assistants.jws.converter.PlayerConverter;
import fr.epita.assistants.jws.data.model.GameModel;
import fr.epita.assistants.jws.data.model.PlayerModel;
import fr.epita.assistants.jws.utils.GameInfo;
import fr.epita.assistants.jws.utils.PlayerInfo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GameRepository implements PanacheRepository<GameModel> {

    public GameRepository(){}

    public List<GameModel> findRunning()
    {
        return list("state", "RUNNING");
    }

    public List<GameModel> findNotRunning()
    {
        return list("state", "NOT_RUNNING");
    }

    public List<GameModel> listAllGames()
    {
        return listAll();
    }

    @Transactional
    public GameModel JoinGame(Long id, PlayerInfo playerInfo, boolean isFull) {

        PlayerModel playerModel = PlayerConverter.convertToModel(playerInfo);
        GameModel gameModel = findById(id);

        if (gameModel == null)
        {
            return null;
        }

        List<String> map = new ArrayList<>(gameModel.getMap());

        if (gameModel.players.size() == 4)
        {
            gameModel.setPlayers(null);
            return gameModel;
        }

        if(gameModel.getState().equals("RUNNING") || gameModel.getState().equals("FINISHED"))
        {
            gameModel.setPlayers(null);
            return gameModel;
        }

        playerModel.setGame(gameModel);
        gameModel.getPlayers().add(playerModel);

        // Return the updated game model
        return gameModel;
    }

    @Transactional
    public void AddGame(GameInfo gameEntity)
    {
        GameModel gameModel = new GameModel();
        List<PlayerModel> lst = new ArrayList<>();
        for (PlayerInfo playerEntity : gameEntity.getPlayers()) {
            PlayerModel playerModel = PlayerConverter.convertToModel(playerEntity);
            playerModel.setGame(gameModel);
            lst.add(playerModel);
        }
        gameModel.setPlayers(lst);
        GameModel fromEntity = GameConverter.convertToModel(gameEntity, gameModel);
        persist(fromEntity);
        gameEntity.setId(fromEntity.getId());
    }

    public GameModel GetGame(Long id)
    {
        return findById(id);
    }

    @Transactional
    public GameModel StartGame(Long id){

        GameModel game = findById(id);
        if (game == null)
        {
            return null;
        }
        List<String> map = new ArrayList<>(game.getMap());
        if(game.getState().equals("FINISHED"))
        {
            return null;
        }
        if (game.players.size() == 1)
        {
            game.setState("FINISHED");
        }
        else {
            game.setState("RUNNING");
        }
        return game;
    }

}

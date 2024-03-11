package fr.epita.assistants.jws.presentation.rest.response;

import fr.epita.assistants.jws.domain.service.GameService;
import fr.epita.assistants.jws.domain.service.PlayerService;
import fr.epita.assistants.jws.utils.*;
import lombok.Getter;
@Getter
public class GameResponse {

    public GameResponse() {}

   public CreatedGame Create(String playerName, GameService gameService)
    {
        return gameService.createGame(playerName);
    }
    public GameInfo GetGameById(Long id, GameService gameService)
    {
        return gameService.GetGameInfos(id);
    }
    public GameInfo JoinGame(Long id, String name, GameService gameService, boolean isFull)
    {
        return gameService.JoinGame(id, name, isFull);
    }

    public GameInfo StartGame(Long id, GameService gameService)
    {
        return gameService.StartGame(id);
    }

    public PutBombResult PutBomb(int posX, int posY, Long gameId, Long playerId, PlayerService playerService)
    {
        return playerService.PutBomb(posX, posY, gameId, playerId);
    }

    public MovePlayerResult MovePlayer(int posX, int posY, Long gameId, Long playerId, PlayerService playerService)
    {
        return playerService.MovePlayer(posX, posY, gameId, playerId);
    }
}

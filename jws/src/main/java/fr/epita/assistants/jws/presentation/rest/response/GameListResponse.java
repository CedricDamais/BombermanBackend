package fr.epita.assistants.jws.presentation.rest.response;

import fr.epita.assistants.jws.domain.service.GameService;
import fr.epita.assistants.jws.utils.Games;
import lombok.Getter;

import java.util.List;

@Getter
public class GameListResponse {

    public GameListResponse() {}
    public List<Games> getGames(GameService gameService)
    {
        return gameService.getAllGames();
    }

}

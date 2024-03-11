package fr.epita.assistants.jws.presentation.rest;

import fr.epita.assistants.jws.domain.service.PlayerService;
import fr.epita.assistants.jws.errors.ErrorBad;
import fr.epita.assistants.jws.errors.ErrorNotFound;
import fr.epita.assistants.jws.presentation.rest.request.MovePlayerRequest;
import fr.epita.assistants.jws.presentation.rest.response.GameResponse;
import fr.epita.assistants.jws.utils.GameInfo;
import fr.epita.assistants.jws.utils.GameState;
import fr.epita.assistants.jws.utils.MovePlayerResult;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/games/{gamesId}/players/{playerId}/move")
@Produces(MediaType.APPLICATION_JSON)
public class MovePlayerEndpoint {

    @Inject
    PlayerService playerService;

    @POST
    public Response MovePlayerInfo(@PathParam("gamesId")String gamesId, @PathParam("playerId")String playerId, MovePlayerRequest movePlayerRequest)
    {
        if(gamesId == null || playerId == null || movePlayerRequest == null)
        {
            return ErrorBad.BadRequest();
        }
        GameResponse gameResponse = new GameResponse();

        int posX = movePlayerRequest.getPosX();
        int posY = movePlayerRequest.getPosY();

        Long gameId = Long.parseLong(gamesId);
        Long player_Id = Long.parseLong(playerId);

        MovePlayerResult game = gameResponse.MovePlayer(posX, posY, gameId, player_Id, playerService);
        return respond(game);
    }

    public Response respond(MovePlayerResult movePlayerResult)
    {
        GameInfo gameInfoCheck = new GameInfo(movePlayerResult.getGame());
        if(movePlayerResult.getErrorMessage().equals("NG"))
        {
            return ErrorNotFound.NotFound();
        }
        if (gameInfoCheck.getState() == null || !gameInfoCheck.getState().equals(GameState.RUNNING))
        {
            return ErrorBad.BadRequest();
        }
        if(movePlayerResult.getErrorMessage().equals("NO"))
        {
            GameInfo gameInfo = new GameInfo(movePlayerResult.getGame());
            return Response.ok(gameInfo).build();
        }
        else if(movePlayerResult.getErrorMessage().equals("NVT") || movePlayerResult.getErrorMessage().equals("DM"))
        {
            return ErrorBad.BadRequest();
        }
        else
        {
            return ErrorBad.BadRequest();
        }
    }
}

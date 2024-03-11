package fr.epita.assistants.jws.presentation.rest;

import fr.epita.assistants.jws.domain.service.PlayerService;
import fr.epita.assistants.jws.errors.ErrorBad;
import fr.epita.assistants.jws.errors.ErrorNotFound;
import fr.epita.assistants.jws.presentation.rest.request.PutBombRequest;
import fr.epita.assistants.jws.presentation.rest.response.GameResponse;
import fr.epita.assistants.jws.utils.GameInfo;
import fr.epita.assistants.jws.utils.GameState;
import fr.epita.assistants.jws.utils.PutBombResult;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/games/{gameId}/players/{playerId}/bomb")
@Produces(MediaType.APPLICATION_JSON)
public class PutBombEndpoint {

    @Inject
    PlayerService playerService;
    @POST
    public Response PutBombInfo(@PathParam("gameId")String gameId, @PathParam("playerId")String playerId, PutBombRequest putBombRequest)
    {
        if(putBombRequest == null || gameId == null || playerId == null)
        {
            return ErrorBad.BadRequest();
        }
        GameResponse response = new GameResponse();
        Long game_id = Long.parseLong(gameId);
        Long player_id = Long.parseLong(playerId);
        PutBombResult putBombResult = response.PutBomb(putBombRequest.posX, putBombRequest.posY, game_id, player_id, playerService);
        return BombResponse(putBombResult);

    }

    public Response BombResponse(PutBombResult putBombResult)
    {
        GameInfo info = new GameInfo(putBombResult.getGame());
        if (putBombResult.getErrorMessage().equals("PNF"))
        {
            return ErrorNotFound.NotFound();
        }
        if (putBombResult.getErrorMessage().equals("NO"))
        {
            return Response.ok(new GameInfo(putBombResult.getGame())).build();
        }
        if (info.getState() == null || !info.getState().equals(GameState.RUNNING))
        {
            return ErrorBad.BadRequest();
        }
        else if(putBombResult.getErrorMessage().equals("WG"))
        {
            return ErrorBad.BadRequest();
        }
        else if(putBombResult.getErrorMessage().equals("WP"))
        {
            return ErrorBad.BadRequest();
        }
        else
        {
            return ErrorNotFound.NotFound();
        }
    }
}

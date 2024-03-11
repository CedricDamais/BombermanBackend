package fr.epita.assistants.jws.presentation.rest;

import fr.epita.assistants.jws.domain.service.GameService;
import fr.epita.assistants.jws.errors.ErrorBad;
import fr.epita.assistants.jws.errors.ErrorNotFound;
import fr.epita.assistants.jws.presentation.rest.request.JoinGameRequest;
import fr.epita.assistants.jws.presentation.rest.response.GameResponse;
import fr.epita.assistants.jws.utils.GameInfo;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/games/{gameId}")
@Produces(MediaType.APPLICATION_JSON)
public class GameIdEndpoint {
    @Inject
    GameService gameService;

    @GET
    public Response GetGameInfo(@PathParam("gameId") String gameId) {
        GameResponse response = new GameResponse();
        Long id = Long.parseLong(gameId);
        GameInfo game = response.GetGameById(id, gameService);
        if (game == null)
        {
            return ErrorNotFound.NotFound();
        }
        return Response.ok(game).build();
    }

    @POST
    public Response JoinGameInfo(@PathParam("gameId") String gameId, JoinGameRequest request)
    {
        if (request == null || request.getName() == null)
        {
            return ErrorBad.BadRequest();
        }

        GameResponse response = new GameResponse();
        Long id = Long.parseLong(gameId);

        boolean isFull = false;
        GameInfo game = response.JoinGame(id, request.getName(), gameService, isFull);

        if (game == null)
        {
            return ErrorNotFound.NotFound();
        }

        if(game.getPlayers() == null)
        {
            return ErrorBad.BadRequest();
        }
        return Response.ok(game).build();
    }
}

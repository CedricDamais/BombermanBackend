package fr.epita.assistants.jws.presentation.rest;

import fr.epita.assistants.jws.domain.service.GameService;
import fr.epita.assistants.jws.errors.ErrorNotFound;
import fr.epita.assistants.jws.presentation.rest.response.GameResponse;
import fr.epita.assistants.jws.utils.GameInfo;

import javax.inject.Inject;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/games/{gameId}/start")
@Produces(MediaType.APPLICATION_JSON)
public class StartGameEndpoint {

    @Inject
    GameService gameService;

    @PATCH
    public Response StartGameInfo(@PathParam("gameId") String gameId)
    {
        GameResponse response = new GameResponse();
        Long id = Long.parseLong(gameId);
        GameInfo gameInfo = response.StartGame(id, gameService);
        if (gameInfo == null)
        {
            return ErrorNotFound.NotFound();
        }
        return Response.ok(gameInfo).build();
    }
}

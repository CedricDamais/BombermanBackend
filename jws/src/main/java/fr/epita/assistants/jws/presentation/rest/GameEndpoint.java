package fr.epita.assistants.jws.presentation.rest;

import fr.epita.assistants.jws.domain.service.GameService;
import fr.epita.assistants.jws.domain.service.PlayerService;
import fr.epita.assistants.jws.presentation.rest.request.CreateGameRequest;
import fr.epita.assistants.jws.presentation.rest.response.GameListResponse;
import fr.epita.assistants.jws.presentation.rest.response.GameResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/games")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class GameEndpoint {

    @Inject
    GameService gameService;

    @GET
    public Response statsGames() {
        GameListResponse response = new GameListResponse();
        return Response.ok(response.getGames(gameService)).build();
    }

    @POST
    public Response StartGame(CreateGameRequest request) {
        if(request == null || request.getName() == null)
        {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        GameResponse response = new GameResponse();
        String name = request.getName();
        return Response.ok(response.Create(name, gameService)).build();
    }
}

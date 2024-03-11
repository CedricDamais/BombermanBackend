package fr.epita.assistants.jws.errors;

import javax.ws.rs.core.Response;

public class ErrorInvalidBomb {

    public ErrorInvalidBomb(){}

    public static Response InvalidBomb(){
        return Response.status(Response.Status.TOO_MANY_REQUESTS).build();
    }
}

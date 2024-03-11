package fr.epita.assistants.jws.errors;

import javax.ws.rs.core.Response;

public class ErrorNotFound {

    public ErrorNotFound(){}

    public static Response NotFound()
    {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}

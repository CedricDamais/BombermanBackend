package fr.epita.assistants.jws.errors;

import javax.ws.rs.core.Response;

public class ErrorBad {
    public ErrorBad() {}

    public static Response BadRequest()
    {
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}

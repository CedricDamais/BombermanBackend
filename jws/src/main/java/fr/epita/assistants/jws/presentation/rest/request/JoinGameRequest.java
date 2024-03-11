package fr.epita.assistants.jws.presentation.rest.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinGameRequest {
    private String name;
    public JoinGameRequest(){}

    public JoinGameRequest(String name){this.name = name;}
}

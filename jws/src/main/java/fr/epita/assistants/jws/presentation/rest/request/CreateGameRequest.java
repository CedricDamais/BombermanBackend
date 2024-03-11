package fr.epita.assistants.jws.presentation.rest.request;

import lombok.Getter;

@Getter
public class CreateGameRequest {
    private String name;
    public CreateGameRequest(){}

    public CreateGameRequest(String name){
        this.name = name;
    }
}

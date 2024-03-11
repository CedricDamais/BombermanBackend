package fr.epita.assistants.jws.presentation.rest.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovePlayerRequest {
    private int posX;
    private int posY;
    public MovePlayerRequest(){}

    public MovePlayerRequest(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
    }
}

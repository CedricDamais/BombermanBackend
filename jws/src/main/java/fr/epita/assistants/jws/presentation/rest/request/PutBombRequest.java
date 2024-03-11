package fr.epita.assistants.jws.presentation.rest.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PutBombRequest {
    public int posX;
    public int posY;
    public PutBombRequest(){}

    public PutBombRequest(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
    }
}

package fr.epita.assistants.jws.utils;

import fr.epita.assistants.jws.data.model.GameModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PutBombResult
{
    private GameModel game;
    private String errorMessage;
}

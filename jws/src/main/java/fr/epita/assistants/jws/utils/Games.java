package fr.epita.assistants.jws.utils;

import fr.epita.assistants.jws.domain.entity.GameEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Games {
    private Long id;
    private int players;
    private GameState state;

    public Games(GameEntity game)
    {
        this.id = game.getId();
        this.players = game.getPlayers().size();
        this.state = game.getState();
    }
}

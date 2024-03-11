package fr.epita.assistants.jws.domain.entity;

import fr.epita.assistants.jws.utils.GameState;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameInfoEntity extends PanacheEntityBase {
    private Long id;
    private GameState state;
    private int players;

    public GameInfoEntity(GameEntity game)
    {
        this.id = game.getId();
        this.players = game.getPlayers().size();
        this.state = game.getState();
    }
}

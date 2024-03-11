package fr.epita.assistants.jws.domain.entity;

import fr.epita.assistants.jws.utils.GameState;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class GameEntity extends PanacheEntityBase {
    private GameState state;
    private LocalDateTime startTime;
    private List<PlayerEntity> players;
    protected List<String> map;
    private long id;

    public GameEntity()
    {
        this.state = GameState.STARTING;
        this.startTime = LocalDateTime.now();
        this.players = new ArrayList<>();
    }

}

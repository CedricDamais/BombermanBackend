package fr.epita.assistants.jws.utils;

import fr.epita.assistants.jws.domain.entity.GameEntity;
import fr.epita.assistants.jws.domain.entity.PlayerEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CreatedGame {
    private LocalDateTime startTime;
    private GameState state;
    private List<PlayerEntity> players;
    protected List<String> map;
    private long id;

    public CreatedGame(GameEntity entity)
    {
        this.startTime = entity.getStartTime();
        this.state = entity.getState();
        this.players = entity.getPlayers();
        this.map = entity.getMap();
        this.id = entity.getId();
    }

}

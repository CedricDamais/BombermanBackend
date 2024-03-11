package fr.epita.assistants.jws.utils;

import fr.epita.assistants.jws.converter.GameConverter;
import fr.epita.assistants.jws.data.model.GameModel;
import fr.epita.assistants.jws.data.model.PlayerModel;
import fr.epita.assistants.jws.domain.entity.GameEntity;
import fr.epita.assistants.jws.domain.entity.PlayerEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GameInfo {
    private LocalDateTime startTime;
    private GameState state;
    private List<PlayerInfo> players;
    protected List<String> map;
    private long id;

    public GameInfo(GameEntity entity) {
        this.startTime = entity.getStartTime();
        this.state = entity.getState();
        for (PlayerEntity playerEntity : entity.getPlayers()) {
            if (this.players == null) {
                this.players = new ArrayList<>();
            }
            PlayerInfo playerInfo = new PlayerInfo(playerEntity);
            this.players.add(playerInfo);
        }
        this.map = entity.getMap();
        this.id = entity.getId();
    }

    public GameInfo(GameModel entity) {
        if (entity != null) {
            this.startTime = entity.getStartTime();
            this.state = GameConverter.GetEnumState(entity.getState());
            for (PlayerModel playerEntity : entity.getPlayers()) {
                if (this.players == null) {
                    this.players = new ArrayList<>();
                }
                PlayerInfo playerInfo = new PlayerInfo(playerEntity);
                this.players.add(playerInfo);
            }
            this.map = entity.getMap();
            this.id = entity.getId();
        }
    }
}

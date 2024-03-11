package fr.epita.assistants.jws.data.model;

import fr.epita.assistants.jws.utils.GameInfo;
import fr.epita.assistants.jws.utils.GameState;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class GameModel
{
    @Column(length = 255) private String state;
    @Column private LocalDateTime startTime;

    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<PlayerModel> players;

    @ElementCollection @CollectionTable(name= "game_map", joinColumns = @JoinColumn(name="id"))
    private List<String> map;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    public GameModel(){}

    @Transient
    public void addPlayer(PlayerModel playerModel)
    {
        if(this.players == null)
        {
            this.players = new ArrayList<>();
        }
        playerModel.setGame(this);
        this.players.add(playerModel);
    }
}

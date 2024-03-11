package fr.epita.assistants.jws.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
public class PlayerModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(name="lastbomb") private LocalDateTime lastBomb;
    @Column(name="lastmovement") private LocalDateTime lastmovement;

    private Integer lives;
    private String name;

    @Column(name= "posX") private Integer posX;
    @Column(name= "posY") private Integer posY;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private GameModel game;


}

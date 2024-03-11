package fr.epita.assistants.jws.data.repository;

import fr.epita.assistants.jws.converter.PlayerConverter;
import fr.epita.assistants.jws.data.model.PlayerModel;
import fr.epita.assistants.jws.utils.*;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class PlayerRepository implements PanacheRepository<PlayerModel> {

    public PlayerRepository() {
    }

    public List<PlayerModel> findByGameId(Long gameId) {
        return list("game_id", gameId);
    }

    public PlayerModel findPlayer(String name) {
        return find("name", name).firstResult();
    }

    public List<PlayerModel> findAllPlayers() {
        return listAll();
    }

    @Transactional
    public MovePlayerResult MovePlayer(int newPosX, int newPosY, Long gameId, Long playerId) {
        MovePlayerResult movePlayerResult = new MovePlayerResult();
        PlayerModel wantedplayer = find("id = ?1 and game.id = ?2", playerId, gameId).firstResult();

        if (wantedplayer == null) {
            movePlayerResult.setErrorMessage("NG");
            return movePlayerResult;
        }

        String state = wantedplayer.getGame().getState();

        if (!isValidMove(wantedplayer.getPosX(), wantedplayer.getPosY(), newPosX, newPosY) || !state.equals("RUNNING")) {
            movePlayerResult.setErrorMessage("DM");
            return movePlayerResult;
        }

        List<String> map = wantedplayer.getGame().getMap();

        char[] newrow = DecodeMap.decodeLine(map.get(newPosY)).toCharArray();

        if (newrow[newPosX] != 'G') {
            movePlayerResult.setGame(wantedplayer.getGame());
            movePlayerResult.setErrorMessage("NVT");
            return movePlayerResult;
        }

        wantedplayer.setPosX(newPosX);
        wantedplayer.setPosY(newPosY);

        wantedplayer.setLastmovement(LocalDateTime.now());

        movePlayerResult.setGame(wantedplayer.getGame());
        movePlayerResult.setErrorMessage("NO");
        return movePlayerResult;
    }

    public boolean isValidMove(int currentPosX, int currentPosY, int newPosX, int newPosY) {
        // Calculate the differences in X and Y positions
        int diffX = Math.abs(newPosX - currentPosX);
        int diffY = Math.abs(newPosY - currentPosY);

        // Check for adjacent horizontal or vertical move (not diagonal)
        boolean isHorizontalMove = diffX == 1 && diffY == 0;
        boolean isVerticalMove = diffX == 0 && diffY == 1;

        // The move is valid if it's either adjacent horizontally or vertically
        return isHorizontalMove || isVerticalMove;
    }

    @Transactional
    public void AddPlayer(PlayerInfo playerEntity) {
        PlayerModel player = PlayerConverter.convertToModel(playerEntity);
        persist(player);
        playerEntity.setId(player.getId());
    }

    @Transactional
    public PutBombResult PutBomb(int posX, int posY, Long gameId, Long playerId) {
        PutBombResult putBombResult = new PutBombResult();

        PlayerModel wantedplayer = find("id = ?1 and game.id = ?2", playerId, gameId).firstResult();

        if (wantedplayer == null) {
            putBombResult.setErrorMessage("PNF");
            return putBombResult;
        }

        String state = wantedplayer.getGame().getState();

        if (!state.equals("RUNNING")) {
            putBombResult.setErrorMessage("WG");
            return putBombResult;
        }

        if (posX != wantedplayer.getPosX() || posY != wantedplayer.getPosY()) {
            putBombResult.setErrorMessage("WP");
            return putBombResult;
        }

        List<String> map = wantedplayer.getGame().getMap();

        String updatedLine = updateLineWithBomb(DecodeMap.decodeLine(map.get(posY)), posX);
        map.set(posY, updatedLine);
        putBombResult.setErrorMessage("NO");
        putBombResult.setGame(wantedplayer.getGame());


        return putBombResult;

    }

    private String updateLineWithBomb(String line, int posX) {
        char[] chars = line.toCharArray();
        if (posX < chars.length) {
            chars[posX] = 'B'; // Place the bomb
        }
        return EncodeMap.encodeLine(new String(chars));
    }

}

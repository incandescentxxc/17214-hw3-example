package edu.cmu.cs214.hw3.storage;

import java.util.HashMap;
import java.util.Map;

import edu.cmu.cs214.hw3.model.game.Game;

public final class GameStorage {
    private static Map<String, Game> games;

    private static GameStorage instance;

    private GameStorage() {
        games = new HashMap<>();
    }

    public static synchronized GameStorage getInstance() {
        if (instance == null) {
            instance = new GameStorage();
        }
        return instance;
    }
    
    public Map<String, Game> getGames() {
        return games;
    }

    public void setGame(Game game) {
        games.put(game.getId(), game);
    }


}

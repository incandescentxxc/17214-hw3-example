package edu.cmu.cs214.hw3.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import edu.cmu.cs214.hw3.controller.dto.GameInitBody;
import edu.cmu.cs214.hw3.controller.dto.GameOperations;
import edu.cmu.cs214.hw3.exception.InvalidGameException;
import edu.cmu.cs214.hw3.exception.InvalidParaException;
import edu.cmu.cs214.hw3.model.game.Game;
import edu.cmu.cs214.hw3.storage.GameStorage;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GameService {
    public Game createGame(GameInitBody body) {
        Game game = new Game(body);
        game.setId(UUID.randomUUID().toString());
        GameStorage.getInstance().setGame(game);
        return game;
    }

    public Game selectWorker(GameOperations operations) throws InvalidGameException, InvalidParaException {
        if (!GameStorage.getInstance().getGames().containsKey(operations.getGameId())) {
            throw new InvalidGameException("Game not found.");
        }
        Game game = GameStorage.getInstance().getGames().get(operations.getGameId());
        if (!game.select(operations.getWorkerId(), operations.getPlayerId(), operations.getPositionX(),
                operations.getPositionY())) {
            throw new InvalidParaException("Input parameter invalid.");
        }
        return game;
    }

    public Game unSelectWorker(GameOperations operations) throws InvalidGameException, InvalidParaException {
        if (!GameStorage.getInstance().getGames().containsKey(operations.getGameId())) {
            throw new InvalidGameException("Game not found.");
        }
        Game game = GameStorage.getInstance().getGames().get(operations.getGameId());
        if (!game.unSelect(operations.getPlayerId(), operations.getPositionX(), operations.getPositionY())) {
            throw new InvalidParaException("Input parameter invalid.");
        }
        return game;
    }

    public Game placeWorker(GameOperations operations) throws InvalidGameException, InvalidParaException {
        if (!GameStorage.getInstance().getGames().containsKey(operations.getGameId())) {
            throw new InvalidGameException("Game not found.");
        }
        Game game = GameStorage.getInstance().getGames().get(operations.getGameId());

        if (!game.placeWorker(operations.getPlayerId(), operations.getPositionX(), operations.getPositionY())) {
            throw new InvalidParaException("Input parameter invalid.");
        }
        return game;
    }

    public Game moveWorker(GameOperations operations) throws InvalidGameException, InvalidParaException {
        if (!GameStorage.getInstance().getGames().containsKey(operations.getGameId())) {
            throw new InvalidGameException("Game not found.");
        }
        Game game = GameStorage.getInstance().getGames().get(operations.getGameId());

        if (!game.move(operations.getPlayerId(), operations.getPositionX(), operations.getPositionY())) {
            throw new InvalidParaException("Input parameter invalid.");
        }
        return game;
    }

    public Game buildWorker(GameOperations operations) throws InvalidGameException, InvalidParaException {
        if (!GameStorage.getInstance().getGames().containsKey(operations.getGameId())) {
            throw new InvalidGameException("Game not found.");
        }
        Game game = GameStorage.getInstance().getGames().get(operations.getGameId());

        if (!game.build(operations.getPlayerId(), operations.getPositionX(), operations.getPositionY(),
                operations.getBuildDome())) {
            throw new InvalidParaException("Input parameter invalid.");
        }
        return game;
    }

    public Game skipWorker(GameOperations operations) throws InvalidGameException, InvalidParaException {
        if (!GameStorage.getInstance().getGames().containsKey(operations.getGameId())) {
            throw new InvalidGameException("Game not found.");
        }
        Game game = GameStorage.getInstance().getGames().get(operations.getGameId());

        if (!game.skip(operations.getPlayerId(), operations.getPositionX(), operations.getPositionY())) {
            throw new InvalidParaException("Input parameter invalid.");
        }
        return game;
    }

}

package edu.cmu.cs214.hw3.service;

import java.util.UUID;

import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.server.annotation.ExceptionHandler;
import com.linecorp.armeria.server.annotation.Get;
import com.linecorp.armeria.server.annotation.Param;
import com.linecorp.armeria.server.annotation.Post;

import edu.cmu.cs214.hw3.dto.GameInitBody;
import edu.cmu.cs214.hw3.dto.GameOperations;
import edu.cmu.cs214.hw3.exception.BadRequestExceptionHandler;
import edu.cmu.cs214.hw3.exception.InvalidGameException;
import edu.cmu.cs214.hw3.exception.InvalidParaException;
import edu.cmu.cs214.hw3.model.game.Game;
import edu.cmu.cs214.hw3.storage.GameStorage;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GameService {

    @Post("/start")
    public HttpResponse createGame(GameInitBody body) {
        Game game = new Game(body);
        game.setId(UUID.randomUUID().toString());
        GameStorage.getInstance().setGame(game);
        return HttpResponse.ofJson(game);
    }

    @Post("/select")
    public HttpResponse selectWorker(GameOperations operations) throws InvalidGameException, InvalidParaException {
        if (!GameStorage.getInstance().getGames().containsKey(operations.getGameId())) {
            throw new InvalidGameException("Game not found.");
        }
        Game game = GameStorage.getInstance().getGames().get(operations.getGameId());
        if (!game.select(operations.getWorkerId(), operations.getPlayerId(), operations.getPositionX(),
                operations.getPositionY())) {
            throw new InvalidParaException("Input parameter invalid.");
        }
        return HttpResponse.ofJson(game);
    }

    @Post("/unselect")
    public HttpResponse unSelectWorker(GameOperations operations) throws InvalidGameException, InvalidParaException {
        if (!GameStorage.getInstance().getGames().containsKey(operations.getGameId())) {
            throw new InvalidGameException("Game not found.");
        }
        Game game = GameStorage.getInstance().getGames().get(operations.getGameId());
        if (!game.unSelect(operations.getPlayerId(), operations.getPositionX(), operations.getPositionY())) {
            throw new InvalidParaException("Input parameter invalid.");
        }
        return HttpResponse.ofJson(game);
    }

    @Post("/placeWorker")
    public HttpResponse placeWorker(GameOperations operations) throws InvalidGameException, InvalidParaException {
        if (!GameStorage.getInstance().getGames().containsKey(operations.getGameId())) {
            throw new InvalidGameException("Game not found.");
        }
        Game game = GameStorage.getInstance().getGames().get(operations.getGameId());

        if (!game.placeWorker(operations.getPlayerId(), operations.getPositionX(), operations.getPositionY())) {
            throw new InvalidParaException("Input parameter invalid.");
        }
        return HttpResponse.ofJson(game);
    }

    @Post("/move")
    public HttpResponse moveWorker(GameOperations operations) throws InvalidGameException, InvalidParaException {
        if (!GameStorage.getInstance().getGames().containsKey(operations.getGameId())) {
            throw new InvalidGameException("Game not found.");
        }
        Game game = GameStorage.getInstance().getGames().get(operations.getGameId());

        if (!game.move(operations.getPlayerId(), operations.getPositionX(), operations.getPositionY())) {
            throw new InvalidParaException("Input parameter invalid.");
        }
        return HttpResponse.ofJson(game);
    }

    @Post("/build")
    public HttpResponse buildWorker(GameOperations operations) throws InvalidGameException, InvalidParaException {
        if (!GameStorage.getInstance().getGames().containsKey(operations.getGameId())) {
            throw new InvalidGameException("Game not found.");
        }
        Game game = GameStorage.getInstance().getGames().get(operations.getGameId());

        if (!game.build(operations.getPlayerId(), operations.getPositionX(), operations.getPositionY(),
                operations.getBuildDome())) {
            throw new InvalidParaException("Input parameter invalid.");
        }
        return HttpResponse.ofJson(game);
    }

    @Post("/skip")
    public HttpResponse skipWorker(GameOperations operations) throws InvalidGameException, InvalidParaException {
        if (!GameStorage.getInstance().getGames().containsKey(operations.getGameId())) {
            throw new InvalidGameException("Game not found.");
        }
        Game game = GameStorage.getInstance().getGames().get(operations.getGameId());

        if (!game.skip(operations.getPlayerId(), operations.getPositionX(), operations.getPositionY())) {
            throw new InvalidParaException("Input parameter invalid.");
        }
        return HttpResponse.ofJson(game);
    }

    @Post("/test")
    @ExceptionHandler(BadRequestExceptionHandler.class)
    public HttpResponse testGame(GameInitBody body) {
        Game game = new Game(body);
        game.setId(UUID.randomUUID().toString());
        GameStorage.getInstance().setGame(game);
        return HttpResponse.ofJson(game);
    }

    @Get("/check/:id")
    public HttpResponse checkGame(@Param String id) {
        Game game = GameStorage.getInstance().getGames().get(id);
        return HttpResponse.ofJson(game);
    }

}

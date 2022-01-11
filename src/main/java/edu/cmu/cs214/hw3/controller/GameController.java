package edu.cmu.cs214.hw3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.cmu.cs214.hw3.controller.dto.GameInitBody;
import edu.cmu.cs214.hw3.controller.dto.GameOperations;
import edu.cmu.cs214.hw3.exception.InvalidGameException;
import edu.cmu.cs214.hw3.exception.InvalidParaException;
import edu.cmu.cs214.hw3.model.game.Game;
import edu.cmu.cs214.hw3.service.GameService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    @PostMapping("/start")
    public ResponseEntity<Game> start(@RequestBody GameInitBody body) {
        log.info("start game: request: {}", body);
        return ResponseEntity.ok(gameService.createGame(body));
    }

    @PostMapping("/placeWorker")
    public ResponseEntity<Game> placeWorker(@RequestBody GameOperations body)
            throws InvalidGameException, InvalidParaException {
        log.info("place worker request: {}", body);
        return ResponseEntity.ok(gameService.placeWorker(body));
    }

    @PostMapping("/move")
    public ResponseEntity<Game> moveWorker(@RequestBody GameOperations body)
            throws InvalidGameException, InvalidParaException {
        log.info("move worker request: {}", body);
        return ResponseEntity.ok(gameService.moveWorker(body));
    }

    @PostMapping("/build")
    public ResponseEntity<Game> buildWorker(@RequestBody GameOperations body)
            throws InvalidGameException, InvalidParaException {
        log.info("build worker request: {}", body);
        return ResponseEntity.ok(gameService.buildWorker(body));
    }

    @PostMapping("/select")
    public ResponseEntity<Game> selectWorker(@RequestBody GameOperations body)
            throws InvalidGameException, InvalidParaException {
        log.info("select worker request: {}", body);
        return ResponseEntity.ok(gameService.selectWorker(body));
    }

    @PostMapping("/unselect")
    public ResponseEntity<Game> unSelectWorker(@RequestBody GameOperations body)
            throws InvalidGameException, InvalidParaException {
        log.info("unselect worker request: {}", body);
        return ResponseEntity.ok(gameService.unSelectWorker(body));
    }

    @PostMapping("/skip")
    public ResponseEntity<Game> skipWorker(@RequestBody GameOperations body)
            throws InvalidGameException, InvalidParaException {
        log.info("skip worker request: {}", body);
        return ResponseEntity.ok(gameService.skipWorker(body));
    }

}

package edu.cmu.cs214.hw3.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GameOperations {
    private int buildDome;
    private int workerId;
    private int playerId;
    private int positionX;
    private int positionY;
    private String gameId;

    @JsonCreator
    GameOperations(@JsonProperty("buildDome") int buildDome, @JsonProperty("workerId") int workerId,
            @JsonProperty("playerId") int playerId, @JsonProperty("positionX") int positionX,
            @JsonProperty("positionY") int positionY, @JsonProperty("gameId") String gameId) {
        this.buildDome = buildDome;
        this.workerId = workerId;
        this.playerId = playerId;
        this.positionX = positionX;
        this.positionY = positionY;
        this.gameId = gameId;
    }
}

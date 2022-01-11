package edu.cmu.cs214.hw3.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameOperations {
    private int buildDome;
    private int workerId;
    private int playerId;
    private int positionX;
    private int positionY;
    private String gameId;
}

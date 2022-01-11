package edu.cmu.cs214.hw3.controller.dto;

import edu.cmu.cs214.hw3.model.GodPower;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GameInitBody {
    private int startPlayer;
    private String name1;
    private String name2;
    private GodPower power1;
    private GodPower power2;
}

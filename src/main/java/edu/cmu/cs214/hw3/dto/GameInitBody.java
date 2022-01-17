package edu.cmu.cs214.hw3.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import edu.cmu.cs214.hw3.model.GodPower;
import lombok.Data;

@Data
public class GameInitBody {
    private int startPlayer;
    private String name1;
    private String name2;
    private GodPower power1;
    private GodPower power2;

    @JsonCreator
    GameInitBody(@JsonProperty("startPlayer") int startPlayer, @JsonProperty("name1") String name1,
            @JsonProperty("name2") String name2, @JsonProperty("power1") GodPower power1,
            @JsonProperty("power2") GodPower power2) {
        this.startPlayer = startPlayer;
        this.name1 = name1;
        this.name2 = name2;
        this.power1 = power1;
        this.power2 = power2;
    }
}

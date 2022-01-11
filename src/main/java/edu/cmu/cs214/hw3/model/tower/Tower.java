package edu.cmu.cs214.hw3.model.tower;

import lombok.Data;

/**
 * A data model for Tower in the Game
 */
@Data
public class Tower {
    /**
     * level is the height of tower (doesn't take dome into account)
     */
    private int level;
    private boolean hasDome;
    private static final int MAX_HEIGHT = 3;

    public Tower(int level) {
        this.level = level;
        this.hasDome = false;
    }

    /**
     * check whether this tower can be added one block or one dome
     * 
     * @return true if it is valid to add one block or a dome
     */
    public boolean canBuildUp() {
        return !this.hasDome;
    }

    /**
     * Check whether it is possible to build a tower by adding a block only
     * 
     * @return true if this can be added a block
     */
    public boolean canBuildBlock() {
        if (!this.hasDome && this.level < MAX_HEIGHT) {
            return true;
        }
        return false;
    }

    /**
     * Add a block or a dome depending on the current level of tower
     * 
     */
    public void buildUp() {
        if (this.level == MAX_HEIGHT && !this.hasDome) {
            this.hasDome = true;
        } else if (this.level < MAX_HEIGHT) {
            this.level++;
        }
    }

    /**
     * Getter function for hasDome field
     * 
     * @return hasDome
     */
    public boolean hasDome() {
        return this.hasDome;
    }

    public void buildDome() {
        this.hasDome = true;
    }
}

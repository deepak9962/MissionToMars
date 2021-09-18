package com.mtm;

public interface SpaceShip {
    boolean launch = false;
    boolean land = false;

    // Checks if the Rocket exceeds the weight limit
    default boolean canCarry(Item item) {
        return false;
    }

    // Updates the Rocket weight
    default void carry(Item item) {

    }
}
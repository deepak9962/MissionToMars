package com.mtm;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Rocket implements SpaceShip {

    int rocketCost;
    int rocketWeight;
    int rocketMaxWeight;
    int cargoCarried;
    int currentWeight;
    int cargoLimit;

    double launchExplosionChance;
    double landingCrashChance;

    double randomNumber;

    Rocket() {
        cargoCarried = 0;
        cargoLimit = 0;
        launchExplosionChance = 0.0;
        landingCrashChance = 0.0;
        randomNumber = new Random().nextDouble();
    }

    /**
     * @Override the method launch from Interface SpaceShip
     * @return return true
     */
    public boolean launch() {
        return true;
    }

    /**
     * @Override the method land from Interface SpaceShip
     * @return return true
     */
    public boolean land() {
        return true;
    }

    /**
     * @Override the method canCarry from Interface SpaceShip
     * Check whether a rocket is able to carry the item or not
     * @param item item which rocket will carry with it
     * @return returns true or false based on whether it can carry or not
     */
    @Override
    public boolean canCarry(@NotNull Item item) {
        return (this.currentWeight + item.weight) <= rocketMaxWeight;
    }

    /**
     * @Override the method carry from Interface SpaceShip
     * It carries loads item in the rocket
     * @param item items which rocket will carry with it
     */
    @Override
    public void carry(@NotNull Item item) {
        this.currentWeight = this.currentWeight + item.weight;
        this.cargoCarried = this.currentWeight - this.rocketWeight;
    }
}

/**
 * Class U1 extends Class Rocket
 */
class U1 extends Rocket {

    /**
     * Overriding the values for rocket as per U1 class rocket
     */
    U1() {
        rocketCost = 100;   // Cost in Millions Dollars
        rocketWeight = 10000;   // Weight in Kilograms
        rocketMaxWeight = 18000;    // Weight in Kilograms
        cargoLimit = rocketMaxWeight - rocketWeight;    // Cargo maximum carry limit
        currentWeight = currentWeight + rocketWeight;   // Current weight of the Rocket
    }

    /**
     * @Override method in Rocket class
     * @return returns boolean value whether rocket launches successfully or not
     */
    @Override
    public boolean launch() {
        launchExplosionChance = 0.05 * (double) (cargoCarried / cargoLimit);    // Chance of launch explosion in 5%
        return launchExplosionChance <= this.randomNumber;
    }

    /**
     * @Override method in Rocket class
     * @return returns boolean value whether rocket lands successfully or not
     */
    @Override
    public boolean land() {
        landingCrashChance = 0.01 * (double) (cargoCarried / cargoLimit);   // Chance of landing crash in 1%
        return landingCrashChance <= this.randomNumber;
    }
}

/**
 * Class U2 extends Class Rocket
 */
class U2 extends Rocket {

    U2() {
        rocketCost = 120;   // Cost in Millions Dollars
        rocketWeight = 18000;   // Weight in Kilograms
        rocketMaxWeight = 29000;    // Weight in Kilograms
        cargoLimit = rocketMaxWeight - rocketWeight;    // Cargo maximum carry limit
        currentWeight = currentWeight + rocketWeight;   // Current weight of the Rocket
    }

    /**
     * @Override method in Rocket class
     * @return returns boolean value whether rocket launches successfully or not
     */
    @Override
    public boolean launch() {
        launchExplosionChance = 0.04 * (double) (cargoCarried / cargoLimit);    // Chance of launch explosion in 4%
        return launchExplosionChance <= this.randomNumber;
    }

    /**
     * @Override method in Rocket class
     * @return returns boolean value whether rocket lands successfully or not
     */
    @Override
    public boolean land() {
        landingCrashChance = 0.08 * (double) (cargoCarried / cargoLimit);   // Chance of landing crash in 8%
        return landingCrashChance <= this.randomNumber;
    }
}
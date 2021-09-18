package com.mtm;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Item> itemsPhaseOne = new ArrayList<>();  // ArrayList of Items for Phase One
        ArrayList<Item> itemsPhaseTwo = new ArrayList<>();  // ArrayList of Items for Phase Two
        Simulation simulation = new Simulation();

        // Add the list of loaded items for Phase One
        itemsPhaseOne = simulation.loadItems(itemsPhaseOne, 1);
        // Add the list of loaded items for Phase Two
        itemsPhaseTwo = simulation.loadItems(itemsPhaseTwo, 2);

        ArrayList<U1> phaseOneU1;   // ArrayList of U1 rockets for Phase One
        ArrayList<U2> phaseOneU2;   // ArrayList of U2 rockets for Phase One
        ArrayList<U1> phaseTwoU1;   // ArrayList of U1 rockets for Phase Two
        ArrayList<U2> phaseTwoU2;   // ArrayList of U2 rockets for Phase Two

        // Simulate the U1 rockets for Phase One
        System.out.println("\n--------------------SIMULATING PHASE ONE 'U1'--------------------");
        phaseOneU1 = simulation.loadU1(itemsPhaseOne);  // Load the U1 rocket(s) for Phase One items
        int totalBudgetForPhaseOneU1 = simulation.runSimulation(phaseOneU1);    // Get the total budget
        System.out.println("Total required budget for Phase 1 for U1 : " + totalBudgetForPhaseOneU1 + " Million");

        // Simulate the U2 rockets for Phase One
        System.out.println("\n--------------------SIMULATING PHASE ONE 'U2'--------------------");
        phaseOneU2 = simulation.loadU2(itemsPhaseOne);  // Load the U1 rocket(s) for Phase One items
        int totalBudgetForPhaseOneU2 = simulation.runSimulation(phaseOneU2);    // Get the total budget
        System.out.println("Total required budget for Phase 1 for U2 : " + totalBudgetForPhaseOneU2 + " Million");

        // Simulate the U1 rockets for Phase Two
        System.out.println("\n--------------------SIMULATING PHASE TWO 'U1'--------------------");
        phaseTwoU1 = simulation.loadU1(itemsPhaseTwo);  // Load the U1 rocket(s) for Phase One items
        int totalBudgetForPhaseTwoU1 = simulation.runSimulation(phaseTwoU1);    // Get the total budget
        System.out.println("Total required budget for Phase 2 for U1 : " + totalBudgetForPhaseTwoU1 + " Million");

        // Simulate the U2 rockets for Phase Two
        System.out.println("\n--------------------SIMULATING PHASE TWO 'U2'--------------------");
        phaseTwoU2 = simulation.loadU2(itemsPhaseTwo);  // Load the U1 rocket(s) for Phase One items
        int totalBudgetForPhaseTwoU2 = simulation.runSimulation(phaseTwoU2);    // Get the total budget
        System.out.println("Total required budget for Phase 2 for U2 : " + totalBudgetForPhaseTwoU2 + " Million");

    }
}
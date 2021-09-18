package com.mtm;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Simulation class to simulate all the rockets and return the total budget required for the mission.
 *
 * This simulation does the following few things for the app:
 * - It loads all the Items from the file for the appropriate phase
 * - Then it creates the fills the rocket(s) until all the items are successfully fills in the rocket(s)
 * - It loads two types of Rockets : U1 and U2
 * - Then it runs the simulation for whether all the rocket(s) launches and lands successfully or crashes.
 * - if crashes then create a new rocket and add in appropriate instance of rocket.
 * - calculate the budget required for the rocket(s).
 * - Returns the budget.
 */
public class Simulation {

    /**
     * loadItems is an Arraylist where all the items from the file gets loaded.
     *
     * @param items ArrayList of Items to add and get the values loaded from the file.
     * @param phase to load the different files for different phases
     * @return returns the arraylist of the items loaded.
     */
    ArrayList<Item> loadItems(ArrayList<Item> items, int phase) {

        try {
            Scanner scanner = null;
            if (phase == 1) {
                //  load file for phase one
                scanner = new Scanner(new File("assets/phase-1.txt"));
            } else if (phase == 2) {
                // load file for phase two
                scanner = new Scanner(new File("assets/phase-2.txt"));
            } else {
                // Show the error if the phase is out of phases.
                System.out.println("Something went wrong! phase error!");
            }
            String str_line;

            // scan the file
            // get the lines
            // split the line from "="
            // add the values to its appropriate variables.
            while (true) {
                assert scanner != null;
                if (!scanner.hasNextLine()) break;
                Item item = new Item();
                str_line = scanner.nextLine();
                String STRING_SPLITTER = "=";   // String Splitter
                if (str_line.contains(STRING_SPLITTER)) {
                    String[] part = str_line.split(STRING_SPLITTER);
                    item.name = part[0];
                    item.weight = Integer.parseInt(part[1]);
                    items.add(item);
                }
            }

            // close the scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();    // if file could not find, handle the exception error
        }

        // Returns the arraylist of loaded items
        return items;
    }

    /**
     * loads the items in the U1 rocket(s) until all the items are loaded successfully.
     *
     * @param items get all the items
     * @return returns the arraylist of rocket(s)
     */
    public ArrayList<U1> loadU1(@NotNull ArrayList<Item> items) {

        ArrayList<U1> fleetU1 = new ArrayList<>();  // Create the fleets of U1

        int numberOfItems = items.size() - 1;
        int i = 0;

        U1 u1 = new U1();
        int numberOfRockets = 0;
        while (i <= numberOfItems) {
            // fill the rocket and create a new rocket if there are still item(s) left.
            while (u1.currentWeight <= u1.rocketMaxWeight) {
                if (u1.canCarry(items.get(i))) {
                    u1.carry(items.get(i));     // fill the rocket
                    i++;

                    // if all the items loads successfully then break the loop.
                    // else if there are still item(s) left and rocket is full
                    // create a new rocket and start continuing the filling.
                    // repeat until all the items are loaded.
                    if (i > numberOfItems) {
                        numberOfRockets++;
                        System.out.println("Rocket " + numberOfRockets + " Loaded!");
                        fleetU1.add(u1);
                        System.out.println("All items loaded for U1");
                        break;  // breaking the loop if all the items are loaded successfully.
                    } else if (!u1.canCarry(items.get(i))) {
                        numberOfRockets++;
                        System.out.println("Rocket " + numberOfRockets + " Loaded!");
                        fleetU1.add(u1);
                        u1 = new U1();  // create a new Rocket if the rocket is full and still item(s) are left.
                    }
                }
            }
        }

        // returns the arraylist of rockets
        return fleetU1;
    }

    /**
     * loads the items in the U2 rocket(s) until all the items are loaded successfully.
     *
     * @param items get all the items
     * @return returns the arraylist of rocket(s)
     */
    public ArrayList<U2> loadU2(@NotNull ArrayList<Item> items) {

        ArrayList<U2> fleetU2 = new ArrayList<>();

        int numberOfItems = items.size() - 1;
        int i = 0;

        U2 u2 = new U2();
        int numberOfRockets = 0;
        while (i <= numberOfItems) {
            while (u2.currentWeight <= u2.rocketMaxWeight) {
                if (u2.canCarry(items.get(i))) {
                    u2.carry(items.get(i));
                    i++;

                    if (i > numberOfItems) {
                        numberOfRockets++;
                        System.out.println("Rocket " + numberOfRockets + " Loaded!");
                        fleetU2.add(u2);
                        System.out.println("All items loaded for U2");
                        break;
                    } else if (!u2.canCarry(items.get(i))) {
                        numberOfRockets++;
                        System.out.println("Rocket " + numberOfRockets + " Loaded!");
                        fleetU2.add(u2);
                        u2 = new U2();
                    }
                }
            }
        }
        return fleetU2;
    }

    /**
     * run the simulation and check if the launch and land is successful or if it crashes, if crashes then create
     * a new rocket and add that in the list of its appropriate instance rocket list and return the total budget for
     * the mission
     *
     * @param rockets gets the arraylist of rockets to test launch and land, and calculates total budget.
     * @return returns the total budget
     */
    @SuppressWarnings("unchecked")
    int runSimulation(@NotNull ArrayList rockets) {
        int totalBudget = 0;
        Rocket rocket;

        // for loop to test the rockets one by one and calculates the price.
        for (int i = 0; i < rockets.size(); i++) {
            rocket = (Rocket) rockets.get(i);
            if (rocket.launch()) {  // if successfully launches
                System.out.println("Rocket " + (i + 1) + " launch successful!");

                if (rocket.land()) {    // if successfully lands
                    System.out.println("Rocket " + (i + 1) + " land successful!");
                    totalBudget = totalBudget + rocket.rocketCost;  // calculate the cost and add in the budget
                } else {    // if crashes at the time of landing
                    System.out.println("Rocket " + (i + 1) + " crash landing!");
                    totalBudget = totalBudget + rocket.rocketCost;  // calculate the cost and add in the budget
                    addNewRocket(rockets, i);   // insert a new rocket
                }

            } else {    // if crashes at the time of launching
                System.out.println("Rocket " + (i + 1) + " crashed!");
                totalBudget = totalBudget + rocket.rocketCost; // calculate the cost and add in the budget
                addNewRocket(rockets, i);   // insert a new rocket
            }
        }

        // returns the total budget
        return totalBudget;
    }

    /**
     * create a new rocket when a rocket get crashed
     *
     * @param rocket get the arraylist of rockets
     * @param i get the index of the crashed rocket
     */
    private void addNewRocket(@NotNull ArrayList<Rocket> rocket, int i) {
        Rocket crashedRocket = rocket.get(i);

        // create a new rocket and transfer the items to the new rocket
        if (crashedRocket instanceof U1) {  // for U!
            System.out.println("Adding new Rocket for crashed rocket!");
            U1 u1 = new U1();
            u1.currentWeight = crashedRocket.currentWeight;
            u1.cargoCarried = crashedRocket.cargoCarried;
            rocket.add(i + 1, u1);  // add the rocket in the list of rockets
            System.out.println("Added new Rocket for crashed rocket!");

        } else if (crashedRocket instanceof U2) {   // for U2
            System.out.println("Adding new Rocket for crashed rocket!");
            U2 u2 = new U2();
            u2.currentWeight = crashedRocket.currentWeight;
            u2.cargoCarried = crashedRocket.cargoCarried;
            rocket.add(i + 1, u2);
            System.out.println("Added new Rocket for crashed rocket!");

        } else {    // handle the error if something goes wrong
            System.out.println("Something went wrong!\nCould not create a new rocket for crashed one!");
        }
    }
}

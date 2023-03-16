package assignment4;

import java.util.ArrayList;
import java.util.Scanner;

public class Editor {

    // ^^ Behövs en constructor...?

    // Method for printing all the solar systems
    // public void printAll (ArrayList solarSystemsArrayList) {
    //   for (int i = 0; i < solarSystemsArrayList.size(); i++) {
    //     System.out.println(solarSystemsArrayList.get(i).toString());
    //   }
    // }

  // ^^ Testar en annan metod
  public void printAll (ArrayList<SolarSystem> solarSystemsArrayList) {
    for (SolarSystem system : solarSystemsArrayList) {
      System.out.println(system.toString());
    }
  }

  // Method for adding a planet
  public Planet addPlanet (ArrayList<SolarSystem> solarSystemArrayList) {
    // Start a scanner
    Scanner scanner = new Scanner(System.in);

    // Create a variable to save the user's choice
    int choice = 0;

    // Ask which star to add the planet to
    System.out.println("Which star do you want to add the planet to?");
    for (int i = 0; i < solarSystemArrayList.size(); i++) {
      System.out.println(i + 1 + ": " + solarSystemArrayList.get(i).getStar().getName());
    }

    // Ask for the name of the planet

    // Ask for the radius of the planet

    // Ask for the orbit radius of the planet

    // Create a new planet

    // Add the planet to the star

    // Return the planet
  }
}

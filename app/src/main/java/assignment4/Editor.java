package assignment4;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Editor {

  // ^^ Behövs en constructor...?

  // Method for printing all the solar systems
  public void printAll (ArrayList<SolarSystem> solarSystemsArrayList) {
    for (SolarSystem oneSystem : solarSystemsArrayList) {
      System.out.println(oneSystem.toString());
    }
  }

  // Method for adding a planet
  public void addPlanet (ArrayList<SolarSystem> solarSystemArrayList, Scanner scanner) {
    // Ask which star to add the planet to
    System.out.println("Which star do you want to add the planet to?");
    for (int i = 0; i < solarSystemArrayList.size(); i++) {
      System.out.println(i + 1 + ": " + solarSystemArrayList.get(i).getStar().getName());
    }

    // Save the index of the star
    // Set the index to -1 first so the while loop starts, and will continue running if no valid index is entered
    int starIndex = -1;
    while (starIndex < 0 || starIndex >= solarSystemArrayList.size()) {
      try {
        starIndex = scanner.nextInt() - 1;
        if (starIndex < 0 || starIndex >= solarSystemArrayList.size()) {
          System.out.println("Invalid index. Please enter a valid index.");
        }

        // If the user writes something else than an integer
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a valid index.");

        // Clear the scanner
        scanner.next();
      }
    }

    // Ask for the name of the planet
    System.out.println("Information about the planet you want to add:");
    System.out.print("Name: ");
    String name = scanner.next();

    // Ask for the radius of the planet
    int radius = -1;
    while (radius < 0) {
      try {
        System.out.print("Radius in km: ");
        radius = scanner.nextInt();
        if (radius < 0) {
          System.out.println("Radius cannot be negative. Please enter a valid radius.");
        }
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a valid radius.");
        scanner.next();
      }
    }

    // Ask for the orbit radius of the planet
    int orbitRadius = -1;
    while (orbitRadius < 0) {
      try {
        System.out.print("Orbit radius in km: ");
        orbitRadius = scanner.nextInt();
        if (orbitRadius < 0) {
          System.out.println("Orbit radius cannot be negative. Please enter a valid orbit radius.");
        }
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a valid orbit radius.");
        scanner.next();
      }
    }

    // Add the planet to the star
    try {
      solarSystemArrayList.get(starIndex).getStar().addPlanet(name, radius, orbitRadius);
      System.out.println("The planet " + name + " was successfully added to the star " + solarSystemArrayList.get(starIndex).getStar().getName());

      // If the name, radius or orbit radius is invalid, the message will be printed and the program will return to the main menu
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }
}

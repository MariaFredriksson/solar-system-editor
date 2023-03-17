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
    int starIndex = scanner.nextInt() - 1;

    // ^^ Gör felhantering för när användaren inte skriver in ett nummer, eller ett index som inte finns

    // Ask for the name of the planet
    System.out.println("Information about the planet you want to add:");
    System.out.println("Name: ");
    String name = scanner.next();

    // Ask for the radius of the planet
    System.out.println("Radius in km: ");
    int radius = scanner.nextInt();

    // ^^ Felhantering för när användaren inte skriver in ett nummer

    // Ask for the orbit radius of the planet
    System.out.println("Orbit radius in km: ");
    int orbitRadius = scanner.nextInt();

    // ^^ Felhantering för när användaren inte skriver in ett nummer

    // Add the planet to the star
    solarSystemArrayList.get(starIndex).getStar().addPlanet(name, radius, orbitRadius);

    // Print out a confirmation message
    System.out.println("The planet " + name + " was successfully added to the star " + solarSystemArrayList.get(starIndex).getStar().getName());

    // Return the planet
    // ^^ Behövs en return här...?
  }
}

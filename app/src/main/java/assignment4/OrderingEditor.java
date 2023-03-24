package assignment4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * A class representing an editor for ordering the members of the solar systems.
 */
public class OrderingEditor extends Editor {
  /**
   * Method for ordering the members of the solar systems.
   *
   * @param solarSystemsArrayList - The arrayList with all the solar systems
   * @param scanner               - The scanner for getting user input
   */
  public void orderSystems(ArrayList<SolarSystem> solarSystemsArrayList, Scanner scanner) {
    // Check that the solarSystemsArrayList is not empty
    if (solarSystemsArrayList == null || solarSystemsArrayList.isEmpty()) {
      System.out.println("There are no solar systems.");
      return;
    }

    // Ask the user how the solar systems should be ordered
    System.out.println("How do you want to order the members of the solar systems?");
    System.out.println("1: By radius (smallest to largest)");
    System.out.println("2: By orbital-radius (smallest to largest)");

    int choice = getIntInput(scanner, "", "Invalid input. Please enter a valid input.", 2);

    // Make a switch case for the different orders
    switch (choice) {
      case 1:
        orderSystemsByRadius(solarSystemsArrayList);
        break;
      case 2:
        orderSystemsByOrbitRadius(solarSystemsArrayList);
        break;
      default:
        break;
    }

    // Ask what the user wants to do next
    if (subMenu(scanner, "Order the members of the solar systems") == 1) {
      orderSystems(solarSystemsArrayList, scanner);
    }
  }

  private void orderSystemsByRadius(ArrayList<SolarSystem> solarSystemsArrayList) {
    int lengthOfAllSolarSystems = 0;

    // Loop through the solar systems arraylist
    for (int i = 0; i < solarSystemsArrayList.size(); i++) {
      // Add 1 for the star
      lengthOfAllSolarSystems++;

      // Get to know how many planets and moons there are by looping through
      // the planets and add the length of the heavenlyBodiesArray for the planet
      for (Planet planet : solarSystemsArrayList.get(i).getStar().getPlanetsArrayList()) {
        lengthOfAllSolarSystems += planet.getHeavenlyBodies().length;
      }
    }

    // Make an array that will contain of all the members of the solar systems
    HeavenlyBody[] allSolarSystemsArray = new HeavenlyBody[lengthOfAllSolarSystems];

    // Keep track of the index of where to add something in the array
    int index = 0;

    // Loop through the solar systems arraylist and add the members to the array
    for (int i = 0; i < solarSystemsArrayList.size(); i++) {
      // Add the star to the array
      allSolarSystemsArray[index] = solarSystemsArrayList.get(i).getStar();
      index++;

      // Loop through the planets and add them to the array
      for (Planet planet : solarSystemsArrayList.get(i).getStar().getPlanetsArrayList()) {
        allSolarSystemsArray[index] = planet;
        index++;

        // Loop through the moons and add them to the array
        for (Moon moon : planet.getMoonsArrayList()) {
          allSolarSystemsArray[index] = moon;
          index++;
        }
      }
    }

    // Sort the array by radius
    Arrays.sort(allSolarSystemsArray);

    // Print the array
    for (HeavenlyBody heavenlyBody : allSolarSystemsArray) {
      System.out.println(heavenlyBody.getName() + " - radius: " + heavenlyBody.getAvgRadiusInKm() + "km");
    }
  }

  private void orderSystemsByOrbitRadius(ArrayList<SolarSystem> solarSystemsArrayList) {
    // Create an arraylist that will contain all the planets and moons
    // (and not the suns, since they don't have an orbital radius)
    ArrayList<OrbitingBody> allPlanetsAndMoonsArrayList = new ArrayList<>();

    // Loop through the solar systems arraylist
    for (int i = 0; i < solarSystemsArrayList.size(); i++) {
      // Loop through the planets of that solar system
      for (Planet planet : solarSystemsArrayList.get(i).getStar().getPlanetsArrayList()) {
        // Add the planet to the arraylist
        allPlanetsAndMoonsArrayList.add(planet);

        // Loop through the moons of that planet
        for (Moon moon : planet.getMoonsArrayList()) {
          // Add the moon to the arraylist
          allPlanetsAndMoonsArrayList.add(moon);
        }
      }
    }

    // Sort the arraylist by orbital radius
    Collections.sort(allPlanetsAndMoonsArrayList, OrbitingBody.orbitRadiusComparator);

    // Print the arraylist
    for (OrbitingBody orbitingBody : allPlanetsAndMoonsArrayList) {
      System.out.println(orbitingBody.getName() + " - orbit radius: "
          + orbitingBody.getAvgOrbitRadiusInKm() + "km");
    }
  }
}

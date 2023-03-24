package assignment4;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class representing an editor that adds members of solar systems.
 */
public class AddingEditor extends Editor {
  /**
   * Adds a planet to a star.
   *
   * @param solarSystemsArrayList - The arrayList containing all the solar systems
   * @param scanner               - The scanner used to get input from the user
   */
  public void addPlanet(ArrayList<SolarSystem> solarSystemsArrayList, Scanner scanner) {
    // Check that the solarSystemsArrayList is not empty
    if (checkEmptyArrayList("There are no solar systems. Please add one.", solarSystemsArrayList)) {
      return;
    }

    // Ask which star to add the planet to
    System.out.println("Which star do you want to add the planet to?");
    printStars(solarSystemsArrayList);

    // Ask for the index of the star
    // Leave the prompt empty, since the prompt is already printed
    int starIndex = getIntInput(scanner, "",
        "Invalid index. Please enter a valid index.", solarSystemsArrayList.size());

    // Subtract 1 from the index to get the correct index in the arrayList
    starIndex--;

    // Ask for the name of the planet
    System.out.println("Information about the planet you want to add:");
    System.out.print("Name: ");
    String name = scanner.next();

    // Ask for the radius of the planet
    // Set the max value to Integer.MAX_VALUE just to set it to a very high number,
    // so the starIndex can have a maxValue that is important to it
    int radius = getIntInput(scanner, "Radius in km: ",
        "Radius cannot be negative. Please enter a valid radius.", Integer.MAX_VALUE);

    // Ask for the orbit radius of the planet
    int orbitRadius = getIntInput(scanner, "Orbit radius in km: ",
        "Orbit radius cannot be negative. Please enter a valid orbit radius.", Integer.MAX_VALUE);

    // Add the planet to the star
    try {
      solarSystemsArrayList.get(starIndex).getStar().addPlanet(name, radius, orbitRadius);
      System.out.println("The planet " + name + " was successfully added to the star "
          + solarSystemsArrayList.get(starIndex).getStar().getName());

      // If the name, radius or orbit radius is invalid, the message will be printed
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      System.out.println("The planet could not be added.");
    }

    // Ask the user what to do next
    // If the user wants to add a planet, the method will be called again
    if (subMenu(scanner, "Add a planet") == 1) {
      addPlanet(solarSystemsArrayList, scanner);
    }

    // If the user wants to return to the main menu, the method just continues and
    // ends,
    // and thus returns to the main menu
  }
  
  /**
   * Adds a moon to a planet.
   *
   * @param solarSystemsArrayList - The arrayList containing all the solar systems
   * @param scanner               - The scanner used to get input from the user
   */
  public void addMoon(ArrayList<SolarSystem> solarSystemsArrayList, Scanner scanner) {
    // Check that the solarSystemsArrayList is not empty
    if (checkEmptyArrayList("There are no solar systems. Please add one.", solarSystemsArrayList)) {
      return;
    }

    // Ask which star to add the moon to
    System.out.println("Which star do you want to add the moon to?");
    printStars(solarSystemsArrayList);

    // Ask for the index of the star
    // Leave the prompt empty, since the prompt is already printed
    int starIndex = getIntInput(scanner, "",
        "Invalid index. Please enter a valid index.", solarSystemsArrayList.size());

    // Subtract 1 from the index to get the correct index in the arrayList
    starIndex--;

    Star star = solarSystemsArrayList.get(starIndex).getStar();

    // Ask which planet to add the moon to
    System.out.println("Which planet do you want to add the moon to?");
    printPlanets(star);

    // Ask for the index of the planet
    int planetIndex = getIntInput(scanner, "", "Invalid index. Please enter a valid index.",
        solarSystemsArrayList.get(starIndex).getStar().getPlanetsArrayList().size());

    // Subtract 1 from the index to get the correct index in the arrayList
    planetIndex--;

    Planet planet = star.getPlanetsArrayList().get(planetIndex);

    // Ask for the name of the moon
    System.out.println("Information about the moon you want to add:");
    System.out.print("Name: ");
    String name = scanner.next();

    // Ask for the radius of the moon
    // Set the max value to Integer.MAX_VALUE just to set it to a very high number,
    // so the starIndex and planetIndex can have a maxValue that is important to it
    int radius = getIntInput(scanner, "Radius in km: ", "Radius cannot be negative. Please enter a valid radius.",
        Integer.MAX_VALUE);

    // Ask for the orbit radius of the moon
    int orbitRadius = getIntInput(scanner, "Orbit radius in km: ",
        "Orbit radius cannot be negative. Please enter a valid orbit radius.", Integer.MAX_VALUE);

    // Add the moon to the planet
    try {
      planet.addMoon(name, radius, orbitRadius);
      System.out.println("The moon " + name + " was successfully added to the planet " + planet.getName());

      // If the name, radius or orbit radius is invalid, the message will be printed
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      System.out.println("The moon could not be added.");
    }

    // Ask the user what to do next
    // If the user wants to add a moon, the method will be called again
    if (subMenu(scanner, "Add a moon") == 1) {
      addMoon(solarSystemsArrayList, scanner);
    }

    // If the user wants to return to the main menu, the method just continues and
    // ends, and thus returns to the main menu
  }

  /**
   * Creates a new solar system and returns it.
   *
   * @param scanner - The scanner used to get input from the user
   * @return - The new solar system
   */
  public SolarSystem createSolarSystem(Scanner scanner) {
    // Ask for the name of the solar system
    System.out.println("Enter the name of the solar system you want to add:");
    String solarSystemName = scanner.next();

    // Ask for the name of the star
    System.out.println("Information about the star you want to add:");
    System.out.print("Name: ");
    String starName = scanner.next();

    // Ask for the radius of the star
    // Set the max value to Integer.MAX_VALUE just to set it to a very high number
    int radius = getIntInput(scanner, "Radius in km: ",
        "Radius cannot be negative. Please enter a valid radius.", Integer.MAX_VALUE);

    try {
      // Add a new solar system
      SolarSystem newSolarSystem = new SolarSystem(solarSystemName);

      // Add the star to the solar system
      newSolarSystem.addStar(starName, radius);

      // Print a confirmation message that the solar system and the star were added
      // successfully
      System.out.println("The solar system " + solarSystemName + " containing the star "
          + starName + " was successfully added");

      return newSolarSystem;

      // If the name, radius or orbit radius is invalid, the message will be printed
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      System.out.println("The solar system and the star could not be added.");

      // Return null if the solar system could not be added
      return null;
    }
  }
}

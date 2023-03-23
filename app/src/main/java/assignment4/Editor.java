package assignment4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A class representing an editor.
 */
public class Editor {

  // ^^ Is a constructor needed...?

  /**
   * Prints all heavenly bodies in the solar systems.
   *
   * @param solarSystemsArrayList - The arrayList containing all the solar systems
   * @param scanner               - The scanner used to get input from the user
   */
  public void printAll(ArrayList<SolarSystem> solarSystemsArrayList, Scanner scanner) {
    // Check that the solarSystemsArrayList is not empty
    if (solarSystemsArrayList == null || solarSystemsArrayList.isEmpty()) {
      System.out.println("There are no solar systems.");
      return;
    }

    for (SolarSystem oneSystem : solarSystemsArrayList) {
      System.out.println(oneSystem.toString());
    }

    // Ask the user what to do next
    // If the user wants to list all solar systems again, the method will be called again
    if (subMenu(scanner, "List all solar systems") == 1) {
      printAll(solarSystemsArrayList, scanner);
    }

    // If the user wants to return to the main menu, the method just continues and
    // ends, and thus returns to the main menu
  }

  /**
   * Adds a planet to a star.
   *
   * @param solarSystemsArrayList - The arrayList containing all the solar systems
   * @param scanner               - The scanner used to get input from the user
   */
  public void addPlanet(ArrayList<SolarSystem> solarSystemsArrayList, Scanner scanner) {
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

    // If the user wants to return to the main menu, the method just continues and ends, 
    // and thus returns to the main menu
  }

  /**
   * Adds a moon to a planet.
   *
   * @param solarSystemsArrayList - The arrayList containing all the solar systems
   * @param scanner               - The scanner used to get input from the user
   */
  public void addMoon(ArrayList<SolarSystem> solarSystemsArrayList, Scanner scanner) {
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

  /**
   * Deletes a member from a solar system.
   *
   * @param solarSystemsArrayList - The arrayList containing all the solar systems
   * @param scanner               - The scanner used to get input from the user
   */
  public void deleteMember(ArrayList<SolarSystem> solarSystemsArrayList, Scanner scanner) {
    // Check that the solarSystemsArrayList is not empty
    if (solarSystemsArrayList == null || solarSystemsArrayList.isEmpty()) {
      System.out.println("There are no solar systems.");
      return;
    }

    // Print all the members of the solar system
    System.out.println("Here are all the members of the solar systems:\n");
    for (SolarSystem oneSystem : solarSystemsArrayList) {
      System.out.println(oneSystem.toString());
    }

    // Ask what the user wants to delete
    System.out.println("What do you want to delete?");
    System.out.println("1: A star");
    System.out.println("2: A planet");
    System.out.println("3: A moon");

    int choice = getIntInput(scanner, "", "Invalid choice. Please enter a valid choice.", 3);

    // Make a switch statement to delete the correct member
    switch (choice) {
      case 1:
        // Delete a star
        deleteStar(solarSystemsArrayList, scanner);
        break;
      case 2:
        // Delete a planet
        deletePlanet(solarSystemsArrayList, scanner);
        break;
      case 3:
        // Delete a moon
        deleteMoon(solarSystemsArrayList, scanner);
        break;
      default:
        // Invalid choice
        System.out.println("Invalid choice. Please try again.\n");
        break;
    } 

    // Tell the user that the deletion was successful
    System.out.println("The member of the solar system was successfully deleted\n");

    // Ask what the user wants to do next. If the user still wants to delete
    // something, call the method again
    if (subMenu(scanner, "Delete a member of the solar system") == 1) {
      deleteMember(solarSystemsArrayList, scanner);
    }
  }

  private void deleteStar(ArrayList<SolarSystem> solarSystemsArrayList, Scanner scanner) {
    // Ask which star the user wants to delete
    System.out.println("Which star do you want to delete?");
    printStars(solarSystemsArrayList);

    int starIndex = getIntInput(scanner, "", "Invalid index. Please enter a valid index.",
        solarSystemsArrayList.size());

    // Subtract 1 from the index to get the correct index in the arrayList
    starIndex--;

    // Delete the star
    solarSystemsArrayList.remove(starIndex);
  }

  private void deletePlanet(ArrayList<SolarSystem> solarSystemsArrayList, Scanner scanner) {
    // Ask from which star the user wants to delete a planet
    System.out.println("From which star do you want to delete a planet?");
    printStars(solarSystemsArrayList);

    int starIndex = getIntInput(scanner, "", "Invalid index. Please enter a valid index.",
        solarSystemsArrayList.size());

    // Subtract 1 from the index to get the correct index in the arrayList
    starIndex--;
    Star star = solarSystemsArrayList.get(starIndex).getStar();

    // Check that the selected star has planets
    if (checkEmptyArrayList(solarSystemsArrayList, scanner, "This star has no planets.", star.getPlanetsArrayList())) {
      return;
    }

    // Ask which planet the user wants to delete
    System.out.println("Which planet do you want to delete?");
    printPlanets(star);

    int planetIndex = getIntInput(scanner, "", "Invalid index. Please enter a valid index.",
        star.getPlanetsArrayList().size());

    // Subtract 1 from the index to get the correct index in the arrayList
    planetIndex--;

    // Delete the planet
    star.getPlanetsArrayList().remove(planetIndex);
  }

  private void deleteMoon(ArrayList<SolarSystem> solarSystemsArrayList, Scanner scanner) {
    // Ask from which star the user wants to delete a moon
    System.out.println("From which star do you want to delete a moon?");
    printStars(solarSystemsArrayList);

    int starIndex = getIntInput(scanner, "", "Invalid index. Please enter a valid index.",
        solarSystemsArrayList.size());

    // Subtract 1 from the index to get the correct index in the arrayList
    starIndex--;
    Star star = solarSystemsArrayList.get(starIndex).getStar();

    // Check that the selected star has planets
    if (checkEmptyArrayList(solarSystemsArrayList, scanner, "This star has no planets.", star.getPlanetsArrayList())) {
      return;
    }

    // Ask from which planet the user wants to delete a moon
    System.out.println("From which planet do you want to delete a moon?");
    printPlanets(star);

    int planetIndex = getIntInput(scanner, "", "Invalid index. Please enter a valid index.",
        star.getPlanetsArrayList().size());

    // Subtract 1 from the index to get the correct index in the arrayList
    planetIndex--;
    Planet planet = star.getPlanetsArrayList().get(planetIndex);

    // Check that the selected planet has moons
    if (checkEmptyArrayList(solarSystemsArrayList, scanner, "This planet has no moons.", planet.getMoonsArrayList())) {
      return;
    }

    // Ask which moon the user wants to delete
    System.out.println("Which moon do you want to delete?");
    printMoons(planet);

    int moonIndex = getIntInput(scanner, "", "Invalid index. Please enter a valid index.",
        planet.getMoonsArrayList().size());

    // Subtract 1 from the index to get the correct index in the arrayList
    moonIndex--;

    // Delete the moon
    planet.getMoonsArrayList().remove(moonIndex);
  }

  // TODO: Remake the print methods to be more general and one method for all prints

  private void printStars(ArrayList<SolarSystem> solarSystemsArrayList) {
    for (int i = 0; i < solarSystemsArrayList.size(); i++) {
      System.out.println(i + 1 + ": " + solarSystemsArrayList.get(i).getStar().getName());
    }
  }

  private void printPlanets(Star star) {
    for (int i = 0; i < star.getPlanetsArrayList().size(); i++) {
      System.out.println(i + 1 + ": " + star.getPlanetsArrayList().get(i).getName());
    }
  }

  private void printMoons(Planet planet) {
    for (int i = 0; i < planet.getMoonsArrayList().size(); i++) {
      System.out.println(i + 1 + ": " + planet.getMoonsArrayList().get(i).getName());
    }
  }

  private boolean checkEmptyArrayList(ArrayList<SolarSystem> solarSystemsArrayList, 
      Scanner scanner, String message, ArrayList<?> list) {
    if (list == null || list.isEmpty()) {
      System.out.println(message);

      // Ask what the user wants to do next. If the user still wants to delete
      // something, call the method again
      if (subMenu(scanner, "Delete a member of the solar system") == 1) {
        deleteMember(solarSystemsArrayList, scanner);
      }

      // Otherwise return to the main menu
      return true;
    }

    // If the list is not empty, return false
    return false;
  }

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

  /**
   * Method for getting an integer input from the user.
   *
   * @param scanner      - The scanner for getting user input
   * @param prompt       - The message that will be printed to the user
   * @param errorMessage - The message that will be printed if the user enters an
   *                     invalid input
   * @param maxValue     - The max value that the user can enter
   * @return - The integer that the user entered
   */
  public int getIntInput(Scanner scanner, String prompt, String errorMessage, int maxValue) {
    // Set the index to -1 first so the while loop starts, and will continue running
    // if no valid index is entered
    int input = -1;

    // Ask for input until a valid input is entered
    // Since the indexes that are printed to the user start at 1, the input must be
    // greater than 0 and less than or equal to the max value
    while (input <= 0 || input > maxValue) {
      try {
        System.out.print(prompt);
        input = scanner.nextInt();
        if (input <= 0 || input > maxValue) {
          System.out.println(errorMessage);
        }

        // If anything else than an integer is entered, the message will be printed and
        // the program will continue to ask for input 
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a valid integer.");

        // Clear the scanner from the invalid input (to avoid an infinite loop) 
        // so the scanner can register the next input from the user
        scanner.next();
      }
    }
    return input;
  }

  private int subMenu(Scanner scanner, String firstChoice) {
    // Ask what the user wants to do next
    System.out.println("\nWhat do you want to do next?");
    System.out.println("1: " + firstChoice);
    System.out.println("2: Return to the main menu");

    // Save the user's choice
    int choice = getIntInput(scanner, "", "Invalid index. Please enter a valid index.", 2);

    return choice;
  }
}

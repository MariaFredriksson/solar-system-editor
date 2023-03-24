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

  // TODO:
  // ^^ Is a constructor needed...?

  /**
   * The main menu of the program.
   *
   * @param solarSystemsArrayList - The arrayList containing all the solar systems
   * @return - The updated arrayList containing all the solar systems
   */
  public ArrayList<SolarSystem> mainMenu(ArrayList<SolarSystem> solarSystemsArrayList) {
    // Create an adding editor
    AddingEditor addingEditor = new AddingEditor();

    // Create an ordering editor
    OrderingEditor orderingEditor = new OrderingEditor();

    // Open a scanner
    Scanner scanner = new Scanner(System.in, "UTF-8");

    // Create a variable to save the user's choice
    int choice = 0;

    // While loop that keeps the program running until the user exits
    while (choice != 7) {
      // Print a menu
      System.out.println("\nPlease select an option:");
      System.out.println("1. List all solar systems");
      System.out.println("2. Delete a member of the solar system");
      System.out.println("3. Add a planet");
      System.out.println("4. Add a moon");
      System.out.println("5. Create a new solar system and a star");
      System.out.println("6. List all heavenly bodies in order");
      System.out.println("7. Exit\n");

      // Save the user's choice
      choice = getIntInput(scanner, "", "Invalid input. Please enter a valid input.", 7);

      // Switch statement that handles the user's choice
      switch (choice) {
        case 1:
          // List all solar systems
          System.out.println("List all solar systems\n");
          printAll(solarSystemsArrayList, scanner);
          break;
        case 2:
          // Delete a member of the solar system
          System.out.println("Delete a member of the solar system\n");
          deleteMember(solarSystemsArrayList, scanner);
          break;
        case 3:
          // Add a planet
          System.out.println("Add a planet\n");
          addingEditor.addPlanet(solarSystemsArrayList, scanner);
          break;
        case 4:
          // Add a moon
          System.out.println("Add a moon\n");
          addingEditor.addMoon(solarSystemsArrayList, scanner);
          break;
        case 5:
          System.out.println("Create a new solar system and a star\n");

          // Create a new solar system
          SolarSystem newSolarSystem = addingEditor.createSolarSystem(scanner);

          // If the solar system is not null, add it to the arrayList
          if (newSolarSystem != null) {
            solarSystemsArrayList.add(newSolarSystem);
          }
          break;
        case 6:
          // List all heavenly bodies in order
          System.out.println("List all heavenly bodies in order\n");
          orderingEditor.orderSystems(solarSystemsArrayList, scanner);
          break;
        case 7:
          // Exit
          System.out.println("Exit");
          break;
        default:
          // Invalid choice
          System.out.println("Invalid choice. Please try again.\n");
          break;
      }
    }

    // Close the scanner
    scanner.close();

    // Return the arrayList
    return solarSystemsArrayList;
  }

  /**
   * Prints all heavenly bodies in the solar systems.
   *
   * @param solarSystemsArrayList - The arrayList containing all the solar systems
   * @param scanner               - The scanner used to get input from the user
   */
  public void printAll(ArrayList<SolarSystem> solarSystemsArrayList, Scanner scanner) {
    // TODO: make a general method for checkEmptySolarSystemsArrayList
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
    
    // Tell the user that the deletion was successful
    System.out.println("The star was successfully deleted\n");
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

    // Tell the user that the deletion was successful
    System.out.println("The planet was successfully deleted\n");
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

    // Tell the user that the deletion was successful
    System.out.println("The moon was successfully deleted\n");
  }

  // TODO: Remake the print methods to be more general and one method for all prints

  /**
   * Prints all the stars in the solar system.
   *
   * @param solarSystemsArrayList - The arrayList containing all the solar systems
   */
  public void printStars(ArrayList<SolarSystem> solarSystemsArrayList) {
    for (int i = 0; i < solarSystemsArrayList.size(); i++) {
      System.out.println(i + 1 + ": " + solarSystemsArrayList.get(i).getStar().getName());
    }
  }

  /**
   * Prints all the planets in the solar system.
   *
   * @param star - The star containing the planets
   */
  public void printPlanets(Star star) {
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
      return true;
    }

    // If the list is not empty, return false
    return false;
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

  /**
   * Method for printing a sub menu.
   *
   * @param scanner     - The scanner for getting user input
   * @param firstChoice - The first choice that the user can make
   * @return - The choice that the user made
   */
  public int subMenu(Scanner scanner, String firstChoice) {
    // Ask what the user wants to do next
    System.out.println("\nWhat do you want to do next?");
    System.out.println("1: " + firstChoice);
    System.out.println("2: Return to the main menu");

    // Save the user's choice
    int choice = getIntInput(scanner, "", "Invalid index. Please enter a valid index.", 2);

    return choice;
  }
}

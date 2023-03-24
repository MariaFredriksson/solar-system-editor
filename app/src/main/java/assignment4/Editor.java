package assignment4;

import java.util.ArrayList;
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

    // Create a deleting editor
    DeletingEditor deletingEditor = new DeletingEditor();

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
          deletingEditor.deleteMember(solarSystemsArrayList, scanner);
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
    // Check that the solarSystemsArrayList is not empty
    if (checkEmptyArrayList("There are no solar systems.", solarSystemsArrayList)) {
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

  /**
   * Prints all the moons in the solar system.
   *
   * @param planet - The planet containing the moons
   */
  public void printMoons(Planet planet) {
    for (int i = 0; i < planet.getMoonsArrayList().size(); i++) {
      System.out.println(i + 1 + ": " + planet.getMoonsArrayList().get(i).getName());
    }
  }

  /**
   * Checks if the passed arrayList is empty.
   *
   * @param message - The message that will be printed if the arrayList is empty
   * @param list    - The arrayList that will be checked
   * @return - True if the arrayList is empty, false if it is not
   */
  public boolean checkEmptyArrayList(String message, ArrayList<?> list) {
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

package assignment4;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Editor {

  // ^^ Behövs en constructor...?

  // Method for printing all the solar systems
  public void printAll (ArrayList<SolarSystem> solarSystemsArrayList, Scanner scanner) {
    for (SolarSystem oneSystem : solarSystemsArrayList) {
      System.out.println(oneSystem.toString());
    }

    // Add delay before returning to the main menu, to make the output easier to read
    // addDelay(2000);

    // ^^ Or should the user have an option to return to the main menu or list all the solar systems again?
    // Ask the user what to do next
    int choice = subMenu(scanner, "List all solar systems");

    // If the user wants to list all solar systems again, the method will be called again
    if (choice == 1) {
      printAll(solarSystemsArrayList, scanner);
    }

    // If the user wants to return to the main menu, the method just continues and
    // ends, and thus returns to the main menu
  }

  // Method for adding a planet
  public void addPlanet (ArrayList<SolarSystem> solarSystemsArrayList, Scanner scanner) {
    // Ask which star to add the planet to
    System.out.println("Which star do you want to add the planet to?");
    for (int i = 0; i < solarSystemsArrayList.size(); i++) {
      System.out.println(i + 1 + ": " + solarSystemsArrayList.get(i).getStar().getName());
    }
          
    // Ask for the index of the star
    // Leave the prompt empty, since the prompt is already printed close to the for loop above
    int starIndex = getIntInput(scanner, "", "Invalid index. Please enter a valid index.", solarSystemsArrayList.size());

    // Subtract 1 from the index to get the correct index in the arrayList
    starIndex--;

    // Ask for the name of the planet
    System.out.println("Information about the planet you want to add:");
    System.out.print("Name: ");
    String name = scanner.next();

    // Ask for the radius of the planet
    // Set the max value to Integer.MAX_VALUE just to set it to a very high number, so the starIndex can have a maxValue that is important to it
    int radius = getIntInput(scanner, "Radius in km: ", "Radius cannot be negative. Please enter a valid radius.", Integer.MAX_VALUE);
    
    // Ask for the orbit radius of the planet
    int orbitRadius = getIntInput(scanner, "Orbit radius in km: ", "Orbit radius cannot be negative. Please enter a valid orbit radius.", Integer.MAX_VALUE);

    // Add the planet to the star
    try {
      solarSystemsArrayList.get(starIndex).getStar().addPlanet(name, radius, orbitRadius);
      System.out.println("The planet " + name + " was successfully added to the star " + solarSystemsArrayList.get(starIndex).getStar().getName());

      // If the name, radius or orbit radius is invalid, the message will be printed
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      System.out.println("The planet could not be added.");
    }

    // Add a delay before returning to the main menu to make it easier to read the output
    // addDelay(2000);

    // ^^ Or should the user have an option to return to the main menu or add another planet?
    // Ask the user what to do next
    int choice = subMenu(scanner, "Add a planet");

    // If the user wants to add a planet, the method will be called again
    if (choice == 1) {
      addPlanet(solarSystemsArrayList, scanner);
    }

    // If the user wants to return to the main menu, the method just continues and ends, and thus returns to the main menu
  }

  // Method for adding a moon
  public void addMoon(ArrayList<SolarSystem> solarSystemsArrayList, Scanner scanner) {
    // Ask which star to add the moon to
    System.out.println("Which star do you want to add the moon to?");
    for (int i = 0; i < solarSystemsArrayList.size(); i++) {
      System.out.println(i + 1 + ": " + solarSystemsArrayList.get(i).getStar().getName());
    }

    // Ask for the index of the star
    // Leave the prompt empty, since the prompt is already printed close to the for
    // loop above
    int starIndex = getIntInput(scanner, "", "Invalid index. Please enter a valid index.", solarSystemsArrayList.size());

    // Subtract 1 from the index to get the correct index in the arrayList
    starIndex--;

    // Ask which planet to add the moon to
    System.out.println("Which planet do you want to add the moon to?");
    for (int i = 0; i < solarSystemsArrayList.get(starIndex).getStar().getPlanetsArrayList().size(); i++) {
      System.out.println(i + 1 + ": " + solarSystemsArrayList.get(starIndex).getStar().getPlanetsArrayList().get(i).getName());
    }

    // Ask for the index of the planet
    // Leave the prompt empty, since the prompt is already printed close to the for
    // loop above
    int planetIndex = getIntInput(scanner, "", "Invalid index. Please enter a valid index.",
        solarSystemsArrayList.get(starIndex).getStar().getPlanetsArrayList().size());

    // Subtract 1 from the index to get the correct index in the arrayList
    planetIndex--;

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
      solarSystemsArrayList.get(starIndex).getStar().getPlanetsArrayList().get(planetIndex).addMoon(name, radius, orbitRadius);
      System.out.println("The moon " + name + " was successfully added to the planet "
          + solarSystemsArrayList.get(starIndex).getStar().getPlanetsArrayList().get(planetIndex).getName());

      // If the name, radius or orbit radius is invalid, the message will be printed
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      System.out.println("The moon could not be added.");
    }

    // Ask the user what to do next
    int choice = subMenu(scanner, "Add a moon");

    // If the user wants to add a moon, the method will be called again
    if (choice == 1) {
      addMoon(solarSystemsArrayList, scanner);
    }

    // If the user wants to return to the main menu, the method just continues and
    // ends, and thus returns to the main menu
  }

  // Method for creating a new solar system
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
    int radius = getIntInput(scanner, "Radius in km: ", "Radius cannot be negative. Please enter a valid radius.", Integer.MAX_VALUE);

    try {
      // Add a new solar system
      SolarSystem newSolarSystem = new SolarSystem(solarSystemName);

      // Add the star to the solar system
      newSolarSystem.addStar(starName, radius);

      // Print a confirmation message that the solar system and the star were added
      // successfully
      System.out.println("The solar system " + solarSystemName + " containing the star " + starName + " was successfully added");

      return newSolarSystem;

      // If the name, radius or orbit radius is invalid, the message will be printed
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      System.out.println("The solar system and the star could not be added.");

      // Return null if the solar system could not be added
      return null;
    }
  }

  public SolarSystem deleteMember(ArrayList<SolarSystem> solarSystemsArrayList, Scanner scanner) {
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
        SolarSystem systemToDelete = deleteStar(solarSystemsArrayList, scanner);

        // Return the solar system the star is in, so the whole solar system can be deleted in App.js
        return systemToDelete;
      case 2:
        // Delete a planet
        // deletePlanet(solarSystemsArrayList, scanner);
        break;
      case 3:
        // Delete a moon
        // deleteMoon(solarSystemsArrayList, scanner);
        break;
      default:
        // Invalid choice
        System.out.println("Invalid choice. Please try again.\n");
        break;
    } 

    // Return null, since the whole solar system is not deleted
    return null;

    // // Ask which member of the solar system the user wants to delete
    // System.out.println("Which member of the solar system do you want to delete?");
    // int counter = 1;
    // ArrayList<HeavenlyBody> allHeavenlyBodies = new ArrayList<HeavenlyBody>();

    // // Make a loop to go through the arrayList of solar systems
    // for (int i = 0; i < solarSystemArrayList.size(); i++) {
    //   // Make another for loop to print the star, the planets of the star, and the moons of planets
    //   for (HeavenlyBody heavenlyBody : solarSystemArrayList.get(i).getStar().getHeavenlyBodies()) {
    //     System.out.println(counter + ": " + heavenlyBody.getName());
    //     counter++;

    //     // Add the heavenly body to the arrayList
    //     allHeavenlyBodies.add(heavenlyBody);
    //   }
    // }

    // // Save the index of the member the user wants to delete
    // int memberIndex = getIntInput(scanner, "", "Invalid index. Please enter a valid index.", allHeavenlyBodies.size());

    // // Subtract 1 from the index to get the correct index in the arrayList
    // memberIndex--;

    // // Delete the member
    // allHeavenlyBodies.remove(memberIndex);
    // System.out.println("The member was successfully deleted.");
  }

  private SolarSystem deleteStar(ArrayList<SolarSystem> solarSystemsArrayList, Scanner scanner) {
    // Ask which star the user wants to delete
    System.out.println("Which star do you want to delete?");
    for (int i = 0; i < solarSystemsArrayList.size(); i++) {
      System.out.println(i + 1 + ": " + solarSystemsArrayList.get(i).getStar().getName());
    }

    int starIndex = getIntInput(scanner, "", "Invalid index. Please enter a valid index.",
        solarSystemsArrayList.size());

    // Subtract 1 from the index to get the correct index in the arrayList
    starIndex--;

    // Return the solar system the star is in, so the whole solar system can be deleted in App.js
    return solarSystemsArrayList.get(starIndex);
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

  // private void addDelay(int milliseconds) {
  //   try {
  //     Thread.sleep(milliseconds);
  //   } catch (InterruptedException e) {
  //     e.printStackTrace();
  //   }
  // }

  private int getIntInput(Scanner scanner, String prompt, String errorMessage, int maxValue) {
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

        // Clear the scanner from the invalid input (to avoid an infinite loop) so the scanner can register the next input from the user
        scanner.next();
      }
    }
    return input;
  }
}

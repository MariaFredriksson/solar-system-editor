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

    // Add delay to make the output easier to read
    addDelay(2000);
  }

  // Method for adding a planet
  public void addPlanet (ArrayList<SolarSystem> solarSystemArrayList, Scanner scanner) {
    // Ask which star to add the planet to
    System.out.println("Which star do you want to add the planet to?");
    for (int i = 0; i < solarSystemArrayList.size(); i++) {
      System.out.println(i + 1 + ": " + solarSystemArrayList.get(i).getStar().getName());
    }
    
    // Set the index to -1 first so the while loop starts, and will continue running if no valid index is entered
    // int starIndex = -1;
    // while (!validateIntInput(scanner, "Invalid index. Please enter a valid index.", solarSystemArrayList.size(), starIndex)) {
      //   System.out.print("Enter the index of the star: ");
      // }
      
      // // Subtract 1 from the index to get the correct index in the arrayList
      // starIndex--;
      

      
      // // Ask for the radius of the planet
      // int radius = -1;
      // while (!validateIntInput(scanner, "Radius cannot be negative. Please enter a valid radius.", Integer.MAX_VALUE, radius)) {
        //   System.out.print("Radius in km: ");
        // }
        
        // // Ask for the orbit radius of the planet
        // int orbitRadius = -1;
        // while (!validateIntInput(scanner, "Orbit radius cannot be negative. Please enter a valid orbit radius.", Integer.MAX_VALUE, orbitRadius)) {
          //   System.out.print("Orbit radius in km: ");
          // }
          
    // Ask for the index of the star
    // Leave the prompt empty, since the prompt is already printed close to the for loop above
    int starIndex = getIntInput(scanner, "", "Invalid index. Please enter a valid index.", solarSystemArrayList.size());

    // Subtract 1 from the index to get the correct index in the arrayList
    starIndex--;

    // Ask for the name of the planet
    System.out.println("Information about the planet you want to add:");
    System.out.print("Name: ");
    String name = scanner.next();

    // Ask for the name of the planet
    int radius = getIntInput(scanner, "Radius in km: ", "Radius cannot be negative. Please enter a valid radius.", Integer.MAX_VALUE);
    
    // Ask for the orbit radius of the planet
    int orbitRadius = getIntInput(scanner, "Orbit radius in km: ", "Orbit radius cannot be negative. Please enter a valid orbit radius.", Integer.MAX_VALUE);

    // Add the planet to the star
    try {
      solarSystemArrayList.get(starIndex).getStar().addPlanet(name, radius, orbitRadius);
      System.out.println("The planet " + name + " was successfully added to the star " + solarSystemArrayList.get(starIndex).getStar().getName());

      // If the name, radius or orbit radius is invalid, the message will be printed and the program will return to the main menu
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }

    // Add a delay before returning to the main menu to make it easier to read the output
    addDelay(2000);
  }

  private void addDelay(int milliseconds) {
    try {
      Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private int getIntInput(Scanner scanner, String prompt, String errorMessage, int maxValue) {
    int input = -1;
    while (input < 0 || input >= maxValue) {
      try {
        System.out.print(prompt);
        input = scanner.nextInt();
        if (input < 0 || input >= maxValue) {
          System.out.println(errorMessage);
        }
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a valid integer.");
        scanner.next();
      }
    }
    return input;
  }

  // public boolean validateIntInput(Scanner scanner, String errorMessage, int maxValue, int input) {
  //   try {
  //     input = scanner.nextInt();
  //     if (input < 0 || input >= maxValue) {
  //       System.out.println(errorMessage);
  //       return false;
  //     }
  //     return true;

  //     // If anything else than an integer is entered, the message will be printed and the program will continue to ask for input (through the wile loop where the method is called)
  //   } catch (InputMismatchException e) {
  //     System.out.println("Invalid input. Please enter a valid index.");

  //     // Clear the scanner from the invalid input, to avoid an infinite loop
  //     scanner.next();
  //     return false;
  //   }
  // }
}

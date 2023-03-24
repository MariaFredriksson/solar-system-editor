package assignment4;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class representing an editor that can delete members of a solar system.
 */
public class DeletingEditor extends Editor {
  /**
   * Deletes a member from a solar system.
   *
   * @param solarSystemsArrayList - The arrayList containing all the solar systems
   * @param scanner               - The scanner used to get input from the user
   */
  public void deleteMember(ArrayList<SolarSystem> solarSystemsArrayList, Scanner scanner) {
    // Check that the solarSystemsArrayList is not empty
    if (checkEmptyArrayList("There are no solar systems.", solarSystemsArrayList)) {
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
    if (checkEmptyArrayList("This star has no planets.", star.getPlanetsArrayList())) {
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
    if (checkEmptyArrayList("This star has no planets.", star.getPlanetsArrayList())) {
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
    if (checkEmptyArrayList("This planet has no moons.", planet.getMoonsArrayList())) {
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
}

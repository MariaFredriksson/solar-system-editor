/**
 * This Java source file was generated by the Gradle 'init' task.
 */

package assignment4;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the generated Hello World Greeting App.
 */
public class App {

  /**
   * Returns a nice greeting message.
   *
   * @return the greeting message.
   */
  public String getGreeting() {
    return "Hello World!!";
  }

  /**
   * The App starting point.
   *
   * @param args Unused program arguments.
   */
  public static void main(String[] args) {
    App theApp = new App();
    System.out.println(theApp.getGreeting());

    // Create a new instance of the Editor class
    Editor editor = new Editor();

    // Create an arrayList to save all the solar systems
    ArrayList<SolarSystem> solarSystemsArrayList = new ArrayList<>();

    // Create a new solar system and add it to the arrayList
    SolarSystem solarSystem = new SolarSystem("Solar System");
    solarSystemsArrayList.add(solarSystem);

    // Add a star to the solar system
    Star star = solarSystem.addStar("Sun", 696342);

    // Add a planet to the sun
    Planet planet = star.addPlanet("Earth", 6371, 149600000);

    // Add a moon to the planet
    planet.addMoon("Moon", 1737, 384400);

    // Print the solar system
    // System.out.println(solarSystem.toString());

    // Create a new solar system and add it to the arrayList
    SolarSystem solarSystem2 = new SolarSystem("New Solar System");
    solarSystemsArrayList.add(solarSystem2);

    // Add a star to the solar system2
    Star star2 = solarSystem2.addStar("Proxima Centauri", 118000);

    // Add planets to the star
    Planet planet1 = star2.addPlanet("Proxima b", 6356, 70000000);

    // Add moons to planet1
    planet1.addMoon("Proxima b I", 1638, 170000);
    planet1.addMoon("Proxima b II", 1843, 220000);
    planet1.addMoon("Proxima b III", 2381, 280000);

    Planet planet2 = star2.addPlanet("Proxima c", 8495, 125000000);

    // Add moons to planet2
    planet2.addMoon("Proxima c I", 1794, 190000);
    planet2.addMoon("Proxima c II", 2098, 230000);

    // Loop through the solar systems and print them
    // for (SolarSystem system : solarSystemsArrayList) {
    //   System.out.println(system.toString());
    // }

    // ^^ Read from file

    // Print a welcome message
    System.out.println("Welcome to the Solar System .......!");

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
      choice = scanner.nextInt();

      // TODO: Add some error handling for the user's input

      // Switch statement that handles the user's choice
      switch (choice) {
        case 1:
          // List all solar systems
          System.out.println("List all solar systems\n");
          editor.printAll(solarSystemsArrayList, scanner);
          break;
        case 2:
          // Delete a member of the solar system
          System.out.println("Delete a member of the solar system\n");
          editor.deleteMember(solarSystemsArrayList, scanner);
          break;
        case 3:
          // Add a planet
          System.out.println("Add a planet\n");
          editor.addPlanet(solarSystemsArrayList, scanner);
          break;
        case 4:
          // Add a moon
          System.out.println("Add a moon\n");
          editor.addMoon(solarSystemsArrayList, scanner);
          break;
        case 5:
          System.out.println("Create a new solar system and a star\n");

          // Create a new solar system
          SolarSystem newSolarSystem = editor.createSolarSystem(scanner);

          // If the solar system is not null, add it to the arrayList
          if (newSolarSystem != null) {
            solarSystemsArrayList.add(newSolarSystem);
          }
          break;
        case 6:
          // List all heavenly bodies in order
          System.out.println("List all heavenly bodies in order\n");
          editor.orderSystems(solarSystemsArrayList, scanner);
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

    // ^^ Exit program by breaking the while loop...?

    // Close the scanner
    scanner.close();

    // ^^ Then save the solar systems to a file

    // Print a goodbye message
    System.out.println("Goodbye!");

    ///// 1. You should be able to create a new solar system with one and only one
    ///// central star, planets orbiting the star
    ///// and moons orbiting the planets.
    ///// 1.1. All should have a name and an average radius.
    ///// 1.1.1. There are max an min limits for the radii depending on the type of
    ///// heavenly body. You decide these
    ///// limits, document in your README.md.
    ///// 1.2. The planets and the moons have an average orbiting radius.
    ///// 1.2.1. The planet and moons have max and min limits on the radius. E.g.
    ///// the moon cannot be so close so it
    ///// collides with the planet. You decide these limits, document your README.md
    ///// 2. You should be able to list all solar systems.
    ///// 3. You should be able to select a particular solar system see detailed
    ///// information about it in a hierarchal way.
    ///// I.e. the star on top then the planet with the respective moons. You should
    ///// then be able to:
    ///// 3.1. delete a member of the solar system.
    ///// 3.1.1. If the sun is deleted everything is deleted, if a planet is deleted
    ///// the moons of the planet are
    ///// deleted.
    ///// 3.2. Add a planet or moon to the solar system. The rules should of course
    ///// apply.
    ///// 3.3. You may handle this hierarchically, e.g. selecting the solar system,
    ///// then selecting a planet, then
    ///// selecting a moon and performing the above operations.
    ///// 3.4. There should be at least 2 ways of ordering the solar system
    ///// information.
    ///// 3.4.1. By size
    ///// 3.4.2. By orbital radius (closer first)
    ///// 4. You should be able to quit the application
    // 5. The registry information should be loaded from a file solarsystems.data
    // when the application starts.
    // The format is specified below.
    // 6. The registry information should be saved to a file solarsystems.data when
    // the application exits. The
    // format is specified below.
    // 7. There should be at least one class diagram showing the application
    // structure with all classes and correct
    // relations between the classes. You do not need to add every operation or
    // attribute in the class diagram.
    // 8. Basic error handling, i.e. it should not crash. No need for user friendly
    // error messages.
  }
}

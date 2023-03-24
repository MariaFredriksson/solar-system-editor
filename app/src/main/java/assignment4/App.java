/**
 * This Java source file was generated by the Gradle 'init' task.
 */

package assignment4;

import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

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
    // App theApp = new App();
    // System.out.println(theApp.getGreeting());

    try {
      // Get the root directory
      String rootDirectory = System.getProperty("user.dir");
      System.out.println("Current dir: " + rootDirectory);

      // It the root directory contains app, remove it and the slash before it
      if (rootDirectory.contains("app")) {
        rootDirectory = rootDirectory.substring(0, rootDirectory.indexOf("app") - 1);
      }

      System.out.println("Current dir: " + rootDirectory);

      // Create a path to the file
      Path path = Paths.get(rootDirectory, "app", "src", "main", "java", "assignment4", "data", "solarsystems.data");

      // Create a charset
      Charset cs = Charset.forName("UTF-8");

      // Create a new instance of the FileHandler class
      FileHandler fileHandler = new FileHandler();

      // Create an arrayList to save all the solar systems from the file
      ArrayList<SolarSystem> solarSystemsArrayList = fileHandler.readFile(path, cs);

      // Print a welcome message
      System.out.println("Welcome to the Solar System Manager!");

      // Create a new instance of the Editor class
      Editor editor = new Editor();

      // Start the main menu, and when the user exits, save the solar systems to the arraylist
      solarSystemsArrayList = editor.mainMenu(solarSystemsArrayList);

      // Save the solar systems to a file
      fileHandler.writeFile(path, cs, solarSystemsArrayList);

      // Print a goodbye message
      System.out.println("Goodbye!");

    } catch (Exception e) {
      System.out.println("An error occurred: " + e.getMessage() + "\nPlease try again.");
    }

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
    ///// 5. The registry information should be loaded from a file solarsystems.data
    ///// when the application starts.
    ///// The format is specified below.
    ///// 6. The registry information should be saved to a file solarsystems.data when
    ///// the application exits. The
    ///// format is specified below.
    // 7. There should be at least one class diagram showing the application
    // structure with all classes and correct
    // relations between the classes. You do not need to add every operation or
    // attribute in the class diagram.
    ///// 8. Basic error handling, i.e. it should not crash. No need for user friendly
    ///// error messages.
  }
}

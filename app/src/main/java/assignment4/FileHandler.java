package assignment4;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for handling files.
 */
public class FileHandler {
  /**
   * Read a file and return an arraylist with the solar systems.
   *
   * @param path - Path to the file
   * @param cs   - Charset
   * @return - Arraylist with the solar systems
   */
  public ArrayList<SolarSystem> readFile(Path path, Charset cs) {
    try {
      // Read all the lines from the file
      List<String> lines = Files.readAllLines(path, cs);
      
      // Create an arraylist with the solar systems
      ArrayList<SolarSystem> solarSystemsArrayList = new ArrayList<>();

      // Create a star and a planet, so planets and moons can be added to them
      Star star = null;
      Planet planet = null;

      // Loop through the list and create objects
      for (int i = 0; i < lines.size(); i++) {
        // Check if the line is empty
        if (lines.get(i).isBlank()) {
          continue;
        }

        // Split the line into parts
        String[] parts = lines.get(i).split(":");
        String name = parts[0];
        int radius = Integer.parseInt(parts[1]);

        // If the line contains 2 parts, it's a star
        if (parts.length == 2) {
          // Create a new solar system with the name of the star
          SolarSystem solarSystem = new SolarSystem(name + " system");

          // Add the solar system to the arraylist
          solarSystemsArrayList.add(solarSystem);

          // Create a new star and add it to the solar system
          star = solarSystem.addStar(name, radius);

          // If the line contains 3 parts, it's either a planet or a moon
        } else if (parts.length == 3) {
          double orbitRadius = Double.parseDouble(parts[2]);

          // If the name starts with two hyphens, it's a moon
          // (This if statement needs to be first since the start of the name of the moons
          // is also one hyphen
          // (but then another one)), so the moons would otherwise be added as planets)
          if (name.startsWith("--")) {
            // Remove the two hyphens from the name
            name = name.substring(2);

            // Create a new moon and add it to the planet
            planet.addMoon(name, radius, orbitRadius);

            // If the name starts with only one hyphen, it's a planet
          } else if (name.startsWith("-")) {
            // Remove the hyphen from the name
            name = name.substring(1);

            // Create a new planet and add it to the star
            planet = star.addPlanet(name, radius, orbitRadius);

          } else {
            // Throw an exception to quit the program
            throw new RuntimeException("The file is not formatted correctly. The line "
                + (i + 1) + " is not formatted correctly.");
          }
        } else {
          // Throw an exception to quit the program
          throw new RuntimeException("The file is not formatted correctly. The line "
              + (i + 1) + " is not formatted correctly.");
        }
      }

      // Return the arraylist with the solar systems
      return solarSystemsArrayList;

    } catch (IOException e) {
      // Throw an exception to quit the program
      throw new RuntimeException("Could not read file");
    }
  }

  /**
   * Write a file with the solar systems.
   *
   * @param path - Path to the file
   * @param cs   - Charset
   * @param solarSystemsArrayList - Arraylist with the solar systems
   */
  public void writeFile(Path path, Charset cs, ArrayList<SolarSystem> solarSystemsArrayList) {
    // Create a list with the lines to write to the file
    List<String> lines = new ArrayList<>();

    // Loop through the solar systems
    for (SolarSystem solarSystem : solarSystemsArrayList) {
      // Get the star from the solar system
      Star star = solarSystem.getStar();

      // Add the star to the list
      lines.add(star.getName() + ":" + star.getAvgRadiusInKm());

      // Loop through the planets
      for (Planet planet : star.getPlanetsArrayList()) {
        // Add the planet to the list
        lines.add("-" + planet.getName() + ":" + planet.getAvgRadiusInKm() + ":" + planet.getAvgOrbitRadiusInKm());

        // Loop through the moons
        for (Moon moon : planet.getMoonsArrayList()) {
          // Add the moon to the list
          lines.add("--" + moon.getName() + ":" + moon.getAvgRadiusInKm() + ":" + moon.getAvgOrbitRadiusInKm());
        }
      }
    }

    // Write the list to the file
    try {
      Files.write(path, lines, cs);
    } catch (IOException e) {
      // Throw an exception to quit the program
      throw new RuntimeException("Could not write to file");
    }
  }
}
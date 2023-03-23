package assignment4;

// import java.io.BufferedReader;
// import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
// import java.rmi.registry.Registry;
// ^^ Tips from Jimmy
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
  public static ArrayList<SolarSystem> readFile(Path path, Charset cs) {
    List<String> lines = new ArrayList<>();
    try {
      lines = Files.readAllLines(path, cs);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      //^^ Vad vill jag göra om jag inte kan läsa min fil?
      //^^ Kanske starta om på något sätt?
      //^^ Kankske stänga av hela programmet?
    }
    
    // ^^ Skapa en arraylist med solsystemen
    ArrayList<SolarSystem> solarSystemsArrayList = new ArrayList<>();

    //^^ Iterera igenom listan och skapa objekt
    // Loop through the list and create objects
    // int index = 0;
    // Create a star and a planet, so planets and moons can be added to them
    Star star = null;
    Planet planet = null;

    for (int i = 0; i < lines.size(); i++) {
      // ^^ Kolla så att inte raden är tom
      // Check if the line is empty
      if (lines.get(i).isBlank()) {
        continue;
      }

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

      } else if (parts.length == 3) {
        // If the line contains 3 parts, it's either a planet or a moon
        double orbitRadius = Double.parseDouble(parts[2]);

        // If the name starts with two hyphens, it's a moon
        // (This if statement needs to be first since the start of the name of the moons is also one hyphen 
        // (but then another one)), so the moons would otherwise be added as planets)
        if (name.startsWith("--")) {
          // Remove the two hyphens from the name
          name = name.substring(2);

          // Create a new moon and add it to the planet
          planet.addMoon(name, radius, orbitRadius);
          
          // If the name starts with one hyphen, it's a planet
        } else if (name.startsWith("-")) {
          // Remove the hyphen from the name
          name = name.substring(1);

          // Create a new planet and add it to the star
          planet = star.addPlanet(name, radius, orbitRadius);

        } else {
          // ^^ What should happen if a line is not formatted correctly?
        }
      } else {
        // ^^ What should happen if a line is not formatted correctly?
      }

      // index++;
    }

    // // ^^ Test to print the solar systems
    // for (SolarSystem solarSystem : solarSystemsArrayList) {
    //   System.out.println(solarSystem.toString());
    // }

    // Return the arraylist with the solar systems
    return solarSystemsArrayList;
  }

  // TODO write och options create och truncate existing 

  /**
   * Write a file with the solar systems.
   *
   * @param path - Path to the file
   * @param cs   - Charset
   * @param solarSystemsArrayList - Arraylist with the solar systems
   */
  public static void writeFile(Path path, Charset cs, ArrayList<SolarSystem> solarSystemsArrayList) {
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
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  // public static void loadFile(String filename, Registry registry) throws IOException {
  //   try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
  //     String line;
  //     while ((line = br.readLine()) != null) {
  //       String[] parts = line.split(":");
  //       String name = parts[0];
  //       double radius = Double.parseDouble(parts[1]);
  //       if (parts.length == 2) {
  //         registry.addStar(new Star(name, radius));
  //       } else if (parts.length == 3) {
  //         double orbitRadius = Double.parseDouble(parts[2]);
  //         registry.addPlanet(name, radius, orbitRadius);
  //       } else if (parts.length == 4) {
  //         double orbitRadius = Double.parseDouble(parts[3]);
  //         registry.addMoon(parts[1], name, radius, orbitRadius);
  //       }
  //     }
  //   }
  // }

  // private static void parseCelestialObjects(String[] parts, int startIndex, Registry registry,
  //     CelestialObject parent) {
  //   for (int i = startIndex; i < parts.length; i += 2) {
  //     String name = parts[i];
  //     double radius = Double.parseDouble(parts[i + 1]);
  //     if (i == startIndex) {
  //       registry.addPlanet(name, radius, parent);
  //     } else {
  //       registry.addMoon(name, radius, parent);
  //     }
  //     CelestialObject celestialObject = registry.getCelestialObject(name);
  //     parseCelestialObjects(parts, i + 2, registry, celestialObject);
  //   }
  // }

  // public static void saveRegistry(String filename, Registry registry) throws IOException {
  //   try (FileOutputStream fos = new FileOutputStream(filename);
  //       ObjectOutputStream oos = new ObjectOutputStream(fos)) {
  //     oos.writeObject(registry);
  //   }
  // }

  // public static void saveFile(String filename, Registry registry) throws IOException {
  //   try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
  //     for (Star star : registry.getStars()) {
  //       bw.write(star.getName() + ":" + star.getAvgRadiusInKm());
  //       bw.newLine();
  //       for (Planet planet : star.getPlanetsArrayList()) {
  //         bw.write(planet.getName() + ":" + planet.getAvgRadiusInKm() + ":" + planet.getAvgOrbitRadiusInKm());
  //         bw.newLine();
  //         for (Moon moon : planet.getMoonsArrayList()) {
  //           bw.write(moon.getName() + ":" + moon.getAvgRadiusInKm() + ":" + moon.getAvgOrbitRadiusInKm());
  //           bw.newLine();
  //         }
  //       }
  //     }
  //   }
  // }
}

// package assignment4;

// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;
// import java.nio.charset.Charset;
// import java.nio.file.Path;
// import java.rmi.registry.Registry;
// import java.util.ArrayList;
// import java.util.List;
// // ^^ Tips from Jimmy
// import java.nio.file.Files;

// public class FileHandler {
//   public void readFile(Path path, Charset cs) {
//     List<String> lines = new ArrayList<>();
//     try {
//       lines = Files.readAllLines(path, cs);
//     } catch (IOException e) {
//       // TODO Auto-generated catch block
//       e.printStackTrace();
//       //^^ Vad vill jag göra om jag inte kan läsa min fil?
//       //^^ Kanske starta om på något sätt?
//       //^^ Kankske stänga av hela programmet?
//     }
    
//     //^^ Iterera igenom listan och skapa objekt
//   }

//   // public static void loadFile(String filename, Registry registry) throws IOException {
//   //   try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
//   //     String line;
//   //     while ((line = br.readLine()) != null) {
//   //       String[] parts = line.split(":");
//   //       String name = parts[0];
//   //       double radius = Double.parseDouble(parts[1]);
//   //       if (parts.length == 2) {
//   //         registry.addStar(new Star(name, radius));
//   //       } else if (parts.length == 3) {
//   //         double orbitRadius = Double.parseDouble(parts[2]);
//   //         registry.addPlanet(name, radius, orbitRadius);
//   //       } else if (parts.length == 4) {
//   //         double orbitRadius = Double.parseDouble(parts[3]);
//   //         registry.addMoon(parts[1], name, radius, orbitRadius);
//   //       }
//   //     }
//   //   }
//   // }

//   // private static void parseCelestialObjects(String[] parts, int startIndex, Registry registry,
//   //     CelestialObject parent) {
//   //   for (int i = startIndex; i < parts.length; i += 2) {
//   //     String name = parts[i];
//   //     double radius = Double.parseDouble(parts[i + 1]);
//   //     if (i == startIndex) {
//   //       registry.addPlanet(name, radius, parent);
//   //     } else {
//   //       registry.addMoon(name, radius, parent);
//   //     }
//   //     CelestialObject celestialObject = registry.getCelestialObject(name);
//   //     parseCelestialObjects(parts, i + 2, registry, celestialObject);
//   //   }
//   // }

//   // public static void saveRegistry(String filename, Registry registry) throws IOException {
//   //   try (FileOutputStream fos = new FileOutputStream(filename);
//   //       ObjectOutputStream oos = new ObjectOutputStream(fos)) {
//   //     oos.writeObject(registry);
//   //   }
//   // }

//   // public static void saveFile(String filename, Registry registry) throws IOException {
//   //   try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
//   //     for (Star star : registry.getStars()) {
//   //       bw.write(star.getName() + ":" + star.getAvgRadiusInKm());
//   //       bw.newLine();
//   //       for (Planet planet : star.getPlanetsArrayList()) {
//   //         bw.write(planet.getName() + ":" + planet.getAvgRadiusInKm() + ":" + planet.getAvgOrbitRadiusInKm());
//   //         bw.newLine();
//   //         for (Moon moon : planet.getMoonsArrayList()) {
//   //           bw.write(moon.getName() + ":" + moon.getAvgRadiusInKm() + ":" + moon.getAvgOrbitRadiusInKm());
//   //           bw.newLine();
//   //         }
//   //       }
//   //     }
//   //   }
//   // }
// }

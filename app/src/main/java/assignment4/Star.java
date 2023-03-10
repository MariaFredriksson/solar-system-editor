package assignment4;

import java.util.ArrayList;

/**
 * A class representing a star.
 */
public class Star extends HeavenlyBody {
  private ArrayList<Planet> planets = new ArrayList<>();

  public Star(String name, int avgRadiusInKm) {
    super(name, avgRadiusInKm);
  }

  /**
   * Adds a new planet to the list of planets orbiting this star.
   *
   * @param name               the name of the planet
   * @param avgRadiusInKm      the average radius of the planet in kilometers
   * @param avgOrbitRadiusInKm the average orbit radius of the planet in
   *                           kilometers
   * @return the newly created planet object
   */
  public Planet addPlanet(String name, int avgRadiusInKm, double avgOrbitRadiusInKm) {
    Planet newPlanet = new Planet(name, avgRadiusInKm, avgOrbitRadiusInKm);
    planets.add(newPlanet);
    return newPlanet;
  }

  /**
   * Returns an array of all the heavenly bodies (planets and moons) in this star
   * system.
   *
   * @return an array of all the heavenly bodies in this star system
   */
  public HeavenlyBody[] getHeavenlyBodies() {
    int lengthOfPlanetsAndMoons = 0;
    // Get to know how many planets and moons there are by looping through 
    // the planets and add the length of the heavenlyBodiesArray for the planet
    for (Planet planet : planets) {
      lengthOfPlanetsAndMoons += planet.getHeavenlyBodies().length;
    }

    // Make an array
    HeavenlyBody[] heavenlyBodiesArray = new HeavenlyBody[lengthOfPlanetsAndMoons + 1];

    // Add the star by creating a new star via the constructor
    heavenlyBodiesArray[0] = new Star(this.getName(), this.getAvgRadiusInKm());

    // Keep track of the index of where to add something next
    int indexInArray = 1;

    // Loop through the planets arrayList 
    for (Planet planet : planets) {
      // Get the array with copies of planets and moons from each planet object
      HeavenlyBody[] planetsheavenlyBodiesArray = planet.getHeavenlyBodies();

      // Loop through the array with copies of planet and its moons, and add to the array
      for (HeavenlyBody planetsAndMoons : planetsheavenlyBodiesArray) {
        heavenlyBodiesArray[indexInArray] = planetsAndMoons;
        indexInArray++;
      }
    }

    // Return the array
    return heavenlyBodiesArray;
  }

  @Override
  protected boolean checkAvgRadiusInKm(int radius) {
    // If the radius is not within the allowed range, return false
    return !(radius < 16700);
  }

  @Override
  public String toString() {
    // Create a string and add the star
    String stringToPrint = "Star: " + this.getName() + ", average radius " + this.getAvgRadiusInKm() + "km\n";

    HeavenlyBody[] arrayToPrint = this.getHeavenlyBodies();

    // Start at index 1 to exclude the star (on index 0)
    for (int i = 1; i < arrayToPrint.length; i++) {
      stringToPrint += arrayToPrint[i].toString();
    }

    // Return the string
    return stringToPrint;
  }
}

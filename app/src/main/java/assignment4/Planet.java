package assignment4;

import java.util.ArrayList;

/**
 * The Planet class represents a planet in the solar system, and extends the
 * HeavenlyBody class.
 * It contains information about the average orbit radius of the planet, as well
 * as a list of its moons.
 */
public class Planet extends HeavenlyBody {
  private double avgOrbitRadiusInKm;
  private ArrayList<Moon> moons = new ArrayList<>();

  protected Planet(String name, int avgRadiusInKm, double avgOrbitRadiusInKm) {
    super(name, avgRadiusInKm);
    setAvgOrbitRadiusInKm(avgOrbitRadiusInKm);
  }

  private void setAvgOrbitRadiusInKm(double orbitRadius) {
    // The planets must have an orbit radius of at least 18000km and twice of the planet's radius.
    if (orbitRadius < 18000 || orbitRadius < this.getAvgRadiusInKm() * 2) {
      throw new IllegalArgumentException("The orbit radius is too small.");
    }
    this.avgOrbitRadiusInKm = orbitRadius;
  }

  /**
   * Adds a new moon to the list of moons for the planet with the specified name,
   * average radius, and average orbit radius.
   *
   * @param name               the name of the moon
   * @param avgRadiusInKm      the average radius of the moon, in kilometers
   * @param avgOrbitRadiusInKm the average orbit radius of the moon, in kilometers
   * @return the newly created moon object
   */
  public Moon addMoon(String name, int avgRadiusInKm, double avgOrbitRadiusInKm) {
    // Check so a moon is not bigger than half the size of the planet
    if (avgRadiusInKm > (this.getAvgRadiusInKm() / 2)) {
      throw new IllegalArgumentException("The radius of the moon is too large.");
    }

    Moon newMoon = new Moon(name, avgRadiusInKm, avgOrbitRadiusInKm);
    // Add the newly created moon to the arrayList moons
    moons.add(newMoon);
    return newMoon;
  }

  public double getAvgOrbitRadiusInKm() {
    return this.avgOrbitRadiusInKm;
  }

  /**
   * Returns an array of HeavenlyBody objects containing the planet and its moons.
   *
   * @return an array of HeavenlyBody objects containing the planet and its moons
   */
  public HeavenlyBody[] getHeavenlyBodies() {
    // Make an array
    HeavenlyBody[] heavenlyBodiesArray = new HeavenlyBody[moons.size() + 1];

    // Add the planet by creating a new planet via the constructor
    heavenlyBodiesArray[0] = new Planet(this.getName(), this.getAvgRadiusInKm(), this.getAvgOrbitRadiusInKm());

    // Loop through the moons arrayList and add the moons by creating new moons via the moon constructor
    int i = 0;
    for (Moon moon : moons) {
      heavenlyBodiesArray[i + 1] = new Moon(moon.getName(), moon.getAvgRadiusInKm(), moon.getAvgOrbitRadiusInKm());
      i++;
    }

    // Return the array
    return heavenlyBodiesArray;
  }

  @Override
  protected boolean checkAvgRadiusInKm(int radius) {
    // If the radius is not within the allowed range, return false
    return !(radius < 2000 || radius > 200000);
  }

  @Override
  public String toString() {
    return "  Planet: " + this.getName() + ", average radius " + this.getAvgRadiusInKm() 
      + "km, average orbit radius " + this.getAvgOrbitRadiusInKm() + "km\n";
  }
}

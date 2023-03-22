package assignment4;

/**
 * A class representing a moon.
 * This class extends the abstract class {@link HeavenlyBody}, which represents all types of celestial objects.
 * 
 */
public class Moon extends HeavenlyBody {
  private double avgOrbitRadiusInKm;

  /**
   * Constructs a new moon object with the given name, average radius and average
   * orbit radius.
   *
   * @param name - the name of the moon
   * @param avgRadiusInKm - the average radius of the moon in kilometers
   * @param avgOrbitRadiusInKm - the average orbit radius of the moon in kilometers
   * @throws IllegalArgumentException - if the average orbit radius is less than 60km
   */
  protected Moon(String name, int avgRadiusInKm, double avgOrbitRadiusInKm) {
    super(name, avgRadiusInKm);
    setAvgOrbitRadiusInKm(avgOrbitRadiusInKm);
  }

  private void setAvgOrbitRadiusInKm(double orbitRadius) {
    // The moons must have an orbit radius of at least 60km.
    if (orbitRadius < 60) {
      throw new IllegalArgumentException("The orbit radius is too small.");
    }
    this.avgOrbitRadiusInKm = orbitRadius;
  }

  public double getAvgOrbitRadiusInKm() {
    return this.avgOrbitRadiusInKm;
  }

  @Override
  protected boolean checkAvgRadiusInKm(int radius) {
    // If the radius is not within the allowed range, return false
    return !(radius < 6 || radius > 10000);
  }

  @Override
  public String toString() {
    return "    Moon: " + this.getName() + ", average radius " + this.getAvgRadiusInKm() 
      + "km, average orbit radius " + this.getAvgOrbitRadiusInKm() + "km\n";
  }
}

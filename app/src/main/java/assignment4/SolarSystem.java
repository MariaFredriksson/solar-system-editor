package assignment4;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * A class representing a solar system.
 */
public class SolarSystem {
  private String name;

  // The only star in the solar system
  private Star theStar;

  public SolarSystem(String name) {
    setName(name);
  }

  public String getName() {
    return this.name;
  }

  private void setName(String newName) {
    if (newName == null || newName.isBlank()) {
      throw new IllegalArgumentException("Name must not be null or empty");
    }
    this.name = newName;
  }

  /**
   * Adds a new star.
   *
   * @param name               the name of the star
   * @param avgRadiusInKm      the average radius of the star in kilometers
   * @return the newly created star object
   */
  public Star addStar(String name, int avgRadiusInKm) {
    Star newStar = new Star(name, avgRadiusInKm);

    // If there is already a star in the solar system, throw an exception
    if (theStar != null) {
      throw new IllegalArgumentException("There is already a star in the solar system.");
    }

    // Set theStar (the only star in the solar system) to the newly created star
    theStar = newStar;

    return newStar;
  }

  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = 
      "I want this to be mutable because I want to be able to access the methods of the star.")
  public Star getStar() {
    return theStar;
  }

  @Override
  public String toString() {
    // Write the name of the solar system, and then call the toString method of the star
    return this.name + "\n" + theStar.toString();
  }
}

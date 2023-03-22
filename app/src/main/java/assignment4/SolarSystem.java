package assignment4;

// import java.util.ArrayList;

/**
 * A class representing a solar system.
 */
public class SolarSystem {
  private String name;
  //^^ Behöver jag ha denna arrayList...? Det är ju bara en stjärna i varje solar system i denna uppgift...
  // private ArrayList<HeavenlyBody> heavenlyBodies = new ArrayList<>();

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

    // heavenlyBodies.add(newStar);

    // If there is already a star in the solar system, throw an exception
    if (theStar != null) {
      throw new IllegalArgumentException("There is already a star in the solar system.");
    }

    // Set theStar (the only star in the solar system) to the newly created star
    theStar = newStar;

    return newStar;
  }

  public Star getStar() {
    return theStar;
  }

  @Override
  public String toString() {
    // Write the name of the solar system, and then call the toString method of the star
    return this.name + "\n" + theStar.toString();
  }
}

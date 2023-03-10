package assignment4;

/**
 * Represents a celestial body in the solar system.
 */
public abstract class HeavenlyBody implements Comparable<HeavenlyBody> {
  private String name;
  private int avgRadiusInKm;

  protected HeavenlyBody(String name, int avgRadiusInKm) {
    setName(name);
    setAvgRadiusInKm(avgRadiusInKm);
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

  public int getAvgRadiusInKm() {
    return this.avgRadiusInKm;
  }

  private void setAvgRadiusInKm(int radius) {
    // If the check returns false, throw an exception
    if (!checkAvgRadiusInKm(radius)) {
      throw new IllegalArgumentException("Radius is not within the allowed range");
    }
    this.avgRadiusInKm = radius;
  }
  
  // An abstract method can contain no code. The code to be executed is in each child class, 
  // which also MUST have this method (and override the method).
  protected abstract boolean checkAvgRadiusInKm(int radius);
  
  // Made this method abstract, beacuse it didn't do anything in this class, 
  // but was very much needed in the child classes.
  @Override
  public abstract String toString();

  @Override
  public int compareTo(HeavenlyBody otherBody) {
    return Double.compare(this.getAvgRadiusInKm(), otherBody.getAvgRadiusInKm());
  }
}

package assignment4;

import java.util.Comparator;

/**
 * An interface representing an orbiting body.
 */
public interface OrbitingBody {
  public String getName();

  public double getAvgOrbitRadiusInKm();

  public Comparator<OrbitingBody> orbitRadiusComparator = new Comparator<OrbitingBody>() {
    @Override
    public int compare(OrbitingBody hb1, OrbitingBody hb2) {
      return (int) (hb1.getAvgOrbitRadiusInKm() - hb2.getAvgOrbitRadiusInKm());
    }
  };
}

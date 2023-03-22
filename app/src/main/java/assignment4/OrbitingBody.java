package assignment4;

import java.util.Comparator;

public interface OrbitingBody {
  public String getName();

  public double getAvgOrbitRadiusInKm();

  // private void setAvgOrbitRadiusInKm(double orbitRadius);

  public static Comparator<OrbitingBody> orbitRadiusComparator = new Comparator<OrbitingBody>() {
    @Override
    public int compare(OrbitingBody hb1, OrbitingBody hb2) {
      return (int) (hb1.getAvgOrbitRadiusInKm() - hb2.getAvgOrbitRadiusInKm());
    }
  };

}

import java.util.ArrayList;
import java.util.Objects;

public class Planet {
    private int orbitTime;
    private ArrayList<Moon> moons;
    private String designation;

    public Planet(int orbitTime, String designation) {
        this.orbitTime = orbitTime;
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getOrbitTime() {
        return orbitTime;
    }

    public void setOrbitTime(int orbitTime) {
        this.orbitTime = orbitTime;
    }

    public void addMoon(Moon moon) {
        this.moons.add(moon);
    }

    public ArrayList<Moon> getMoons() {
        return moons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return orbitTime == planet.orbitTime && Objects.equals(moons, planet.moons) && Objects.equals(designation, planet.designation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orbitTime, moons, designation);
    }

    @Override
    public String toString() {
        return "Planet{" +
                "orbitTime=" + orbitTime +
                ", moons=" + moons +
                ", designation='" + designation + '\'' +
                '}';
    }
}

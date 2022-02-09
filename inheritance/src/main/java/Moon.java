import java.util.Objects;

public class Moon {
    private Planet planet;
    private int orbitTime;
    private boolean atmosphere;
    private String designation;

    public Moon(Planet planet, int orbitTime, boolean atmosphere, String designation) {
        this.planet = planet;
        this.orbitTime = orbitTime;
        this.atmosphere = atmosphere;
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public int getOrbitTime() {
        return orbitTime;
    }

    public void setOrbitTime(int orbitTime) {
        this.orbitTime = orbitTime;
    }

    public boolean isAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(boolean atmosphere) {
        this.atmosphere = atmosphere;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Moon moon = (Moon) o;
        return orbitTime == moon.orbitTime && atmosphere == moon.atmosphere && Objects.equals(planet, moon.planet) && Objects.equals(designation, moon.designation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(planet, orbitTime, atmosphere, designation);
    }
}

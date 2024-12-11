package model.parkmap;

public class Location {
    private String name;
    private String coordinates;
    private boolean isBusy;
    private boolean isVisible;

    public Location(String name, String coordinates, boolean isBusy, boolean isVisible) {
        this.name = name;
        this.coordinates = coordinates;
        this.isBusy = isBusy;
        this.isVisible = isVisible;
    }

    public String getName() { return name; }
    public String getCoordinates() { return coordinates; }
    public boolean isBusy() { return isBusy; }
    public boolean isVisible() { return isVisible; }
}

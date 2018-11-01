package nottheory.donationtracker.Model;
import java.util.ArrayList;

public class LocationCollection {
    private ArrayList<Location> locations = new ArrayList<>();
    private static int nextRow; //the id of a location is defined by its row in the .csv file or the order in which it was added


    public LocationCollection() {
        //default method instantiates empty locations list
    }

    public LocationCollection(CSVReader reader){
        for(int i = 1; i <= reader.size(); i++) {
            locations.add(new Location(reader, i));
            nextRow = i+1;
        }
    }

    public LocationCollection(ArrayList<Location> list) {
        for (Location l : list) {
            locations.add(l);
        }
    }

    public void addLocation(Location l) {
        locations.add(l);
        l.setRow(nextRow);
        nextRow++;
    }
    public void removeLocation(Location l) {
        locations.remove(l);
    }

    public Location getLocationFromRow(int row) {
        for(Location l: locations) {
            if(l.getRow() == row) {
                return l;
            }
        }
        return null;
    }

    public Location getLocationByName(String name) {
        for (Location l : locations) {
            if (l.getName().equals(name)) {
                return l;
            }
        }
        return null;
    }

    public ArrayList<String> getLocationNames() {
        ArrayList<String> ret = new ArrayList<>();
        for(Location l : locations){
            ret.add(l.getName());
        }
        return ret;
    }

    public int getNumLocations() {
        return locations.size();
    }
    public DonationCollection getAllDonations() {
        DonationCollection ret = new DonationCollection();
        for(Location l: locations) {
            ret.addDonations(l.getDonationCollection());
        }
        return ret;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

}

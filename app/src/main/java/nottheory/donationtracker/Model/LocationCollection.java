package nottheory.donationtracker.Model;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class LocationCollection {
    private final ArrayList<Location> locations = new ArrayList<>();
    private /*static*/ int nextRow; //the id of a location is defined by its row in the .csv file
    // or the order in which it was added


    public LocationCollection() {
        //default method instantiates empty locations list
    }


    public LocationCollection(Iterable<Location> list) {
        for (Location l : list) {
            locations.add(l);
        }
    }

    public void addLocation(Location l) {

        locations.add(l);
        l.setRow(nextRow);
        nextRow++;
        Log.d("LOCATION ADDED", l.logText());
    }

    public Location getLocationFromRow(int row) {
        for(Location l: locations) {
            Log.d("LOCATION SEARCHED", l.logText());
            if (l.getRow() == row) {
                return l;
            }
        }
        Log.d("LOCATION NOT FOUND", "ROW NUMBER ATTEMPTED" + row);
        return null;
    }

    public Location getLocationByName(String name) {
        for (Location l : locations) {
            String thisName = l.getName();
            if (thisName.equals(name)) {
                return l;
            }
        }
        return null;
    }

    public Location getLocationWithDonation(Donation d) {
        for(Location l: locations) {
            for(Donation don: l.getDonations()) {
                String donationName = don.getName();
                if (donationName.equals(d.getName())) {
                    return l;
                }
            }
        }
        return null;
    }

    public List<String> getLocationNames() {
        List<String> ret = new ArrayList<>();
        for(Location l : locations){
            ret.add(l.getName());
        }
        return ret;
    }

    public int getNumLocations() {
        return locations.size();
    }

    public List<Donation> getAllDonationsAL() {
       List<Donation> ret = new ArrayList<>();
        for(Location l: locations) {
                ret.addAll(l.getDonations());
        }
        return ret;
    }


    public ArrayList<Location> getLocations() {
        return locations;
    }
}

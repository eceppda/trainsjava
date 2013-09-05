/**
 * User: eceppda
 * Date: 9/5/13
 * Time: 11:11 AM
 */
public class Route implements Comparable<Route> {

    public int traveledDistance;
    public String path;
    public Town currentTown;

    public Route(int d, String p, Town t) {
        this.traveledDistance = d;
        this.path = p;
        this.currentTown = t;
    }

    public int numberOfStops() {
        String[] hops = path.split("[A-Z]");
        return hops.length -1;
    }

    @Override
    public int compareTo(Route o) {
        if (this.traveledDistance > o.traveledDistance)
            return 1;
        else if (this.traveledDistance == o.traveledDistance)
            return 0;
        else
            return -1;
    }

}

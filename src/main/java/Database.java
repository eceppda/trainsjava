import java.util.*;

/**
 * User: eceppda
 * Date: 9/5/13
 * Time: 10:59 AM
 */
public class Database {

    private final int maxSearchLength= 30;
    private HashMap<String, Town> graph = new HashMap<String, Town>();
    private PriorityQueue<Route> queue = new PriorityQueue<Route>();

    public Map<String, Town> getMap() {
        return this.graph;
    }

    public void initDB(final String graph) {

        String[] towns = graph.split(",");

        for(int i=0; i <towns.length; i++) {

            String town = towns[i];
            if(town.length() > 3) {
                town = town.substring(1);
            }
            String town1 = town.substring(0,1);
            String town2 = town.substring(1,2);
            Integer distance = Integer.valueOf(town.substring(2));

//            System.out.printf("Path1: %s%n", town1 );
//            System.out.printf("Town2: %s%n", town2 );
//            System.out.printf("Distance: %s%n", distance );

            Town t1 = null;
            Town t2 = null;

            if(this.graph.containsKey(town1)) {
               t1 = this.graph.get(town1);

            } else {
                t1 = new Town(town1);
                this.graph.put(town1, t1);
            }

            if(this.graph.containsKey(town2)) {
                t2 = this.graph.get(town2);
            } else {
                t2 = new Town(town2);
                this.graph.put(town2, t2);
            }

            t1.addNeighbor(t2, distance);

        }
    }

    public int tripsWithMaxStops(Town start, Town end, int maxStops) {

        int count = 0;
        List<Route> results = search(start, end, maxSearchLength);
        for(Route r: results) {
            if( r.numberOfStops() <= maxStops) {
                count++;
            }
        }
        return count;

    }

    public int tripsWithExactStops(Town start, Town end, int stops) {

        int count = 0;
        List<Route> results = search(start, end, maxSearchLength);
        for(Route r: results) {
            if( r.numberOfStops() == stops) {
                count++;
            }
        }
        return count;
    }

    public String getDistanceAsString(String route) {
        int i = getDistance(route);
        if( i == 0) {
            return "NO SUCH ROUTE";
        } else {
            return String.valueOf(i);
        }
    }

    public int getDistance(String route) {
        String[] stops = route.split("-");

        Town start = graph.get(stops[0]);
        Town end = graph.get(stops[stops.length-1]);

        List<Route> results = search(start, end, maxSearchLength);

        for(Route r : results) {
            if(r.path.equals(route)) {
                return r.traveledDistance;
            }
        }
        return 0;
    }

    public int shortestRoute(Town from, Town to) {
        int result = 0;
        List<Route> results = search(from, to, maxSearchLength);

        for(Route route: results) {
            if( result > route.traveledDistance || result == 0) {
                result = route.traveledDistance;
            }
        }
        return result;
    }

    public int numberOfRoutes(Town from, Town to) {
        return search(from, to, maxSearchLength).size();
    }

    private List<Route> search(Town source, Town destination, Integer distanceLimit) {

        List<Route> answer = new ArrayList<>();

        queue.add(new Route(0, source.getName(), source));

        while (queue.peek() != null) {

            Route route = queue.poll();

            if (route.traveledDistance == distanceLimit)

                break;

            HashMap<Town, Integer> neighbors = route.currentTown.getNeighbors();

            Set<Town> keys = neighbors.keySet();

            Iterator<Town> iterator = keys.iterator();

            while (iterator.hasNext()) {

                Town t = iterator.next();
                int traveled = route.traveledDistance + neighbors.get(t);
                queue.add(new Route(traveled, route.path + "-" + t.getName(), t));
                if (t.getName().equals(destination.getName()) && traveled < distanceLimit) {
                    route.path = route.path + "-" + t.getName();
                    route.traveledDistance = traveled;
                    answer.add(route);
//                    System.out.println(p.path + t.getName());
                }

            }
        }

        return answer;
    }
}

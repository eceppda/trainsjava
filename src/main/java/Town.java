import java.util.HashMap;

/**
 * User: eceppda
 * Date: 9/5/13
 * Time: 11:06 AM
 */
public class Town implements Comparable<Town> {

    private final String name;
    private int distance;

    private HashMap<Town, Integer> neighbors;

    public Town(String name) {
        this.name = name;
        this.distance = Integer.MAX_VALUE;
    }

    public HashMap<Town, Integer> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(Town town, Integer distance) {
        if (this.neighbors == null)
            this.neighbors = new HashMap<>();
        this.neighbors.put(town, distance);
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int compareTo(Town o) {
        if (this.distance > o.distance)
            return 1;
        else if (this.distance == o.distance)
            return 0;
        else
            return -1;
    }
}

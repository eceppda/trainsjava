package trains;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Developer: Jeff Hemminger
 *
 * Unit tests for the train problem as described in the README.
 */
public class TrainTest {

    private Database trainDB;

    @Before
    public void setUp() throws Exception {
        this.trainDB = new Database();
        this.trainDB.initDB("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
    }

    @Test
    public void testInitDb() {
        Map<String, Town> townMap = this.trainDB.getMap();
        Set<String> keySet = townMap.keySet();
        assertEquals(5, keySet.size());
    }

    /**
     * 1. The distance of the route A-B-C.
     */
    @Test
    public void testGetDistance1() {
        String distance = this.trainDB.getDistanceAsString("A-B-C");
        assertEquals("Expects A-B-C to return 9", "9", distance);
    }

    /**
     * 2. The distance of the route A-D.
     */
    @Test
    public void testGetDistance2() {
        String distance = this.trainDB.getDistanceAsString("A-D");
        assertEquals("Expects A-D to return 5", "5", distance);
    }

    /**
     * 3. The distance of the route A-D-C.
     */
    @Test
    public void getDistance3() {
        String distance = this.trainDB.getDistanceAsString("A-D-C");
        assertEquals("Expects A-D-C to return 13", "13", distance);
    }

    /**
     * 4. The distance of the route A-E-B-C-D.
     */
    @Test
    public void getDistance4() {
        String distance = this.trainDB.getDistanceAsString("A-E-B-C-D");
        assertEquals("Expects A-E-B-C-D to return 22", "22", distance);
    }

    /**
     * 5. The distance of the route A-E-D.
     */
    @Test
    public void getDistance5() {
        String distance = this.trainDB.getDistanceAsString("A-E-D");
        assertEquals("Expects A-B-C to return NO SUCH ROUTE", "NO SUCH ROUTE", distance);
    }

    /**
     * 6. The number of trips starting at C and ending at C with a maximum of 3
     * stops.  In the sample data below, there are two such trips:
     * C-D-C (2 stops). and C-E-B-C (3 stops).
     */
    @Test
    public void numberOfTrips() {
        Map<String, Town> townMap = this.trainDB.getMap();
        Town c = townMap.get("C");
        int result = this.trainDB.tripsWithMaxStops(c, c, 3);
        assertEquals("2 trips should be returned", 2, result);
    }

    /**
     * 7. The number of trips starting at A and ending at C with exactly 4 stops.
     * In the sample data below, there are three such trips: A to C (via B,C,D);
     * A to C (via D,C,D); and A to C (via D,E,B).
     */
    @Test
    public void numberOfTrips2() {
        Map<String, Town> townMap = this.trainDB.getMap();
        Town a = townMap.get("A");
        Town c = townMap.get("C");
        int result = this.trainDB.tripsWithExactStops(a, c, 4);
        assertEquals("3 trips should be returned", 3, result);
    }

    /**
     * 8. The length of the shortest route (in terms of distance to travel) from A to C.
     */
    @Test
    public void lengthOfShortestRoute() {
        Map<String, Town> townMap = this.trainDB.getMap();
        Town a = townMap.get("A");
        Town c = townMap.get("C");
        int result =  this.trainDB.shortestRoute(a, c);
        assertTrue("Result size should be 9", 9 == result);
    }

    /**
     * 9. The length of the shortest route (in terms of distance to travel) from B to B.
     */
    @Test
    public void lengthOfShortestRoute2() {
        Map<String, Town> townMap = this.trainDB.getMap();
        Town b = townMap.get("B");
        int result =  this.trainDB.shortestRoute(b, b);
        assertTrue("Result size should be 9", 9 == result);
    }

    /**
     * 10. The number of different routes from C to C with a distance of less than 30.
     * In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC.
     */
    @Test
    public void numberOfDifferentRoutes() {
        Map<String, Town> townMap = this.trainDB.getMap();
        Town c = townMap.get("C");
        int result =  this.trainDB.numberOfRoutes(c, c);
        assertTrue("Result size is 7", 7 == result);
    }
}
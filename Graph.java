/*Miller Hickman*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Graph {
    HashMap<String, Intersection> Intersections;
    HashMap<String, String> trace;
    Set<String> roadIDs = new HashSet<>();

    void parseData(String fileName) throws FileNotFoundException 
    {
        Intersections = new HashMap<>();
        Scanner scanner = new Scanner(new File(fileName));
        while(scanner.hasNextLine()) 
        {
            String type = scanner.next();
            String id = scanner.next();
            String para1 = scanner.next();
            String para2 = scanner.next();
            if(type.equals("i"))
            {
                Intersections.put(id, new Intersection(id, Double.parseDouble(para1), Double.parseDouble(para2)));
            }
            else 
            {
                Intersection i1 = Intersections.get(para1);
                Intersection i2 = Intersections.get(para2);

                Road road = new Road(id, para2, haversineFunction(i1, i2));
                i1.addRoad(road);

                road = new Road(id, para1, haversineFunction(i1, i2));
                i2.addRoad(road);
            }
        }
    }

    /**
     * Haversine function to find distance between two points.
     * Obtained from: https://en.wikipedia.org/wiki/Haversine_formula
     **/
    double haversineFunction(Intersection i1, Intersection i2) {
        double earthRadius = 6356.752; //km
        return 2 * earthRadius * Math.asin(Math.sqrt(
                Math.pow(Math.sin((i2.getLatitude() - i1.getLatitude()) / 2), 2) + Math.cos(i1.getLatitude()) * Math.cos(i2.getLatitude() * Math.pow(Math.sin((i2.getLongitude() - i1.getLongitude()) / 2), 2)
        )));
    }

    void shortestPath(String start, String end) 
    {
        trace = new HashMap<>();
        roadIDs = new HashSet<>();
        Heap heap = new Heap();
        heap.addElement(start, 0);
        while(heap.size > 0) 
        {
            String intersectionId = heap.removeTop();
            if(intersectionId.equals(end)) 
            {
                break;
            }
            for(Road road : Intersections.get(intersectionId).roadList) 
            {
                if(!heap.contatinIntersection(road.destinationID)) 
                {
                    heap.addElement(road.destinationID, heap.getValue(intersectionId) + road.weight);
                    trace.put(road.destinationID, road.id);
                } 
                else if(heap.getValue(road.destinationID) > heap.getValue(intersectionId) + road.weight) 
                {
                    heap.updateValue(road.destinationID, heap.getValue(intersectionId) + road.weight);
                    trace.replace(road.destinationID, road.id);
                }
            }
        }
        if(!heap.contatinIntersection(end)) 
        {
            System.out.println("Can't find path");
            return;
        }
        List<String> path = new ArrayList<>();
        String intersectionId = end;
        while(!intersectionId.equals(start)) 
        {
            path.add(intersectionId);
            roadIDs.add(trace.get(intersectionId));

            for(Road road : Intersections.get(intersectionId).roadList)
            {
                if(road.id.equals(trace.get(intersectionId))) 
                {
                    intersectionId = road.destinationID;
                    break;
                }
            }
        }
        path.add(start);
        for(int i = path.size() - 1; i >= 0; i--)
        {
            System.out.println(path.get(i));
        }
        System.out.println("Distance traveled(Km): " + heap.getValue(end));
    }

    
}
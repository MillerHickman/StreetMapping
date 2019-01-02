/*Miller Hickman*/

import java.io.FileNotFoundException;

public class StreetMap {

    public static void main(String[] args) throws FileNotFoundException 
    {
        Graph g = new Graph();
        g.parseData(args[0]);
        boolean show = false;
        boolean directions = false;
        String start = "";
        String end = "";
        for(int i = 0; i < args.length; i++)
        {
            if(args[i].equals("--show"))
            {
                show = true;
            }
            else if(args[i].equals("--directions")) 
            {
                directions = true;
                start = args[i + 1];
                end = args[i + 2];
            }
        }
        if(directions)
        {
            g.shortestPath(start, end);
        }
        if(show)
        {
            new GUI(g.Intersections, g.roadIDs);
        }
    }
}
Miller Hickman
Class CSC 172 
Lab Section MW1650
URID: 31352543
email: mhickman@u.rochester.edu

This program consits of six files: StreetMap.java, Graph.java, GUI.java, Road.java, Intersection.java, and Heap.java.

The Intersection class contains the id of the intersection, the latitude and longitude, and a list of roads that start from that intersection.

The Road class cotains the id of the road, the end of the road, and the length of the road(weight).

The Heap class contains a value List that holds that holds the shortest distance from the source to the intersection whose hash ID is equivalent.
The Heap class also contains a position List which keeps the position of the hash ID on heap.

The Graph class reads in the data from the file and makes a list of all Intersection objects using the parse() method.  
The shortestPath() method uses Djikstra's algorithm to find the shortest path between any two intersections.  
The ShortestPath() method has a big O of O(nlog(n)), however hashing every Intersections ID in the hashMap has a big O of O(log(n)), so the final big O is O(nlog(n)log(n))

The GUI class displays the Map of the intersections and roads.  It uses roadIDs to know which roads need to be highlighted yellow to show the requested route.
checking roadIDs each loop to see if a road should be hilighted is O(log(n)), so tdrawing the map in total hasa a big O of O(nlog(n)).
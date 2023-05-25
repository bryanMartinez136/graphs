
import java.util.ArrayList;

public interface Graph { // Graph class ADT
  //***** Problem 1 *****/
  // Initialize the graph with some number of vertices
  void init(int n);

  // Return the number of vertices
  int nodeCount();

  // Return the current number of edges. This operation must be a constant time operation.
  int edgeCount();

  // Adds a new edge from node v to node w with weight wgt. You may assume that weights are > 0.
  // If an edge from node v to node w already exists, then replace it with wgt.
  void addEdge(int v, int w, int wgt);

  // Get the weight value for an edge
  int getWeight(int v, int w);

 // Set the weight of v and w.
 // You may assume that wgt > 0.
  void setWeight(int v, int w, int wgt);

  // Removes the edge from the graph.
  void removeEdge(int v, int w);

  // Returns true if and only if the graph has an edge between v and w.
  // This must be a constant time operation.
  boolean hasEdge(int v, int w);

  // Returns an ArrayList containing vertex id's of the neighbors of v.
  ArrayList<Integer> neighbors(int v);

  // Resets the Visited array to all false (required for BFS).
  void resetVisited();



  // Performs a Breadth-First-Search starting at vertex, v. On PreVisit, the current vertex's label/id should be appended to the end of an ArrayList. Do not perform a PostVisit operation.
  // Index 0 of the array should be the starting vertex, v. Index 1 should be one of v's neighbors and so on.
  // Once BFS is completed, the ArrayList is returned.
  ArrayList<Integer> BFS(int v);
  //***** End Problem 1 *****/


  //***** Problem 2 *****/
  // Returns true if there is a path between v and w. Otherwise returns false. You may use the BFS method (above) for this method.
  boolean hasPath(int v, int w);
  //***** End Problem 2 *****/


  //***** Problem 3 *****/
  // Performs a topologicalSort if the graph and returns an ArrayList that contains the vertex labels/id in topologically sorted order.
  // Note: You will not be able to use the BFS method above. You must implement the ``modified'' BFS algorithm discussed in class.
  ArrayList<Integer> topologicalSort();
    //***** End Problem 3 *****/

}
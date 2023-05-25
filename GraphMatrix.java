import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class GraphMatrix implements Graph{
    //TODO.
private int[][] matrix; 
private boolean[] visited; 
private int numVert; 
private int numEdge; 

// Initialize the graph with some number of vertices
   public void init(int n){
    this.matrix= new int[n][n]; 
    this.visited = new boolean[n]; 
    numVert=n; 
    numEdge=0; 
}

private boolean validIndex(int x, int y){
    // only a valid index if the indices are less than the zie and greater than 0
    return (x < numVert && x >= 0 && y < numVert && y >= 0); 
}
  // Return the number of vertices
  public int nodeCount(){return this.numVert;}

  // Return the current number of edges. This operation must be a constant time operation.
  public int edgeCount(){return this.numEdge;}

  // Adds a new edge from node v to node w with weight wgt. You may assume that weights are > 0.
  // If an edge from node v to node w already exists, then replace it with wgt.
  public void addEdge(int v, int w, int wgt){
    if(!validIndex(v,w)){
        // invalid index
        System.out.println("Invalid index!");
        return; 
    }
    if(v == w){
        // vertex can't have edge to itself
        return; 
    }
    
    // from row v to col w, with weight = wgt.
    matrix[v][w] = wgt; 
    numEdge++; // increment edge counter
  }

  // Get the weight value for an edge
  public int getWeight(int v, int w){
    // check if valid index for v and w 
    if(!validIndex(v,w)){
        System.out.println("Invalid index !!");
        throw new IllegalArgumentException();       
    }
    return matrix[v][w]; // even if v == w, 0 would be return since there is no edge
  }

 // Set the weight of v and w.
 // You may assume that wgt > 0.
public void setWeight(int v, int w, int wgt){
    if(!validIndex(v,w)){
        // invalid index
        System.out.println("Invalid index!");
        return; 
    }
    
    if(!hasEdge(v, w)){numEdge++;}
    matrix[v][w] = wgt; 

}


  // Removes the edge from the graph.
  public void removeEdge(int v, int w){
    if(!validIndex(v,w)){
        // invalid index
        System.out.println("Invalid index!");
        return; 
    }
    if(!hasEdge(v, w)){return;} // there is no edge to remove
    matrix[v][w] = 0; // 0 represents no edge
    numEdge--; 
  }

  // Returns true if and only if the graph has an edge between v and w.
  // This must be a constant time operation.
  public boolean hasEdge(int v, int w){
    // if the edge is non zero then there is an edge and it returns true
    return (matrix[v][w]!=0);
  }

  // Returns an ArrayList containing vertex id's of the neighbors of v.
  public ArrayList<Integer> neighbors(int v){
    ArrayList<Integer> list = new ArrayList<>();
    for(int i = 0; i < numVert; i++){
        if(matrix[v][i] != 0){
            list.add(i);
        }
    }
    return list; 
  }

  // Resets the Visited array to all false (required for BFS).
  public void resetVisited(){
    this.visited = new boolean[numVert]; 
  }

  // Performs a Breadth-First-Search starting at vertex, v. On PreVisit, the current vertex's label/id should be appended to the end of an ArrayList. Do not perform a PostVisit operation.
  // Index 0 of the array should be the starting vertex, v. Index 1 should be one of v's neighbors and so on.
  // Once BFS is completed, the ArrayList is returned.
 public ArrayList<Integer> BFS(int v){
    if(!validIndex(v, v)){ // check if index is valid
        System.out.println("Invalid index!");
        throw new IllegalArgumentException();
    }
    ArrayList<Integer> list = new ArrayList<>();//main list that we will return
    ArrayList<Integer> temp = new ArrayList<>();// list to hold all the neighbors

    Queue<Integer> q = new ArrayDeque<Integer>(numVert);
    q.add(v); // add the "root" to the queue
    if(!visited[v]){
        list.add(v);
        visited[v]= true;// visited the node so true
    } // add to the list

     
    
    while(!q.isEmpty()){
        int node = q.poll(); // set the node equal to the top of the q and remove from queue
        int i = 0; // to check the neighbors list later

        temp = neighbors(node); // set temp list equal to the neighbors

        while(i< temp.size()){ // so long as the neighbor list is not null, can add to the queue
            if(!visited[temp.get(i)]){ // if we have not visited the node, add the first neighbor to list and queue, and set visited to true
                q.add(temp.get(i));
                list.add(temp.get(i));
                visited[temp.get(i)] = true;
            }
            // if the if statement is false, then we have visited the node and it is not queued.
            i++;
        }
    }
    
    return list;
 }
  //***** Problem 2 *****/
  // Returns true if there is a path between v and w. Otherwise returns false. You may use the BFS method (above) for this method.
  public boolean hasPath(int v, int w){
    if(!validIndex(v, w)){
        System.out.println("Not valid indexes!"); // error if no valid index
        throw new IllegalArgumentException(); 
    }
    resetVisited(); // reset visited so we can perfomr BFS
    ArrayList<Integer> path = BFS(v); 
    if(path.contains(w)){ // if w is in the BFS, there is a path 
        resetVisited(); // need to reset visited before returning
        return true; 
    }
    // reset visited here 
    resetVisited();

    // made it this far, then not in BFS and no path from v to w
    return false; 
  }
  //***** End Problem 2 *****/


  //***** Problem 3 *****/
  // Performs a topologicalSort if the graph and returns an ArrayList that contains the vertex labels/id in topologically sorted order.
  // Note: You will not be able to use the BFS method above. You must implement the ``modified'' BFS algorithm discussed in class.
  public ArrayList<Integer> topologicalSort(){
    resetVisited();
    int[] deg = new int [numVert];
    ArrayList<Integer> list = new ArrayList<>(); 
    ArrayList<Integer> temp = new ArrayList<>(); 

    for (int i  = 0; i < numVert; i++) { deg[i] = 0; } 
    // Process every edge populate the in degree list
    for (int i = 0; i < numVert; i++) { 
        temp = neighbors(i);
        for(int k = 0; k < temp.size(); k++) {
            deg[temp.get(k)]++;            
        }
    }

    Queue<Integer> q = new ArrayDeque<Integer>(numVert);
    for(int i = 0; i < numVert; i++)
    {
        if(deg[i] == 0){
            q.add(i); 
        }
    }
    
    
    while(!q.isEmpty()){
    int node = q.poll(); // set the node equal to the top of the q and remove from queue
    //     int i = 0; // to check the neighbors list later
    list.add(node); 

    temp = neighbors(node); // set temp list equal to the neighbors
    // For the length of the array list of neighbors, decrement in degree array list for whichever vertices appear
    // If a vertex has been processed to the point where it has 0 in degrees, then  enqueue to have its neighbors checked
    for(int i=0;  i < temp.size(); i++){ 
        deg[temp.get(i)]--; 
        if(deg[temp.get(i)] == 0){ 
                q.add(temp.get(i)); 
             }
      }
    }
    
    return list;

    
    
   
  }
    //***** End Problem 3 *****/

} 
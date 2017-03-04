import java.io.*;
import java.util.*;

class GFG {
    private int v;
    private LinkedList<Integer> adj[];
    
    GFG(int v){
        adj = new LinkedList[v];
        for(int i = 0; i < adj.length; i++){
            adj[i] = new LinkedList();
        }
        this.v = v;
            
    }
    
    void addEdge(int v, int w){
        adj[v].add(w);
    }
    
    void BFS(int s){
        HashMap visited = new HashMap(v);
        
        LinkedList<Integer> queque = new LinkedList<Integer>();
        queque.add(s);
        visited.put(s, true);
        // System.out.println(queque);
        while(!queque.isEmpty()){
            int c = queque.poll();
            System.out.print(c + " ");
            adj[c].stream().forEach(v -> {if (visited.get(v) == null) { queque.add(v); visited.put(v, true); } });
        }
       
    }
    
    void DFSutil(int s, HashMap visited){
       visited.put(s, true);
       System.out.print(s + " ");
       adj[s].stream().forEach(v -> { if(visited.get(v) == null) { DFSutil(v, visited); } });
    }
    
    void DFS(int s){
         HashMap visited = new HashMap(v);
         
         DFSutil(s, visited);
    }
	public static void main (String[] args) {
		GFG g = new GFG(8);
		g.addEdge(1, 2);
		g.addEdge(1, 3);
		g.addEdge(2, 1);
		g.addEdge(2, 4);
		g.addEdge(2, 5);
		g.addEdge(3, 1);
		g.addEdge(3, 6);
		g.addEdge(3, 7);
		g.addEdge(4, 2);
		g.addEdge(5, 2);
		g.addEdge(6, 3);
		g.addEdge(7, 3);
		System.out.print("Deep first search: "); g.DFS(1);
		System.out.println();
		System.out.print("Breadth first search: "); g.BFS(1);
		
	}
}
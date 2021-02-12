import java.util.ArrayDeque;

public class Graph {

	public int[][]adjacence;
	
	public static int maxValue = Integer.MAX_VALUE;

    
    public int createGraph(String strCurrentLine, int[]sommets1, int[]sommets2, int i) {
    	String[] tmp = new String[2];        
    	
    	// Split des sommets par espace
    	tmp = strCurrentLine.split("\\s+");
    	
     	sommets1[i] = Integer.parseInt(tmp[0]);
     	sommets2[i] = Integer.parseInt(tmp[1]); 
     	
     	return Math.max(sommets1[i], sommets2[i]);
    }
    
    public int[][] createAdjacencyList(int[]sommets1, int[]sommets2, int nbSommets, int nbAretes, int[]compteur) {    	
    	adjacence = new int[nbSommets][];
    	
    	for(int i=0; i<nbSommets; i++) {
    		adjacence[i] = new int[compteur[i]+1];
    		compteur[i] = 0;
    	}
		
   		for(int j=0; j<nbAretes; j++) {
   			adjacence[sommets2[j]][compteur[sommets2[j]]] = sommets1[j];
   			compteur[sommets2[j]]++;
   			adjacence[sommets1[j]][compteur[sommets1[j]]] = sommets2[j];
   			compteur[sommets1[j]]++;   			
    	}
   		
   		return adjacence;
    }
    
    public int[] neighbors(int u) {
    	return adjacence[u];
    }
    
    // Retourne le tableau des distances de chaque sommet en partant du sommet sommetDepart
    public int[] bfs(int n, int sommetDepart) {
    	ArrayDeque<Integer> queue = new ArrayDeque<>();
    	int[] dist = new int [n];
    	
    	queue.clear();
        for (int i = 0; i < dist.length; ++i) { dist[i] = maxValue; }

        dist[sommetDepart] = 0;
        queue.add(sommetDepart);
        
        while ( ! queue.isEmpty()) {
            int u = queue.poll(); // FIFO
            int d = dist[u];
            for (int v : neighbors(u)) {
                if (dist[v] == maxValue) { // first discovery of v
                    dist[v] = d + 1;
                    queue.add(v);
                }
            }
        }
        return dist;
    }   
    
    // Renvoie un sommet de la plus grande composante connexe
    public int largestConnectedComponent(int nbSommets, int n) {
    	int sommet = 0;
    	int i = 0;
    	int largestConnectedComponent = 0;
    	boolean[] visited = new boolean[nbSommets];
    	
    	for(i=0; i<nbSommets; i++) {
    		if(visited[i] == false) {
	    		int[] dist = bfs(n, i);
	    		int sizeConnectedComponent = 0;
	    		
	    		for(int j=0; j<dist.length; j++) {
	    			if(dist[j] != Graph.maxValue) {
	    				visited[j] = true;
	    				sizeConnectedComponent++;
	    			}
	    		}
	    		
	    		if(sizeConnectedComponent > largestConnectedComponent) {
	    			largestConnectedComponent = sizeConnectedComponent;
	    			sommet = i;
	    		}
    		}
    	}   	
    	return sommet;
    }
    
}

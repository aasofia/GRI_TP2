import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TP2 {
	
	public static void main(String []args) {	

		// Instance de la classe Graph
		Graph g = new Graph();
		Operations o = new Operations();
			
		// Nombre estime d'aretes
		int estimNbAretes = Integer.parseInt(args[2]);
		
		// Nombre d'aretes
		int nbAretes = 0;
		
		// Nombre de sommets
		int nbSommets = 0;
		
		// Initilisation des tableaux des sommets
        int[] sommets1 = new int[estimNbAretes];
        int[] sommets2 = new int[estimNbAretes];

		BufferedReader bufferedreader = null;
        FileReader filereader = null;
        
        
        try {
            filereader = new FileReader(args[1]);

        	bufferedreader = new BufferedReader(filereader);
        	
        	// Ligne en cours
            String strCurrentLine;
         
            while ((strCurrentLine = bufferedreader.readLine()) != null) {    
            	if (!strCurrentLine.contains("#")) {
	            	// CreateGraph ajoute les sommets aux deux tableaux et renvoie le plus grand des 2 sommets
	            	int maxSommet = g.createGraph(strCurrentLine, sommets1, sommets2, nbAretes);
	          	
	            	nbAretes++;
	            	
	            	if(maxSommet+1 > nbSommets) nbSommets = maxSommet+1;   
            	}
            } 
        } catch (IOException e) {
            e.printStackTrace();
          } finally {
            try {
              if (bufferedreader != null)
                bufferedreader.close();
              if (filereader != null)
                filereader.close();
            } catch (IOException e) {
              e.printStackTrace();
            }
          }

        // Tableau qui compte le nombre de voisins de chaque sommet
        int[]compteur = new int[nbSommets];
     
        try {
            filereader = new FileReader(args[1]);
        	bufferedreader = new BufferedReader(filereader);
        	
            String strCurrentLine;
 
            while ((strCurrentLine = bufferedreader.readLine()) != null) {
            	if (!strCurrentLine.contains("#")) {
	            	String[] tmp = new String[2];         	
	            	// Split des sommets par espace
	            	tmp = strCurrentLine.split("\\s+");
	            	
	            	compteur[Integer.parseInt(tmp[0])]++;
	            	compteur[Integer.parseInt(tmp[1])]++;
            	}
            } 
        } catch (IOException e) {
            e.printStackTrace();
          } finally {
            try {
              if (bufferedreader != null)
                bufferedreader.close();
              if (filereader != null)
                filereader.close();
            } catch (IOException e) {
              e.printStackTrace();
            }
          }

        // Creation de la liste dadjacence
        int[][] adjacence = g.createAdjacencyList(sommets1, sommets2, nbSommets, nbAretes, compteur);
        
        // Sommet de la plus grande composante connexe
        int sommetDepart = g.largestConnectedComponent(nbSommets, adjacence.length);

        // Ligne a executer lorsque le sommet de depart doit etre un sommet de la plus grande composante connexe
        //if(args[0].equals("2-sweep")) o.twosweep(g, sommetDepart, adjacence.length, 1, 0, 0);
        
        if(args[0].equals("2-sweep")) o.twosweep(g, Integer.parseInt(args[3]), adjacence.length, 1, 0, 0);
        
        if(args[0].equals("4-sweep")) o.foursweep(g, Integer.parseInt(args[3]), adjacence.length);
        
        if(args[0].equals("sum-sweep")) o.sumsweep(g, Integer.parseInt(args[3]), adjacence.length);
        
        if(args[0].equals("diametre")) o.calculexact(g, Integer.parseInt(args[3]), adjacence.length, 0);
      
      
	}	
	
}


public class Operations {

	public int[] twosweep(Graph g, int sommetDepart, int n, int exo, int maxEcc, int compteur){
		int[] dist1 = g.bfs(n, sommetDepart);
		
		int maxDist1 = 0;
		int i;
		int v = 0;
		compteur++;
		for(i=0; i<dist1.length; i++) {
			if(dist1[i] > maxDist1 && dist1[i]!= Graph.maxValue) {
				maxDist1 = dist1[i];
				v = i;
			}
		}
		
		int[] dist2 = g.bfs(n, v);
		int maxDist2 = 0;
		int j;
		int w = 0;
		compteur++;
		for(j=0; j<dist2.length; j++) {
			if(dist2[j] > maxDist2 && dist2[j]!= Graph.maxValue) {
				maxDist2 = dist2[j];
				w = j;
			}
		}
		
		if(exo == 1) {
			System.out.println("v="+v);
			System.out.println("w="+w);
			System.out.println("diam>="+maxDist2);
		}
		
		if(exo == 2) {
			System.out.println("diam>="+maxDist2);
		}
				
		if(exo == 3) {
			if(maxDist1 > maxDist2 && maxDist1 > maxEcc) {
				dist2 = dist1;
				maxEcc = maxDist1;
			} else if(maxDist2 > maxDist1 && maxDist2 > maxEcc) {
				maxEcc = maxDist2;
			}
			if(compteur < 4) twosweep(g, w, n, 3, maxEcc, compteur);
		}
				
		return dist2;
		
	}
	
	public void foursweep(Graph g, int sommetDepart, int n) {
		int[]dist = twosweep(g, sommetDepart, n, 0, 0, 0);
		
		int maxDist = 0;
		
		for(int i=0; i<dist.length; i++) {
			if(dist[i] > maxDist && dist[i]!= Graph.maxValue) {
				maxDist = dist[i];
			}
		}
		
		int distMilieu = maxDist/2;
		int sommetMilieu = 0;
		for(int i=0; i<dist.length; i++) {
			if(dist[i] == distMilieu && dist[i]!= Graph.maxValue) {
				sommetMilieu = i;
			}
		}
		
		twosweep(g, sommetMilieu, n, 2, 0, 0);
	}
	
	public void sumsweep(Graph g, int sommetDepart, int n) {
		int maxEcc = 0;
		
		int[]dist = twosweep(g, sommetDepart, n, 3, maxEcc, 0);
		
		int maxDist = 0;
		
		for(int i=0; i<dist.length; i++) {
			if(dist[i] > maxDist && dist[i]!= Graph.maxValue) {
				maxDist = dist[i];
			}
		}		
		System.out.println("diam>="+maxDist);
	
	}
	
	public int ecc(Graph g, int sommetDepart, int n) {
		int[]dist1 = twosweep(g, sommetDepart, n, 0, 0, 0);
		
		int maxDist = 0;
		
		for(int i=0; i<dist1.length; i++) {
			if(dist1[i] > maxDist && dist1[i]!= Graph.maxValue) {
				maxDist = dist1[i];
			}
		}
		return maxDist;
	}
	
	public void calculexact(Graph g, int sommetDepart, int n, int diamlow) {
		int maxii=0;
		int a=0;
		int valu=0;

		int[] dist = g.bfs(n, sommetDepart);
		int [] compos_connex= new int [n];
		
		for(int i=0; i<dist.length; i++) {
			if (dist[i] != Graph.maxValue) 
				compos_connex[i]=i;
			    valu=ecc(g,i,n);
	    	    if(valu > maxii) {
	    	    	maxii = valu;
					a=i;
				} 
		}
		if (maxii<=diamlow) {
			System.out.println("diam="+maxii);
		}
		
		int ecca = ecc(g,a,n);

		if (ecca > diamlow)
				diamlow=ecca;
			
		int eccval =0;
		for (int val=0; val<dist.length; val++) {
			eccval = ecc(g,val,n);
			dist[val] = dist[val]+ecca;
			if (dist[val] < eccval) 
				eccval=dist[val];
			//System.out.println("la borne="+dist[val]);	
		}
	
		 System.out.println("diam="+diamlow);
	   		
	}
	
}

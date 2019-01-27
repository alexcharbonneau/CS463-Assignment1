package ObjectLabelling;

import java.util.Arrays;

public class ObjectLabelling {

	public static int[][] countGroups(int[][] m) {

	    
	    int row = m.length;
	    int col = m[0].length;
	    int eCorners = 0;
	    int iCorners = 0;
	    int numObjects = 0;
	    int labelCount = 1;
	    
	    for (int i=0; i<m.length-1; i++) {
	    	for (int j=0; j<m[0].length-1; j++) {
	    		//for counting external corners
	    		if (m[i][j]==1 && m[i][j+1]==1 && m[i+1][j]==1 && m[i+1][j+1]==0) {
	    			eCorners++;
	    		}
	    		if (m[i][j]==1 && m[i][j+1]==1 && m[i+1][j]==0 && m[i+1][j+1]==1) {
	    			eCorners++;
	    		}
	    		if (m[i][j]==1 && m[i][j+1]==0 && m[i+1][j]==1 && m[i+1][j+1]==1) {
	    			eCorners++;
	    		}
	    		if (m[i][j]==0 && m[i][j+1]==1 && m[i+1][j]==1 && m[i+1][j+1]==1) {
	    			eCorners++;
	    		}
	    		//for counting internal corners
	    		if (m[i][j]==0 && m[i][j+1]==0 && m[i+1][j]==0 && m[i+1][j+1]==1) {
	    			iCorners++;
	    		}
	    		if (m[i][j]==0 && m[i][j+1]==0 && m[i+1][j]==1 && m[i+1][j+1]==0) {
	    			iCorners++;
	    		}
	    		if (m[i][j]==0 && m[i][j+1]==1 && m[i+1][j]==0 && m[i+1][j+1]==0) {
	    			iCorners++;
	    		}
	    		if (m[i][j]==1 && m[i][j+1]==0 && m[i+1][j]==0 && m[i+1][j+1]==0) {
	    			iCorners++;
	    		}
	    		
	    	}
	    }
	    numObjects = (iCorners-eCorners)/4;
	    
//	    System.out.println(eCorners);
//	    System.out.println(iCorners);
//	    System.out.print(numObjects);

	    //turning 1's in the array into -1's
	    for(int i=0; i<m.length; i++) {
	    	for(int j=0; j<m[0].length; j++) {
	    		if(m[i][j]==1) {
	    			m[i][j]=-1;
	    		}
	    	}
	    }

	    FindComponents(m, labelCount);
	    
    
	    return m;
	    
	}
	
    public static void FindComponents(int[][]x, int y) {
    	for(int i=0; i<x.length; i++) {
    		for(int j=0; j<x[0].length; j++) {
    			if(x[i][j]==-1) {
    				x[i][j]=y;
    				ConnectedComponents(x, i, j);
    				y++;
    			}
    		
    		}
    	}
    	PrintArray(x);
    }
    
    public static void ConnectedComponents(int[][]x, int i, int j) {
    	if(i>0) {
    		if(x[i-1][j]!=0) {
    			x[i-1][j]=x[i][j];
    		}
    	}
    	
       	if(j>0) {
    		if(x[i][j-1]!=0) {
    			x[i][j-1]=x[i][j];
    		}
    	}
       	
    	if(i<x.length-1) {
    		if(x[i+1][j]<0) {
    			x[i+1][j]=x[i][j];
    		}
    	}
    	
    	
    	if(j<x[0].length-1) {
    		if(x[i][j+1]<0) {
    			x[i][j+1]=x[i][j];
    		}
    	}
    	
    	if(i<x.length-1 && x[i+1][j]>0) {
    		ConnectedComponents(x, i+1, j);
    	}
    	
    	if(j<x[0].length-1 && x[i][j+1]>0) {
    		ConnectedComponents(x, i, j+1);
    	}
    	

    }
    public static void PrintArray(int[][]m) {
    	for(int i=0; i<m.length; i++) {
	    	for(int j=0; j<m[0].length; j++) {
	    		System.out.print(m[i][j] + " ");
	    	}
	    	System.out.println();
	    }
	
    }
    
    
}

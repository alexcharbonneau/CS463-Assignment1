package ObjectLabelling;

import java.util.Arrays;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.*;
import java.util.*;
import java.io.BufferedWriter;

public class ObjectLabelling {

	public static int[][] countGroups(int[][] m) {

	    
	    int row = m.length;
	    int col = m[0].length;
	    int eCorners = 0;
	    int iCorners = 0;
	    double numObjects = 0;
	    int labelCount = 0;
	    
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
	    

//	    System.out.println("Number of objects = " + numObjects);

	    //turning 1's in the array into -1's
	    for(int i=0; i<m.length; i++) {
	    	for(int j=0; j<m[0].length; j++) {
	    		if(m[i][j]==1) {
	    			m[i][j]=-1;
	    		}
	    	}
	    }

	    FindComponents(m, labelCount);
//	    UnionFind4n(m);
	    UnionFind8n(m);


	    CorrectLabels(m);
	    CountLabels(m);
//	    PrintArray(m);
	    return m;
	    
	}
	
	public static void FindComponents(int[][]x, int labelCount) { //finding pixels that arent 0's
		for(int i=0; i<x.length; i++) {
			for(int j=0;j<x[0].length; j++) {
				if(x[i][j]==-1) {
					labelCount++;
					x[i][j]=labelCount;
					ConnectedComponents(x, i, j);
				}
				if(x[i][j]>0) {
					ConnectedComponents(x, i, j);
				}
			}
		}
	}
	
	public static void ConnectedComponents(int[][]x, int i, int j) { //turning right neighbor & top neighbor into connected component
		if(j<x[0].length-1) {
			if(x[i][j+1]==-1) {
				x[i][j+1] = x[i][j];
			}
		}
		if(i<x.length-1) {
			if(x[i+1][j]==-1) {
				x[i+1][j] = x[i][j];
			}
		}
	}
	
	public static void UnionFind4n(int[][]x) {
		for(int i=0; i<x.length; i++) {
			for(int j=0;j<x[0].length; j++) {
				if(x[i][j]!=0) {
					if(i>0) {
						if(x[i-1][j]<x[i][j] && x[i-1][j]!=0) {
							int lower = x[i-1][j];
							int higher = x[i][j];
							reLabel(x, lower, higher);
						}
						if(x[i-1][j]>x[i][j] && x[i-1][j]!=0) {
							int lower = x[i][j];
							int higher = x[i-1][j];
							reLabel(x, lower, higher);
						}
					}
					if(j>0) {
						if(x[i][j-1]<x[i][j] && x[i][j-1]!=0) {
							int lower = x[i][j-1];
							int higher = x[i][j];
							reLabel(x, lower, higher);
						}
						if(x[i][j-1]>x[i][j] && x[i][j-1]!=0) {
							int lower = x[i][j];
							int higher = x[i][j-1];
							reLabel(x, lower, higher);
						}
					}
				}

			}
		}
	}
	
	public static void UnionFind8n(int[][]x) { //finding all other neighbors
		for(int i=0; i<x.length; i++) {
			for(int j=0;j<x[0].length; j++) {
				if(x[i][j]!=0) {
					if(i>0) {
						if(x[i-1][j]<x[i][j] && x[i-1][j]!=0) {
							int lower = x[i-1][j];
							int higher = x[i][j];
							reLabel(x, lower, higher);
						}
						if(x[i-1][j]>x[i][j] && x[i-1][j]!=0) {
							int lower = x[i][j];
							int higher = x[i-1][j];
							reLabel(x, lower, higher);
						}
						
					}
					if(j>0) {
						if(x[i][j-1]<x[i][j] && x[i][j-1]!=0) {
							int lower = x[i][j-1];
							int higher = x[i][j];
							reLabel(x, lower, higher);
						}
						if(x[i][j-1]>x[i][j] && x[i][j-1]!=0) {
							int lower = x[i][j];
							int higher = x[i][j-1];
							reLabel(x, lower, higher);
						}
					}
					if(i>0 && j>0) {
						if(x[i-1][j-1]<x[i][j] && x[i-1][j-1]!=0) {
							int lower = x[i-1][j-1];
							int higher = x[i][j];
							reLabel(x, lower, higher);
						}
						if(x[i-1][j-1]>x[i][j] && x[i-1][j-1]!=0) {
							int lower = x[i][j];
							int higher = x[i-1][j-1];
							reLabel(x, lower, higher);
						}
					}
					if(i>0 && j<x[0].length-1) {
						if(x[i-1][j+1]<x[i][j] && x[i-1][j+1]!=0) {
							int lower = x[i-1][j+1];
							int higher = x[i][j];
							reLabel(x, lower, higher);
						}
						if(x[i-1][j+1]>x[i][j] && x[i-1][j+1]!=0) {
							int lower = x[i][j];
							int higher = x[i-1][j+1];
							reLabel(x, lower, higher);
						}
					}
					if(i<x.length-1 && j>0) {
						if(x[i+1][j-1]<x[i][j] && x[i+1][j-1]!=0) {
							int lower = x[i+1][j-1];
							int higher = x[i][j];
							reLabel(x, lower, higher);
						}
						if(x[i+1][j-1]>x[i][j] && x[i+1][j-1]!=0) {
							int lower = x[i][j];
							int higher = x[i+1][j-1];
							reLabel(x, lower, higher);
						}
					}
					if(i<x.length-1 && j<x[0].length-1) {
						if(x[i+1][j+1]<x[i][j] && x[i+1][j+1]!=0) {
							int lower = x[i+1][j+1];
							int higher = x[i][j];
							reLabel(x, lower, higher);
						}
						if(x[i+1][j+1]>x[i][j] && x[i+1][j+1]!=0) {
							int lower = x[i][j];
							int higher = x[i+1][j+1];
							reLabel(x, lower, higher);
						}
					}
				}

			}
		}
	}
	
	

	
	public static void reLabel(int[][]x, int lower, int higher) { //relabelling to have same label for a connected component
		for(int i=0; i<x.length; i++) {
			for(int j=0;j<x[0].length; j++) {
				if(x[i][j]==higher) {
					x[i][j]=lower;
				}
			}
		}
	}
	
	
	
	public static void CountLabels(int[][]x) { //counting the number of labels
		int count =0;
		for (int labelNum=1; labelNum<500; labelNum++) {
			for (int i=0; i<x.length; i++){
				for(int j=0;j<x[0].length;j++) {
					if (x[i][j]==labelNum) {
						count++;
						labelNum++;
					}
				}	
			}
		}

//		System.out.print("Number of different objects = " + count);
	}
	
	public static void CorrectLabels(int[][]m) { //correcting the labels to have numbers from 1 to n
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		int label = 1;
		for (int i=0; i<m.length;i++) {
			for (int j=0; j<m[0].length; j++) {
				if(m[i][j]!=0) {
					label=m[i][j];
					if(!numbers.contains(label)) {
						numbers.add(label);
					}
					m[i][j]=(numbers.indexOf(label)+1);
				}	
			}
		}
	}
	
	public static void FileOutputter(int[][]m){
	    try {
	    	
	        StringBuilder content = new StringBuilder();
	        
	        for(int i=0; i<m.length; i++) {
				for(int j=0;j<m[0].length; j++) {
					content.append(" ");
					content.append(m[i][j]);
				}
				content.append("\n");
	        }
	        String strI = content.toString();
	        File file = new File("/users/Tyler/bird.txt");

	        // if file doesnt exists, then create it
	        if (!file.exists()) {
	            file.createNewFile();
	        }

	        FileWriter fw = new FileWriter(file.getAbsoluteFile());
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write(strI);
	        bw.close();

	        System.out.println("Done");

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}


    
    public static void PrintArray(int[][]m) { //print the array
    	for(int i=0; i<m.length; i++) {
	    	for(int j=0; j<m[0].length; j++) {
	    		System.out.print(m[i][j] + " ");
	    	}
	    	System.out.println();
	    }
	
    }
    
    
}

package ImageFeatures;

import java.util.ArrayList;
import java.util.*;

public class ImageFeatures {
	
	

	public static int area(int[][] binaryImageAreaCentroid) {
		
		
		int rows = 0;
		int columns = 0;
		rows = binaryImageAreaCentroid.length;
		columns = binaryImageAreaCentroid[0].length;
		int area = 0;
		


		 for (int i = 0; i<rows; i++) {                                
			 for (int j = 0; j<columns; j++) {
				 		if (binaryImageAreaCentroid[i][j] == 1) {
				 			area ++;
				 		}
				 		
	
	
	

}
}       return area;
		 
	}
	
	
	public static double r(int[][] binaryImageAreaCentroid, double objArea) {   //centroid (r,_)
		
		
		int rows = 0;
		int columns = 0;
		rows = binaryImageAreaCentroid.length;
		columns = binaryImageAreaCentroid[0].length;
		double r = 0;
		int rowCount = 0;
		int sumR = 0;
		
	

        
        	  
          
			for (int i = 0; i<rows; i++) { 	
		
			  	 for (int j = 0; j<columns; j++) {
			  		
			  	
				 		if (binaryImageAreaCentroid[i][j]== 1) {
				 			rowCount+= 1;

				 		}
				 		
						sumR += (rowCount*i);
	
				 		rowCount = 0;
				 	
				 		
				 		
			  	 		} 	
			  	

			
          }
			
			r = (1/objArea)*(sumR);
			
	 		return r;
		

		
			
			
	}
			
			public static double c(int[][] binaryImageAreaCentroid, double objArea) { 
				
				int rows = 0;
				int columns = 0;
				rows = binaryImageAreaCentroid.length;
				columns = binaryImageAreaCentroid[0].length;
				double c = 0;
				int columnCount = 0;
				int sumC = 0;
				int increment = 0;
			
				
			
			
			
			while (increment < columns){
			for (int i = 0; i<rows; i++) { 
				
		 		if (binaryImageAreaCentroid[i][increment]== 1) {
		 			columnCount = columnCount + binaryImageAreaCentroid[i][increment];
		 		}
			
		 		 
			}
			
		
			
			sumC += (columnCount*increment);
			increment++;
			columnCount = 0;
			
					}
		
			
	 		     
	 		c = (1/objArea)*(sumC);	
	 	

			return c;
	 	
	 	 
	 		 
		
			}
			
			/**
			 * 
			 * @param r
			 * @param c
			 * @param binaryImage
			 * @return
			 * @description Checks if pixel at position r & c is a border pixel
			 */
			public static boolean isBorderPixelN4(int r, int c, int[][] binaryImage) {
				return ((binaryImage[r-1][c] == 0 || binaryImage[r+1][c] == 0 || binaryImage[r][c-1] == 0 || binaryImage[r][c+1] == 0)) ;	
			}
			
			/**
			 * 
			 * @param binaryObjectArray
			 * @return
			 * @description calculates the perimeter length N4 with connectivity-8
			 */
			public static double n4PerimeterLength(int[][] binaryObjectArray) {
				
				int firstPixelRow = 0;
				int firstPixelColumn = 0;
				double perimeter = 0;
				boolean[][] alreadyVisitedPixelArray = new boolean[binaryObjectArray.length][binaryObjectArray[0].length];
				
				OUTER_LOOP : for (int i = 0; i < binaryObjectArray.length; i++) {		//find first border pixel
					for (int j = 0; j < binaryObjectArray[0].length; j++) {
						if(binaryObjectArray[i][j] == 1 && isBorderPixelN4(i, j, binaryObjectArray)) {
							firstPixelRow = i;
							firstPixelColumn = j;
							break OUTER_LOOP;
						}
					}
				}
				
				int nextPixelRow = firstPixelRow;
				int nextPixelColumn = firstPixelColumn;
				
				
				do {																					//find the next pixel border scanning clockwise starting at [row][column+1]
					if(binaryObjectArray[nextPixelRow][nextPixelColumn + 1] == 1 
							&& isBorderPixelN4(nextPixelRow, nextPixelColumn + 1, binaryObjectArray) 
							&& alreadyVisitedPixelArray[nextPixelRow][nextPixelColumn +1] == false) {
						alreadyVisitedPixelArray[nextPixelRow][nextPixelColumn +1] = true;
						nextPixelColumn += 1;
						perimeter += 1;
					}
					else if(binaryObjectArray[nextPixelRow + 1][nextPixelColumn + 1] == 1 
							&& isBorderPixelN4(nextPixelRow +1, nextPixelColumn +1, binaryObjectArray) 
							&& alreadyVisitedPixelArray[nextPixelRow +1][nextPixelColumn +1] == false) {
						alreadyVisitedPixelArray[nextPixelRow +1][nextPixelColumn +1] = true;
						nextPixelRow += 1;
						nextPixelColumn += 1;
						perimeter += 1.40;
					}
					else if(binaryObjectArray[nextPixelRow + 1][nextPixelColumn] == 1 
							&& isBorderPixelN4(nextPixelRow+1, nextPixelColumn, binaryObjectArray) 
							&& alreadyVisitedPixelArray[nextPixelRow +1][nextPixelColumn] == false) {
						alreadyVisitedPixelArray[nextPixelRow +1][nextPixelColumn] = true;
						nextPixelRow += 1;
						perimeter += 1.0;
					}
					else if(binaryObjectArray[nextPixelRow + 1][nextPixelColumn -1] == 1 
							&& isBorderPixelN4(nextPixelRow +1, nextPixelColumn -1, binaryObjectArray) 
							&& alreadyVisitedPixelArray[nextPixelRow +1][nextPixelColumn -1] == false) {
						
						alreadyVisitedPixelArray[nextPixelRow +1][nextPixelColumn-1] = true;
						nextPixelRow += 1;
						nextPixelColumn -= 1;
						perimeter += 1.40;
					}
					else if(binaryObjectArray[nextPixelRow][nextPixelColumn -1] == 1 
							&& isBorderPixelN4(nextPixelRow, nextPixelColumn -1, binaryObjectArray) 
							&& alreadyVisitedPixelArray[nextPixelRow][nextPixelColumn -1] == false) {
						alreadyVisitedPixelArray[nextPixelRow][nextPixelColumn -1] = true;
						nextPixelColumn -= 1;
						perimeter += 1;
					}
					else if(binaryObjectArray[nextPixelRow - 1][nextPixelColumn -1] == 1 
							&& isBorderPixelN4(nextPixelRow -1, nextPixelColumn-1, binaryObjectArray) 
							&& alreadyVisitedPixelArray[nextPixelRow -1][nextPixelColumn -1] == false) {
						alreadyVisitedPixelArray[nextPixelRow -1][nextPixelColumn -1] = true;
						nextPixelRow -= 1;
						nextPixelColumn -= 1;
						perimeter += 1.40;
					}
					
					else if(binaryObjectArray[nextPixelRow - 1][nextPixelColumn] == 1 
							&& isBorderPixelN4(nextPixelRow -1, nextPixelColumn, binaryObjectArray) 
							&& alreadyVisitedPixelArray[nextPixelRow -1][nextPixelColumn] == false) {
						alreadyVisitedPixelArray[nextPixelRow -1][nextPixelColumn] = true;
						nextPixelRow -= 1;
						perimeter += 1;

					}
					else if(binaryObjectArray[nextPixelRow - 1][nextPixelColumn +1] == 1 
							&& isBorderPixelN4(nextPixelRow -1, nextPixelColumn + 1, binaryObjectArray) 
							&& alreadyVisitedPixelArray[nextPixelRow -1][nextPixelColumn+1] == false) {
						alreadyVisitedPixelArray[nextPixelRow -1][nextPixelColumn+1] = true;
						nextPixelRow -= 1;
						nextPixelColumn += 1;
						perimeter += 1.4;
					}
					
				} while (alreadyVisitedPixelArray[firstPixelRow][firstPixelColumn] == false);

				
				return perimeter;
			}
				  
	
		public static void main (String[] args) {
			int[][] arrayTest = {
					{0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,1,1,0,0,0,0},
					{0,0,0,0,1,1,1,1,1,0},
					{0,0,1,1,1,1,1,1,1,0},
					{0,1,1,1,1,1,1,0,0,0},
					{0,0,0,0,1,1,0,0,0,0},
					{0,0,0,0,0,1,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0}
					};
					
					
					
			System.out.println(n4PerimeterLength(arrayTest));
			
		} 		 
				
	}
	

 
	




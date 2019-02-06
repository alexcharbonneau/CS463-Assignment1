package ImageFeatures;

import java.util.ArrayList;

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
			
			public static double c(int[][] binaryImageAreaCentroid, double objArea) {    //centroid (_,c)
				
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
			
			public static double getPerimeter(int[][] pixelmap) {
				double perimeter = 0;
				int foundbounds = 0;
				for (int i = 1; i < pixelmap.length - 1; i++) {
					for (int j = 1; j < pixelmap[0].length - 1; j++) {
						if (pixelmap[i][j] != 0 && isBorderPixelN4(i, j, pixelmap)) {
							foundbounds++;
							Boolean found = false;
							if (pixelmap[i + 1][j] == 0 && found == false) {
								if (pixelmap[i + 1][j + 1] != 0) {
									perimeter+=Math.sqrt(2) / 2;
									found = true;
								}
								else {
									perimeter += 0.5;
									found = true;
								}
							}
							
							if (pixelmap[i][j + 1] == 0&& found == false) {
								if (pixelmap[i + 1][j + 1] != 0) {
									perimeter+=Math.sqrt(2) / 2;
									found = true;
								}
								else {
									perimeter += 0.5;
									found = true;
								}
							}
							
							if (pixelmap[i][j - 1] == 0&& found == false) {
								if (pixelmap[i-1][j+1] != 0) {
									perimeter+=Math.sqrt(2) / 2;
									found = true;
								}
							}
							else {
								perimeter += 0.5;
								found = true;
							}
							
							if (pixelmap[i - 1][j - 1] == 0&& found == false) {
								if (pixelmap[i][j+1] != 0) {
									perimeter+=Math.sqrt(2) / 2;
									found = true;
								}
							}
							else {
								perimeter += 0.5;
								found = true;
							}
						}
					}
				}
				//System.out.println("foundbounds: " + foundbounds);
				return perimeter;	
			}
			
			
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
			
		public static double circularity(double perimeter, int area) {
			return Math.pow(perimeter,2) / area;
		}
				
		
		/*
		 * Second moment function to find second-order row moment
		 */
		public static double SecondMomentR(int[][]binaryImageAreaCentroid, int area, double r) {
			double secondMomentRow = 0;
			int rowCount = 0;
			double sumR = 0;
			
			
			for(int i=0; i<binaryImageAreaCentroid.length; i++) {
				for (int j=0; j<binaryImageAreaCentroid[0].length; j++) {
			 		if (binaryImageAreaCentroid[i][j]!= 0) {
			 			rowCount+= 1;
			 		}
			
				}
				if(rowCount!=0) {
					sumR += (Math.pow((i)-r,2))*rowCount;
					rowCount=0;
				}
			}
			secondMomentRow = (((double)1/area)*sumR);
//			System.out.println(secondMomentRow);
			return secondMomentRow;
		}
		
		/*
		 * Second moment function to find second-order column moment
		 */
		public static double SecondMomentC(int[][]binaryImageAreaCentroid, int area, double c) {
			double secondMomentCol = 0;
			int colCount = 0;
			double sumC = 0;
			
			
			for(int i=0; i<binaryImageAreaCentroid[0].length; i++) {
				for (int j=0; j<binaryImageAreaCentroid.length; j++) {
			 		if (binaryImageAreaCentroid[j][i]!= 0) {
			 			colCount+= 1;
			 		}
			
				}
				if(colCount!=0) {
					sumC += Math.pow((i)-c,2)*colCount;
					colCount=0;
				}
			}
			secondMomentCol = (((double)1/area)*sumC);
	//		System.out.println(secondMomentCol);
			return secondMomentCol;
		}
		
		/*
		 * Second moment function to find second-order mixed moment
		 */
		public static double SecondMomentRC(int[][]binaryImageAreaCentroid, int area, double r, double c) {
			double secondMomentMix = 0;
			double sumRC = 0;
			
			for(int i=0; i<binaryImageAreaCentroid.length; i++) {
				for (int j=0; j<binaryImageAreaCentroid[0].length; j++) {
			 		if (binaryImageAreaCentroid[i][j]!= 0) {
			 			sumRC += ((i-r)*(j-c));

			 		}
				}
			}
			
			secondMomentMix = (sumRC/area);
	//		System.out.println(secondMomentMix);
			return secondMomentMix;
			
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
					
					
					System.out.println(getPerimeter(arrayTest));
//			System.out.println(n4PerimeterLength(arrayTest));
//			SecondMomentR(arrayTest,area(arrayTest),r(arrayTest, area(arrayTest)));
//			SecondMomentC(arrayTest,area(arrayTest),c(arrayTest, area(arrayTest)));
//			SecondMomentRC(arrayTest,area(arrayTest),r(arrayTest, area(arrayTest)),c(arrayTest, area(arrayTest)));

		} 		 
				
	}
	

 
	




package SignificantObjects;

import java.awt.Point;

/* Author: Alexandre Charbonneau
 * This module assumes that every object found in the matrix will have a different label
 * labels must be an int
 * */

public class SignificantObjects {

	int sigsize = 4;
	
	public SignificantObjects() {
		
	}
	
	public ObjectDetails[] getObjects(int[][]A) {
		int objectCount = 0;
		int[] count = new int[A.length * A[0].length];	//making an array to count label size
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[0].length; j++) {
				count[A[i][j]]++;
			}
		}
		
		for (int i = 1; i < count.length; i++)	//finding how many objects are significant ignoring zeros
			if (count[i] >= sigsize)
				objectCount++;
		
		ObjectDetails[] resultList = new ObjectDetails[objectCount];	//building the array which contains the objectdetails
		
		for (int i = 1; i < count.length; i++) {
			int y = 0;
			int x = 0;
			if (count[i] >= sigsize) {
				boolean found = false;
				int row = 0;
				int col = 0;
				//finding what are the starting x and y, top left corner
				while (!found) {
					if (A[row][col] == i) {
						found = true;
					}
					else {
						col++;
						if (col == A[0].length) {
							col = 0;
							row++;
						}
					}
				}
				y = row;
				found = false;
				row = 0;
				col = 0;
				while (!found) {
					if (A[row][col] == i) {
						found = true;
					}
					else {
						row++;
						if (row == A.length) {
							row = 0;
							col++;
						}
					}
				}
				x = col;
				found = false;
			
				//starting from bottom right to find the other extremity
				
				int bottomx = 0;
				int bottomy = 0;
				
				row = A.length - 1;	//reseting those variables
				col = A[0].length - 1;
				
				while (!found) {
					if (A[row][col] == i) {
						found = true;
					}
					else {
						col--;
						if (col == -1) {
							col = A[0].length - 1;
							row--;
						}
					}
				}
				
				bottomy = row;
				found = false;
				
				row = A.length - 1;	//reseting those variables
				col = A[0].length - 1;
				
				while (!found) {
					if (A[row][col] == i) {
						found = true;
					}
					else {
						row--;
						if (row == -1) {
							row = A.length - 1;
							col--;
						}
					}
				}
				
				bottomx = col;
				
				int[][] pixels = new int[bottomy - y + 1][bottomx - x + 1];
				resultList[i - 1] = new ObjectDetails(new Point(x, y), pixels);
				for (int j = 0; j < pixels.length; j++) {
					for (int k = 0; k < pixels[0].length; k++) {
						if (A[j+y][k+x] == i)
							pixels[j][k] = 1;				//maps a binary representation of the object
					}
				}
				resultList[i - 1].setArea(count[i]);
			}	
		}
		
		return resultList;
		
	}
}

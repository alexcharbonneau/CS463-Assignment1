package SignificantObjects;

import java.awt.Point;

/* Author: Alexandre Charbonneau
 * This module assumes that every object found in the matrix will have a different label
 * labels must be an int
 * */

public class SignificantObjects {

	int sigsize = 20;
	
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
			else	//deleting objects which are too small
				for (int j = 0; j < A.length; j++) {
					for (int k = 0; k < A[0].length; k++) {
						if (A[j][k] == i)
							A[j][k] = 0;
					}
				}
		
		ObjectDetails[] resultList = new ObjectDetails[objectCount];	//building the array which contains the objectdetails
		
		int listPos = 0;
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
				
				int[][] pixels = new int[bottomy - y + 1 + 2][bottomx - x + 1 + 2];	//adding a padding of zeros around the object, hence the +2
				resultList[listPos] = new ObjectDetails(new Point(x, y), pixels);
				for (int j = 1; j < pixels.length - 1; j++) {							//starting row and col 1 so that zeros can be padded around it
					for (int k = 1; k < pixels[0].length - 1; k++) {
						if (A[j + y - 1][k + x - 1] == i)
							pixels[j][k] = 1;				//maps a binary representation of the object
					}
				}
				resultList[listPos].setArea(count[i]);
				listPos++;
			}	
		}
		
		return resultList;
		
	}
}

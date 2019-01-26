package SignificantObjects;

import java.awt.Point;

/* Author: Alexandre Charbonneau
 * This module assumes that every object found in the matrix will have a different label
 * labels must be an int
 * */

public class SignificantObjects {

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
		
		for (int i = 0; i < count.length; i++)	//finding how many objects are significant
			if (count[i] >= 20)
				objectCount++;
		
		ObjectDetails[] resultList = new ObjectDetails[objectCount];	//building the array which contains the objectdetails
		
		for (int i = 0; i < count.length; i++) {
			int y = 0;
			int x = 0;
			if (count[i] >= 20) {
				boolean found = false;
				int row = 0;
				int col = 0;
				//finding what are the starting x and y, top left corner
				while (!found) {
					if (A[row][col] == count[i]) {
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
				y = col;
				found = false;
				row = 0;
				col = 0;
				while (!found) {
					if (A[row][col] == count[i]) {
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
				x = row;
				found = false;
			
				//starting from bottom right to find the other extremity
				
				int bottomx = 0;
				int bottomy = 0;
				
				row = A.length - 1;	//reseting those variables
				col = A[0].length - 1;
				
				while (!found) {
					if (A[row][col] == count[i]) {
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
					if (A[row][col] == count[i]) {
						found = true;
					}
					else {
						row--;
						if (row == -1) {
							row = A[0].length - 1;
							col--;
						}
					}
				}
				
				bottomx = col;
				
				int[][] pixels = new int[bottomx - x + 1][ bottomy - y + 1];
				resultList[i] = new ObjectDetails(new Point(x, y), pixels);
			
			}

			
		}
		
		
		return resultList;
		
	}
}

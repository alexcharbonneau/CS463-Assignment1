package SignificantObjects;

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
		
		for (int i = 0; i < count.length; i++) {
			int y, x;
			if (count[i] >= 20) {
				objectCount++;
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
				y = row;
				found = false;
				row = 0;
				col = 0;
				while (!found) {
					
				}
			}
		}
		
		return null;
		
	}
}

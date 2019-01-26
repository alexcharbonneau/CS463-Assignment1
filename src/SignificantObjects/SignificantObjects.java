package SignificantObjects;

public class SignificantObjects {

	public SignificantObjects() {
		
	}
	
	public ObjectDetails[] getObjects(int[][]A) {
		
		int[] count = new int[A.length * A[0].length];	//making an array to count label size
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[0].length; j++) {
				count[A[i][j]]++;
			}
		}
		
		for (int i = 0; i < count.length; i++) {
			int y, x;
			if (count[i] >= 20) {
				for (int j = 0; j < A.length; j++) {
					for (int k = 0; k < A[0].length; k++) {
						
					}
				}
			}
		}
		
		return null;
		
	}
}

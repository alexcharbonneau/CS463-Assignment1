package Threshold;

public class Threshold {
	
	public Threshold() {
		//leave empty till further notice
	}
	
	public void automaticThreshold() {
		//to be implemented 
	}
	
	public static int[][] PGMThreshold(int[][] imageArray, int thresholdValue) throws ArrayIndexOutOfBoundsException {
			
			int imageNumberOfRows = imageArray.length;
			int imageNumberOfColumns = imageArray[0].length;
			
			for(int i =0; i < imageNumberOfRows; i++) 
			{
				for(int j=0; j< imageNumberOfColumns; j++) {
					if(imageArray[i][j] <= thresholdValue) {
						imageArray[i][j] = 1;
					}
					else {
						imageArray[i][j] = 0;
					}
					
				}
			}
			return imageArray;
		}
	
}

/** Author Lee Carrier-Coupal 
 * 
 */


package Threshold;

public class Threshold {
	
	
	//constructor
	public Threshold() {
		//leave empty till further notice
	}
	
	/**
	 * 
	 * @param imageArray 		2d array with values 0..255
	 * @return
	 */
	public int automaticThreshold(int[][] imageArray) {
		//to be implemented 
		
		return 0;
	}
	
	/**
	 * 
	 * @param imageArray   		2d array with values 0..255
	 * @param thresholdValue 	value between 0..255
	 * @return 
	 * @throws ArrayIndexOutOfBoundsException
	 */
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

/** Author Lee Carrier-Coupal 
 * 
 */


package Threshold;

public class Threshold {

	/**
	 * 
	 * @param imageArray   		2d array with values 0..255
	 * @param thresholdValue 	value between 0..255
	 * @return binary array (after threshold)
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
	
	/**
	 * 
	 * @param convertedImage
	 * @return histogram of pixel intensity distribution
	 */
	public int[] histogram(int[][] convertedImage) {			//creates an histogram of pixel intensity h(i)
		int[] histogram = new int[256];
		for (int i = 0; i < convertedImage.length; i++) {
			for (int j = 0; j < convertedImage[0].length; j++) {
				histogram[convertedImage[i][j]]++;
			}
		}
		return histogram;
	}
	/**
	 * 
	 * @param histogram
	 * @param totalNumberOfPixel
	 * @param thresholdValue
	 * @return first population weight probability q1
	 */
	private double firstPopulationProbabilityWeight(int[] histogram, int totalNumberOfPixel, int thresholdValue) {					
		double pixelIntensityProbabilityWeight = 0;
		
		for (int i = 0; i < thresholdValue; i++) {
			pixelIntensityProbabilityWeight  += ((double)histogram[i]) / ((double)totalNumberOfPixel);
		}
		return pixelIntensityProbabilityWeight;
	}
	/**
	 * 
	 * @param histogram
	 * @param totalNumberOfPixel
	 * @param thresholdValue
	 * @return second population weight probability q2
	 */
	private double secondPopulationProbabilityWeight(int[] histogram, int totalNumberOfPixel, int thresholdValue) {					 
		double pixelIntensityProbabilityWeight = 0;
		
		for (int i = thresholdValue; i < histogram.length; i++) {
			pixelIntensityProbabilityWeight += ((double)histogram[i]) / ((double)totalNumberOfPixel);
		}
		return pixelIntensityProbabilityWeight;
	}
	
	/**
	 * 
	 * @param histogram
	 * @param totalNumberOfPixel
	 * @param thresholdValue
	 * @return first population mean μ1
	 */
	private double meanFirstPopulation(int[] histogram, int totalNumberOfPixel, int thresholdValue) {						
		double meanFirstPopulation = 0;
		for (int i = 0; i < thresholdValue; i++) {
			meanFirstPopulation += (i * (((double)histogram[i]) / ((double)totalNumberOfPixel))) 
					/ firstPopulationProbabilityWeight(histogram, totalNumberOfPixel, thresholdValue);						 
		}
		return meanFirstPopulation;
	}
	/**
	 * 
	 * @param histogram
	 * @param totalNumberOfPixel
	 * @param thresholdValue
	 * @return second population mean μ2
	 */
	private double meanSecondPopulation(int[] histogram, int totalNumberOfPixel, int thresholdValue) {						
		double meanSecondPopulation = 0;
		for (int i = thresholdValue; i < histogram.length; i++) {
			meanSecondPopulation += (i * (((double)histogram[i]) / ((double)totalNumberOfPixel))) 
					/ secondPopulationProbabilityWeight(histogram, totalNumberOfPixel, thresholdValue);						 
		}
		return meanSecondPopulation;
	}
	/**
	 * 
	 * @param histogram
	 * @param totalNumberOfPixel
	 * @param thresholdValue
	 * @return first population variance σ1^2
	 */
	private double varianceFirstPopulation(int[] histogram, int totalNumberOfPixel, int thresholdValue) {					 
		double varianceFirstPopulation = 0;
		for (int i = 0; i < thresholdValue; i++) {
			varianceFirstPopulation += Math.pow(i - meanFirstPopulation(histogram, totalNumberOfPixel, thresholdValue),2)
					* (((double)histogram[i]) / ((double)totalNumberOfPixel)) 
					/ firstPopulationProbabilityWeight(histogram, totalNumberOfPixel, thresholdValue);
		}
		
		return Math.pow(varianceFirstPopulation,2);
	}
	
	/**
	 * 
	 * @param histogram
	 * @param totalNumberOfPixel
	 * @param thresholdValue
	 * @return second population variance σ2^2
	 */
	private double varianceSecondPopulation(int[] histogram, int totalNumberOfPixel, int thresholdValue) {					
		double varianceSecondPopulation =0;
		for (int i = thresholdValue+1; i < histogram.length; i++) {
			varianceSecondPopulation += Math.pow(i - meanSecondPopulation(histogram, totalNumberOfPixel, thresholdValue),2) 
					* (((double)histogram[i]) / ((double)totalNumberOfPixel)) 
					/ secondPopulationProbabilityWeight(histogram, totalNumberOfPixel, thresholdValue);
		}
		return Math.pow(varianceSecondPopulation,2);
	}
	/**
	 * 
	 * @param histogram
	 * @param totalNumberOfPixel
	 * @param thresholdValue
	 * @return square root of within class variant (σw^2)
	 */
	private double withinGroupVariance(int[] histogram, int totalNumberOfPixel, int thresholdValue) {
		double withinGroupVariance = 0;
		withinGroupVariance = (firstPopulationProbabilityWeight(histogram, totalNumberOfPixel, thresholdValue) 
				* varianceFirstPopulation(histogram, totalNumberOfPixel, thresholdValue)) 
				+ (secondPopulationProbabilityWeight(histogram, totalNumberOfPixel, thresholdValue) 
						* varianceSecondPopulation(histogram, totalNumberOfPixel, thresholdValue));
		return Math.sqrt(withinGroupVariance);
	}
	
	/**
	 * 
	 * @param imageArray 		2d array with values 0..255
	 * @return optimal threshold value
	 * @description finds minimal within class variance and outputs associated threshold value
	 */
	public int automaticThreshold(int[][] imageArray) {
		int[] histogram = new int[256];
		histogram = histogram(imageArray).clone();
		int totalNumberOfPixel = imageArray.length * imageArray[0].length;
		double[] withinGroupVariance = new double[256];
		
		
		int optimalThreshold = 1;
		
		for (int i = 25; i < 240; i++) {
			withinGroupVariance[i] = withinGroupVariance(histogram, totalNumberOfPixel, i);
		}
		double smallestWithinGroupVariance = 1000000;
		
		for (int i = 25; i < 240; i++) {
			if(smallestWithinGroupVariance > withinGroupVariance[i]) {
				smallestWithinGroupVariance = withinGroupVariance[i];
				optimalThreshold = i;
			}
		}

		return optimalThreshold;
	}
}

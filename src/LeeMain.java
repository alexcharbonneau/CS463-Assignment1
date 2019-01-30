import java.io.File;

import UserInterface.UserInterface;
import Threshold.Threshold;


public class LeeMain {
	
	//testing purpose only (hardcoded)
	public static void main(String[] args) {
		UserInterface test = new UserInterface();
		int[][] convertedImage = test.convertImage(new File("src/resources/images/image5.pgm")).clone();
		Threshold threshold = new Threshold();
		int[][] binaryImage = threshold.PGMThreshold(convertedImage, 127).clone();
		//test.displayMatrix(binaryImage, 1);
		
	}
}

import java.io.File;

import UserInterface.UserInterface;
import Threshold.Threshold;


public class LeeMain {
	
	//testing purpose only (hardcoded)
	public static void main(String[] args) {
		UserInterface test = new UserInterface();
		int[][] convertedImage = test.convertImage(new File("src/resources/images/image3.pgm")).clone();
		Threshold threshold = new Threshold();
		
		System.out.println("Hello");

		System.out.println(threshold.automaticThreshold(convertedImage));
		System.out.println();

				
	}
}

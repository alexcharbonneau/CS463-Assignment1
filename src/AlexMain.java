import java.io.File;

import NoiseFilter.NoiseFilter;
import ObjectLabelling.ObjectLabelling;
import SignificantObjects.ObjectDetails;
import SignificantObjects.SignificantObjects;
import Threshold.Threshold;
import UserInterface.UserInterface;

public class AlexMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//UserInterface test = new UserInterface();
		//UserInterface test2 = new UserInterface();
		UserInterface test3 = new UserInterface();
		
		
		//int[][] A = {{0,1,1,0,1}, {0,1,1,0,1}, {0,1,1,0,1}, {0,1,1,0,1}, {0,1,1,0,1}, {0,1,1,0,1}, {0,1,1,0,1}, {0,1,1,0,1}, {0,1,1,0,1}};
		//test.displayMatrix(A, 1);
		
		String path = new String("src\\Resources\\Images\\image3.pgm");
		int[][] converted = test3.convertImage(new File(path));
		
	
		
		converted = Threshold.PGMThreshold(converted, 127);
		converted = NoiseFilter.dilate(converted);
		converted = NoiseFilter.erode2(converted);

		
		
		int[][] objtest = {
				{0,0,1,1},
				{0,0,1,1},
				{1,1,1,0},
				{0,0,1,0},
				{0,2,1,0},
				{2,2,1,0},
				{0,2,1,0},
				{0,3,0,0},
				{0,3,3,3}				
		};
		
		converted = ObjectLabelling.countGroups(converted);
		test3.displayMatrix(converted, UserInterface.ColorMode.LABELS);
		SignificantObjects sig = new SignificantObjects();
		ObjectDetails obd[] = sig.getObjects(converted);
		
		//test.displayMatrix(obd[7].getPixelMap(), UserInterface.ColorMode.BINARY);
		
		//int[][] original = test2.convertImage(new File(path));
		//test2.displayMatrix(original, UserInterface.ColorMode.GRAYSCALE);
		
		System.out.println();
	}

}

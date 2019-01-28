import java.io.File;

import NoiseFilter.NoiseFilter;
import SignificantObjects.ObjectDetails;
import SignificantObjects.SignificantObjects;
import Threshold.Threshold;
import UserInterface.UserInterface;

public class AlexMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserInterface test = new UserInterface();
		//int[][] A = {{0,1,1,0,1}, {0,1,1,0,1}, {0,1,1,0,1}, {0,1,1,0,1}, {0,1,1,0,1}, {0,1,1,0,1}, {0,1,1,0,1}, {0,1,1,0,1}, {0,1,1,0,1}};
		//test.displayMatrix(A, 1);
		String path = new String("src\\Resources\\Images\\image1.pgm");
		int[][] converted = test.convertImage(new File(path));

		test.displayMatrix(Threshold.PGMThreshold(converted, 127), 1);


		UserInterface test3 = new UserInterface();
		converted = NoiseFilter.dilate(converted);
		test3.displayMatrix(converted, 1);

		UserInterface test2 = new UserInterface();
		converted = NoiseFilter.erode2(converted);
		test2.displayMatrix(converted, 1);
		
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
		
		SignificantObjects sig = new SignificantObjects();
		ObjectDetails obd[] = sig.getObjects(objtest);
	}

}

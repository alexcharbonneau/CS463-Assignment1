import java.io.File;

import NoiseFilter.NoiseFilter;
import Threshold.Threshold;
import UserInterface.UserInterface;

public class AlexMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserInterface test = new UserInterface();
		//int[][] A = {{0,1,1,0,1}, {0,1,1,0,1}, {0,1,1,0,1}, {0,1,1,0,1}, {0,1,1,0,1}, {0,1,1,0,1}, {0,1,1,0,1}, {0,1,1,0,1}, {0,1,1,0,1}};
		//test.displayMatrix(A, 1);
		String path = new String("src\\Resources\\Images\\image3.pgm");
		int[][] converted = test.convertImage(new File(path));
		
		Threshold t = new Threshold();
		test.displayMatrix(Threshold.PGMThreshold(converted, 127), 1);


		UserInterface test3 = new UserInterface();
		converted = NoiseFilter.dilate(converted);
		test3.displayMatrix(converted, 1);

		UserInterface test2 = new UserInterface();
		converted = NoiseFilter.erode2(converted);
		test2.displayMatrix(converted, 1);
		
		
	}

}

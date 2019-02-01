import java.io.File;

import NoiseFilter.NoiseFilter;
import ObjectLabelling.ObjectLabelling;
import SignificantObjects.ObjectDetails;
import SignificantObjects.SignificantObjects;
import Threshold.Threshold;
import UserInterface.UserInterface;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TylerMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int[][]testArray;     // 2nd Dilation test array
//		testArray = new int [][] { 
//			{0,0,0,0,0,0,0,0,0},
//			{0,1,1,0,1,1,1,0,0},
//			{0,1,1,0,1,1,1,0,0},
//			{1,0,0,0,0,0,0,0,0},
//			{0,1,0,1,1,1,1,0,0},
//			{0,1,0,1,1,1,1,0,0},
//			{0,1,0,1,0,0,0,1,0},
//			{0,0,1,0,0,0,0,0,1},
//		};
		
		
		UserInterface test5 = new UserInterface();
		int[][] testArray = test5.convertImage(new File("/Users/Tyler/git/CS463-Assignment1/bin/Resources/Images/image1.pgm"));
		Threshold threshold = new Threshold();
		testArray=threshold.PGMThreshold(testArray, 150);
		
		NoiseFilter dilate = new NoiseFilter();
		NoiseFilter erode = new NoiseFilter();
		
		testArray = NoiseFilter.dilate(testArray);
		testArray = NoiseFilter.erode(testArray);
		
		
		ObjectLabelling tester = new ObjectLabelling();
		tester.countGroups(testArray);
		test5.displayMatrix(testArray, UserInterface.ColorMode.LABELS);
		
		SignificantObjects sig = new SignificantObjects();
		ObjectDetails obd[] = sig.getObjects(testArray);
		System.out.println();
	}

}

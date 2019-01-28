import UserInterface.UserInterface;
import NoiseFilter.NoiseFilter;
import ImageFeatures.ImageFeatures;

public class CoreyMain {

	public static void main(String[] args) {
	
		
	//	UserInterface coreyTest = new UserInterface();
		
		
		int[][] binaryImageDilation;
		binaryImageDilation = new int[][]{   // 1st Dilation test array
			 {0,0,0,0},
			 {0,1,1,0},
			 {0,0,0,0}
		};
			
		int[][]binaryImageDilation2;     // 2nd Dilation test array
		binaryImageDilation2 = new int [][] { 
			{0,0,0,0,0,0,0,0},
			{0,1,1,1,1,1,1,0},
			{0,0,0,1,1,1,1,0},
			{0,0,0,1,1,1,1,0},
			{0,0,1,1,1,1,1,0},
			{0,0,0,1,1,1,1,0},
			{0,0,1,1,0,0,0,0},
			{0,0,0,0,0,0,0,0},
		};
		
		
		int[][] binaryImageErosion1;
		binaryImageErosion1 = new int [][] { //Erosion test array
			{0,0,1,1,0},
			{0,0,1,1,0},
			{0,0,1,1,0},
			{1,1,1,1,1},
		};
		
		int[][]binaryImageErosion2;     
		binaryImageErosion2 = new int [][] { //Erosion2 test array
			{0,0,0,0,0,0,0,0},
			{0,1,1,1,1,1,1,0},
			{0,0,0,1,1,1,1,0},
			{0,0,0,1,1,1,1,0},
			{0,0,1,1,1,1,1,0},
			{0,0,0,1,1,1,1,0},
			{0,0,1,1,0,0,0,0},
			{0,0,0,0,0,0,0,0},
		};
		
		int[][]binaryImageErosion3;
		binaryImageErosion3 = new int [13][13];
			for (int i = 0 ; i<13; i++) {
				for (int j = 0; j<13; j++) {
					binaryImageErosion3[i][j] = 1;
				}
			}
				binaryImageErosion3[1][6] = 0;
				
				
				
		int[][]binaryImageAreaCentroid;
		binaryImageAreaCentroid = new int[][] {
			
			{0,0,0,0,0,0,0,0},
			{0,0,0,1,1,0,0,0},
			{0,0,0,1,1,1,1,1},
			{0,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,0,0},
			{0,0,0,1,1,0,0,0},
			{0,0,0,0,1,0,0,0},
			{0,0,0,0,0,0,0,0}				
			
		};
		
			
		

		

		
	
	//	NoiseFilter erosion = new NoiseFilter();
	//	erosion.erode(binaryImageErosion1);  //Erosion using line Structure
		
		

	//	NoiseFilter erosion2 = new NoiseFilter();
	//	erosion2.erode2(binaryImageErosion3); //Erosion using Squarebox Structure on big array
		
		

	//	NoiseFilter dilate = new NoiseFilter();
	//	dilate.dilate(binaryImageDilation);  //Dilation using small array
		
	ImageFeatures test = new ImageFeatures();
	
	
//	test.area(binaryImageAreaCentroid);
	
	int objArea = test.area(binaryImageAreaCentroid);
	test.r(binaryImageAreaCentroid, objArea);
	test.c(binaryImageAreaCentroid, objArea);
		
		
	
		
		
		
		
	
		
				

		
	}

}	
			
			

				
	



import UserInterface.UserInterface;
import NoiseFilter.NoiseFilter;

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
			
		
		int[][] dilationStructure;
		dilationStructure = new int [][] {
			
			{1},
			{1},
			{1,1}
			
		};
		
		
		int[][]erosionStructure2;
		erosionStructure2 = new int [][] {
			{1,1,1},
			{1,1,1},
			{1,1,1}
		};
		
		
		int[][] erosionStructure1;
		erosionStructure1 = new int [][] {
			{1},
			{1},
			{1}
		};
		

		
	//	int n = 4;
	//	int m = 5;
	//	NoiseFilter erosion = new NoiseFilter();
	//	erosion.erode(binaryImageErosion1,erosionStructure1, n,m);  //Erosion using line Structure
		
		
	//	int n = 8;
	//	int m = 8;
	//	NoiseFilter erosion2 = new NoiseFilter();
	//	erosion2.erode2(binaryImageErosion2, erosionStructure2, n, m); //Erosion using Squarebox Structure on big array
		
		
		int n = 3;
		int m = 4;
	
		NoiseFilter dilate = new NoiseFilter();
		dilate.dilate(binaryImageDilation, dilationStructure, n, m);  //Dilation using small array
		
		
		
	
		
				

		
	}

}	
			
			

				
	



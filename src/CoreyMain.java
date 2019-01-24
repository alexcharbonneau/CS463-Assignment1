import UserInterface.UserInterface;

public class CoreyMain {

	public static void main(String[] args) {
	
		
		UserInterface coreyTest = new UserInterface();
		
		int[][] binaryImageDilation;
		binaryImageDilation = new int[][]{   //Dilation test array
			 {0,0,0,0},
			 {0,1,1,0},
			 {0,0,0,0}
		};
				
		int[][] binaryImageErosion;
		binaryImageErosion = new int [][] { //Erosion test array
			{0,0,1,1,0},
			{0,0,1,1,0},
			{0,0,1,1,0},
			{1,1,1,1,1}
		};
		
		
		
		int[][] dilationStructure;
		dilationStructure = new int [][] {
			
			{1},
			{1},
			{1,1}
			
		};
		
		int[][] erosionStructure;
		erosionStructure = new int [][] {
			{1},
			{1},
			{1}
		};
		
		}
		
				
	}		
			
			
				
	



package NoiseFilter;

public class NoiseFilter {

	
	
	/* 	dilate(B,S) takes binary image B, places the origin
		of structuring element S over each 1-pixel, and ORs
		the structuring element S into the output image at
		the corresponding position.
	
		Structure:  1
	               [1]1    [] = origin
 	---------------------------------------------------------------
	
	
		erode(B,S) takes a binary image B, places the origin 
		of structuring element S over every pixel position, and
		ORs a binary 1 into that position of the output image 
		only if every position of S (with a 1) covers a 1 in B.
		
		Structure: 1
		          [1]  []= origin
	               1
	 */
	
	

		
		
	//	public void dilate(int[][] binaryImageDilation, int[][] dilationStructure, int n, int m){
			
			
			
			
			
		////	 for (int i = 0; i<n; i++) {
			//	 for (int j = 0; j<m; j++) {
					 
										 
					 
	//			 }
				 
		//	 }
	//	}

			 public void erode(int[][] binaryImageErosion, int[][] erosionStructure){
			
				 
				
				 int n = 4;
				 int m = 5;
				 int[][] erodedImage;
				 erodedImage = new int [4][5];
				
				 
			
				 for (int i = 0; i<n; i++) {                                 //EROSION USING STRUCTURE1 (from slides)
					 for (int j = 0; j<m; j++) {		 
						 if ( i+2 < n && erosionStructure[0][0] == binaryImageErosion[i][j]  &&  //added array boundary
								 erosionStructure[1][0] == binaryImageErosion[i+1][j] &&        //origin
								 erosionStructure[2][0] == binaryImageErosion[i+2][j]) {
					
							 		erodedImage[i+1][j] = 1;  //set origin to 1
					 }
						 
						 else if (i+1<n) {
							 	erodedImage[i+1][j] = 0;    //set origin to 0
								 
						 	  }
						 
							   else erodedImage[i][j] = 0;
						 
						 
						 	System.out.print(erodedImage[i][j]);
					 }
					 		System.out.println();
			
		}
			
	 }
			 	
		

	}
			 
	 
		 

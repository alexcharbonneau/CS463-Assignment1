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

	  public void erode(int[][] binaryImageErosion1, int[][] erosionStructure1,int[][] binaryImageErosion2, int[][] erosionStructure2){
			
				 
				
				 int n = 8;
				 int m = 8;
				 int[][] erodedImage;
				 erodedImage = new int [8][8];
				
				 
			
//				 for (int i = 0; i<n; i++) {                                 //EROSION USING STRUCTURE1 (from slides)
//					 for (int j = 0; j<m; j++) {		 
//						 if ( i+2 < n && erosionStructure1[0][0] == binaryImageErosion1[i][j]  &&  //added array boundary
//								 erosionStructure1[1][0] == binaryImageErosion1[i+1][j] &&        //origin
//								 erosionStructure1[2][0] == binaryImageErosion1[i+2][j]) {
//					
//							 		erodedImage[i+1][j] = 1;  //set origin to 1
//						 			}
//
//						 else if (i+1<n) {
//							 	erodedImage[i+1][j] = 0;    //set origin to 0
//								 
//						 	  }
//						 
//						 else 	
//							 	erodedImage[i][j] = 0;	
//						 	System.out.print(erodedImage[i][j]);
//					 }
//					 		System.out.println();
//			
//				}
//			
				 
				 
				 

				 for (int i = 0; i<n; i++) {                                 //EROSION USING STRUCTURE2 (from slides)
					 for (int j = 0; j<m; j++) {		 
						 if ( i+2 < n && j+2 <m && erosionStructure2[0][0] == binaryImageErosion2[i][j]  &&  //added array boundary
								 erosionStructure2[1][0] == binaryImageErosion2[i+1][j] &&        
								 erosionStructure2[2][0] == binaryImageErosion2[i+2][j] &&
								 erosionStructure2[0][1] == binaryImageErosion2[i][j+1] &&
								 erosionStructure2[1][1] == binaryImageErosion2[i+1][j+1] && //origin
								 erosionStructure2[2][1] == binaryImageErosion2[i+2][j+1] &&
								 erosionStructure2[0][2] == binaryImageErosion2[i][j+2] &&
								 erosionStructure2[1][2] == binaryImageErosion2[i+1][j+2] &&
								 erosionStructure2[2][2] == binaryImageErosion2[i+2][j+2]) {
								 
								
					
							 		erodedImage[i+1][j+1] = 1;  //set origin to 1
							 		
						 			}

						 else if (i+1<n && j+1 <m) {
							 	erodedImage[i+1][j+1] = 0;    //set origin to 0
						 }	 
						 	  						 
						  	
					 
						 	System.out.print(erodedImage[i][j]);
					 }
					 		System.out.println();
			
				 
			 } 
				
		

	}
			 
}
		 

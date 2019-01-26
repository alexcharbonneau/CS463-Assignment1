package NoiseFilter;


	
public class NoiseFilter {	

		
		
		public void dilate(int[][] binaryImageDilation, int[][] dilationStructure, int n, int m){

			
			int[][] dilatedImage;
			dilatedImage = new int [8][8];

	 		for (int i = 0; i<n; i++) {                                 //dilate using structure
	 				for (int j = 0; j<m; j++) {		 
	 					if ( i+2 < n && dilationStructure[1][0] == binaryImageDilation[i+1][j]) {
	 							dilatedImage[i][j] = 1;
	 							dilatedImage[i+1][j] = 1;
	 							dilatedImage[i+1][j+1] = 1;		
	 							}
	 					
	 					else if (i+1<n && j+1<m) {	
	 							dilatedImage[i+1][j+1] = 0;
	 							}				
	 											
	 				
	 					System.out.print(dilatedImage[i][j]);
	 				}    	
	 					System.out.println();
	 			}
		}

	
	
	  public void erode(int[][] binaryImageErosion1, int[][] erosionStructure1, int n, int m){
			
				 int[][] erodedImage;
				 erodedImage = new int [n][m];
					 
			
				 for (int i = 0; i<n; i++) {                                 //EROSION USING STRUCTURE1 (from slides)
					 for (int j = 0; j<m; j++) {		 
						 if ( i+2 < n && erosionStructure1[0][0] == binaryImageErosion1[i][j]  &&  //added array boundary
								 erosionStructure1[1][0] == binaryImageErosion1[i+1][j] &&        //origin
								 erosionStructure1[2][0] == binaryImageErosion1[i+2][j]) {
					
							 		erodedImage[i+1][j] = 1;  //set origin to 1
						 			}

						 else if (i+1<n) {
							 	erodedImage[i+1][j] = 0;    //set origin to 0
								 
						 	  }
						 
						 	System.out.print(erodedImage[i][j]);
					 }
					 		System.out.println();
				 }	
				}
			
				 
			public void erode2(	int[][] binaryImageErosion2, int[][] erosionStructure2, int n, int m) {
				
				 int[][] erodedImage;
				 erodedImage = new int [n][m];
			
				 

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
		 

package NoiseFilter;


	
public class NoiseFilter {	


		
		
		public static int[][] dilate(int[][] binaryImageDilation){
			
				int[][] dilationStructure;
				dilationStructure = new int [][] {
				
				{1},
				{1},
				{1,1}
				
			};
		
			
			int rows = 0;
			int columns = 0;
			rows = binaryImageDilation.length;
			columns = binaryImageDilation[0].length;
			
			int[][] dilatedImage;
			dilatedImage = new int [rows][columns];
			
			

	 		for (int i = 0; i<rows; i++) {                                 //dilate using structure
	 				for (int j = 0; j<columns; j++) {		 
	 					if ( i+2 < rows && dilationStructure[1][0] == binaryImageDilation[i+1][j]) {
	 							dilatedImage[i][j] = 1;
	 							dilatedImage[i+1][j] = 1;
	 							dilatedImage[i+1][j+1] = 1;		
	 							}
	 					
	 					else if (i+1<rows && j+1<columns) {	
	 							dilatedImage[i+1][j+1] = 0;
	 							}				
	 											
	 				
	 				//	System.out.print(dilatedImage[i][j]);
	 				}    	
	 				//	System.out.println();
	 			}
	 		
	 		return dilatedImage;
		}

	
	
	  public static int[][] erode(int[][] binaryImageErosion1){
		  
		  			int[][] erosionStructure1;
		  			erosionStructure1 = new int [][] {
		  				{1},
		  				{1},
		  				{1}
		  			};
		  
		  			
		  			int rows = 0;
					int columns = 0;
					rows = binaryImageErosion1.length;
					columns = binaryImageErosion1[0].length;
					
					int[][] erodedImage;
					erodedImage = new int [rows][columns];
		  
		  
			
					 
			
				 for (int i = 0; i<rows; i++) {                                 //EROSION USING STRUCTURE1 (from slides)
					 for (int j = 0; j<columns; j++) {		 
						 if ( i+2 < rows && erosionStructure1[0][0] == binaryImageErosion1[i][j]  &&  //added array boundary
								 erosionStructure1[1][0] == binaryImageErosion1[i+1][j] &&        //origin
								 erosionStructure1[2][0] == binaryImageErosion1[i+2][j]) {
					
							 		erodedImage[i+1][j] = 1;  //set origin to 1
						 			}

						 else if (i+1<rows) {
							 	erodedImage[i+1][j] = 0;    //set origin to 0
								 
						 	  }
						 
						// 	System.out.print(erodedImage[i][j]);
					 }
					// 		System.out.println();
				 }	
				 return erodedImage;
				}
			
				 
		public static int[][] erode2(int[][] binaryImageErosion2) {
					
					int[][]erosionStructure2;
					erosionStructure2 = new int [][] {
						{1,1,1},
						{1,1,1},
						{1,1,1}
					};
				
							
					int rows = 0;
					int columns = 0;
					rows = binaryImageErosion2.length;
					columns = binaryImageErosion2[0].length;
					
					int[][] erodedImage;
					erodedImage = new int [rows][columns];
		 
				 

				 for (int i = 0; i<rows; i++) {                                 //EROSION USING STRUCTURE2 (from slides)
					 for (int j = 0; j<columns; j++) {		 
						 if ( i+2 < rows && j+2 <columns && erosionStructure2[0][0] == binaryImageErosion2[i][j]  &&  //added array boundary
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

						 else if (i+1<rows && j+1 <columns) {
							 	erodedImage[i+1][j+1] = 0;    //set origin to 0
						 }	 
						 	  						 
						  	
					 
					//	 	System.out.print(erodedImage[i][j]);
					 }
					 //		System.out.println();
			
				 
			 } 
				
		    return erodedImage;

	}
			 
}
		 

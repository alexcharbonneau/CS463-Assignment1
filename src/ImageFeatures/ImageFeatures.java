package ImageFeatures;

public class ImageFeatures {
	
	

	public static int area(int[][] binaryImageAreaCentroid) {
		
		
		int rows = 0;
		int columns = 0;
		rows = binaryImageAreaCentroid.length;
		columns = binaryImageAreaCentroid[0].length;
		int area = 0;
		


		 for (int i = 0; i<rows; i++) {                                
			 for (int j = 0; j<columns; j++) {
				 		if (binaryImageAreaCentroid[i][j] == 1) {
				 			area ++;
				 		}
				 		
	
	
	

}
}       return area;
		 
	}
	
	
	public static double r(int[][] binaryImageAreaCentroid, double objArea) {   //centroid (r,_)
		
		
		int rows = 0;
		int columns = 0;
		rows = binaryImageAreaCentroid.length;
		columns = binaryImageAreaCentroid[0].length;
		double r = 0;
		int rowCount = 0;
		int sumR = 0;
		
	

        
        	  
          
			for (int i = 0; i<rows; i++) { 	
		
			  	 for (int j = 0; j<columns; j++) {
			  		
			  	
				 		if (binaryImageAreaCentroid[i][j]== 1) {
				 			rowCount+= 1;

				 		}
				 		
						sumR += (rowCount*i);
	
				 		rowCount = 0;
				 	
				 		
				 		
			  	 		} 	
			  	

			
          }
			
			r = (1/objArea)*(sumR);
			
	 		return r;
		

		
			
			
	}
			
			public static double c(int[][] binaryImageAreaCentroid, double objArea) { 
				
				int rows = 0;
				int columns = 0;
				rows = binaryImageAreaCentroid.length;
				columns = binaryImageAreaCentroid[0].length;
				double c = 0;
				int columnCount = 0;
				int sumC = 0;
				int increment = 0;
			
				
			
			
			
			while (increment < columns){
			for (int i = 0; i<rows; i++) { 
				
		 		if (binaryImageAreaCentroid[i][increment]== 1) {
		 			columnCount = columnCount + binaryImageAreaCentroid[i][increment];
		 		}
			
		 		 
			}
			
		
			
			sumC += (columnCount*increment);
			increment++;
			columnCount = 0;
			
					}
		
			
	 		     
	 		c = (1/objArea)*(sumC);	
	 	

			return c;
	 	
	 	 
	 		 
		
			}
		
		
				  
	
		 		 
				
			
	
	
	}
	
	




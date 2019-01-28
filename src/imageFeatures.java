
public class imageFeatures {
	
	

	public static int area(int[][] binaryImageAreaCentroid) {
		
		
		int rows = 0;
		int columns = 0;
		rows = binaryImageAreaCentroid.length;
		columns = binaryImageAreaCentroid[0].length;
		int area = 0;
		double r = 0;
		double c = 0;
		double rounded = Math.round(r);


		 for (int i = 0; i<rows; i++) {                                
			 for (int j = 0; j<columns; j++) {
				 		if (binaryImageAreaCentroid[i][j] == 1) {
				 			area ++;
				 		}
				 		
	
	
	

}
}       return area;
		 
	}
	
	
	public static void centroid(int[][] binaryImageAreaCentroid, double objArea) {
		
		
		int rows = 0;
		int columns = 0;
		rows = binaryImageAreaCentroid.length;
		columns = binaryImageAreaCentroid[0].length;
		double r = 0;
		double c = 0;
		int rowCount = 0;
		int columnCount = 0;
		int sumR = 0;
		int sumC = 0;
		int columnCounting = 0;
int increment = 0;
	

        
        	  
          
			for (int i = 0; i<rows; i++) { 	
		
			  	 for (int j = 0; j<columns; j++) {
			  		
			  	
				 		if (binaryImageAreaCentroid[i][j]== 1) {
				 			rowCount+= 1;

				 		}
				 		
						sumR += (rowCount*i);
	
				 		rowCount = 0;
				 	
				 		
				 		
			  	 		} 	
			  	
			
				
			  	
			 
			  	
			  
				
		
			
			
			
          }
			
			for (int i = 0; i<rows; i++) { 	
				column
			
				r = (1/objArea)*(sumR);
		 		//int roundedR = (int) Math.round(r);     
		 		c = (1/objArea)*(sumC);
		 		//System.out.print(c);
		 		
		 	 
		 		 
				  
	
		 		 
				
			
	
	
	}
	
	
}



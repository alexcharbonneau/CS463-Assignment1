package ObjectGrouping;

import SignificantObjects.ObjectDetails;

public class ObjectGrouping {

	public static ObjectDetails[] GroupObjects(ObjectDetails[] toBeGrouped){
		int count = 0;
		//method does not work, needs to be worked on
		for (int i=1; i<toBeGrouped.length; i++) {
			if (toBeGrouped[count].getGroup() == 0) {
				if(	(toBeGrouped[count].getArea() < toBeGrouped[i].getArea()+(toBeGrouped[i].getArea()*0.05)
					|| toBeGrouped[count].getArea() > toBeGrouped[i].getArea()-(toBeGrouped[i].getArea()*0.05))
					&&(toBeGrouped[count].getSecondMomentsMixed() < toBeGrouped[i].getSecondMomentsMixed()+(toBeGrouped[i].getSecondMomentsMixed()*0.05))
					||	(toBeGrouped[count].getSecondMomentsMixed()> toBeGrouped[i].getSecondMomentsMixed()+(toBeGrouped[i].getSecondMomentsMixed()*0.05))
				){
					//group object[count] & object[i] together
				}
				
			}
			
		}	
		
		
		
		return null;
	}
}

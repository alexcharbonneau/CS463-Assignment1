package ObjectGrouping;

import SignificantObjects.ObjectDetails;

public class ObjectGrouping {

	public static double upperlimit = 1.1;
	public static double lowerlimit = 0.9;
	
	
	public static void GroupObjects(ObjectDetails[] toBeGrouped){
		int count = 1;
		double secondcoef = 0;
		double areacoef = 0;
		double circoef = 0;
		for (int i = 0; i < toBeGrouped.length; i++) {
			if (toBeGrouped[i].getGroup() == 0) {
				toBeGrouped[i].setGroup(count);
			}
			for (int j = 0; j < toBeGrouped.length; j++) {
				if (toBeGrouped[j].getGroup() == 0) {
					secondcoef = toBeGrouped[i].getSecondMomentsMixed() / toBeGrouped[j].getSecondMomentsMixed();
					areacoef = ((double)toBeGrouped[i].getArea()) / ((double)toBeGrouped[j].getArea());
					circoef = toBeGrouped[i].getCircularity() / toBeGrouped[j].getCircularity();
					if (secondcoef < upperlimit && secondcoef > lowerlimit) {
						if (areacoef < upperlimit && areacoef > lowerlimit) {
							if (circoef < upperlimit && circoef > lowerlimit) {
								toBeGrouped[j].setGroup(count);
							}
						}
					}
				}
			}
			count++;
		}	
	}
}
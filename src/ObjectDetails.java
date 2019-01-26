/*
 * **************Critical Class, do not modify on a whim!**************************
 * Holds data for each individual object which was found on the main image
 */



import java.awt.Point;

public class ObjectDetails {
	
	private Point origin;	//where that particular object lies on the original 2d array
	private int[][] pixels;	//0 means background, 1 means foreground
	private double circularity;
	private double boundingBox;
	private double secondMomentRow;
	private double secondMomentCol;
	private double secondMomentMixed;
	
	public ObjectDetails(Point p, int[][] pixels) {
		if (pixels.length != 0)
			this.pixels = new int[pixels.length][pixels[0].length];
		else
			
		origin.x = p.x;
		origin.y = p.y;
	}
}

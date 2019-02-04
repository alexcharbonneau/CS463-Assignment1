package SignificantObjects;
/*
 * **************Critical Class, do not modify on a whim!**************************
 * Holds data for each individual object which was found on the main image
 */



import java.awt.Point;

public class ObjectDetails {
	
	private Point origin;			//where that particular object lies on the original 2d array
	private int[][] pixels;			//0 means background, 1 means foreground
	private double circularity;	
	private int boundingBoxRows;	//the number of rows to the bounding boxes
	private int boundingBoxCols;	//the number of columns to the bounding boxes
	private int area;				//how many pixels are in the object
	private int perimeter;			//how many pixels on the outer layer of the object
	private double secondMomentRow;
	private double secondMomentCol;
	private double secondMomentMixed;
	
	public ObjectDetails(Point p, int[][] pixels) {

		this.pixels = pixels;
		origin = p;
		boundingBoxRows = pixels.length;
		boundingBoxCols = pixels[0].length;
	}
	
	public int[][] getPixelMap(){
		return pixels;
	}
	
	public void setCircularity(double c) {
		circularity = c;
	}
	
	public double getCircularity() {
		return circularity;
	}
	
	public Point getOrigin() {
		return origin;
	}
	
	public void setArea(int a) {
		area = a;
	}
	
	public int getArea() {
		return area;
	}
	
	public void setPerimeter(int p) {
		perimeter = p;
	}
	
	public int getPerimeter() {
		return perimeter;
	}
	
	public int getBoundingBoxRows() {
		return boundingBoxRows;
	}
	
	public int getBoundingBoxCols() {
		return boundingBoxCols;
	}
	
	public void setSecondMomentsRow(double s) {
		secondMomentRow = s;
	}
	
	public void setSecondMomentsCol(double s) {
		secondMomentCol = s;
	}
	
	public void setSecondMomentsMixed(double s) {
		secondMomentMixed = s;
	}
	
	public double getSecondMomentsRow() {
		return secondMomentRow;
	}
	
	public double getSecondMomentsCol() {
		return secondMomentCol;
	}
	
	public double getSecondMomentsMixed() {
		return secondMomentMixed;
	}
	
	
	
	@Override
	public String toString() {
		return ("Object: "+ ((Object)this).hashCode()
				+ "Bounding Box" + this.boundingBoxRows + " Rows, " + this.boundingBoxCols + " Columns.\n"
				+ "Area: " + this.area + " pixels.\n"
				+ "Perimeter: " + this.perimeter + " pixels.\n"
				+ "Circularity: " + this.circularity + "\n"
				+ "Second Moments Mixed: " + this.secondMomentMixed + "\n");
	}
}

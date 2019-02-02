package UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class MyJPanel extends JPanel{

	private int[][] A;
	private int colorMode;
	private ArrayList<Integer>labels;
	private Color[] colors = new Color[10];
	public MyJPanel() {
		labels = new ArrayList<Integer>();
	}
	
	public void setA(int[][]A) {
		this.A = A;
		this.setPreferredSize(new Dimension(A[0].length, A.length));
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[0].length; j++) {
				if (!labels.contains(A[i][j])){
					labels.add(A[i][j]);
				}
			}
		}
		colors[1] = Color.BLACK;
		colors[2] = Color.BLUE;
		colors[3] = Color.ORANGE;
		colors[4] = Color.GREEN;
		colors[5] = Color.MAGENTA;
		colors[6] = Color.GRAY;
		colors[7] = Color.YELLOW;
		colors[8] = Color.PINK;
		colors[0] = Color.RED;
				
	}
	
	public void setMode(UserInterface.ColorMode m) {
		if (m == UserInterface.ColorMode.COLOR)
			colorMode = 5;
		if (m == UserInterface.ColorMode.BINARY)
			colorMode = 2;
		if (m == UserInterface.ColorMode.GRAYSCALE)
			colorMode = 1;
		if (m == UserInterface.ColorMode.GROUPS)
			colorMode = 4;
		if (m == UserInterface.ColorMode.LABELS)
			colorMode = 3;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (colorMode == 1) {	//grayscale
			for (int i = 0; i < A[0].length; i++) {
				for (int j = 0; j < A.length; j++) {
					g.setColor(new Color(A[j][i], A[j][i], A[j][i]));
					g.drawLine(i, j, i, j);
				}
			}
		}
		else if (colorMode == 2) { //binary
			for (int i = 0; i < A[0].length; i++) {
				for (int j = 0; j < A.length; j++) {
					if (A[j][i] != 0) {
						g.setColor(Color.BLACK);
						
					}
					else
						g.setColor(Color.WHITE);
					g.drawLine(i, j, i, j);
				}
			}
		}
		else if (colorMode == 3) {	//labels
			for (int i = 0; i < A[0].length; i++) {
				for (int j = 0; j < A.length; j++) {
					if (A[j][i] == 0)
						g.setColor(Color.WHITE);
					else
						g.setColor(colors[labels.indexOf(A[j][i]) % 8]);
					g.drawLine(i, j, i, j);
				}
			}
		}
	}
}

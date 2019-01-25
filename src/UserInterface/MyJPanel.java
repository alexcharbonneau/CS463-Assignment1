package UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MyJPanel extends JPanel{

	private int[][] A;
	private int colorMode;
	
	public void setA(int[][]A) {
		this.A = A;
		this.setPreferredSize(new Dimension(A[0].length, A.length));
	}
	
	public void setMode(int m) {
		colorMode = m;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (colorMode == 255) {
			for (int i = 0; i < A[0].length; i++) {
				for (int j = 0; j < A.length; j++) {
					g.setColor(new Color(A[j][i], A[j][i], A[j][i]));
					g.drawLine(i, j, i, j);
				}
			}
		}
		else if (colorMode == 1) { //binary
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
	}
}

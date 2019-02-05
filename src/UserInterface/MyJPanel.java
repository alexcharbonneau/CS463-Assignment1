package UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import SignificantObjects.ObjectDetails;

public class MyJPanel extends JPanel implements MouseListener{

	private int[][] A;
	private int colorMode;
	private ArrayList<Integer>labels;
	private Color[] colors = new Color[10];
	private UserInterface registeredUI;
	
	private ObjectDetails clickedObject;
	
	public MyJPanel(UserInterface ui) {
		registeredUI = ui;
		labels = new ArrayList<Integer>();
		addMouseListener(this);
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
			if (clickedObject != null) {
				g.setColor(Color.BLACK);
				g.drawRect(clickedObject.getOrigin().x, clickedObject.getOrigin().y, clickedObject.getPixelMap()[0].length-2, clickedObject.getPixelMap().length-2);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		ObjectDetails[] list;
		list = registeredUI.getObjectDetails();
		Point clickedP = e.getPoint();
		for (int i = 0; i < list.length; i++) {
			if ( (clickedP.x > list[i].getOrigin().x) && (clickedP.x < list[i].getOrigin().x + list[i].getPixelMap()[0].length - 1 ))
				if ( (clickedP.y > list[i].getOrigin().y) && (clickedP.y < list[i].getOrigin().y + list[i].getPixelMap().length - 1)) {
					clickedObject = list[i];
					System.out.println(clickedObject.toString());
					this.repaint();
					break;
				}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

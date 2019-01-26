/** Author: Alexandre Charbonneau
 ** Handles the initial file loading and processes it into a 2D array of int
 **/

package UserInterface;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class UserInterface {
	
	
	private JFrame mainWindow;
	private JPanel mainPanel;
	private MyJPanel drawingBoard;
	private JButton submit;
	private JLabel instructions;
	private JTextField fileInputField;
	private String filename;
	private File imageFile;
	private int[][] convertedMatrix;
	
	
	public UserInterface() {
		filename = new String();
		fileInputField = new JTextField(35);
		submit = new JButton("OK");
		instructions = new JLabel("Please enter the image file path");
		mainWindow = new JFrame();
		mainWindow.setTitle("Object Detection Engine");
		mainPanel = new JPanel();
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
		mainWindow.add(mainPanel);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(instructions, BorderLayout.PAGE_START);
		mainPanel.add(fileInputField, BorderLayout.WEST);
		mainPanel.add(submit, BorderLayout.EAST);
		mainWindow.pack();
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!fileInputField.getText().equals("")) {
					if (imageFile != null)
						mainPanel.remove(drawingBoard);	//gets rid of any previous image
					instructions.setText("...loading image...");
					filename = fileInputField.getText();
					imageFile = new File(filename);
					if (imageFile.exists() == true) {
						instructions.setText("File loaded successfully!");
						convertImage(imageFile);
					}
					else {
						instructions.setText("File not found");
					}
				}
			}
			
		});
	}
	
	public int[][] convertImage(File f) {
		int heigth;
		int width;
		int colorMode = 255;
		try {
			java.util.Scanner scanner = new Scanner(f);	
			scanner.nextLine(); //ignoring P2 for now
			width = scanner.nextInt();
			heigth = scanner.nextInt();
			colorMode = scanner.nextInt();
			convertedMatrix = new int[heigth][width];
			for (int i = 0; i < heigth; i++) {
				for (int j = 0; j < width; j++) {
					if (scanner.hasNext() == true)
						convertedMatrix[i][j] = scanner.nextInt();
					else {
						instructions.setText("This image file is not of a supported format");
						break;
					}
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		displayMatrix(convertedMatrix, colorMode);
		return convertedMatrix;
	}
	
	/*
	 * 2 color modes supported right now: binary and grayscale256
	 * binary image must use colorMode parameter: 1
	 * 		any non zero int found will be colored black, the rest will be white
	 * grayscale will shade the image with the appropriate grey ranging from 0-255 from the int found in the matrix
	 * */
	
	public void displayMatrix(int[][]A, int colorMode) {
		if (drawingBoard != null) {
			mainPanel.remove(drawingBoard);
		}
		drawingBoard = new MyJPanel();
		mainPanel.add(drawingBoard, BorderLayout.SOUTH);
		drawingBoard.setA(A);
		drawingBoard.setMode(colorMode);
		mainWindow.repaint();
		mainWindow.pack();
	}
}

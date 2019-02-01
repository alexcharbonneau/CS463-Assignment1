/** Author: Alexandre Charbonneau
 ** Handles the initial file loading and processes it into a 2D array of int
 **/

package UserInterface;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
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
	
	public enum ColorMode {GRAYSCALE, BINARY, LABELS, COLOR, GROUPS};
	
	private JFrame mainWindow;
	private JPanel mainPanel;
	private MyJPanel drawingBoard;
	private JPanel fileLoadArea, topLineArea, holder1, holder2, holder3, holder4;
	private JButton submit;
	private JLabel instructions;
	private JTextField fileInputField;
	private String filename;
	private File imageFile;
	private int[][] convertedMatrix;
	private Toolkit tk;
	private Dimension screensize;
	
	public UserInterface() {
		tk = Toolkit.getDefaultToolkit();
		screensize = tk.getScreenSize();
	
		holder1 = new JPanel();
		holder2 = new JPanel();
		holder3 = new JPanel();
		holder4 = new JPanel();
		
		filename = new String();
		fileInputField = new JTextField(40);
		submit = new JButton("OK");
		instructions = new JLabel("Please enter the image file path");
		mainWindow = new JFrame();
		mainWindow.setTitle("Object Detection Engine");
		mainPanel = new JPanel();
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
		mainWindow.add(mainPanel);
		mainWindow.setMaximumSize(screensize);
		mainWindow.setMinimumSize(new Dimension((int)(screensize.getWidth()-100), (int)(screensize.getHeight()-100)));
		mainPanel.setLayout(new BorderLayout());
		
		topLineArea = new JPanel();
		topLineArea.setLayout(new GridLayout(1, 5));
		fileLoadArea = new JPanel();
		fileLoadArea.setLayout(new BorderLayout());
		
		fileLoadArea.add(instructions, BorderLayout.PAGE_START);
		fileLoadArea.add(fileInputField, BorderLayout.LINE_START);
		fileLoadArea.add(submit, BorderLayout.LINE_END);
		
		topLineArea.add(fileLoadArea);
		topLineArea.add(holder1);
		topLineArea.add(holder2);
		topLineArea.add(holder3);
		mainPanel.add(topLineArea, BorderLayout.PAGE_START);
		
		
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
		int ColorModeFromFile = 0;
		try {
			java.util.Scanner scanner = new Scanner(f);	
			scanner.nextLine(); //ignoring P2 for now
			width = scanner.nextInt();
			heigth = scanner.nextInt();
			ColorModeFromFile = scanner.nextInt();
			
			double ratiow = width / screensize.width + 1.5;
			double ratioh = heigth / screensize.height + 1.5;
			
			int ratioWRounded = (int) ratiow;
			int ratioHRounded = (int) ratioh;
			
			convertedMatrix = new int[heigth / ratioHRounded][width / ratioWRounded];
			for (int i = 0; i < convertedMatrix.length; i ++) {
				for (int j = 0; j <convertedMatrix[0].length; j ++) {
					if (scanner.hasNext()) {
						convertedMatrix[i][j] = scanner.nextInt();
						if (scanner.hasNext()) {
							for (int k = 1; (k < ratioWRounded); k++)
								scanner.next();
							if (j == convertedMatrix[0].length - 1 && width % convertedMatrix[0].length != 0 && convertedMatrix[0].length < width) {
								for (int l = 0; l < width % convertedMatrix[0].length; l++) //getting rid of the extra input for the row
									if (scanner.hasNext())
										scanner.next();
								for (int l = 1; l < ratioHRounded; l++) { //getting rid of the extra rows. NOTE: apparently they are all written on a single line
									for (int m = 0; m < width; m++)
										if (scanner.hasNext()) {
											scanner.next();
										}
								}
							}
						}
					}
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
		displayMatrix(convertedMatrix, ColorMode.GRAYSCALE);
		return convertedMatrix;
	}
	
	/*
	 * 2 color modes supported right now: binary and grayscale256
	 * binary image must use colorMode parameter: 1
	 * 		any non zero int found will be colored black, the rest will be white
	 * grayscale will shade the image with the appropriate grey ranging from 0-255 from the int found in the matrix
	 * */
	
	public void displayMatrix(int[][]A, ColorMode mode) {
		if (drawingBoard != null) {
			mainPanel.remove(drawingBoard);
		}
		drawingBoard = new MyJPanel();
		mainPanel.add(drawingBoard, BorderLayout.CENTER);
		drawingBoard.setA(A);
		drawingBoard.setMode(mode);
		mainWindow.repaint();
		mainWindow.pack();
	}
}

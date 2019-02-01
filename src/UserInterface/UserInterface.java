/** Author: Alexandre Charbonneau
 ** Handles the initial file loading and processes it into a 2D array of int
 **/

package UserInterface;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ObjectLabelling.ObjectLabelling;
import Threshold.Threshold;


public class UserInterface {
	
	public enum ColorMode {GRAYSCALE, BINARY, LABELS, COLOR, GROUPS};
	
	private ColorMode toDisplay;
	
	
	
	private JFrame mainWindow;
	private JPanel mainPanel;
	private MyJPanel drawingBoard;
	private JPanel fileLoadArea, topLineArea, holder1, holder2, holder3, buttonArea;
	private JButton submit, JButtonErode1, JButtonErode2, JButtonDilate, JButtonOriginal, JButtonBinary, JButtonLabels, JButtonGroup, JButtonReport;
	private JLabel JLinstructions, JLgrayScaleThreshold, JLview, JLnoiseFilter, JLreports;
	private JTextField fileInputField;
	private String filename;
	private File imageFile;
	private int[][] convertedMatrix;	//the original matrix acquired from the image file
	private int[][] binaryMatrix;
	private int[][] filteredMatrix;
	private int[][] connectedMatrix;
	private Toolkit tk;
	private Dimension screensize;
	private JSlider JSThreshold;
	
	public UserInterface() {
		tk = Toolkit.getDefaultToolkit();
		screensize = tk.getScreenSize();
	
		toDisplay = ColorMode.GRAYSCALE;
		
		holder1 = new JPanel();
		holder2 = new JPanel();
		holder3 = new JPanel();
		
		buttonArea = new JPanel();
		buttonArea.setLayout(new GridLayout(0, 1, 1, 10));
		
		filename = new String();
		fileInputField = new JTextField(40);
		fileInputField.setText("src\\Resources\\Images\\image3.pgm");
		submit = new JButton("OK");
		JButtonErode1 = new JButton("Erode 1");
		JButtonErode2 = new JButton("Erode 2");
		JButtonDilate = new JButton("Dilate");
		JButtonOriginal = new JButton("Original");
		JButtonBinary = new JButton("Binary");
		JButtonLabels = new JButton("Labels");
		JButtonGroup = new JButton("Group");
		JButtonReport = new JButton("Report");
		
		JLgrayScaleThreshold = new JLabel("Grayscale Threshold");
		JLgrayScaleThreshold.setHorizontalAlignment(SwingConstants.CENTER);
		JLview = new JLabel("Views");
		JLview.setHorizontalAlignment(SwingConstants.CENTER);
		JLnoiseFilter = new JLabel("Noise Filters");
		JLnoiseFilter.setHorizontalAlignment(SwingConstants.CENTER);
		JLreports = new JLabel("Reports");
		JLreports.setHorizontalAlignment(SwingConstants.CENTER);
		
		JSThreshold = new JSlider(0, 255, 127);
		JSThreshold.setValue(127);
		
		JLview.setFont(new Font("arial black", Font.BOLD, 20));
		JLnoiseFilter.setFont(new Font("arial black", Font.BOLD, 20));
		JLgrayScaleThreshold.setFont(new Font("arial black", Font.BOLD, 20));
		JLreports.setFont(new Font("arial black", Font.BOLD, 20));
		
		buttonArea.add(JLgrayScaleThreshold);
		buttonArea.add(JSThreshold);
		buttonArea.add(JLnoiseFilter);
		buttonArea.add(JButtonErode1);
		buttonArea.add(JButtonErode2);
		buttonArea.add(JButtonDilate);
		buttonArea.add(JLview);
		buttonArea.add(JButtonOriginal);
		buttonArea.add(JButtonBinary);
		buttonArea.add(JButtonLabels);
		buttonArea.add(JButtonGroup);
		buttonArea.add(JLreports);
		buttonArea.add(JButtonReport);
		
		
		JLinstructions = new JLabel("Please enter the image file path");
		
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
		
		fileLoadArea.add(JLinstructions, BorderLayout.PAGE_START);
		fileLoadArea.add(fileInputField, BorderLayout.LINE_START);
		fileLoadArea.add(submit, BorderLayout.LINE_END);
		
		topLineArea.add(fileLoadArea);
		topLineArea.add(holder1);
		topLineArea.add(holder2);
		topLineArea.add(holder3);
		mainPanel.add(topLineArea, BorderLayout.PAGE_START);
		mainPanel.add(buttonArea, BorderLayout.LINE_END);
		
		JButtonBinary.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				toDisplay = ColorMode.BINARY;
				JButtonBinary.setEnabled(false);
				JButtonGroup.setEnabled(true);
				JButtonLabels.setEnabled(true);
				JButtonOriginal.setEnabled(true);
				displayMatrix(binaryMatrix, toDisplay);
			}
		});
		
		JButtonOriginal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButtonBinary.setEnabled(true);
				JButtonGroup.setEnabled(true);
				JButtonLabels.setEnabled(true);
				JButtonOriginal.setEnabled(false);
				toDisplay = ColorMode.GRAYSCALE;
				displayMatrix(convertedMatrix, toDisplay);
			}
		});
		
		JButtonLabels.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButtonBinary.setEnabled(true);
				JButtonGroup.setEnabled(true);
				JButtonLabels.setEnabled(false);
				JButtonOriginal.setEnabled(true);
				toDisplay = ColorMode.LABELS;
				displayMatrix(connectedMatrix, toDisplay);
			}
		});
		
		
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!fileInputField.getText().equals("")) {
					if (imageFile != null)
						mainPanel.remove(drawingBoard);	//gets rid of any previous image
					JLinstructions.setText("...loading image...");
					filename = fileInputField.getText();
					imageFile = new File(filename);
					if (imageFile.exists() == true) {
						JLinstructions.setText("File loaded successfully!");
						convertImage(imageFile);
					}
					else {
						JLinstructions.setText("File not found");
					}
				}
			}
			
		});
		
		JSThreshold.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				for (int i = 0; i < convertedMatrix.length; i++) {
					for (int j = 0; j < convertedMatrix[0].length; j++) {
						binaryMatrix[i][j] = convertedMatrix[i][j];
					}
				}
				
				binaryMatrix = Threshold.PGMThreshold(binaryMatrix, source.getValue());
				
				if (toDisplay == ColorMode.BINARY)
					displayMatrix(binaryMatrix, toDisplay);
				
				for (int i = 0; i < binaryMatrix.length; i++) {
					for (int j = 0; j < binaryMatrix[0].length; j++) {
						filteredMatrix[i][j] = binaryMatrix[i][j];
					}
				}
				for (int i = 0; i < binaryMatrix.length; i++) {
					for (int j = 0; j < binaryMatrix[0].length; j++) {
						connectedMatrix[i][j] = binaryMatrix[i][j];
					}
				}
				connectedMatrix = ObjectLabelling.countGroups(connectedMatrix);
				if (toDisplay == ColorMode.LABELS)
					displayMatrix(connectedMatrix, toDisplay);
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
			binaryMatrix = new int[heigth / ratioHRounded][width / ratioWRounded];
			filteredMatrix = new int[heigth / ratioHRounded][width / ratioWRounded];
			connectedMatrix = new int[heigth / ratioHRounded][width / ratioWRounded];
			
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
						JLinstructions.setText("This image file is not of a supported format");
						break;
					}
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < convertedMatrix.length; i++) {
			for (int j = 0; j < convertedMatrix[0].length; j++) {
				binaryMatrix[i][j] = convertedMatrix[i][j];
			}
		}
		displayMatrix(convertedMatrix, toDisplay);
		binaryMatrix = Threshold.PGMThreshold(binaryMatrix, JSThreshold.getValue());
		connectedMatrix = ObjectLabelling.countGroups(binaryMatrix);
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

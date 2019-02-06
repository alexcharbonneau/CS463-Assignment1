/** Author: Alexandre Charbonneau
 ** Handles the initial file loading and processes it into a 2D array of int
 **/

package UserInterface;
import static java.nio.file.StandardOpenOption.CREATE;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
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

import ImageFeatures.ImageFeatures;
import NoiseFilter.NoiseFilter;
import ObjectGrouping.ObjectGrouping;
import ObjectLabelling.ObjectLabelling;
import SignificantObjects.ObjectDetails;
import SignificantObjects.SignificantObjects;
import Threshold.Threshold;


public class UserInterface {
	
	public enum ColorMode {GRAYSCALE, BINARY, LABELS, COLOR, GROUPS};
	
	private ColorMode toDisplay;
	
	private JFrame mainWindow;
	private JPanel mainPanel;
	private MyJPanel drawingBoard;
	private JPanel fileLoadArea, topLineArea, holder1, holder2, holder3, buttonArea;
	private JButton submit, JButtonErode1, JButtonErode2, JButtonDilate, JButtonOriginal, JButtonBinary, JButtonLabels, JButtonGroup, JButtonReport;
	private JLabel JLinstructions, JLgrayScaleThreshold, JLview, JLnoiseFilter, JLreports, JLslidervalue;
	private JTextField fileInputField;
	private String filename;
	private File imageFile;
	
	private int[][] convertedMatrix;	//the original matrix acquired from the image file
	private int[][] binaryMatrix;
	private int[][] filteredMatrix;
	private int[][] connectedMatrix;
	
	private ObjectDetails[] objectList;
	
	private int erode1count = 0;
	private int erode2count = 0; 
	private int dilatecount = 0;
	
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
		fileInputField.setText("src\\Resources\\Images\\image1.pgm");
		
		
		submit = new JButton("OK");
		submit.setPreferredSize(new Dimension(60, 30));
		JButtonErode1 = new JButton("Erode 1 x" + erode1count);
		JButtonErode2 = new JButton("Erode 2 x" + erode2count);
		JButtonDilate = new JButton("Dilate x" + dilatecount);
		JButtonOriginal = new JButton("Original");
		JButtonOriginal.setEnabled(false);
		JButtonBinary = new JButton("Binary");
		JButtonBinary.setEnabled(false);
		JButtonLabels = new JButton("Labels");
		JButtonLabels.setEnabled(false);
		JButtonGroup = new JButton("Group");
		JButtonGroup.setEnabled(false);
		JButtonReport = new JButton("Report");
		JButtonReport.setEnabled(false);
		JButtonErode1.setEnabled(false);
		JButtonErode2.setEnabled(false);
		JButtonDilate.setEnabled(false);
		
		
		JLgrayScaleThreshold = new JLabel("Threshold Value: ");
		JLgrayScaleThreshold.setHorizontalAlignment(SwingConstants.CENTER);
		JLview = new JLabel("Views");
		JLview.setHorizontalAlignment(SwingConstants.CENTER);
		JLnoiseFilter = new JLabel("Noise Filters");
		JLnoiseFilter.setHorizontalAlignment(SwingConstants.CENTER);
		JLreports = new JLabel("Reports");
		JLreports.setHorizontalAlignment(SwingConstants.CENTER);
		JLslidervalue = new JLabel();
		
		JSThreshold = new JSlider(0, 255, 127);
		JSThreshold.setValue(127);
		Hashtable<Integer, JLabel> labelTable = new Hashtable();
		labelTable.put(0, new JLabel("0"));
		labelTable.put(255, new JLabel("255"));
		JSThreshold.setLabelTable(labelTable);
		JSThreshold.setMajorTickSpacing(255/2);
		JSThreshold.setMinorTickSpacing(255/15);
		JSThreshold.setPaintTicks(true);
		JSThreshold.setPaintLabels(true);
		
		
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
		mainWindow.setMaximumSize(new Dimension(screensize.width - 10, screensize.height - 10));
		mainWindow.setPreferredSize(new Dimension(700, 700));
		mainPanel.setLayout(new BorderLayout());
		mainWindow.pack();
		
		topLineArea = new JPanel();

		fileLoadArea = new JPanel();
		
		fileLoadArea.add(fileInputField);
		fileLoadArea.add(submit);
	
		topLineArea.add(JLinstructions);
		topLineArea.add(fileLoadArea);

		mainPanel.add(topLineArea, BorderLayout.PAGE_START);
		mainPanel.add(buttonArea, BorderLayout.LINE_END);
		
		
		JButtonErode1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				filteredMatrix = NoiseFilter.erode(filteredMatrix);
				for (int i = 0; i < filteredMatrix.length; i++) {
					for (int j = 0; j < filteredMatrix[0].length; j++) {
						connectedMatrix[i][j] = filteredMatrix[i][j];
					}
				}
				updateChanges();
				if (toDisplay == ColorMode.BINARY)
					displayMatrix(filteredMatrix, toDisplay);
				if (toDisplay == ColorMode.LABELS)
					displayMatrix(connectedMatrix, toDisplay);
				if (toDisplay == ColorMode.GROUPS)
					displayMatrix(connectedMatrix, toDisplay);
				erode1count++;
				JButtonErode1.setText("Erode 1 x" + erode1count);
			}
		});
		
		JButtonErode2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				filteredMatrix = NoiseFilter.erode2(filteredMatrix);
			
				for (int i = 0; i < filteredMatrix.length; i++) {
					for (int j = 0; j < filteredMatrix[0].length; j++) {
						connectedMatrix[i][j] = filteredMatrix[i][j];
					}
				}
				updateChanges();
				if (toDisplay == ColorMode.BINARY)
					displayMatrix(filteredMatrix, toDisplay);
				if (toDisplay == ColorMode.LABELS)
					displayMatrix(connectedMatrix, toDisplay);
				if (toDisplay == ColorMode.GROUPS)
					displayMatrix(connectedMatrix, toDisplay);
				erode2count++;
				JButtonErode2.setText("Erode 2 x" + erode2count);
			}
		});
		
		
		JButtonDilate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				filteredMatrix = NoiseFilter.dilate(filteredMatrix);
				
				for (int i = 0; i < filteredMatrix.length; i++) {
					for (int j = 0; j < filteredMatrix[0].length; j++) {
						connectedMatrix[i][j] = filteredMatrix[i][j];
					}
				}
				updateChanges();
				if (toDisplay == ColorMode.BINARY)
					displayMatrix(filteredMatrix, toDisplay);
				if (toDisplay == ColorMode.LABELS)
					displayMatrix(connectedMatrix, toDisplay);
				if (toDisplay == ColorMode.GROUPS)
					displayMatrix(connectedMatrix, toDisplay);
				dilatecount++;
				JButtonDilate.setText("Dilate x" + dilatecount);
			}
		});
		
		JButtonBinary.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				toDisplay = ColorMode.BINARY;
				JButtonBinary.setEnabled(false);
				JButtonGroup.setEnabled(true);
				JButtonLabels.setEnabled(true);
				JButtonOriginal.setEnabled(true);
				displayMatrix(filteredMatrix, toDisplay);
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
		
		JButtonGroup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButtonBinary.setEnabled(true);
				JButtonGroup.setEnabled(false);
				JButtonLabels.setEnabled(true);
				JButtonOriginal.setEnabled(true);
				toDisplay = ColorMode.GROUPS;
				displayMatrix(connectedMatrix, toDisplay);
			}
		});
		
		JButtonReport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Number of objects: " + objectList.length + "\n");
				for (int i = 0; i < objectList.length; i++) {
					System.out.println(objectList[i].toString());
				}
				
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
						toDisplay = ColorMode.GRAYSCALE;
						convertImage(imageFile);
						JButtonErode1.setEnabled(true);
						JButtonErode2.setEnabled(true);
						JButtonDilate.setEnabled(true);
						JButtonOriginal.setEnabled(false);
						JButtonGroup.setEnabled(true);
						JButtonLabels.setEnabled(true);
						JButtonBinary.setEnabled(true);
					}
					else {
						imageFile = null;
						JLinstructions.setText("File not found");
					}
				}
			}
			
		});
		
		JSThreshold.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if (convertedMatrix != null) {
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
							connectedMatrix[i][j] = binaryMatrix[i][j];
							filteredMatrix[i][j] = binaryMatrix[i][j];
						}
					}
					connectedMatrix = ObjectLabelling.countGroups(connectedMatrix);
					if (toDisplay == ColorMode.LABELS)
						displayMatrix(connectedMatrix, toDisplay);
					erode1count = 0;
					erode2count = 0;
					dilatecount = 0;
					JButtonErode1.setText("Erode 1 x" + erode1count);
					JButtonErode2.setText("Erode 2 x" + erode2count);
					JButtonDilate.setText("Dilate x" + dilatecount);
					JLgrayScaleThreshold.setText("Threshold Value: " + JSThreshold.getValue());
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
			
			int biggestRatio;
			
			if (ratioWRounded > ratioHRounded)
				biggestRatio = ratioWRounded;
			else
				biggestRatio = ratioHRounded;
			
			convertedMatrix = new int[heigth / biggestRatio][width / biggestRatio];
			binaryMatrix = new int[heigth / biggestRatio][width / biggestRatio];
			filteredMatrix = new int[heigth / biggestRatio][width / biggestRatio];
			connectedMatrix = new int[heigth / biggestRatio][width / biggestRatio];
			
			for (int i = 0; i < convertedMatrix.length; i ++) {
				for (int j = 0; j < convertedMatrix[0].length; j ++) {
					if (scanner.hasNext()) {
						convertedMatrix[i][j] = scanner.nextInt();
						if (scanner.hasNext()) {
							for (int k = 1; (k < biggestRatio); k++)
								scanner.next();
							if (j == convertedMatrix[0].length - 1  && convertedMatrix[0].length < width) {
								for (int l = 0; l < width % convertedMatrix[0].length; l++) //getting rid of the extra input for the row
									if (scanner.hasNext())
										scanner.next();
								for (int l = 1; l < biggestRatio; l++) { //getting rid of the extra rows. NOTE: apparently they are all written on a single line
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
			mainWindow.setMinimumSize(new Dimension (700, 700));
			mainWindow.setMaximumSize(new Dimension(screensize.width - 50, screensize.height - 50));
			mainWindow.setPreferredSize(new Dimension(convertedMatrix[0].length + 100 + buttonArea.getSize().width, screensize.height - 100));
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
		
		
		Threshold t = new Threshold();
		JSThreshold.setValue(t.automaticThreshold(binaryMatrix));
		
		//copying binarymatrix from original
		for (int i = 0; i < convertedMatrix.length; i++) {
			for (int j = 0; j < convertedMatrix[0].length; j++) {
				binaryMatrix[i][j] = convertedMatrix[i][j];
			}
		}
		
		JLgrayScaleThreshold.setText("Threshold Value: " + JSThreshold.getValue());
		binaryMatrix = Threshold.PGMThreshold(binaryMatrix, JSThreshold.getValue());
		
		//resetting the other matrixes as well
		for (int i = 0; i < binaryMatrix.length; i++) {
			for (int j = 0; j < binaryMatrix[0].length; j++) {
				connectedMatrix[i][j] = binaryMatrix[i][j];
				filteredMatrix[i][j] = binaryMatrix[i][j];
			}
		}
		
		updateChanges();
		
		if (objectList.length > 0)
			JButtonReport.setEnabled(true);
		
		
		mainWindow.pack();
		displayMatrix(convertedMatrix, toDisplay);
		return convertedMatrix;
	}
	
	//will recalculate the labels and the groups
	private void updateChanges() {

		connectedMatrix = ObjectLabelling.countGroups(connectedMatrix);
		SignificantObjects s = new SignificantObjects();
		objectList = s.getObjects(connectedMatrix);
		
		for (int i = 0; i < objectList.length; i++) {
			objectList[i].setSecondMomentsCol(ImageFeatures.SecondMomentC(objectList[i].getPixelMap(), objectList[i].getArea(), ImageFeatures.c(objectList[i].getPixelMap(), objectList[i].getArea())));
			objectList[i].setSecondMomentsRow(ImageFeatures.SecondMomentR(objectList[i].getPixelMap(), objectList[i].getArea(), ImageFeatures.r(objectList[i].getPixelMap(), objectList[i].getArea())));
			objectList[i].setSecondMomentsMixed(ImageFeatures.SecondMomentRC(objectList[i].getPixelMap(), objectList[i].getArea(), objectList[i].getSecondMomentsRow(), objectList[i].getSecondMomentsCol()));
			//objectList[i].setCircularity(ImageFeatures.circularity(ImageFeatures.n4PerimeterLength(objectList[i].getPixelMap()),objectList[i].getArea()));
		}
		ObjectGrouping.GroupObjects(objectList);
	}
	
	public void displayMatrix(int[][]A, ColorMode mode) {
		if (drawingBoard != null) {
			mainPanel.remove(drawingBoard);
		}
		drawingBoard = new MyJPanel(this);
		mainPanel.add(drawingBoard, BorderLayout.CENTER);
		drawingBoard.setA(A);
		drawingBoard.setMode(mode);
		mainWindow.repaint();
		mainWindow.pack();
	}
	
	public ObjectDetails[] getObjectDetails() {
		return objectList;
	}
}

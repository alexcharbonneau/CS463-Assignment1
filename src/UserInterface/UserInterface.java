/** Author: Alexandre Charbonneau
 ** Handles the initial file loading and processes it into a 2D array of int
 **/

package UserInterface;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserInterface {
	
	private JFrame mainWindow;
	private JPanel mainPanel;
	private JButton submit;
	private JLabel instructions;
	private JTextField fileInputField;
	private String filename;
	private File imageFile;
	
	
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
					instructions.setText("...loading image...");
					filename = fileInputField.getText();
					imageFile = new File(filename);
				}
			}
			
		});
	}
}

package UserInterface;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserInterface {
	
	private JFrame mainWindow;
	private JPanel mainPanel;
	
	public UserInterface() {
		mainWindow = new JFrame();
		mainWindow.setTitle("Object Detection Engine");
		mainPanel = new JPanel();
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
		mainWindow.add(mainPanel);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(new JLabel("Please enter the image file path"), BorderLayout.PAGE_START);
		mainPanel.add(new JTextField(35), BorderLayout.WEST);
		mainPanel.add(new JButton("OK"), BorderLayout.EAST);
		mainWindow.pack();
		}
}

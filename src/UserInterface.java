import javax.swing.JFrame;

public class UserInterface {
	
	private JFrame mainWindow;
	
	public UserInterface() {
		mainWindow = new JFrame();
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
		mainWindow.setSize(1000, 1000);
	}
}

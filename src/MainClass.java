import javax.swing.SwingUtilities;

import view.MainFrame;


public class MainClass {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				MainFrame frame = new MainFrame();
				frame.showGUI();
			}
		});
	}
}

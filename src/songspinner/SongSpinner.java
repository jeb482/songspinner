package songspinner;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import egl.math.Vector2;


public class SongSpinner extends java.applet.Applet {
	public static void main(String[] args) {
		JFrame mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		final WebComponent webComp = new WebComponent();
		webComp.setPreferredSize(new Dimension(512,512));
		mainFrame.getContentPane().add(webComp, BorderLayout.CENTER);
	
		JPanel buttonsPanel = new JPanel();
		JButton perfectWebButton = new JButton("Perfect Web");
		JButton clearWebButton = new JButton("Clear Web");
		buttonsPanel.add(perfectWebButton);
		buttonsPanel.add(clearWebButton);
		mainFrame.getContentPane().add(buttonsPanel, BorderLayout.WEST);
	
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	
	
	
	
//	public static A
	
}

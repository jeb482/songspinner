package songspinner;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import egl.math.Vector2;


public class SongSpinner extends java.applet.Applet {
	public static void main(String[] args) {
		JFrame mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// Set up app frame
		final WebComponent webComp = new WebComponent();
		webComp.setPreferredSize(new Dimension(512,512));
		mainFrame.getContentPane().add(webComp, BorderLayout.CENTER);

		
		JPanel buttonsPanel = new JPanel();
		JButton perfectWebButton = new JButton("Perfect Web");
		JButton randomLinearButton = new JButton("Random Linear");
		JButton PlayButton = new JButton("Play Web");
		JButton clearWebButton = new JButton("Clear Web");
		buttonsPanel.add(perfectWebButton);
		buttonsPanel.add(randomLinearButton);
		buttonsPanel.add(clearWebButton);
		mainFrame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
		
		// Callback Functions
		
		perfectWebButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				webComp.setWeb(Web.BuildPerfectWeb(10, 10, 1, .7f));
			}
			
		});
		
		randomLinearButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				webComp.setWeb(Web.BuildLinearWeb(10, 10, 1, .7f, .5f));
			}
			
		});
		
		clearWebButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				webComp.clearWeb();
			}
			
		});
		
		
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	
	
	
	
//	public static A
	
}


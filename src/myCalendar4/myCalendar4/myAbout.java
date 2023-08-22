package myCalendar4;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class myAbout {
	
	boolean  AboutVisible;
	JFrame 	frame 	= new JFrame("Über Days to Go");
	
	public void setVisible(boolean visible)
	{
		AboutVisible= visible;
		frame.setVisible(visible);
	}
	
	public boolean getVisible()
	{
		return AboutVisible;

	}
	
	myAbout()
	{
		JPanel 	panel 	= new JPanel();
		
		JLabel	Label1,Label2, Label3, Label4;
		
		GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);
	
		ImageIcon pic = new ImageIcon("_image.png", " To Go");
		Label1= new JLabel("Days to go ...");
		Label1.setFont(new Font("Arial", Font.BOLD, 16));
		Label2= new JLabel("Tage bis zum Zieldatum");
		Label2.setFont(new Font("Arial", Font.PLAIN, 12));
		Label3 = new JLabel("Version 0.5"); 
		Label3.setFont(new Font("Arial", Font.PLAIN, 10));
		Label4 = new JLabel(" © E. Athanassiou 2022");
		Label4.setFont(new Font("Arial", Font.PLAIN, 10));
		
		panel.add(new JLabel(pic), 	Main.makegbc(0, 0, GridBagConstraints.CENTER, 2));
		panel.add(new JLabel(" "), 	Main.makegbc(0, 1, GridBagConstraints.CENTER, 2));
		panel.add(Label1    ,		Main.makegbc(0, 2, GridBagConstraints.CENTER, 2));
		panel.add(new JLabel(" "), 	Main.makegbc(0, 3, GridBagConstraints.CENTER, 2));
		panel.add(Label2, 			Main.makegbc(0, 4, GridBagConstraints.CENTER, 2));
		panel.add(new JLabel(" "), 	Main.makegbc(0, 5, GridBagConstraints.CENTER, 2));
		panel.add(new JLabel(" "), 	Main.makegbc(0, 6, GridBagConstraints.CENTER, 2));
		panel.add(Label3, 			Main.makegbc(0, 7, GridBagConstraints.CENTER, 2));
		panel.add(Label4, 			Main.makegbc(0, 8, GridBagConstraints.CENTER, 2));
		
		frame.add(panel);
		frame.setMinimumSize(new Dimension(300, 300));
		frame.setLocation(500,300);
		
		frame.addWindowListener(new WindowAdapter() 
		{	@Override
            public void windowClosing(WindowEvent e) 
            { 	setVisible(false);
            }
		});
	}
	 
}
	

package myCalendar4;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class DigitalWatch implements Runnable
{
	JFrame f;
	static Thread t = null;
	String timeString = "";
	String dateString = "";
	String dayString  = "";
	JLabel day, date,time;
	JPanel panel = new JPanel();
	
	public JPanel getPanel()
	{
		return this.panel;
	}
	
	public void updateDigitalWatch(int COLORSET )
	{
	 	this.panel.setVisible(false);
	 	panel.setBackground(Main.ColorSetBg[COLORSET]);
		panel.setForeground(Main.ColorSetLbl[COLORSET]);
		day.setForeground(Main.ColorSetLbl[COLORSET]);
		date.setForeground(Main.ColorSetLbl[COLORSET]);
		time.setForeground(Main.ColorSetVal[COLORSET]);
		this.panel.setVisible(Main.Opt_Item[0].getState());
	}
	 
	 
	DigitalWatch(int COLORSET)
	{
		panel.setLayout(new GridBagLayout());
		panel.setBackground(Main.ColorSetBg[COLORSET]);
		panel.setForeground(Main.ColorSetLbl[COLORSET]);
		
		panel.add(new JLabel("       "), Main.makegbc(0, 0, GridBagConstraints.CENTER, 1));
		panel.add(new JLabel("       "), Main.makegbc(2, 0, GridBagConstraints.CENTER, 1));
		
		SimpleDateFormat Dayformatter = new SimpleDateFormat("EEEE");
		dayString = Dayformatter.format( Calendar.getInstance().getTime() );
		day= new JLabel(dayString);
		day.setFont(new Font("Arial", Font.BOLD, 32));
		day.setForeground(Main.ColorSetLbl[COLORSET]);
	    panel.add(day, Main.makegbc(1, 0, GridBagConstraints.CENTER, 1));
		
		SimpleDateFormat Dateformatter = new SimpleDateFormat("d MMMM");
		dateString = Dateformatter.format( Calendar.getInstance().getTime() );
		date = new JLabel(dateString);
		date.setFont(new Font("Arial", Font.BOLD, 32));
		date.setForeground(Main.ColorSetLbl[COLORSET]);
	    panel.add(date, Main.makegbc(1, 1, GridBagConstraints.CENTER, 1));
		
	    panel.add(new JSeparator(SwingConstants.HORIZONTAL), Main.makegbc(1, 2, GridBagConstraints.HORIZONTAL, 1));
	    
		SimpleDateFormat Timeformatter = new SimpleDateFormat("HH:mm:ss");
		timeString = Timeformatter.format( Calendar.getInstance().getTime() );
		time = new JLabel(timeString);
		time.setFont(new Font("Arial", Font.BOLD, 46));
		time.setForeground(Main.ColorSetVal[COLORSET]);
	    panel.add(time, Main.makegbc(1, 3, GridBagConstraints.CENTER, 1));
	    
	    panel.setBorder(BorderFactory.createTitledBorder(""));
	    panel.setPreferredSize(new Dimension(400, 400));
	    panel.setVisible(true);
		
		t = new Thread(this);
		t.start();
	}
	
	public void run() {
		try {
			while (true) 
			{
				SimpleDateFormat Timeformatter = new SimpleDateFormat("HH:mm:ss");
				timeString = Timeformatter.format( Calendar.getInstance().getTime() );
				time.setText(timeString);
				Thread.sleep(1000); // interval given in milliseconds
			}
		}
		catch (Exception e) { }
	}		
}

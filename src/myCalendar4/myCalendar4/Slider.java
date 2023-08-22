package myCalendar4;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Slider {
	
			JPanel 		panel;
	static	JLabel 		LabelAlter,aLabel ;
	static	JSlider 	meinSlider 	 = new JSlider();
	
	public  JPanel getPanel()
	{	return this.panel; }
	
	public  void updatePanel(int COLORSET)
	{
		this.panel.setVisible(false);
		this.panel.setBackground((Main.ColorSetBg[COLORSET]));
		aLabel.setForeground(Main.ColorSetLbl[COLORSET]); 
		LabelAlter.setText(Long.toString(Main.getDataPack().dataSet[7].Wert));
		LabelAlter.setFont(new Font("Arial", Font.BOLD, 42));
		LabelAlter.setForeground((Main.ColorSetVal[COLORSET]));
		meinSlider.setBackground((Main.ColorSetBg[COLORSET]));
		meinSlider.setForeground((Main.ColorSetLbl[COLORSET]));
		this.panel.setVisible(Main.Opt_Item[3].getState());
	}
	
	public  Slider(int COLORSET, myPreferences	Einstellungen )
	{
		panel = new JPanel();
		myDataPack data_63 = Main.getDataPack();
		panel.setLayout(new GridBagLayout());
		panel.setBackground(Main.ColorSetBg[COLORSET]);
		panel.setForeground(Main.ColorSetLbl[COLORSET]);
		panel.add(new JLabel("                  "), Main.makegbc(0, 0, GridBagConstraints.CENTER, 1));
		panel.add(new JLabel("            "), 		Main.makegbc(1, 0, GridBagConstraints.CENTER, 1));
		aLabel=new JLabel(" Alter ");
		aLabel.setForeground(Main.ColorSetLbl[COLORSET]); 
		panel.add(aLabel, Main.makegbc(2, 0, GridBagConstraints.CENTER, 1));
		LabelAlter = new JLabel(Long.toString(data_63.dataSet[7].Wert));
		LabelAlter.setFont(new Font("Arial", Font.BOLD, 42));
		LabelAlter.setForeground(Main.ColorSetVal[COLORSET]); 
		panel.add(LabelAlter,	    				Main.makegbc(2, 1, GridBagConstraints.CENTER, 1));
		panel.add(new JLabel("            "), 	    Main.makegbc(3, 0, GridBagConstraints.CENTER, 1));
		panel.add(new JLabel("                  "), Main.makegbc(4, 0, GridBagConstraints.CENTER, 1));
		
		Calendar	calendar 	= Calendar.getInstance();
		int 		currentYear = calendar.get(Calendar.YEAR);
		int			birthYear   = Einstellungen.Preferences[7];
		int			actualAge	= currentYear-birthYear;
																		// JSlider meinSlider = new JSlider();	 JSlider-Objekt wird erzeugt
		meinSlider.setMinimum(actualAge);								// Mindestwert 
		meinSlider.setMaximum(70);										// Maximalwert 									
		meinSlider.setMajorTickSpacing(5);								// Abstände zwischen den Teilmarkierungen werden festgelegt
		meinSlider.setMinorTickSpacing(1);	
		meinSlider.createStandardLabels(1);								// Standardmarkierungen werden erzeugt 
		meinSlider.setPaintTicks(true);									// Zeichnen der Markierungen wird aktiviert
		meinSlider.setPaintLabels(true);								// Zeichnen der Labels wird aktiviert
		meinSlider.setValue((int)data_63.dataSet[7].Wert);				// Schiebebalken wird auf das Zielalter gesetzt
		
		meinSlider.setForeground(Main.ColorSetLbl[COLORSET]);
		meinSlider.setBackground(Main.ColorSetBg[COLORSET]);
		
		meinSlider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		panel.add(meinSlider,Main.makegbc(0, 2, GridBagConstraints.HORIZONTAL, 5));				// Schiebebalken wird dem Panel hinzugefügt
		panel.setBorder(BorderFactory.createTitledBorder(""));
		panel.setPreferredSize(new Dimension(400, 400));
		
		meinSlider.addChangeListener(new ChangeListener()
		{
	          public void stateChanged(ChangeEvent e) { 
	        	  int alter= meinSlider.getValue();
	        	  int Year= Einstellungen.Preferences[7] + alter;
	        	  Einstellungen.Preferences[1] = Year; 
	        	  int Month = Einstellungen.Preferences[6];;
	        	  Einstellungen.Preferences[0] = Month;
	  
	        	  data_63.init(Einstellungen);
	              data_63.Calculate(Einstellungen);
	              Main.UpdateMain();      
	    }});		
	}
}

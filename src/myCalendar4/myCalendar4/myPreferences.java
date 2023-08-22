package myCalendar4;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
public class myPreferences {
	
	private static final 	String 	PREFERENCES_PATH = "Preferences.txt";
	
			JFrame 			frame 			= new JFrame("Einstellungen");
	static	String[] 		monthStrings 	= getMonthStrings(); 
			int[]			Preferences 	= new int[9];
	
	
	public void setVisible(boolean visible)
	{
		frame.setVisible(visible);
	}
	
	static public int getMonthIndex(String str)
	{ 	
		for(int i=0;i<12;i++)
				if(monthStrings[i]==str)
						return i+1;
		return 0;
	}
	
	static protected String[] getMonthStrings() 
	{
        String[] months = new java.text.DateFormatSymbols().getMonths();
        int lastIndex = months.length - 1;
        if (months[lastIndex] == null || months[lastIndex].length() <= 0)  //last item empty
        {	String[] monthStrings = new String[lastIndex];
            System.arraycopy(months, 0, monthStrings, 0, lastIndex);
            return monthStrings;
        } 
        else 																//last item not empty
        { 	return months;
        }
    }
	
	public static JFormattedTextField getTextField(JSpinner spinner) 
	{
		JComponent editor = spinner.getEditor();
	    if (editor instanceof JSpinner.DefaultEditor) 
	    {	return ((JSpinner.DefaultEditor)editor).getTextField();
	    } 
	    else 
	    {   System.err.println("Unexpected editor type: "  + spinner.getEditor().getClass() + " isn't a descendant of DefaultEditor");
	        return null;
	    }
	 }
	
	 void setterPref(int idx, int x)
	 { 
		this.Preferences[idx]=x;
	 }
	
	 int getterPref(int idx)
	 {
		 return this.Preferences[idx];
	 }
	 
	  void setPreferences(int p1, int p2, String p3,String p4,String p5,String p6,int p7, int p8 )
	 {
		 setterPref(0,p1);
		 setterPref(1,p2);
		 setterPref(2,Integer.valueOf(p3));
		 setterPref(3,Integer.valueOf(p4));
		 setterPref(4,Integer.valueOf(p5));
		 setterPref(5,Integer.valueOf(p6));
		 setterPref(6,p7 );
		 setterPref(7,p8);
	 }
	
	 static void savePreferences (int[] pr)
	 {	
		try 
		{	File fout = new File(PREFERENCES_PATH);
			FileOutputStream fos = new FileOutputStream(fout);
			BufferedWriter MyWriter = new BufferedWriter(new OutputStreamWriter(fos));
			for (int i = 0; i < 8; i++)
			{	MyWriter.write(Integer.toString(pr[i]));
				MyWriter.newLine();
			}
			MyWriter.close();
		} 
		catch (IOException e) 
		{	e.printStackTrace();}	
	 }
	 
	 void initWithPreferences()
	 {  
		try 
		{	File 	myObj 		= new File(PREFERENCES_PATH);
			int 	i 			= 0;
			Scanner myReader 	= new Scanner(myObj);
			while (myReader.hasNextLine()) 
			{ 	Preferences[i] = Integer.valueOf(myReader.nextLine());
			    i++;
			}
			myReader.close();
		} 
		catch (FileNotFoundException e) 
		{	System.out.println("An error occurred reading " + PREFERENCES_PATH);
			Preferences[0]=12; 
			Preferences[1]=2027;
			Preferences[2]=13; 
			Preferences[3]=30; 
			Preferences[4]=0; 
			Preferences[5]=10; 
			Preferences[6]=12;
			Preferences[7]=1964;
			e.printStackTrace();
		}
	}

	myPreferences(myDataPack data_63)
	{	
		JPanel[]			PrefPanels 	= new JPanel[6]; 
		JLabel[]			PrefLabels 	= new JLabel[6];
		JSpinner 			spinner1,spinner2, spinner3,spinner4;
		SpinnerListModel 	month 		= null;
		SpinnerNumberModel 	Jahr 		= null;
		SpinnerNumberModel 	Jahr2		= null;
		JFormattedTextField ftf 		= null;
		JTabbedPane 		tp			= new JTabbedPane(); 
		JTextField 			tfRestEU,tfEU,tfTrain,tfTrainLfd;
		JButton 			OK,Reset, Quit;
		 
		Calendar			calendar 	= Calendar.getInstance();
		int 				currentYear = calendar.get(Calendar.YEAR);
		String[] 			labels 		= {"Monat: ", "Jahr: ", "Resturlaub: ","Urlaub p.a.: ","Fortbildung lfd.: ","Fortbildung p.a.: "};
		    	
	    for (int i= 0;i<6;i++)
			PrefPanels[i] = new JPanel();
		for (int i= 0;i<6;i++)
		    PrefLabels[i] = new JLabel(labels[i]);
	    
		initWithPreferences();
		
		month 		= new SpinnerListModel(monthStrings);
		spinner1 	= new JSpinner(month);
		
		spinner1.setValue(monthStrings[Preferences[0]-1]);
	  	spinner1.setToolTipText("Monat des Zieltermines auswählen");
	 	ftf 		= getTextField(spinner1);
	   	if (ftf != null ) {
	     	ftf.setColumns(8); //specify more width than we need
	     	ftf.setHorizontalAlignment(JTextField.RIGHT);
	  	}
	         	
	 	Jahr 		= new SpinnerNumberModel(Preferences[1], currentYear, currentYear + 25,1);  
	  	spinner2 	= new JSpinner(Jahr);
	  	spinner2.setEditor(new JSpinner.NumberEditor(spinner2, "#"));
	 	spinner2.setToolTipText("Jahr des Zieltermines auswählen");
	    
	 	spinner3 	= new JSpinner(month);
	 	spinner3.setValue(monthStrings[11]);
	  	spinner3.setToolTipText("Geburttags Monat");
	 	ftf 		= getTextField(spinner3);
	   	if (ftf != null ) {
	     	ftf.setColumns(8); //specify more width than we need
	     	ftf.setHorizontalAlignment(JTextField.RIGHT);
	  	}
	   	
	   	Jahr2 		= new SpinnerNumberModel(1964, 1959, currentYear - 25,1);  
	  	spinner4 	= new JSpinner(Jahr2);
	  	spinner4.setEditor(new JSpinner.NumberEditor(spinner4, "#"));
	 	spinner4.setToolTipText("Geburtsjahr");
	 	
	 	tfRestEU 	= new JTextField(Integer.toString(Preferences[2]), 2); 
	  	tfEU 		= new JTextField(Integer.toString(Preferences[3]), 2); 
	    tfTrainLfd 	= new JTextField(Integer.toString(Preferences[4]), 2); 
	   	tfTrain 	= new JTextField(Integer.toString(Preferences[5]), 2); 	   	
	    
	    OK 			= new JButton("Übernehmen");   
	    Reset 		= new JButton("Reset");
	  	Quit 		= new JButton("Schliessen");
	        
	    GridBagLayout layout = new GridBagLayout();
	    PrefPanels[0].setLayout(layout);
	    PrefPanels[0].add(PrefLabels[0], 	Main.makegbc(2, 2, GridBagConstraints.HORIZONTAL,1));	
	    PrefPanels[0].add(spinner1, 		Main.makegbc(3, 2, GridBagConstraints.HORIZONTAL,1));
	    PrefPanels[0].add(PrefLabels[1],	Main.makegbc(2, 3, GridBagConstraints.HORIZONTAL,1));	
	    PrefPanels[0].add(spinner2,			Main.makegbc(3, 3, GridBagConstraints.HORIZONTAL,1));

	    PrefPanels[1].setLayout(layout);
	    PrefPanels[1].add(PrefLabels[2],	Main.makegbc(2, 2, GridBagConstraints.HORIZONTAL,1));	
	    PrefPanels[1].add(tfRestEU, 		Main.makegbc(3, 2, GridBagConstraints.HORIZONTAL,1));
	    PrefPanels[1].add(PrefLabels[3], 	Main.makegbc(2, 3, GridBagConstraints.HORIZONTAL,1));
	    PrefPanels[1].add(tfEU, 			Main.makegbc(3, 3, GridBagConstraints.HORIZONTAL,1));

	    PrefPanels[2].setLayout(layout);
	    PrefPanels[2].add(PrefLabels[4], 	Main.makegbc(2, 2, GridBagConstraints.HORIZONTAL,1));	
	    PrefPanels[2].add(tfTrainLfd, 		Main.makegbc(3, 2, GridBagConstraints.HORIZONTAL,1)); 
	    PrefPanels[2].add(PrefLabels[5], 	Main.makegbc(2, 3, GridBagConstraints.HORIZONTAL,1));
	    PrefPanels[2].add(tfTrain, 			Main.makegbc(3, 3, GridBagConstraints.HORIZONTAL,1));
	     
	   
	    PrefPanels[4].setLayout(layout);
	    PrefPanels[4].add(PrefLabels[0], 	Main.makegbc(2, 2, GridBagConstraints.HORIZONTAL,1));	
	    PrefPanels[4].add(spinner3, 		Main.makegbc(3, 2, GridBagConstraints.HORIZONTAL,1)); 
	    PrefPanels[4].add(PrefLabels[1], 	Main.makegbc(2, 3, GridBagConstraints.HORIZONTAL,1));	
	    PrefPanels[4].add(spinner4, 		Main.makegbc(3, 3, GridBagConstraints.HORIZONTAL,1)); 
	    
	    PrefPanels[3].setLayout(new GridBagLayout());
	    PrefPanels[3].setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc 		= Main.makegbc(2, 2, GridBagConstraints.HORIZONTAL,1);
	    gbc.ipady 	= 5;
	    gbc.ipadx 	= 10;
	    PrefPanels[3].add(Reset,gbc);
	    gbc 		= Main.makegbc(2, 3, GridBagConstraints.HORIZONTAL,1);
	    gbc.ipady 	= 5;
	    gbc.ipadx 	= 10;
	    PrefPanels[3].add(OK,gbc);
	    gbc 		= Main.makegbc(2, 4, GridBagConstraints.HORIZONTAL,1);
	    gbc.ipady 	= 5;
	    gbc.ipadx 	= 10;
	    PrefPanels[3].add(Quit,gbc);

	    tp.addTab("Datum      ",null, PrefPanels[0], " Einstellen Zieldatum ");  
	    tp.addTab("Urlaub     ",null, PrefPanels[1], " Einstellungen zum Urlaub ");  
	    tp.addTab("Fortbildung",null, PrefPanels[2], " Einstellungen zu Fortbildungen ");
	    tp.addTab("Geburtstag",	null, PrefPanels[4], " Geburtstag ");
	    
	      tfRestEU.setToolTipText("Resturlaub im laufenden Jahr");
	   	      tfEU.setToolTipText("Jährlicher Urlaubsanspruch");
	   	tfTrainLfd.setToolTipText("Verbleibende Schulungstage im laufenden Jahr");
	       tfTrain.setToolTipText("Jährlicher Planwert Tage für Schulungen und Kongresse");
	            OK.setToolTipText("Mit Einstellungen neu rechnen");  
	         Reset.setToolTipText("Einstellungen letzter Übernahme wiederherstellen");
	          Quit.setToolTipText("Einstellungen ohne Übernahme schliessen");
	    
	    frame.add(tp, BorderLayout.CENTER);
	    frame.add(PrefPanels[3], BorderLayout.LINE_END);
	    frame.setMinimumSize(new Dimension(600, 170));
	    frame.setLocation(200,370);
	    frame.pack();	
	        
	    OK.addActionListener(new ActionListener() 
	    {	public void actionPerformed(ActionEvent e) 
	    	{	String 	str		= (String) spinner1.getValue(); 
	    		int 	Month	= getMonthIndex(str);
	    		int 	Year 	= (Integer)spinner2.getValue();
	    		String	 str2 =(String) spinner3.getValue(); 
	    		int 	BirthMonth	= getMonthIndex(str2);
	    		int 	BirthYear 	= (Integer)spinner4.getValue();
	    		
	    		setPreferences(Month, Year,tfRestEU.getText(), tfEU.getText() , tfTrainLfd.getText(), tfTrain.getText(),BirthMonth, BirthYear);
	    		data_63.init(Main.getEinstellungen());
	    		data_63.Calculate(Main.getEinstellungen());
	    		savePreferences(Preferences);
	    		Main.UpdateMain();
				
	    	}
	    });

	    Reset.addActionListener(new ActionListener() 
	    {	public void actionPerformed(ActionEvent e) 
	    	{	spinner1.setValue(monthStrings[Preferences[0]-1]);
	    		spinner2.setValue(Preferences[1]); 
	    		tfRestEU.setText(Integer.toString(Preferences[2])); 
	    		tfEU.setText(Integer.toString(Preferences[3])); 
	    		tfTrainLfd.setText(Integer.toString(Preferences[4])); 
	    		tfTrain.setText(Integer.toString(Preferences[5])); 
	    	}
	    });

	    Quit.addActionListener(new ActionListener() 
	    {	public void actionPerformed(ActionEvent e) 
	    	{	frame.setVisible(false);
	    	}
	    });
	    
	}	

}

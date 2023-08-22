package myCalendar4;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;


public class Main implements ActionListener {	
	
	static 
	{	System.setProperty( "apple.laf.useScreenMenuBar", "true");
		System.setProperty( "apple.awt.application.name", "Days 2 Go" );
        System.setProperty( "apple.awt.application.appearance", "system" );  //  "system":  current macOS appearance /  - "NSAppearanceNameAqua":  light  / "NSAppearanceNameDarkAqua": dark 
	}
	
/*
 * 	Konstanten	
 */
	
final static   	SimpleDateFormat 	 format1 		= new SimpleDateFormat("d MMM yyyy"); 
	final 	static  int 		 	 ANZ_PAN 	 	= 4;
	final 	static  int 		 	 ANZ_COLORS 	= 5;
	final 	static  int 		 	 CALC_VALUES	= 6;
	final	static  Color[]	 		 ColorSetBg  	= { new Color(237,237,237), new Color(22,22,27), Color.WHITE , Color.ORANGE    , Color.cyan};
	final 	static  Color[]	 		 ColorSetLbl 	= { Color.BLACK		      , Color.WHITE        , Color.GRAY  , Color.DARK_GRAY , Color.DARK_GRAY }; 
	final 	static  Color[]	 		 ColorSetVal 	= { new Color(137,137,137), Color.LIGHT_GRAY   , Color.ORANGE, Color.WHITE     , Color.WHITE};
			static 	int []	 		 Col			= new int[ANZ_PAN];
			static  int []			 panelSlot		= { 0, 1, 2, 3};
/*
 *  Variablen 	
 */
			
	  		static 	myDataPack 	     data_63 	 	= new myDataPack(2027,12,31);	
	  		static 	myPreferences	 Einstellungen 	= new myPreferences(data_63);
					JFrame 			 frame 	 	 	= new JFrame("Days to Go");		
					
			static 	Overview 		 myOverview;
			static  Detailview	     myDetailview;
			static  DigitalWatch 	 myWatch;
			static  Slider	 		 mySlider;	
	private static 	JPanel 		  	 OverviewPanel;
	private static 	JPanel 		 	 DetailPanel; 
	private static 	JPanel 			 SliderPanel;
	private static	JPanel			 WatchPanel; 
	
	static	JCheckBoxMenuItem[] 	 Opt_Item 		= new JCheckBoxMenuItem[ANZ_PAN];
	static  JRadioButtonMenuItem[][] m     			= new JRadioButtonMenuItem[ANZ_PAN][ANZ_COLORS];

/*
 * 	get / set Methoden
 */
	
	  		static  myDataPack 		 getDataPack()				{	return data_63; }
	  		static  myPreferences	 getEinstellungen()			{	return Einstellungen; }
	  		
	private static  Overview 		 getOverview()				{	return myOverview;	}
	private static  Detailview 		 getDetailview()			{	return myDetailview; }
	private static  DigitalWatch 	 getDigitalWatch()			{	return myWatch; }
	private static  Slider       	 getSlider()				{	return mySlider; }	
	
			static  JPanel 			 getOverviewPanel() 		{	return Main.OverviewPanel; }
	  		static  JPanel 			 getDetailPanel()			{	return Main.DetailPanel; }
	  		static  JPanel 			 getWatchPanel() 			{	return Main.WatchPanel; }
	  		static  JPanel 			 getSliderPanel()			{	return Main.SliderPanel; }
	  		
			static  void 			 setOverviewPanel(JPanel p) {	Main.OverviewPanel=p; }
	  		static  void 			 setDetailPanel(JPanel p )	{	Main.DetailPanel =p; }
	  		static  void 			 setSliderPanel(JPanel p)	{	Main.SliderPanel=p; }
	  		static  void 			 setWatchPanel(JPanel p) 	{	Main.WatchPanel=p; }
	  		 
	
	static GridBagConstraints makegbc(int x, int y, int fill, int width)
	{	
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx 		= x;
		gbc.gridy 		= y;
		gbc.fill 		= fill; 
		gbc.gridwidth 	= width;
		return gbc;
	}
	
	public static int endOfMonth(int Month, int Year)
	{	
		GregorianCalendar gcalendar = new GregorianCalendar();
		switch (Month)
		{	case  2: return (gcalendar.isLeapYear(Year)? 29:28);
			case  4:
			case  6:
			case  9:
			case 11: return 30;
			default: return 31;
		}
	}
		
	public static void UpdateMain()
	
	{	getDigitalWatch().updateDigitalWatch(Col[0]);  
		    getOverview().updatePanel(Col[1]);
		  getDetailview().updatePanel(Col[2]);	
			  getSlider().updatePanel(Col[3]);	  
	}
	
	
	public static void main(String[] args) 
	{
		new Main();
	}
	
	Main()
	{		
		myAbout 	About 	= new myAbout();
		ImageIcon 	icon 	= new ImageIcon("image.png");
		Cursor 		cursor 	= new Cursor(Cursor.CROSSHAIR_CURSOR);
		
		frame.getRootPane().putClientProperty( "apple.awt.fullscreenable", true );
		
		Desktop desktop = Desktop.getDesktop();
		
		if( desktop.isSupported( Desktop.Action.APP_ABOUT )) 		// show about dialog  
		{	desktop.setAboutHandler( e -> 
			{  	About.setVisible(true);} );					 
		}
		if( desktop.isSupported( Desktop.Action.APP_PREFERENCES ))  // show preferences dialog
		{	desktop.setPreferencesHandler( e -> {  			
		    {	Einstellungen.setVisible(true);}} );
		}
	
		Einstellungen.initWithPreferences();
		
		data_63.init(Einstellungen);		
		data_63.Calculate(Einstellungen);
	
		Random random= new Random();
		for(int i=0;i<ANZ_PAN;i++)
			Col[i] = random.nextInt(ANZ_COLORS);
			
		myWatch		 = new DigitalWatch(Col[0]);
		setWatchPanel(myWatch.getPanel());		
		
		myOverview 	 = new Overview(Col[1]);
		setOverviewPanel(myOverview.getPanel());
		
		myDetailview = new Detailview(Col[2]);
		setDetailPanel(myDetailview.getPanel());
		
		mySlider 	= new Slider(Col[3], Einstellungen);
		setSliderPanel(mySlider.getPanel());
		
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
	
		frame.add(getWatchPanel(),   0);
		frame.add(getOverviewPanel(),1);
		frame.add(getDetailPanel(),  2);
		frame.add(getSliderPanel(),  3);	
		
		frame.setJMenuBar(createMenus(Col));
		frame.setMinimumSize(new Dimension(1600, 400));
		frame.setSize(new Dimension(1601, 400));
		frame.setIconImage(icon.getImage());
        frame.setCursor(cursor);   
		frame.setVisible(true);	
		
		frame.addMouseListener(new CustomMouseListener());
		
		for(int i=0; i<ANZ_PAN;i++)
			Opt_Item[i].addActionListener(this);
		
		for(int i=0; i<ANZ_PAN;i++)
			for(int j=0;j<ANZ_COLORS;j++)
					m[i][j].addActionListener(this); 	
		
		frame.addWindowListener(new WindowAdapter() 
		{	@Override
            public void windowClosing(WindowEvent e) 
            { 	System.exit(0); }
		});
	}

	private static  JMenuBar createMenus( int[] Col)
	{
		String[]		 ColorSchemes 	= { "Dämmerung","Dunkel","Hell","Orange","Himmel"};
		String[] 	 	 Menutext_L1	= { "Uhrzeit & Datum","Übersicht","Detailwerte","Zielalter"};
		String[] 	 	 Menutext_L1_1	= { "Fenster Uhrzeit & Datum","Fenster Übersicht","Fenster Detail","Fenster Alter"};
		boolean[][]		 ColorStat	  	= new boolean [ANZ_PAN][ANZ_COLORS];
		JMenuBar 		 bar 	        = new JMenuBar();	
		JMenu[] 		 Menu 			= new JMenu [ANZ_PAN] ;
		ButtonGroup[] 	 bg 			= new ButtonGroup [ANZ_PAN];
		
		for(int i=0;i<ANZ_PAN;i++)
			ColorStat[i][Col[i]]=true;
		
		for(int i=0;i<ANZ_PAN;i++)	
		{	Menu[i]	= new JMenu(Menutext_L1[i]);
			Menu[i].add(Opt_Item[i] = new JCheckBoxMenuItem(Menutext_L1_1[i],true));
		}
		
		for(int i=0;i<ANZ_PAN;i++)	
		{	bg[i] = new ButtonGroup();
			Menu[i].add(new JSeparator()); 
			for(int j=0;j<ANZ_COLORS;j++)
			{	m[i][j] = new JRadioButtonMenuItem(ColorSchemes[j],ColorStat[i][j]);	
				bg[i].add(m[i][j]) ;
				Menu[i].add(m[i][j]);
			}
			bar.add(Menu[i]);
		}
		return bar;	
	}
	
	private void updateColorMenus()
	{	
		for(int i=0;i<ANZ_PAN;i++)	
		{	for(int j=0;j<ANZ_COLORS;j++)
				m[i][j].setSelected(Col[i]!= j?false:true);
		}
	}	
	
	private int nextColor(int i)
	{	return(Col[i] < ANZ_COLORS-1?(Col[i]+1):0) ;	}	
	
	private void shiftColorPanel(int noPanel)
	{
		switch(noPanel)
		{	case 1:	getOverview().updatePanel(Col[1]=nextColor(1)); 	break;
			case 2: getDetailview().updatePanel(Col[2]=nextColor(2)); 	break;
			case 3: getSlider().updatePanel(Col[3]=nextColor(3));		break;
			case 0: getDigitalWatch().updateDigitalWatch(Col[0]=nextColor(0)); 	break;
		}
		updateColorMenus();
	}
	
	private void changeColorPanel(int noPanel)
	{	
		switch(noPanel)
		{	case 1:	getOverview().updatePanel(Col[1]); 				break;
			case 2: getDetailview().updatePanel(Col[2]);			break;
			case 3: getSlider().updatePanel(Col[3]);				break;
			case 0: getDigitalWatch().updateDigitalWatch(Col[0]);	break;
		}
		updateColorMenus();
	}
	
	private int panelSize()
	{ 	return (frame.getWidth()/visiblePanels()); }
	
	class CustomMouseListener implements MouseListener 
	{
		private boolean semaphor = false;
		private	int 	firstPanel;
		private	int 	secondPanel;
		
	    public void mouseClicked(MouseEvent e) 
	    {	
	    	if(e.getClickCount() ==2)
	    	{	shiftColorPanel(panelSlot[(int) (e.getX()/ panelSize() ) ]); }
	    }
	      
	    public void mousePressed(MouseEvent e) 
	    {	semaphor=true;
	      	firstPanel=panelSlot[(int) (e.getX()/panelSize() )];		
	    }
	      
	    public void mouseReleased(MouseEvent e) 
	    {	if(semaphor)
	      	{	secondPanel = panelSlot[(int) (e.getX()/panelSize() )];
	      		if(firstPanel!=secondPanel)	
	      		{	semaphor= false;
	      			Col[secondPanel] = Col[firstPanel];
	      			changeColorPanel(secondPanel);
	      		}
	      	}
	    }
	      
	    public void mouseEntered(MouseEvent e) 
	    {  }
	      
	    public void mouseExited(MouseEvent e) 
	    { }
	 }
 	
	 public void addPanel(int i)
	 {  
		switch(i)
		{	case 0: frame.add(getWatchPanel());		break; 	
			case 1: frame.add(getOverviewPanel()); 	break;
		 	case 2: frame.add(getDetailPanel());	break;
		 	case 3: frame.add(getSliderPanel());	break;	
		 }
	 }
	 
	 private int visiblePanels()
	 {
		 int visiblePanels=0;
	 	 for (int i= 0;i< ANZ_PAN;i++) 
	 	 {	if(Opt_Item[i].isSelected())
	 	 	{	visiblePanels++;}
	 	 }
	 	 return visiblePanels;
	 }
	 
	 
	 private int oldpanelSize(int x)
	{ 	int lastPanelNo = visiblePanels();
		return (x==1?frame.getWidth()/(lastPanelNo-1):frame.getWidth()/(lastPanelNo+1 )); }
	 
	 
	 public void rebuildPanels(int up)
	 {	 
		int x=0;
		int visiblePanels=visiblePanels();
		int dim = visiblePanels*oldpanelSize(up);
		
		frame.setVisible(false);
	 	frame.getContentPane().removeAll();
		frame.setMinimumSize(new Dimension(visiblePanels*400 , 400));
		frame.setSize(new Dimension(dim+1, 400));
		
	 	for (int i= 0;i< ANZ_PAN;i++) 
	 	{	if(Opt_Item[i].isSelected())
			{	addPanel(i);
				panelSlot[x]=i;
				x++;
			}
	 	}
	 	for(int i=x;i<ANZ_PAN;i++)
	 		panelSlot[i]= -1; 
	 	
	 	frame.validate();
		frame.setVisible(true);
	 }
	 
	 public void actionPerformed (ActionEvent ae) 
	 {	
		for (int i= 0;i< ANZ_PAN;i++) 
		{	
			if(ae.getSource() == Opt_Item[i])
			{	if(Opt_Item[i].isSelected())
				{	rebuildPanels(1);
					for(int j=0;j<ANZ_COLORS;j++)
					{	m[i][j].setEnabled(true);}
				}
				else
				{	rebuildPanels(0);
					for(int j=0;j<ANZ_COLORS;j++)
					{	m[i][j].setEnabled(false);}
				}
			}
		}
		for(int i=0;i<ANZ_COLORS;i++)
		{	if(ae.getSource() == m[1][i])	  getOverview().updatePanel(Col[1]=i );
			if(ae.getSource() == m[2][i])	getDetailview().updatePanel(Col[2]=i);
			if(ae.getSource() == m[3][i])	    getSlider().updatePanel(Col[3]=i);
			if(ae.getSource() == m[0][i]) getDigitalWatch().updateDigitalWatch(Col[0]=i);
		}
	 }	
}

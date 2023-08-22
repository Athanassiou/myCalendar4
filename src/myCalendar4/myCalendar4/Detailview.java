package myCalendar4;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class Detailview {
	
	static  final 		 int[]  		pos 	= { 1,1,1,2, 3,1,3,2, 1,4,1,5, 3,4,3,5 , 1,7,1,8, 3,7,3,8 };
						 JPanel 		panel 	= new JPanel();
						 DetailLabels 	d		= new DetailLabels();
	
	public  JPanel  	 getPanel()		{	return this.panel; }
	public  DetailLabels getDetails()	{ 	return d;}
	
	Detailview (int COLORSET)
	{
		int 	idx;
	
		myDataPack data_63 = Main.getDataPack();
	
		this.panel.setLayout(new GridBagLayout());
		this.panel.setBackground(Main.ColorSetBg[COLORSET]);
		this.panel.setForeground(Main.ColorSetLbl[COLORSET]);		
		for(int i=0;i<3;i++)
			panel.add(new JLabel("        "), Main.makegbc(i*2, 0, GridBagConstraints.CENTER, 1));
		this.panel.add(new JSeparator(SwingConstants.HORIZONTAL), Main.makegbc(1, 3, GridBagConstraints.HORIZONTAL, 3));
		this.panel.add(new JSeparator(SwingConstants.HORIZONTAL), Main.makegbc(1, 6, GridBagConstraints.HORIZONTAL, 3));
	
		for(int i=0;i<6;i++)	
		{	idx=i*4;
			d.getName(i).setText(data_63.AttributNames[i]);
			panel.add(d.getName(i), Main.makegbc(pos[idx], pos[idx+1], GridBagConstraints.CENTER, 1));
			d.getName(i).setForeground(Main.ColorSetLbl[COLORSET]);
			d.getValue(i).setText(Long.toString(data_63.dataSet[i].Wert));
			d.getValue(i).setFont(new Font("Arial", Font.BOLD, 42));
			d.getValue(i).setForeground(Main.ColorSetVal[COLORSET]);
			panel.add(d.getValue(i), Main.makegbc(pos[idx+2], pos[idx+3], GridBagConstraints.CENTER, 1));
		}
		this.panel.setBorder(BorderFactory.createTitledBorder(""));
		this.panel.setPreferredSize(new Dimension(400, 400));
		Main.setDetailPanel(this.panel);
	
	}

	public  void updatePanel(int COLORSET)
	{	myDataPack data_63 = Main.getDataPack();
		this.panel.setVisible(false);
	
		this.panel.setBackground(Main.ColorSetBg[COLORSET]);
		for (int i = 0; i < Main.CALC_VALUES; i++) 
		{	d.getName(i).setText(Main.data_63.AttributNames[i]);
			d.getName(i).setForeground(Main.ColorSetLbl[COLORSET]);
			d.getValue(i).setForeground(Main.ColorSetVal[COLORSET]);
			d.getValue(i).setText( Long.toString(data_63.dataSet[i].Wert));
		}
		this.panel.setVisible(Main.Opt_Item[2].getState());
	}
}

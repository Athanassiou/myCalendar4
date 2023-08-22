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

public class Overview  {
	
	JLabel 	topLabel, yearLabel, monthLabel, dayLabel, workLabel ;
	JLabel	tLabel, yLabel, mLabel, dLabel, wLabel;
	JPanel 	panel;
	
	public  JPanel getPanel()
	{	return this.panel; }
	
	Overview(int COLORSET )
	{
		panel = new JPanel();
		myDataPack data_63 = Main.getDataPack();
		panel.setLayout(new GridBagLayout());
		panel.setBackground(Main.ColorSetBg[COLORSET]);
		panel.setForeground(Main.ColorSetLbl[COLORSET]);
		
		topLabel =new JLabel("Zieldatum");
		topLabel.setForeground(Main.ColorSetLbl[COLORSET]);
		panel.add(topLabel, Main.makegbc(1, 1, GridBagConstraints.CENTER, 1));
        tLabel = new JLabel(Main.format1.format(data_63.date));
        tLabel.setFont(new Font("Arial", Font.BOLD, 42));
        tLabel.setForeground(Main.ColorSetVal[COLORSET]);
		panel.add(tLabel, Main.makegbc(1, 2, GridBagConstraints.CENTER, 1));
		
		panel.add(new JSeparator(SwingConstants.HORIZONTAL), Main.makegbc(0, 3, GridBagConstraints.HORIZONTAL, 3));
		
		yearLabel= data_63.getway2go(2)==1 ? new JLabel("Jahr") : new JLabel("Jahre");
		yearLabel.setForeground(Main.ColorSetLbl[COLORSET]);
		panel.add(yearLabel, Main.makegbc(0, 4, GridBagConstraints.CENTER, 1));
		yLabel = new JLabel(Integer.toString(data_63.getway2go(2)));
        yLabel.setFont(new Font("Arial", Font.BOLD, 42));
        yLabel.setForeground(Main.ColorSetVal[COLORSET]);
		panel.add(yLabel, Main.makegbc(0, 5, GridBagConstraints.CENTER, 1));
			
		monthLabel= data_63.getway2go(1)==1 ?new JLabel("Monat") : new JLabel("Monate");
		monthLabel.setForeground(Main.ColorSetLbl[COLORSET]);
		panel.add(monthLabel, Main.makegbc(1, 4, GridBagConstraints.CENTER, 1));
		mLabel = new JLabel(Integer.toString(data_63.getway2go(1)));
        mLabel.setFont(new Font("Arial", Font.BOLD, 42));
        mLabel.setForeground(Main.ColorSetVal[COLORSET]);
		panel.add(mLabel, Main.makegbc(1, 5, GridBagConstraints.CENTER, 1));
		
		dayLabel= data_63.getway2go(0)==1 ?new JLabel("Tag") : new JLabel("Tage");
		dayLabel.setForeground(Main.ColorSetLbl[COLORSET]);
		panel.add(dayLabel, Main.makegbc(2, 4, GridBagConstraints.CENTER, 1));
		dLabel = new JLabel(Integer.toString(data_63.getway2go(0)));
        dLabel.setFont(new Font("Arial", Font.BOLD, 42));
        dLabel.setForeground(Main.ColorSetVal[COLORSET]);
		panel.add(dLabel, Main.makegbc(2, 5, GridBagConstraints.CENTER, 1));
		
		panel.add(new JSeparator(SwingConstants.HORIZONTAL), Main.makegbc(0, 6, GridBagConstraints.HORIZONTAL, 3));
		
		workLabel = new JLabel("Arbeitstage");
		workLabel.setForeground(Main.ColorSetLbl[COLORSET]);
		panel.add(workLabel, Main.makegbc(1, 7, GridBagConstraints.CENTER, 1));
		wLabel = new JLabel(Long.toString(data_63.dataSet[Main.CALC_VALUES].Wert));
        wLabel.setFont(new Font("Arial", Font.BOLD, 42));
        wLabel.setForeground(Main.ColorSetVal[COLORSET]);
		panel.add(wLabel, Main.makegbc(1, 8, GridBagConstraints.CENTER, 1));
		
		panel.setBorder(BorderFactory.createTitledBorder(""));
		panel.setPreferredSize(new Dimension(400, 400));
		panel.setVisible(true);
	
	}
	
	public  void updatePanel(int COLORSET)
	{	
		myDataPack data_63 = Main.getDataPack();
		
		this.panel.setVisible(false);
		this.panel.setBackground(Main.ColorSetBg[COLORSET]);
			
		yearLabel.setText( (data_63.getway2go(2)==1 )? "Jahr":  "Jahre");
		monthLabel.setText( (data_63.getway2go(1)==1)? "Monat": "Monate");
		dayLabel.setText((data_63.getway2go(0)==1 )  ? "Tag":   "Tage");	
		
		topLabel.setForeground(Main.ColorSetLbl[COLORSET]);
		yearLabel.setForeground(Main.ColorSetLbl[COLORSET]);
		monthLabel.setForeground(Main.ColorSetLbl[COLORSET]);
		dayLabel.setForeground(Main.ColorSetLbl[COLORSET]);
		workLabel.setForeground(Main.ColorSetLbl[COLORSET]);
		
		tLabel.setText(Main.format1.format(data_63.date));
		yLabel.setText(Integer.toString(data_63.getway2go(2)));
		mLabel.setText(Integer.toString(data_63.getway2go(1)));
		dLabel.setText(Integer.toString(data_63.getway2go(0)));	
		wLabel.setText(Long.toString(data_63.dataSet[Main.CALC_VALUES].Wert));
		
		tLabel.setForeground(Main.ColorSetVal[COLORSET]);
		yLabel.setForeground(Main.ColorSetVal[COLORSET]);
		mLabel.setForeground(Main.ColorSetVal[COLORSET]);
		dLabel.setForeground(Main.ColorSetVal[COLORSET]);
		wLabel.setForeground(Main.ColorSetVal[COLORSET]);
	
		this.panel.setVisible(Main.Opt_Item[1].getState());
	}
	

}

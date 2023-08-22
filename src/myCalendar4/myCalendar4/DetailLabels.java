package myCalendar4;

import javax.swing.JLabel;

public class DetailLabels
{
	JLabel[] value = new JLabel[8];
	JLabel[] name	= new JLabel[8];
	
	DetailLabels()
	{
		for(int i=0;i<8;i++)
		{ 	this.name[i] = new JLabel(" ");
			this.value[i]= new JLabel(" ");
		}
	}
	public void   setValue(int i,JLabel s) 	{	this.value[i] = s;}
	public void   setName(int i,JLabel w) 	{	this.name[i]  = w;}
	public JLabel getValue(int i)			{ 	return this.value[i]; }
	public JLabel getName(int i) 			{ 	return this.name[i]; }
	
}

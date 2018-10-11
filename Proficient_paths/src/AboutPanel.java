import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


public class AboutPanel extends JPanel 
{
	private JTextArea about;
	private JPanel console, tools;
	private JScrollPane scroll;
	
	public AboutPanel()
	{
		
		   tools=new JPanel();   //adding the labels and text fields to the panel
		   tools.setLayout(new GridLayout(5, 2));

		      console=new JPanel(); 
		      console.setLayout(new BoxLayout(console, BoxLayout.Y_AXIS));

		      console.add(tools);
		   

		      about =new JTextArea(); //area where user can see activites added
		      about.setText("This program will analyze a network diagram and determine all paths in the network");
		      scroll=new JScrollPane(about);

		      setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		      add(console);
		      add(scroll);
		      
		      about.setEditable(false);
	}
}

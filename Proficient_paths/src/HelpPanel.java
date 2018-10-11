import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


public class HelpPanel extends JPanel 
{
	private JTextArea help;
	private JPanel console, tools;
	private JScrollPane scroll;
	
	public HelpPanel()
	{
		
		   tools=new JPanel();   //adding the labels and text fields to the panel
		   tools.setLayout(new GridLayout(5, 2));

		      console=new JPanel(); 
		      console.setLayout(new BoxLayout(console, BoxLayout.Y_AXIS));

		      console.add(tools);
		   

		      help =new JTextArea(); //area where user can see activites added
		      help.setText("Enter all input before adding the activity." + "\n" + "Activity name can be multiple characters." + "\n" +  
		    		  		"Duration must be an integer." + "\n" + 
		    		        "Starting activity/activities will not have predecessors." + "\n" + 
		    		  		"Enter predecessors as a list, separated by commas." + "\n" + 
		    		        "Once all input is added, click 'Add' to add it to the list." + "\n" 
		    		  		+ "Click 'restart' to clear the list.");
		      scroll=new JScrollPane(help);

		      setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		      add(console);
		      add(scroll);
		      
		      help.setEditable(false);
	}
}

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class StorePanel extends JPanel
 {
   private ArrayList pathList;
   private JButton add, restart, process;
   private JPanel tools, console;
   private JLabel msg, activity, duration, pred;
   private JTextField activityF, durationF, predF;
   private JScrollPane scroll;
   private JTextArea info;
   private JFrame processes;
   
   boolean reset, duplicate = false;

   public StorePanel(ArrayList pathList) // constructor 
    {
      this.pathList = pathList;
      
	  msg=new JLabel("");  //setting the message text that the user will see if something is wrong
      msg.setForeground(Color.red);
      msg.setVisible(false);

      activity =new JLabel("Activity Name:");   //creating the labels the user will see
      duration =new JLabel("Duration:");
      pred = new JLabel("Predecessors:");

      activityF = new JTextField();  //the text fields the user can type into
      durationF = new JTextField();
      predF= new JTextField();


      add=new JButton("Add");   //the button the user will push to add the activity to the list
      add.addActionListener(new ButtonListener());
      
      process = new JButton("Process");
      process.addActionListener(new ButtonListener());
      
      restart = new JButton("Restart");
      restart.addActionListener(new ButtonListener());
   
      tools=new JPanel();   //adding the labels and text fields to the panel
      tools.setLayout(new GridLayout(5, 2));
      tools.add(activity);
      tools.add(activityF);
      tools.add(duration);
      tools.add(durationF);
      tools.add(pred);
      tools.add(predF);

      console=new JPanel(); 
      console.setLayout(new BoxLayout(console, BoxLayout.Y_AXIS));

      console.add(msg);
      console.add(tools);
      console.add(add);
      console.add(restart);
      console.add(process);

      info=new JTextArea(); //area where user can see activities added
      info.setText("No Activities");
      scroll=new JScrollPane(info);

      setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
      add(console);
      add(scroll);

    }



  private class ButtonListener implements ActionListener    // used to check if user entered information correctly
   {
    public void actionPerformed(ActionEvent event)
     {
         // if there is no error, add a activity to activity list
         // otherwise, show an error message
         String act, dur, pre;
         Activity activ=new Activity();
         act = activityF.getText();
		 dur = durationF.getText();
		 String pre_text = predF.getText();
		 
		 if (event.getSource() == restart) //IF THE USER HITS restart
			{

				pathList.clear(); //clear list
				reset = true;
				info.setText("");
			}
		 
		 // Checks if input activity has the same name as existing activity, set bool
		 for(int i = 0; i < pathList.size(); i++)
		 {
		 	 if(act.equals(((Activity) pathList.get(i)).getActivity()))
		 		 {
					 duplicate = true;
				 }
	  	 }

		 if(act.equals("") || dur.equals("") || pred.equals(""))
		 {
			 msg.setText("Please enter all fields");
			 msg.setVisible(true);
		 }
		 //if duplicate activity names existed, error
		 else if(duplicate)
		 {
			 msg.setText("An activity by this name already exists.");
			 msg.setVisible(true);
			 duplicate = false;
		 }
		 else
		 {
			 try                                             // creates activity object and prints details in Text Area on right side
			 {
				 activ.setActivity(act);
				 activ.setDuration(Double.parseDouble(dur));
				 activ.setPred(pre_text);
				 if((info.getText()).equalsIgnoreCase("No Activities"))
				 {
					 info.setText("");
				 }
			info.append(activ.toString());
			pathList.add(activ);
			msg.setText("Activity added");
			msg.setVisible(true);
			activityF.setText("");
			durationF.setText("");
			predF.setText("");
     	}
     catch(NumberFormatException ex)
     {
		 msg.setText("Enter a number for duration");
		 msg.setVisible(true);
	 }
  }
		 
		 if (event.getSource() == process) //IF THE USER HITS process
			{
			 processes = new JFrame("Processed Paths");
			 processes.setVisible(true);
			 processes.setSize(300, 300);
	
			} 
}
}
}

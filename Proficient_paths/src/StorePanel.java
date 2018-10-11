import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class StorePanel extends JPanel
 {
   private ArrayList pathList;
   private PathPanel pathPanel;
   private JButton add, restart;
   private JPanel tools, console;
   private JLabel msg, activity, duration, pred;
   private JTextField activityF, durationF, predF;
   private JScrollPane scroll;
   private JTextArea info;
   
   boolean reset;

   public StorePanel(ArrayList pathList, PathPanel pPanel) // constructor 
    {
      this.pathList = pathList;
      this.pathPanel = pPanel;
      
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
		 pre = predF.getText();
		 
		 if (event.getSource() == restart) //IF THE USER HITS restart
			{

				pathList.clear(); //clear list
				reset = true;
				info.setText("");
			}

		 if(act.equals("") || dur.equals("") || pred.equals(""))
		 {
			 msg.setText("Please enter all fields");
			 msg.setVisible(true);
		 }
		 else
		 {
			 try                                             // creates activity object and prints details in Text Area on right side
			 {
				 activ.setActivity(act);
				 activ.setDuration(Double.parseDouble(dur));
				 activ.setPred(pre);
				 if((info.getText()).equalsIgnoreCase("No Activities"))
				 {
					 info.setText("");
				 }
			info.append(activ.toString());
			pathList.add(activ);
			pathPanel.addCheckBox(activ);
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
}
}
}
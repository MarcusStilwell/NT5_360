import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class PathPanel extends JPanel
 {
   private ArrayList pathList;
   private JPanel lPanel, rPanel;
   private JLabel msg;
   private JTextField money;
   private JScrollPane scroll;

   public PathPanel(ArrayList pathList)
    {
      this.pathList = pathList;

      // organize components for path panel
      msg=new JLabel("Current Total Purchase");
      money=new JTextField(10);
      money.setText("$0.00");
      money.setEditable(false);

      rPanel=new JPanel();      //creating layout and adding panels and text fields
      rPanel.setLayout(new BoxLayout(rPanel, BoxLayout.Y_AXIS));
      rPanel.add(msg);
      rPanel.add(money);

      lPanel=new JPanel();
      lPanel.setPreferredSize(new Dimension(400,300));
      scroll=new JScrollPane(lPanel);

      setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
      add(scroll);
      add(rPanel);

    }

	public void addCheckBox(Activity activ) //checkbox used to indicate whether or not the activity should be added to the purchase
	{
		JCheckBox temp=new JCheckBox(activ.toString());
		temp.addItemListener(new CheckBoxListener());
		lPanel.add(temp);
	}

 private class CheckBoxListener implements ItemListener //listener to add the computer to the purchase amount when box is checked
  {
       public void itemStateChanged(ItemEvent event)
        {
            // compute the total purchase amount when a check box is
            // checked or unchecked.

            double amount=0.0;

            for(int i=0; i<pathList.size(); i++)
            {
				if(((JCheckBox)lPanel.getComponent(i)).isSelected())
				{
					amount+=((Activity)pathList.get(i)).getDuration();
				}
			}

			money.setText("$"+amount);
        }
  }
}
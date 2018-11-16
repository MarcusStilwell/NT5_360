import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.FileWriter;

public class FilePanel extends JPanel {
	
	private JPanel window;
	
	private JTextField titleField;
	private JTextField dateField;
	private JTextField timeField;
	
	private JLabel titleLabel;
	private JLabel dateLabel;
	private JLabel timeLabel;
	
	private JButton create;
	
	FileWriter fileWriter;
	
	public FilePanel()
	{
		
		titleLabel = new JLabel("Title");
		dateLabel = new JLabel("Date");
		timeLabel = new JLabel("Time");	
		
		titleField = new JTextField();
		dateField = new JTextField();
		timeField = new JTextField();
		
		create = new JButton("Create Report");
		
		window = new JPanel();
		window.setLayout(new GridLayout(5, 2));
		
		window.add(titleLabel);
		window.add(titleField);
		window.add(dateLabel);
		window.add(dateField);
		window.add(timeLabel);
		window.add(timeField);
		window.add(create);
		
		create.addActionListener(new ButtonListener());
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
	    add(window);
		
		
	}
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) 
		{
			if (event.getActionCommand()== create.getActionCommand())
			{
				try
				{
					fileWriter = new FileWriter("‎⁨Report.txt");
					fileWriter.write(titleLabel.getText() + " : " + titleField.getText() + "\n");
					fileWriter.write(dateLabel.getText() + " : " + dateField.getText() + "\n");
					fileWriter.write(timeLabel.getText() + " : " + timeField.getText() + "\n");
					fileWriter.close();
					JOptionPane.showMessageDialog(null, "Report Written Successfully!");
					
					
				}
				catch(Exception eve)
				{
					JOptionPane.showMessageDialog(null, eve+"");
				}
				
				
			}
		}

    }
	
}

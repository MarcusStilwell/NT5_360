import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.*;

public class Southern_Panel extends JPanel{ 

	JPanel south_p = new JPanel();
	JButton about_button = new JButton("ABOUT");
	JButton help_button = new JButton("HELP");
	JButton quit_button = new JButton("QUIT");
	JButton restart_button = new JButton("RESTART");
	JButton process_button = new JButton("PROCESS");
	
	public Southern_Panel() {
		super();
		setVisible(true);
		this.add(south_p);
		south_p.setLayout(new FlowLayout());
		south_p.add(about_button);
		about_button.addActionListener(new About_Clicked());
		south_p.add(help_button);
		//help_button.addActionListener(new Help_Clicked());
		south_p.add(quit_button);
		//quit_button.addActionListener(new Quit_Clicked());
		south_p.add(restart_button);
		//restart_button.addActionListener(new Restart_Clicked());
		south_p.add(process_button);
		//process_button.addActionListener(new Process_Clicked());
		}
	static class About_Clicked implements ActionListener {
		
		public void actionPerformed (ActionEvent e) {
			JFrame about_page = new JFrame("About Page");
			about_page.setVisible(true);
			about_page.setSize(200,200);
			JLabel about_info = new JLabel("THIS IS SOME STUFF ABOUT US");
			JPanel about_panel = new JPanel();
			about_page.add(about_panel);
			about_panel.add(about_info); 
			
		}
	}
		
}

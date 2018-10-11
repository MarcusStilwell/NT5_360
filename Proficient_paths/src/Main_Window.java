import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;

public class Main_Window extends JFrame {
	JPanel main_layout = new JPanel();
	JButton button = new JButton("hi");

	public static void main(String[] args) {
		new Main_Window();
		 
	}
	public Main_Window() {
		super("Profecient Paths");
		setVisible(true);
		setSize(600, 500);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(main_layout);
		main_layout.setLayout(new BorderLayout());
		main_layout.add(new Southern_Panel(), BorderLayout.SOUTH);
		//main_layout.add(new Central_Panel(), BorderLayout.CENTER);
		//main_layout.add(new Northern_Panel(), BorderLayout.NORTH); 
		
	}
}

import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.FileWriter;
import javax.swing.*;
import java.util.*;
import java.util.Arrays;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class StorePanel extends JPanel {
	private ArrayList pathList;
	private JButton add, restart, process, close, edit, createFile, critPath;
	private JPanel tools, console, buttonsR, rightSide, buttonsL, tempL, tempR;
	private JLabel msg, activity, duration, pred, title;
	private JTextField activityF, durationF, predF, titleF;
	private JScrollPane scroll;
	private JTextArea info;
	private JFrame processes;
	public static String path_string;
	String[] activities;
	double[] durations;
	//String[] alphaAct;


	
	FileWriter fileWriter;
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();
	

	boolean reset, duplicate = false;
	
	public void updatePrint(ArrayList<Activity> arr) {
        this.info.setText("");
        for(int i = 0; i < arr.size(); i++) {
            String temp_str = arr.get(i).toString() + "\n";
            this.info.append(temp_str);
        }
    }

	public StorePanel(ArrayList pathList) // constructor
	{
		this.pathList = pathList;

		msg = new JLabel(""); // setting the message text that the user will see if something is wrong
		msg.setForeground(Color.red);
		msg.setVisible(false);

		activity = new JLabel("Activity Name:"); // creating the labels the user will see
		duration = new JLabel("Duration:");
		pred = new JLabel("Predecessors:");
		title = new JLabel("Title of Report");
		

		activityF = new JTextField(); // the text fields the user can type into
		durationF = new JTextField();
		predF = new JTextField();
		titleF = new JTextField();

		//*******************************
		//Creating buttons
		//*******************************
		add = new JButton("Add"); 
		add.addActionListener(new ButtonListener());

		process = new JButton("Process");
		process.addActionListener(new ButtonListener());

		restart = new JButton("Restart");
		restart.addActionListener(new ButtonListener());

		close = new JButton("Close");
		close.addActionListener(new ButtonListener());
		
		edit = new JButton("Edit");
		edit.addActionListener(new ButtonListener());
		
		createFile = new JButton("Create Report");
		createFile.addActionListener(new ButtonListener());
		
		critPath = new JButton("Critical Path");
		critPath.addActionListener(new ButtonListener());
		
		//*******************************
		//Panel where the labels and text fields to enter activities appears
		//*******************************
		tools = new JPanel(); 
		tools.setLayout(new GridLayout(5, 2));
		tools.add(activity);
		tools.add(activityF);
		tools.add(duration);
		tools.add(durationF);
		tools.add(pred);
		tools.add(predF);
		tools.add(title);
		tools.add(titleF);

		//*******************************
		//Panel where the buttons on the left side are added (This took a lot of panels and tweaking so it wasn't ugly)
		//*******************************
		tempL = new JPanel();
		tempL.setLayout(new BoxLayout(tempL, BoxLayout.Y_AXIS));
		tempL.add(add);
		tempL.add(edit);
		
		tempR = new JPanel();
		tempR.setLayout(new BoxLayout(tempR, BoxLayout.Y_AXIS));
		tempR.add(critPath);
		tempR.add(createFile);
		
		buttonsL = new JPanel();
		buttonsL.setLayout(new GridLayout(1,2));
		buttonsL.add(tempL);
		buttonsL.add(tempR);
		
		
		
		//*******************************
		//Panel combines error message, tools, and buttonL (Left side buttons)
		//*******************************
		console = new JPanel();
		console.setLayout(new BoxLayout(console, BoxLayout.Y_AXIS));
		console.add(msg);
		console.add(tools);
		console.add(buttonsL);


		//*******************************
		//Text area and scroll for added activities
		//*******************************
		info = new JTextArea(); // area where user can see activities added
		info.setText("No Activities");
		scroll = new JScrollPane(info);
		
		//*******************************
		//Panel where buttons on the right side are added
		//*******************************
		buttonsR = new JPanel();
		buttonsR.setLayout(new BoxLayout(buttonsR, BoxLayout.X_AXIS));
		buttonsR.add(process);
		buttonsR.add(restart);
		buttonsR.add(close);

		//*******************************
		//Panel combines text field and buttons
		//*******************************
		rightSide = new JPanel();
		rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
		rightSide.add(scroll);
		rightSide.add(buttonsR);
		
		//*******************************
		//Adding both panels to main panel
		//*******************************
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(console);
		add(rightSide);

	}

	private class ButtonListener implements ActionListener // used to check if user entered information correctly
	{
		public void actionPerformed(ActionEvent event) {
			// if there is no error, add a activity to activity list
			// otherwise, show an error message
			String act, dur, pre;
			Activity activ = new Activity();
			act = activityF.getText();
			dur = durationF.getText();
			String pre_text = predF.getText();
			Boolean editDuplicate = false;		

			//******************************
			//Create Report Button
			//******************************
			if(event.getSource() == createFile){
				
				durations = new double[pathList.size()];
				
				Arrays.sort(activities);
				ArrayList<Activity> temp_activs = pathList;
				Collections.sort(temp_activs, new Comparator<Activity>() {

					public int compare(Activity o1, Activity o2) {
						return o1.getActivity().compareTo(o2.getActivity());
					}
				});
				
				for (int i = 0; i < pathList.size(); i ++){
					Activity tempDur = (Activity) pathList.get(i);
					durations[i] = tempDur.getDuration();
					
				}
				
				
				try
				{
					fileWriter = new FileWriter(titleF.getText() + ".txt");
					fileWriter.write(title.getText() + " : " + titleF.getText() + "\n");
					fileWriter.write("Date and time file was created: " + dtf.format(now) + "\n");
					fileWriter.write("All paths and their duration: " + "\n");
					fileWriter.write(path_string);
					fileWriter.write("Activities: \n");
					for(int i = 0; i < temp_activs.size(); i ++) {
						fileWriter.write("Activity:" + temp_activs.get(i).getActivity() + " duration: " + temp_activs.get(i).getDuration() + "\n");
					}
					fileWriter.close();
					JOptionPane.showMessageDialog(null, "Report Written Successfully!");
					
					
				}
				catch(Exception eve)
				{
					JOptionPane.showMessageDialog(null, eve+"");
				}
				
			}
			
			
			//******************************
			//Edit Button
			//******************************
			
			if(event.getSource() == edit)
			{
				//checks to see if activity exists
				for (int i = 0; i < pathList.size(); i++) {
					if (act.equals(((Activity) pathList.get(i)).getActivity())) {
						editDuplicate = true;
					}
				}
				//if it exists, the duration changes
				if(editDuplicate == true)
				{
					for(int i=0;i<pathList.size();i++)
					{
						Activity temp = (Activity)pathList.get(i);
						if(temp.getActivity().equals(act) == true)
						{
							((Activity) pathList.get(i)).setDuration(Double.parseDouble(dur));
							updatePrint(pathList);
							
						}
					}
				}
					else 
				{
					msg.setText("That Activity does not exist.");
				}
			}
			//******************************
			//Close Button
			//******************************
			if (event.getSource() == close) {
				System.exit(0);
			}

			//******************************
			//Restart Button
			//******************************
			if (event.getSource() == restart) // IF THE USER HITS restart
			{

				pathList.clear(); // clear list
				reset = true;
				info.setText("");
			}

			// Checks if input activity has the same name as existing activity, set bool
			for (int i = 0; i < pathList.size(); i++) {
				if (act.equals(((Activity) pathList.get(i)).getActivity())) {
					duplicate = true;
				}
			}

			// checks if any fields are not filled out, prints error if true
			if (act.equals("") || dur.equals("") || pred.equals("")) {
				msg.setText("Please enter all fields");
				msg.setVisible(true);
			}

			// if duplicate activity names existed, error
			else if (duplicate) {
				if(event.getSource() != edit)
				{
					msg.setText("An activity by this name already exists.");
					msg.setVisible(true);
					duplicate = false;
				} else {
					msg.setText("");
					msg.setVisible(true);
					duplicate = false;
				}
			}

			else {
				try // creates activity object and prints details in Text Area on right side
				{
					activ.setActivity(act);
					activ.setDuration(Double.parseDouble(dur));
					activ.setPred(pre_text);
					if ((info.getText()).equalsIgnoreCase("No Activities")) {
						info.setText("");
					}
					info.append(activ.toString());
					pathList.add(activ);
					msg.setText("Activity added");
					msg.setVisible(true);
					activityF.setText("");
					durationF.setText("");
					predF.setText("");
				} catch (NumberFormatException ex) {
					msg.setText("Enter a number for duration");
					msg.setVisible(true);
				}
			}

			//******************************
			//Process Button
			//******************************
			if (event.getSource() == process) // IF THE USER HITS process
			{
				// creates new frame that will hold the paths with duration
				processes = new JFrame("Processed Paths");
				processes.setVisible(true);
				processes.setSize(300, 300);

				activities = new String[pathList.size()];

				for (int i = 0; i < pathList.size(); i++) {
					Activity temp_activity = (Activity) pathList.get(i);
					activities[i] = temp_activity.getActivity();
				}

				ArrayList<PathOut> path_out_array = new ArrayList<PathOut>();
				Network temp = new Network(activities);
				boolean one_start = false;
				boolean more_than_one_start = false;
				boolean no_start = false;
				String start_node_string = "a";

				for (int i = 0; i < pathList.size(); i++) {
					Activity temp_activity = (Activity) pathList.get(i);
					String from = temp_activity.getActivity();

					if (temp_activity.getPred()[0].length() == 0) {
						if (one_start == true) {
							System.out.println("REEE");
							more_than_one_start = true;
						} else {
							System.out.println("HELLLO");
							one_start = true;
							start_node_string = temp_activity.getActivity();
						}
					}

					for (int k = 0; k < temp_activity.getPred().length; k++) {
						String to = temp_activity.getPred()[k];
						temp.set_edge(to, from);
					}
				}

				if (one_start = false) {
					no_start = true;
				}

				if (more_than_one_start == true || no_start == true) {
					if (more_than_one_start == true) {
						msg.setText("not all nodes connected");
						msg.setVisible(true);
					} else {
						msg.setText("cycle spotted");
						msg.setVisible(true);
					}
				} else {
					System.out.println("end is: " + temp.getEnd());
					ArrayList<String> ends = temp.getEnd();
					if (temp.cycle == true) {
						msg.setText("cycle spotted");
						msg.setVisible(true);
					} else {
						for (int z = 0; z < ends.size(); z++) {
							temp.get_paths(start_node_string, ends.get(z));
							if (temp.cycle == true) {
								msg.setText("cycle spotted");
								msg.setVisible(true);
							} else {
								ArrayList<String[]> result = temp.bigList;
								System.out.println("result size: " + result.size());
								for (int i = 0; i < result.size(); i++) {
									String temp_path = "";
									int temp_duration = 0;

									for (int k = 0; k < result.get(i).length - 1; k++) {
										for (int j = 0; j < pathList.size(); j++) {
											Activity temp_activity = (Activity) pathList.get(j);
											if (temp_activity.getActivity().equals(result.get(i)[k])) {
												temp_duration += temp_activity.getDuration();
												temp_path += temp_activity.getActivity() + "->";
											}
										}

									}

									temp_path = temp_path.substring(0, temp_path.length() - 2);
									PathOut temp_path_out = new PathOut(temp_path, temp_duration);
									path_out_array.add(temp_path_out);
									Collections.sort(path_out_array, new Comparator<PathOut>() {

										public int compare(PathOut o1, PathOut o2) {
											return o2.dur - o1.dur;
										}
									});

									System.out.println("Path: " + temp_path);
									System.out.println("Duration: " + temp_duration);
								}

								path_string = "";
								for (int i = 0; i < path_out_array.size(); i++) {
									path_string += "Path: " + path_out_array.get(i).path + "\t\t\t";
									path_string += "Duration: " + path_out_array.get(i).dur + "\n";
								}

								System.out.println(path_string);
								JTextArea path_out_area = new JTextArea();
								path_out_area.setText(path_string);
								JScrollPane scroll_path = new JScrollPane(path_out_area);
								processes.add(scroll_path);
							}
						}

					}
				}
			}
			
			//******************************
			//CritPath Button
			//******************************
			if (event.getSource() == critPath) // IF THE USER HITS process
			{
				// creates new frame that will hold the paths with duration
				processes = new JFrame("Processed Paths");
				processes.setVisible(true);
				processes.setSize(300, 300);

				activities = new String[pathList.size()];

				for (int i = 0; i < pathList.size(); i++) {
					Activity temp_activity = (Activity) pathList.get(i);
					activities[i] = temp_activity.getActivity();
				}

				ArrayList<PathOut> path_out_array = new ArrayList<PathOut>();
				Network temp = new Network(activities);
				boolean one_start = false;
				boolean more_than_one_start = false;
				boolean no_start = false;
				String start_node_string = "a";

				for (int i = 0; i < pathList.size(); i++) {
					Activity temp_activity = (Activity) pathList.get(i);
					String from = temp_activity.getActivity();

					if (temp_activity.getPred()[0].length() == 0) {
						if (one_start == true) {
							System.out.println("REEE");
							more_than_one_start = true;
						} else {
							System.out.println("HELLLO");
							one_start = true;
							start_node_string = temp_activity.getActivity();
						}
					}

					for (int k = 0; k < temp_activity.getPred().length; k++) {
						String to = temp_activity.getPred()[k];
						temp.set_edge(to, from);
					}
				}

				if (one_start = false) {
					no_start = true;
				}

				if (more_than_one_start == true || no_start == true) {
					if (more_than_one_start == true) {
						msg.setText("not all nodes connected");
						msg.setVisible(true);
					} else {
						msg.setText("cycle spotted");
						msg.setVisible(true);
					}
				} else {
					System.out.println("end is: " + temp.getEnd());
					ArrayList<String> ends = temp.getEnd();
					if (temp.cycle == true) {
						msg.setText("cycle spotted");
						msg.setVisible(true);
					} else {
						for (int z = 0; z < ends.size(); z++) {
							temp.get_paths(start_node_string, ends.get(z));
							if (temp.cycle == true) {
								msg.setText("cycle spotted");
								msg.setVisible(true);
							} else {
								ArrayList<String[]> result = temp.bigList;
								System.out.println("result size: " + result.size());
								for (int i = 0; i < result.size(); i++) {
									String temp_path = "";
									int temp_duration = 0;

									for (int k = 0; k < result.get(i).length - 1; k++) {
										for (int j = 0; j < pathList.size(); j++) {
											Activity temp_activity = (Activity) pathList.get(j);
											if (temp_activity.getActivity().equals(result.get(i)[k])) {
												temp_duration += temp_activity.getDuration();
												temp_path += temp_activity.getActivity() + "->";
											}
										}

									}

									temp_path = temp_path.substring(0, temp_path.length() - 2);
									PathOut temp_path_out = new PathOut(temp_path, temp_duration);
									path_out_array.add(temp_path_out);
									Collections.sort(path_out_array, new Comparator<PathOut>() {

										public int compare(PathOut o1, PathOut o2) {
											return o2.dur - o1.dur;
										}
									});

									System.out.println("Path: " + temp_path);
									System.out.println("Duration: " + temp_duration);
								}

								path_string = "";
								int max_dur = path_out_array.get(0).dur;
								for (int i = 0; i < path_out_array.size(); i++) {
									if(path_out_array.get(i).dur < max_dur) {
										break;
									}
									path_string += "Critical Path: " + path_out_array.get(i).path + "\t\t";
									path_string += "Duration: " + path_out_array.get(i).dur + "\n";
									
								}
								
							
								System.out.println(path_string);
								JTextArea path_out_area = new JTextArea();
								path_out_area.setText(path_string);
								JScrollPane scroll_path = new JScrollPane(path_out_area);
								processes.add(scroll_path);
							}
						}

					}
				}
			}
		}
	}
}

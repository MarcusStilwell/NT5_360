import javax.swing.*;
import java.util.*;

public class proficientpaths extends JApplet
 {

   private int APPLET_WIDTH = 650, APPLET_HEIGHT = 350;

   private JTabbedPane tPane;
   private StorePanel storePanel;
   private ArrayList pathList;
   private HelpPanel helpPanel;
   private AboutPanel aboutPanel;



   //The method init initializes the Applet with a Pane with two tabs
   public void init()
    {
     //list of paths to be used in every panel
     pathList = new ArrayList();

     storePanel = new StorePanel(pathList);
     
     aboutPanel = new AboutPanel();
     
     helpPanel = new HelpPanel();
     

     //create a tabbed pane with two tabs
     tPane = new JTabbedPane();
     tPane.addTab("Store Activities", storePanel);
     tPane.addTab("About", aboutPanel);
     tPane.addTab("Help", helpPanel);
 

     getContentPane().add(tPane);
     setSize (APPLET_WIDTH, APPLET_HEIGHT); //set Applet size
    }
}

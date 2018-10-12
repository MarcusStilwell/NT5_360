//to use NumberFormat class
import java.text.NumberFormat;
import java.util.ArrayList;

public class Activity
{
    private String activity;
    private double duration;
    private String[] pred;
  

    /************************************************************************
    Constructor method to initialize each variable.
    ************************************************************************/
    public Activity()
    {
        activity = "?";
        duration = 0.00;
        pred = new String[0];
         
    }

    /************************************************************************
    Accessor Methods
    ************************************************************************/
    
    public String getActivity()
    {
        return activity;
    }

    public double getDuration()
    {
        return duration;
    }

    public String[] getPred()
    {
        return pred;
    }

    public void setActivity(String activityName)
    {
        activity = activityName;
    }

    public void setDuration(double durationTime)
    {
        duration = durationTime;
    }

    public void setPred(String predecessors)
    {
        pred = predecessors.split(",");
    }

    public String toString()
    {
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        String result;
        String pred_text = "";
        for(int i =0; i < pred.length-1; i++) {
        	pred_text += pred[i];
        	pred_text += ", ";
        }
        pred_text += pred[pred.length-1];
        result = "\nActivity:\t\t" + activity +
                 "\nDuration:\t\t" + duration +
                 "\nPredecessors:\t\t" + pred_text + "\n\n";

        return result;
    }
}
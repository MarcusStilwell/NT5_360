//to use NumberFormat class
import java.text.NumberFormat;

public class Activity
{
    private String activity;
    private double duration;
    private String pred;
  

    /************************************************************************
    Constructor method to initialize each variable.
    ************************************************************************/
    public Activity()
    {
        activity = "?";
        duration = 0.00;
        pred = "?";
         
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

    public String getPred()
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
        pred = predecessors;
    }

    public String toString()
    {
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        String result;
        result = "\nActivity:\t" + activity +
                 "\nDuration:\t\t" + duration +
                 "\nPredecessors:\t\t" + pred + "\n\n";

        return result;
    }
}
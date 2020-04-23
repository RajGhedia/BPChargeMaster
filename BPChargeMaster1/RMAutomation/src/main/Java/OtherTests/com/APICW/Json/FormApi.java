package OtherTests.com.APICW.Json;

public class FormApi
{
    private GaEventData gaEventData;

    private String event;

    public GaEventData getGaEventData ()
    {
        return gaEventData;
    }

    public void setGaEventData (GaEventData gaEventData)
    {
        this.gaEventData = gaEventData;
    }

    public String getEvent ()
    {
        return event;
    }

    public void setEvent (String event)
    {
        this.event = event;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [gaEventData = "+gaEventData+", event = "+event+"]";
    }
}
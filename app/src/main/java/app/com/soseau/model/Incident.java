package app.com.soseau.model;

import java.io.Serializable;

/**
 * Created by jazz on 24/12/2016.
 */
public class Incident implements Serializable
{
    private String incidentdate;

    private String incidenttitle;

    private String incidentverified;

    private String locationid;

    private String incidentactive;

    private String locationlongitude;

    private String incidentdescription;

    private String incidentid;

    private String incidentmode;

    private String locationname;

    private String locationlatitude;

    public String getIncidentdate ()
    {
        return incidentdate;
    }

    public void setIncidentdate (String incidentdate)
    {
        this.incidentdate = incidentdate;
    }

    public String getIncidenttitle ()
    {
        return incidenttitle;
    }

    public void setIncidenttitle (String incidenttitle)
    {
        this.incidenttitle = incidenttitle;
    }

    public String getIncidentverified ()
    {
        return incidentverified;
    }

    public void setIncidentverified (String incidentverified)
    {
        this.incidentverified = incidentverified;
    }

    public String getLocationid ()
    {
        return locationid;
    }

    public void setLocationid (String locationid)
    {
        this.locationid = locationid;
    }

    public String getIncidentactive ()
    {
        return incidentactive;
    }

    public void setIncidentactive (String incidentactive)
    {
        this.incidentactive = incidentactive;
    }

    public String getLocationlongitude ()
    {
        return locationlongitude;
    }

    public void setLocationlongitude (String locationlongitude)
    {
        this.locationlongitude = locationlongitude;
    }

    public String getIncidentdescription ()
    {
        return incidentdescription;
    }

    public void setIncidentdescription (String incidentdescription)
    {
        this.incidentdescription = incidentdescription;
    }

    public String getIncidentid ()
    {
        return incidentid;
    }

    public void setIncidentid (String incidentid)
    {
        this.incidentid = incidentid;
    }

    public String getIncidentmode ()
    {
        return incidentmode;
    }

    public void setIncidentmode (String incidentmode)
    {
        this.incidentmode = incidentmode;
    }

    public String getLocationname ()
    {
        return locationname;
    }

    public void setLocationname (String locationname)
    {
        this.locationname = locationname;
    }

    public String getLocationlatitude ()
    {
        return locationlatitude;
    }

    public void setLocationlatitude (String locationlatitude)
    {
        this.locationlatitude = locationlatitude;
    }

}

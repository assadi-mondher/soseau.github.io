package app.com.soseau.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by jazz on 24/12/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Payload
{
    private Incidents[] incidents;

    private String domain;

    public Incidents[] getIncidents ()
    {
        return incidents;
    }

    public void setIncidents (Incidents[] incidents)
    {
        this.incidents = incidents;
    }

    public String getDomain ()
    {
        return domain;
    }

    public void setDomain (String domain)
    {
        this.domain = domain;
    }

}

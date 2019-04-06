package app.com.soseau.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import app.com.soseau.model.Media;

/**
 * Created by jazz on 24/12/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Incidents
{
    private Customfields customfields;

    private Categories[] categories;

    private Media[] media;

    private String[] comments;

    private Incident incident;

    public Customfields getCustomfields ()
    {
        return customfields;
    }

    public void setCustomfields (Customfields customfields)
    {
        this.customfields = customfields;
    }

    public Categories[] getCategories ()
    {
        return categories;
    }

    public void setCategories (Categories[] categories)
    {
        this.categories = categories;
    }

    public Media[] getMedia ()
    {
        return media;
    }

    public void setMedia (Media[] media)
    {
        this.media = media;
    }

    public String[] getComments ()
    {
        return comments;
    }

    public void setComments (String[] comments)
    {
        this.comments = comments;
    }

    public Incident getIncident ()
    {
        return incident;
    }

    public void setIncident (Incident incident)
    {
        this.incident = incident;
    }

}

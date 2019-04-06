package app.com.soseau.model;

import java.io.Serializable;

/**
 * Created by jazz on 24/12/2016.
 */
public class Media implements Serializable
{
    private String id;

    private String link_url;

    private String link;

    private String thumb_url;

    private String type;

    private String thumb;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getLink_url ()
    {
        return link_url;
    }

    public void setLink_url (String link_url)
    {
        this.link_url = link_url;
    }

    public String getLink ()
    {
        return link;
    }

    public void setLink (String link)
    {
        this.link = link;
    }

    public String getThumb_url ()
    {
        return thumb_url;
    }

    public void setThumb_url (String thumb_url)
    {
        this.thumb_url = thumb_url;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getThumb ()
    {
        return thumb;
    }

    public void setThumb (String thumb)
    {
        this.thumb = thumb;
    }
}

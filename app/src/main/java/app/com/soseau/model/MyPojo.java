package app.com.soseau.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import app.com.soseau.model.Payload;

/**
 * Created by jazz on 24/12/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MyPojo
{
    private Payload payload;

    public Payload getPayload ()
    {
        return payload;
    }

    public void setPayload (Payload payload)
    {
        this.payload = payload;
    }
}

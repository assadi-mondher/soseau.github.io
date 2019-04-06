package app.com.soseau.model;

/**
 * Created by jazz on 01/01/2017.
 */
public class CustomItem {

    private String field_name;
    private String field_response;

    public CustomItem(String field_name, String field_response) {
        this.field_name = field_name;
        this.field_response = field_response;
    }

    public String getField_name() {
        return field_name;
    }

    public String getField_response() {
        return field_response;
    }

    public void setField_name(String field_name) {
        this.field_name = field_name;
    }

    public void setField_response(String field_response) {
        this.field_response = field_response;
    }
}

package edu.harrisburgu.walmartlist;

import org.json.JSONException;
import org.json.JSONObject;

public class Store {
    protected String name;
    protected String address;
    protected String phoneNumber;
    protected String city;

    // Constructor that takes a JSONObject as a parameter
    public Store(JSONObject object) throws JSONException {
        this.name = object.getString("name");
        this.address = object.getString("street_address");
        this.phoneNumber = object.getString("phone_number_1");
        this.city = object.getString("city");
    }
    public Store() {
    }

    // Getter methods for each field
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCity() {
        return city;
    }
}

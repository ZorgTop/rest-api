package com.appsdeveloperblog.app.ws.mobileappws.ui.model;

import lombok.Data;

@Data
public class AddressRequestModel {

    private String city;
    private String country;
    private String postalCode;
    private String streetName;
    private String type;
}

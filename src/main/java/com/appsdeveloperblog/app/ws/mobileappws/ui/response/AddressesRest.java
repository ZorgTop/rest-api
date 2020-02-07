package com.appsdeveloperblog.app.ws.mobileappws.ui.response;

import lombok.Data;

@Data
public class AddressesRest {

    private String addressId;
    private String city;
    private String country;
    private String postalCode;
    private String streetName;
    private String type;
}

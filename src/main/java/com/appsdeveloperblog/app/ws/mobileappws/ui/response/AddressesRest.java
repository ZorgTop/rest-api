package com.appsdeveloperblog.app.ws.mobileappws.ui.response;

import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

@Data
public class AddressesRest extends ResourceSupport {

    private String addressId;
    private String city;
    private String country;
    private String postalCode;
    private String streetName;
    private String type;
}

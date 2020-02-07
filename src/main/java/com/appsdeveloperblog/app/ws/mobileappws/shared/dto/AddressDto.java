package com.appsdeveloperblog.app.ws.mobileappws.shared.dto;

import lombok.Data;


@Data
public class AddressDto {
    private Long id;
    private String addressId;
    private String city;
    private String country;
    private String postalCode;
    private String streetName;
    private String type;
    private UserDto userDetails;
}

package com.appsdeveloperblog.app.ws.mobileappws.ui.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDetailsRequestModel {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private List<AddressRequestModel> addresses;
}

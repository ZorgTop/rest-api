package com.appsdeveloperblog.app.ws.mobileappws.service;

import com.appsdeveloperblog.app.ws.mobileappws.shared.dto.AddressDto;

import java.util.List;

public interface AddressService {

    List<AddressDto> getAddresses(String userID);
}

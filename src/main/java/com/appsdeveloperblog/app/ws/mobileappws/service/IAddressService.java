package com.appsdeveloperblog.app.ws.mobileappws.service;

import com.appsdeveloperblog.app.ws.mobileappws.shared.dto.AddressDto;

import java.util.List;

public interface IAddressService {

    List<AddressDto> getAddresses(String userID);
}

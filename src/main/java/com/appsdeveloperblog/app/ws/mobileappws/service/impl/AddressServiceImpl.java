package com.appsdeveloperblog.app.ws.mobileappws.service.impl;

import com.appsdeveloperblog.app.ws.mobileappws.io.entity.AddressEntity;
import com.appsdeveloperblog.app.ws.mobileappws.io.entity.UserEntity;
import com.appsdeveloperblog.app.ws.mobileappws.io.repository.AddressRepository;
import com.appsdeveloperblog.app.ws.mobileappws.io.repository.UserRepository;
import com.appsdeveloperblog.app.ws.mobileappws.service.IAddressService;
import com.appsdeveloperblog.app.ws.mobileappws.shared.dto.AddressDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements IAddressService {

    private final AddressRepository addressRepository;

    private final UserRepository userRepository;

    @Override
    public List<AddressDto> getAddresses(String userID) {
        List<AddressDto> addresses = new ArrayList<>();
        UserEntity userEntity = userRepository.findByUserId(userID);
        if (userEntity == null) return addresses;
        Iterable<AddressEntity> entities = addressRepository.findAllByUserDetails(userEntity);
        ModelMapper mapper = new ModelMapper();
        for (AddressEntity entity : entities) {
            AddressDto dto = mapper.map(entity, AddressDto.class);
            addresses.add(dto);
        }

        return addresses;
    }

    @Override
    public AddressDto getAddress(String addressId) {
        AddressDto dto = null;
        AddressEntity entity = addressRepository.findByAddressId(addressId);
        if (entity != null) {
            dto = new ModelMapper().map(entity, AddressDto.class);
        }

        return dto;
    }
}

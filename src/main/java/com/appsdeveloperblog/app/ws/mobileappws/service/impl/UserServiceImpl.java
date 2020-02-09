package com.appsdeveloperblog.app.ws.mobileappws.service.impl;

import com.appsdeveloperblog.app.ws.mobileappws.Utils;
import com.appsdeveloperblog.app.ws.mobileappws.exceptions.UserServiceException;
import com.appsdeveloperblog.app.ws.mobileappws.io.entity.UserEntity;
import com.appsdeveloperblog.app.ws.mobileappws.io.repository.UserRepository;
import com.appsdeveloperblog.app.ws.mobileappws.service.IUserService;
import com.appsdeveloperblog.app.ws.mobileappws.shared.dto.AddressDto;
import com.appsdeveloperblog.app.ws.mobileappws.shared.dto.UserDto;
import com.appsdeveloperblog.app.ws.mobileappws.ui.response.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    private final Utils utils;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto creatUser(UserDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) throw new RuntimeException("Record already exist");
        for (int i = 0; i < dto.getAddresses().size(); i++) {
            AddressDto address = dto.getAddresses().get(i);
            address.setUserDetails(dto);
            address.setAddressId(utils.generateAddressId(30));
            dto.getAddresses().set(i, address);
        }
        ModelMapper modelMapper = new ModelMapper();
        UserEntity userEntity = modelMapper.map(dto, UserEntity.class);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        String publishUserId = utils.generateUserId(30);
        userEntity.setUserId(publishUserId);
        userEntity.setEmailVerificationToken(utils.generateEmailVerificationToken(publishUserId));
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        UserEntity storedDetails = userRepository.save(userEntity);
        UserDto returnValue = modelMapper.map(storedDetails, UserDto.class);
        return returnValue;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        UserEntity entity = userRepository.findByEmail(email);
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        UserDto retunvalue = new UserDto();
        UserEntity entity = userRepository.findByUserId(userId);
        if (entity == null) throw new UsernameNotFoundException(userId);
        BeanUtils.copyProperties(entity, retunvalue);
        return retunvalue;
    }

    @Override
    public UserDto updateUser(String userId, UserDto dto) {
        UserDto returnValue = new UserDto();
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        userEntity.setFirstName(dto.getFirstName());
        userEntity.setLastName(dto.getLastName());
        UserEntity updateEntity = userRepository.save(userEntity);
        BeanUtils.copyProperties(updateEntity, returnValue);
        return returnValue;
    }

    @Override
    public void deleteUserByUserId(String id) {
        UserEntity userEntity = userRepository.findByUserId(id);
        if (userEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        userRepository.delete(userEntity);
    }

    @Override
    public List<UserDto> getUsers(int page, int limit) {
        List<UserDto> returnValue = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, limit);
        List<UserEntity> usersEntity = userRepository.findAll(pageable).getContent();
        for (UserEntity entity : usersEntity) {
            UserDto dto = new UserDto();
            BeanUtils.copyProperties(entity, dto);
            returnValue.add(dto);
        }
        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }
}

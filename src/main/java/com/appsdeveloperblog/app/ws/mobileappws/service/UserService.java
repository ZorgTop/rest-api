package com.appsdeveloperblog.app.ws.mobileappws.service;

import com.appsdeveloperblog.app.ws.mobileappws.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserDto creatUser(UserDto dto);

    UserDto getUserByEmail(String email);

    UserDto getUserByUserId(String id);

    UserDto updateUser(String userId,UserDto dto);

    void deleteUserByUserId(String id);

    List<UserDto> getUsers(int page, int limit);
}

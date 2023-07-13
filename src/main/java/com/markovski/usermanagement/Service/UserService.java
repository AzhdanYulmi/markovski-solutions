package com.markovski.usermanagement.Service;

import com.markovski.usermanagement.DTO.AppUserRequest;
import com.markovski.usermanagement.DTO.AppUserResponse;
import com.markovski.usermanagement.Entity.AppUser;

import java.util.List;

public interface UserService {
    AppUser findUserById(int id);
    AppUserResponse getUserById(int id);

    List<AppUserResponse> getAllUsers();

    AppUserResponse addUser(AppUserRequest request);

    AppUserResponse updateUser(int id, AppUserRequest request);

    AppUserResponse deleteUser(int id);
}

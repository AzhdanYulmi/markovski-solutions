package com.markovski.usermanagement.Mapper;

import com.markovski.usermanagement.DTO.AppUserRequest;
import com.markovski.usermanagement.DTO.AppUserResponse;
import com.markovski.usermanagement.Entity.AppUser;

import java.util.List;

public interface UserMapper {
    AppUserResponse mapUserToResponse(AppUser user);

    AppUser mapRequestToUser(AppUserRequest request);

    List<AppUserResponse> mapListOfUsersToUserResponse(List<AppUser> users);
}

package Mapper;

import DTO.AppUserRequest;
import DTO.AppUserResponse;
import Entity.AppUser;

import java.util.List;

public interface UserMapper {
    AppUserResponse mapUserToResponse(AppUser user);

    AppUser mapRequestToUser(AppUserRequest request);

    List<AppUserResponse> mapListOfUsersToUserResponse(List<AppUser> users);
}

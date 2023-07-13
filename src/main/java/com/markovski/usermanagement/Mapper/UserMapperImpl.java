package com.markovski.usermanagement.Mapper;

import com.markovski.usermanagement.DTO.AppUserRequest;
import com.markovski.usermanagement.DTO.AppUserResponse;
import com.markovski.usermanagement.Entity.AppUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public AppUserResponse mapUserToResponse(AppUser user) {
        AppUserResponse response = new AppUserResponse();
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setDateOfBirth(user.getDateOfBirth());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setEmailAddress(user.getEmailAddress());

        return response;
    }

    @Override
    public AppUser mapRequestToUser(AppUserRequest request) {
        AppUser user = new AppUser();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setEmailAddress(request.getEmailAddress());
        return user;
    }

    @Override
    public List<AppUserResponse> mapListOfUsersToUserResponse(List<AppUser> users) {
        List<AppUserResponse> responseList = new ArrayList<>();

        for (AppUser user : users) {
            AppUserResponse response = mapUserToResponse(user);
            responseList.add(response);
        }

        return responseList;
    }
}

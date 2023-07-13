package com.markovski.usermanagement.Service;

import com.markovski.usermanagement.DTO.AppUserRequest;
import com.markovski.usermanagement.DTO.AppUserResponse;
import com.markovski.usermanagement.Entity.AppUser;
import com.markovski.usermanagement.Mapper.UserMapper;
import com.markovski.usermanagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.markovski.usermanagement.Exception.UserNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public AppUser findUserById(int id) {
        Optional<AppUser> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UserNotFoundException("User not found for ID: " + id);
        }
    }


    @Override
    public AppUserResponse getUserById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(String.format("Invalid ID Input" + id));
        }
        AppUser user = findUserById(id);
        return userMapper.mapUserToResponse(user);
    }

    @Override
    public List<AppUserResponse> getAllUsers() {
        List<AppUser> users = userRepository.findAll();
        return userMapper.mapListOfUsersToUserResponse(users);
    }

    @Override
    public AppUserResponse addUser(AppUserRequest request) {
        AppUser savedUser = userMapper.mapRequestToUser(request);
        userRepository.save(savedUser);
        return userMapper.mapUserToResponse(savedUser);
    }

    @Override
    public AppUserResponse updateUser(int id, AppUserRequest request) {
        AppUser userToUpdate = findUserById(id);
        AppUser updatedUser = userMapper.mapRequestToUser(request);
        updatedUser.setId(id);
        userRepository.save(updatedUser);
        return userMapper.mapUserToResponse(userToUpdate);
    }

    @Override
    public AppUserResponse deleteUser(int id) {
        AppUser userToDelete = findUserById(id);
        userRepository.deleteById(id);
        return userMapper.mapUserToResponse(userToDelete);
    }
}

package Service;

import DTO.AppUserRequest;
import DTO.AppUserResponse;
import Entity.AppUser;
import Mapper.UserMapper;
import Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Exception.UserNotFoundException;

import java.util.List;

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
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format("User hasn't been found" + id)));
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
package Service;

import DTO.AppUserRequest;
import DTO.AppUserResponse;
import Entity.AppUser;

import java.util.List;

public interface UserService {
    AppUser findUserById(int id);
    AppUserResponse getUserById(int id);

    List<AppUserResponse> getAllUsers();

    AppUserResponse addUser(AppUserRequest request);

    AppUserResponse updateUser(int id, AppUserRequest request);

    AppUserResponse deleteUser(int id);
}

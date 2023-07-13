package Controller;

import DTO.AppUserRequest;
import DTO.AppUserResponse;
import Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Exception.UserNotFoundException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<AppUserResponse>> getAllUsers() {
        List<AppUserResponse> userResponses = userService.getAllUsers();
        return ResponseEntity.ok(userResponses);
    }


    @GetMapping("/{id}")
    public ResponseEntity<AppUserResponse> getUserById(@PathVariable int id) {
        try {
            AppUserResponse userResponse = userService.getUserById(id);
            return ResponseEntity.ok(userResponse);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<AppUserResponse> addUser(@RequestBody AppUserRequest request) {
        AppUserResponse addedUser = userService.addUser(request);
        return ResponseEntity.created(URI.create("/users/" + addedUser.getId())).body(addedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUserResponse> updateUser(@PathVariable int id, @RequestBody AppUserRequest request) {
        try {
            AppUserResponse updatedUser = userService.updateUser(id, request);
            return ResponseEntity.ok(updatedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppUserResponse> deleteUser(@PathVariable int id) {
        try {
            AppUserResponse deletedUser = userService.deleteUser(id);
            return ResponseEntity.ok(deletedUser);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

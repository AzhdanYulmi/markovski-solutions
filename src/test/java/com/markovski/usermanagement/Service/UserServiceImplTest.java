package com.markovski.usermanagement.Service;

import com.markovski.usermanagement.DTO.AppUserRequest;
import com.markovski.usermanagement.DTO.AppUserResponse;
import com.markovski.usermanagement.Entity.AppUser;
import com.markovski.usermanagement.Mapper.UserMapper;
import com.markovski.usermanagement.Repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    int userId;

    AppUser user;

    AppUserResponse userResponse;

    AppUserRequest userRequest;

    List<AppUser> users;

    List<AppUserResponse> userResponses;
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setUp() {
        userId = 1;
        user = new AppUser(userId, "John", "Doe", LocalDate.of(1996, 6, 4), "+359878655733", "jdoe@email.com");
        userResponse = userMapper.mapUserToResponse(user);
        userRequest = new AppUserRequest("Rich","Richy",LocalDate.of(1999,9,4),"+359888555397","richns@gmail.com");
        users = Collections.singletonList(
                user
        );
        userResponses = Collections.singletonList(userResponse);
    }

    @Test
    public void findUserById_ExistingUser_ReturnsUser() {

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        AppUser actualUser = userService.findUserById(userId);

        assertEquals(user, actualUser);
    }

    @Test
    public void getUserById_ValidId_ReturnsUserResponse() {

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userMapper.mapUserToResponse(user)).thenReturn(userResponse);

        AppUserResponse actualUserResponse = userService.getUserById(userId);

        assertEquals(userResponse, actualUserResponse);
    }

    @Test
    public void getAllUsers_ReturnsListOfUserResponses() {
        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.mapListOfUsersToUserResponse(users)).thenReturn(userResponses);

        List<AppUserResponse> actualUserResponses = userService.getAllUsers();

        assertEquals(userResponses, actualUserResponses);
    }

    @Test
    public void addUser_ValidRequest_ReturnsUserResponse() {
        AppUser savedUser = new AppUser(1, "Rich","Richy",LocalDate.of(1999,9,4),"+359888555397","richns@gmail.com");
        AppUserResponse expectedUserResponse = new AppUserResponse(1, "Rich","Richy",LocalDate.of(1999,9,4),"+359888555397","richns@gmail.com");

        when(userMapper.mapRequestToUser(userRequest)).thenReturn(savedUser);
        when(userRepository.save(savedUser)).thenReturn(savedUser);
        when(userMapper.mapUserToResponse(savedUser)).thenReturn(expectedUserResponse);

        AppUserResponse actualUserResponse = userService.addUser(userRequest);

        assertEquals(expectedUserResponse, actualUserResponse);
        verify(userRepository).save(savedUser);
    }

    @Test
    public void updateUser_ValidIdAndRequest_ReturnsUserResponse() {
        AppUser updatedUser = new AppUser(userId, "Rich", "Richy", LocalDate.of(1999, 9, 4), "+359888555397", "richns@gmail.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userMapper.mapRequestToUser(userRequest)).thenReturn(updatedUser);
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);

        AppUserResponse actualUserResponse = userService.updateUser(userId, userRequest);

        assertEquals(userResponse, actualUserResponse);
        verify(userRepository).save(updatedUser);
    }


    @Test
    public void deleteUser_ValidId_ReturnsUserResponse() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        AppUserResponse actualUserResponse = userService.deleteUser(userId);

        assertEquals(userResponse, actualUserResponse);
        verify(userRepository).deleteById(userId);
    }
}